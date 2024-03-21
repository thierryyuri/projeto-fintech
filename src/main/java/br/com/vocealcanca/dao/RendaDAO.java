package br.com.vocealcanca.dao;

import java.util.List;

import br.com.vocealcanca.bean.Renda;
import br.com.vocealcanca.exception.DBException;

public interface RendaDAO {
	
	void cadastrar(Renda renda) throws DBException;
	
	void atualizar(Renda renda) throws DBException;
	
	void remover(int codigo) throws DBException;
	
	Renda buscar(int id);
	
	List<Renda> listar(int id);
}
