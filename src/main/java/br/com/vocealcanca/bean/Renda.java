package br.com.vocealcanca.bean;

import java.util.Calendar;

public class Renda extends Receita {
	private int idRenda;
	
	private String fonteRenda;
	private String tipoRenda;
	private String periodicidade;
	
	public Renda(int idReceita, float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int idCliente, int idRenda, String fonte, String tipoRenda, 
			String periodicidade) {
		
		super(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente);
		
		this.idRenda = idRenda;
		this.fonteRenda = fonte;
		this.tipoRenda = tipoRenda;
		this.periodicidade = periodicidade;
	}
	
	public Renda(float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int idCliente, String fonte, String tipoRenda, 
			String periodicidade) {
		
		super(valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente);
		
		this.fonteRenda = fonte;
		this.tipoRenda = tipoRenda;
		this.periodicidade = periodicidade;
	}

	public int getIdRenda() {
		return idRenda;
	}

	public void setIdRenda(int idRenda) {
		this.idRenda = idRenda;
	}

	public String getFonteRenda() {
		return fonteRenda;
	}

	public void setFonteRenda(String fonteRenda) {
		this.fonteRenda = fonteRenda;
	}

	public String getTipoRenda() {
		return tipoRenda;
	}

	public void setTipoRenda(String tipoRenda) {
		this.tipoRenda = tipoRenda;
	}

	public String getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(String periodicidade) {
		this.periodicidade = periodicidade;
	}
	
	
}
