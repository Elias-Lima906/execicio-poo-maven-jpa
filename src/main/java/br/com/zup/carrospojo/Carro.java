package br.com.zup.carrospojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carro {

	@Id
	private String placa;

	@Column(nullable = false)
	private String cor;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String fabricante;

	@Column(name = "vel_max")
	private float velMax;

	private float peso;

	public Carro(String placa, String cor, String nome, String fabricante, float velMax, float peso) {
		super();
		this.placa = placa;
		this.cor = cor;
		this.nome = nome;
		this.fabricante = fabricante;
		this.velMax = velMax;
		this.peso = peso;
	}

	public Carro() {

	}

	@Override
	public String toString() {
		return "\n\t---------------------" + "\n\tPlaca = " + placa + "\n\tCor = " + cor + "\n\tNome = " + nome
				+ "\n\tFabricante = " + fabricante + "\n\tVelocidade Máxima = " + velMax + "\n\tPeso = " + peso
				+ "\n\t---------------------";
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public float getVelMax() {
		return velMax;
	}

	public void setVelMax(float velMax) {
		this.velMax = velMax;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

}
