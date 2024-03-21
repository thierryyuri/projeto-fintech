package br.com.vocealcanca.factory;

import br.com.vocealcanca.dao.ClienteDAO;
import br.com.vocealcanca.dao.GastoDAO;
import br.com.vocealcanca.dao.InvestimentoDAO;
import br.com.vocealcanca.dao.MetaDAO;
import br.com.vocealcanca.dao.RendaDAO;
import br.com.vocealcanca.dao.impl.OracleClienteDAO;
import br.com.vocealcanca.dao.impl.OracleGastoDAO;
import br.com.vocealcanca.dao.impl.OracleInvestimentoDAO;
import br.com.vocealcanca.dao.impl.OracleMetaDAO;
import br.com.vocealcanca.dao.impl.OracleRendaDAO;

public class DAOFactory {
	public static ClienteDAO getClienteDAO() {
		return new OracleClienteDAO();
	}
	
	public static GastoDAO getGastoDAO() {
		return new OracleGastoDAO();
	}
	
	public static RendaDAO getRendaDAO() {
		return new OracleRendaDAO();
	}
	
	public static InvestimentoDAO getInvestimentoDAO() {
		return new OracleInvestimentoDAO();
	}
	
	public static MetaDAO getMetaDAO() {
		return new OracleMetaDAO();
	}
}
