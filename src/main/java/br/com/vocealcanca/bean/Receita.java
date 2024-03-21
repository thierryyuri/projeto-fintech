package br.com.vocealcanca.bean;

import java.util.Calendar;

abstract class Receita {
	private int idReceita;
	private int clienteIdReceita;
	
	protected float valor;
	protected float valorTotal;
	
	protected String nome;
	protected String tipoReceita;
	protected String descricao;
	
	private Calendar dataInicio;
	private Calendar dataFinal;
	
	public Receita(int idReceita, float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int clienteIdReceita) {
		
		this.idReceita = idReceita;
		this.valor = valor;
		this.valorTotal = valorTotal;
		this.nome = nome;
		this.tipoReceita = tipoReceita;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.clienteIdReceita = clienteIdReceita;
	}
	
	public Receita(float valor, float valorTotal, String nome, String tipoReceita, String descricao,
			Calendar dataInicio, Calendar dataFinal, int clienteIdReceita) {
		this.valor = valor;
		this.valorTotal = valorTotal;
		this.nome = nome;
		this.tipoReceita = tipoReceita;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.clienteIdReceita = clienteIdReceita;
	}
	
	public Receita() {}

	public int getIdReceita() {
		return idReceita;
	}

	public void setIdReceita(int idReceita) {
		this.idReceita = idReceita;
	}

	public int getClienteIdReceita() {
		return clienteIdReceita;
	}

	public void setClienteIdReceita(int clienteIdReceita) {
		this.clienteIdReceita = clienteIdReceita;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(String tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public String getDataInicioString() {
		return String.format("%1$td/%1$tm/%1$tY", dataInicio);
	}
	
	public String getDataFinalString() {
		return String.format("%1$td/%1$tm/%1$tY", dataFinal);
	}

}
