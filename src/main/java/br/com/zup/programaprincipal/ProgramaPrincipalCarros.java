package br.com.zup.programaprincipal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.zup.carroexeption.CarroException;
import br.com.zup.carrosdao.CarroDAO;
import br.com.zup.carrospojo.Carro;

public class ProgramaPrincipalCarros {

	public static void menuInicial() {

		System.out.println("\n\tBem Vindo Ao Sistema De Gerenciamento De Carros Da Concessionária!");

		System.out.println("\n\t\tEscolha Uma Das Opções Abaixo:\n");

		System.out.println("\n\t(1) - Para Inserir Um Novo Carro;\n" + "\n\t(2) - Para Remover Um Carro;\n"
				+ "\n\t(3) - Para Buscar Um Carro Pela Placa;\n" + "\n\t(4) - Para Buscar Carros Por Fabricante;\n"
				+ "\n\t(5) - Para Listar Carros Do Banco De Dados;\n" + "\n\t(0) - Para Sair;\n");
	}

	public static void adicionaCarroNoBD(Scanner teclado, CarroDAO carroDAO, String placa) {

		System.out.println("\n\tVou Precisar Das Seguintes Informações:\n");
		System.out.print("\n\tPlaca: ");
		placa = teclado.next();

		System.out.print("\n\tCor: ");
		String cor = teclado.next();

		System.out.print("\n\tNome: ");
		String nome = teclado.next();

		System.out.print("\n\tFabricante: ");
		String fabricante = teclado.next();

		teclado.next();

		System.out.print("\n\tVelocidade Máxima: K/M ");
		float velMax = teclado.nextFloat();

		System.out.print("\n\tPeso: K/G ");
		Float peso = teclado.nextFloat();

		Carro carro = new Carro(placa, cor, nome, fabricante, velMax, peso);
		carroDAO.adicionaCarroNoBD(carro);
	}

	public static void removeCarroPelaPlaca(Scanner teclado, CarroDAO carroDAO, String placa) {

		System.out.println("\n\tPara Remover Um Carro Informe A Placa Do Mesmo!\n");
		System.out.print("\tPlaca: ");
		placa = teclado.next();

		try {
			carroDAO.removeCarroPelaPlaca(placa);
			System.out.println("\n\tCarro Removido Com Sucesso!\n");

		} catch (CarroException e) {
			System.err.println(e.getMensagem());

		}
	}

	public static void consultaCarroPelaPlaca(Scanner teclado, CarroDAO carroDAO, String placa) {

		System.out.println("\n\tPara Buscar Um Carro Informe A Placa Do Mesmo!\n");
		System.out.print("\tPlaca: ");
		placa = teclado.next();

		try {
			Carro carro = carroDAO.culsultaCarroPelaPlaca(placa);
			System.out.println(carro);

		} catch (CarroException e) {
			System.err.println(e.getMensagem());
		}
	}

	public static void buscarCarrosPorFabricante(Scanner teclado, CarroDAO carroDAO) {

		List<Carro> carrosDB = new ArrayList<Carro>();

		System.out.println("\n\tDigite Abaixo O Nome De Algum Fabricante Para Buscar Os Carros Disponíveis!\n");
		System.out.print("\tFabricante: ");
		String fabricante = teclado.next();

		try {
			carrosDB = carroDAO.buscarCarrosPorFabricante(fabricante);
			for (Carro carro : carrosDB) {
				System.out.println("\n\t" + carro);
			}

		} catch (CarroException e) {
			System.err.println(e.getMensagem());
		}
	}

	public static void listaCarrosDoBD(CarroDAO carroDAO) {

		List<Carro> carrosDB = new ArrayList<Carro>();

		try {
			carrosDB = carroDAO.listaCarrosDoBD();

			for (Carro carro : carrosDB) {
				System.out.println("\n\t" + carro);
			}

		} catch (CarroException e) {
			System.err.println(e.getMensagem());
		}

	}

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		CarroDAO carroDAO = new CarroDAO();

		String placa = "";
		int opcaoEscolha;

		do {
			menuInicial();

			System.out.print("\tOpção: ");
			opcaoEscolha = teclado.nextInt();

			switch (opcaoEscolha) {

			case 1:
				adicionaCarroNoBD(teclado, carroDAO, placa);
				break;

			case 2:
				removeCarroPelaPlaca(teclado, carroDAO, placa);
				break;

			case 3:
				consultaCarroPelaPlaca(teclado, carroDAO, placa);
				break;

			case 4:
				buscarCarrosPorFabricante(teclado, carroDAO);

				break;

			case 5:
				listaCarrosDoBD(carroDAO);
				break;

			case 0:
				System.out.println("\n\tAté a Próxima!\n");
				break;

			default:
				System.out.println("\n\tOpção Inválida, Tente Novamente!\n");
				break;
			}
		} while (opcaoEscolha != 0);
	}
}