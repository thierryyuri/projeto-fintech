package br.com.vocealcanca.bean;

import java.util.Calendar;

public class Gasto extends Receita {
	private int idGasto;
	
	private String tipoGasto;
	private String fonteGasto;
	private String periodicidade;
	
	
	public Gasto(int idReceita, float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int clienteIdReceita, 
			int idGasto, String fonte,  String tipoGasto, String periodicidade) {
		super(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, clienteIdReceita);
		
		this.idGasto = idGasto;
		this.fonteGasto = fonte;
		this.tipoGasto = tipoGasto;
		this.periodicidade = periodicidade;
	}
	
	public Gasto(float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int clienteIdReceita, 
			String fonte,  String tipoGasto, String periodicidade) {
		super(valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, clienteIdReceita);
		
		this.fonteGasto = fonte;
		this.tipoGasto = tipoGasto;
		this.periodicidade = periodicidade;
	}

	public int getIdGasto() {
		return idGasto;
	}

	public void setIdGasto(int idGasto) {
		this.idGasto = idGasto;
	}

	public String getTipoGasto() {
		return tipoGasto;
	}

	public void setTipoGasto(String tipoGasto) {
		this.tipoGasto = tipoGasto;
	}

	public String getFonteGasto() {
		return fonteGasto;
	}

	public void setFonteGasto(String fonteGasto) {
		this.fonteGasto = fonteGasto;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}
	
	
}
