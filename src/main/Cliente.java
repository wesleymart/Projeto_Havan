package main;

import java.util.Date;

public class Cliente {

	private String nome;
	private String moedaOrigem;
	private String moedaDestino;
	private Double valorOriginal = 0.00;
	private Double valorConvertido = 0.00;
	private Date data;

	public Cliente(String nome, String moedaOrigem, String moedaDestino, double valorOriginal, double valorConvertido, Date data) {
		this.nome = nome;
		this.moedaOrigem = moedaOrigem;
		this.moedaDestino = moedaDestino;
		this.valorOriginal = valorOriginal;
		this.valorConvertido = valorConvertido;
		this.data = data;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMoedaOrigem() {
		return moedaOrigem;
	}

	public void setMoedaOrigem(String moedaOrigem) {
		this.moedaOrigem = moedaOrigem;
	}

	public String getMoedaDestino() {
		return moedaDestino;
	}

	public void setMoedaDestino(String moedaDestino) {
		this.moedaDestino = moedaDestino;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public Double getValorConvertido() {
		return valorConvertido;
	}

	public void setValorConvertido(Double valorConvertido) {
		this.valorConvertido = valorConvertido;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", moedaOrigem=" + moedaOrigem + ", moedaDestino=" + moedaDestino
				+ ", valorOriginal=" + valorOriginal + ", valorConvertido=" + valorConvertido + "]";
	}
	
	

}
