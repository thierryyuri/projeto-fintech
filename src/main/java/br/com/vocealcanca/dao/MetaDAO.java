package br.com.vocealcanca.dao;

import java.util.List;

import br.com.vocealcanca.bean.Meta;
import br.com.vocealcanca.exception.DBException;

public interface MetaDAO {

	void cadastrar(Meta meta) throws DBException;
	
	void atualizar(Meta meta) throws DBException;
	
	void remover(int codigo) throws DBException;
	
	Meta buscar(int id);
	
	List<Meta> listar(int id);
}
