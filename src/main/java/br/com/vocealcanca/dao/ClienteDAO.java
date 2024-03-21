package br.com.vocealcanca.dao;

import java.util.List;

import br.com.vocealcanca.bean.Cliente;
import br.com.vocealcanca.exception.DBException;

public interface ClienteDAO {
	
	void cadastrar(Cliente cliente) throws DBException;
	
	void atualizar(Cliente cliente) throws DBException;
	
	void remover(int codigo) throws DBException;
	
	Cliente buscar(int id);
	
	Cliente buscarPorCpf(String cpf);
	
	List<Cliente> listar();
	
	boolean validarUsuario(Cliente cliente);
}
