package br.com.vocealcanca.bean;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Cliente {
	private int idCliente;
	private Calendar dataNascimento;
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private String senha;
	private String cpf;
	
	
	// Cliente com cpf
	public Cliente(int idCliente, Calendar dataNascimento, String nome, String telefone, String email, String senha, String endereco, String cpf) throws IllegalArgumentException {
		this.dataNascimento = dataNascimento;
		this.nome = nome;
		this.telefone = telefone;
		this.idCliente = idCliente;
		
		if (!validarEmail(email)) {
            throw new IllegalArgumentException("Email invalido");
        }
		
		this.email = email;
		this.endereco = endereco;
		setSenha(senha);
		this.cpf = cpf;
	}
	
	public Cliente(Calendar dataNascimento, String nome, String telefone, String email, String senha, String endereco, String cpf) throws IllegalArgumentException {
		this.dataNascimento = dataNascimento;
		this.nome = nome;
		this.telefone = telefone;
		
		if (!validarEmail(email)) {
            throw new IllegalArgumentException("Email invalido");
        }
		
		this.email = email;
		this.endereco = endereco;
		setSenha(senha);
		this.cpf = cpf;
	}
	
	public Cliente(String cpf, String senha) {
		this.cpf = cpf;
		setSenha(senha);
	}
	
	private static boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public String getDataNascimentoString() {
		return String.format("%1$td/%1$tm/%1$tY", dataNascimento);
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
