package br.com.zup.carrosdao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.zup.carroexeption.CarroException;
import br.com.zup.carrospojo.Carro;

public class CarroDAO {

	EntityManager manager;

	public CarroDAO() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("carros");
		this.manager = managerFactory.createEntityManager();
	}

	public void adicionaCarroNoBD(Carro carro) {

		manager.getTransaction().begin();
		manager.persist(carro);
		manager.getTransaction().commit();
	}

	public Carro culsultaCarroPelaPlaca(String placa) throws CarroException {
		Carro carroConsultado = manager.find(Carro.class, placa);

		if (carroConsultado != null) {
			return carroConsultado;
		} else {
			throw new CarroException("\n\tCARRO NÃO ENCONTRADO PELA PLACA!\n");
		}
	}

	public void removeCarroPelaPlaca(String placa) throws CarroException {

		Carro carroASerRemovido = manager.find(Carro.class, placa);

		if (carroASerRemovido != null) {
			manager.getTransaction().begin();
			manager.remove(carroASerRemovido);
			manager.getTransaction().commit();
			return;
		} else {
			throw new CarroException("\n\tCARRO NÃO ENCONTRADO PELA PLACA!\n");
		}

	}

	public List<Carro> listaCarrosDoBD() throws CarroException {

		Query query = manager.createQuery("select c from Carro as c");

		List<Carro> carros = query.getResultList();

		if (carros != null) {
			return carros;
		} else {
			throw new CarroException("\n\tNÃO HÁ CARROS NO BANCO DE DADOS!\n");
		}
	}

	public List<Carro> buscarCarrosPorFabricante(String fabricante) throws CarroException {

		Query query = manager.createQuery("select c from Carro as c where c.fabricante = :fabricante");

		query.setParameter("fabricante", fabricante);

		List<Carro> carrosDoMesmoFabricante = query.getResultList();

		if (carrosDoMesmoFabricante != null) {
			return carrosDoMesmoFabricante;
		} else {
			throw new CarroException("\n\tNÃO HÁ CARROS DESTE FABRICANTE NO BANCO DE DADOS!\n");
		}
	}
}