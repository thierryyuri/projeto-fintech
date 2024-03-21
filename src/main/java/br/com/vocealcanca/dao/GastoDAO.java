package br.com.vocealcanca.dao;

import java.util.List;

import br.com.vocealcanca.bean.Gasto;
import br.com.vocealcanca.exception.DBException;

public interface GastoDAO {
	
	void cadastrar(Gasto gasto) throws DBException;
	
	void atualizar(Gasto gasto) throws DBException;
	
	void remover(int codigo) throws DBException;
	
	Gasto buscar(int id);
	
	List<Gasto> listar(int id);

}
