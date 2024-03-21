package br.com.vocealcanca.dao;

import java.util.List;

import br.com.vocealcanca.bean.Investimento;
import br.com.vocealcanca.exception.DBException;

public interface InvestimentoDAO {

	void cadastrar(Investimento investimento) throws DBException;
	
	void atualizar(Investimento investimento) throws DBException;
	
	void remover(int codigo) throws DBException;
	
	Investimento buscar(int id);
	
	List<Investimento> listar(int id);
}
