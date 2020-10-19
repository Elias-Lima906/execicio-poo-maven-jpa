package br.com.zup.carroexeption;

public class CarroException extends Exception {

	private static final long serialVersionUID = 1L;

	private String mensagem;

	public CarroException(String mensagem) {

		this.mensagem = mensagem;
	}

	public String getMensagem() {

		return this.mensagem;
	}

}
