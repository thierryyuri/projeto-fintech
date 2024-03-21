package br.com.vocealcanca.bean;

public class Meta {

	private int idMeta;
	private int idCliente;
	
	private float valorMeta;
	
	private String tipoMeta;
	private String descricao;
	private String nome;
	
	
	public Meta(int idCliente, int idMeta, String tipoMeta, String descricao, float valorMeta, String nome) {
		this.nome = nome;
		this.idMeta = idMeta;
		this.tipoMeta = tipoMeta;
		this.descricao = descricao;
		this.valorMeta = valorMeta;
		this.idCliente = idCliente;
	}
	
	public Meta(int idCliente, String tipoMeta, String descricao, float valorMeta, String nome) {
		this.nome = nome;
		this.tipoMeta = tipoMeta;
		this.descricao = descricao;
		this.valorMeta = valorMeta;
		this.idCliente = idCliente;
	}
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdMeta() {
		return idMeta;
	}

	public void setIdMeta(int idMeta) {
		this.idMeta = idMeta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public float getValorMeta() {
		return valorMeta;
	}

	public void setValorMeta(float valorMeta) {
		this.valorMeta = valorMeta;
	}

	public String getTipoMeta() {
		return tipoMeta;
	}

	public void setTipoMeta(String tipoMeta) {
		this.tipoMeta = tipoMeta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
