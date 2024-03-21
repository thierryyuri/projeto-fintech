package br.com.vocealcanca.bean;

import java.util.Calendar;

public class Investimento extends Receita {
	private int idInvestimento;
	private int numAportesMensais;
	
	private float valorAporteMensal;
	
	private String tipoInvestimento;
	
	public Investimento(int idReceita, float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int clienteIdReceita,
			int idInvestimento, float valorAporteMensal,  String tipoInvestimento, int numAportesMensais) {
		super(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, clienteIdReceita);

		this.idInvestimento = idInvestimento;
		this.tipoInvestimento = tipoInvestimento;
		this.valorAporteMensal = valorAporteMensal;
		this.numAportesMensais = numAportesMensais;
	}
	
	public Investimento(float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int clienteIdReceita,
			float valorAporteMensal,  String tipoInvestimento, int numAportesMensais) {
		super(valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, clienteIdReceita);

		this.tipoInvestimento = tipoInvestimento;
		this.valorAporteMensal = valorAporteMensal;
		this.numAportesMensais = numAportesMensais;
	}
	

	public int getIdInvestimento() {
		return idInvestimento;
	}

	public void setIdInvestimento(int idInvestimento) {
		this.idInvestimento = idInvestimento;
	}
	
	public int getNumAportesMensais() {
		return numAportesMensais;
	}

	public void setNumAportesMensais(int numAportesMensais) {
		this.numAportesMensais = numAportesMensais;
	}

	public float getValorAporteMensal() {
		return valorAporteMensal;
	}

	public void setValorAporteMensal(float valorAporteMensal) {
		this.valorAporteMensal = valorAporteMensal;
	}

	public String getTipoInvestimento() {
		return tipoInvestimento;
	}

	public void setTipoInvestimento(String tipoInvestimento) {
		this.tipoInvestimento = tipoInvestimento;
	}
	
	

}
