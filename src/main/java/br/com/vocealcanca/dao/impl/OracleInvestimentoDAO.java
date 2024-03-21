package br.com.vocealcanca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.bean.Investimento;
import br.com.vocealcanca.dao.InvestimentoDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.singleton.ConnectionManager;

public class OracleInvestimentoDAO implements InvestimentoDAO {
	Connection conexao = null;

	@Override
	public void cadastrar(Investimento investimento) throws DBException {
		Connection conexao = null;
		PreparedStatement stmtReceita = null;
		PreparedStatement stmtInvestimento = null;
		ResultSet generatedKeys = null;

		try {
		    conexao = ConnectionManager.getConnection();
		    conexao.setAutoCommit(false);

		    String sqlReceita = "INSERT INTO T_RECEITA (data_inicio, valor, tipo_receita, descricao, nome, valor_total, data_final, cliente_id_cliente)"
		            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		    stmtReceita = conexao.prepareStatement(sqlReceita, new String[] { "id_receita" });

		    java.sql.Date dataInicio = new java.sql.Date(investimento.getDataInicio().getTimeInMillis());
		    stmtReceita.setDate(1, dataInicio);
		    stmtReceita.setFloat(2, investimento.getValor());
		    stmtReceita.setString(3, investimento.getTipoReceita());
		    stmtReceita.setString(4, investimento.getDescricao());
		    stmtReceita.setString(5, investimento.getNome());
		    stmtReceita.setFloat(6, investimento.getValorTotal());
		    java.sql.Date dataFinal = new java.sql.Date(investimento.getDataFinal().getTimeInMillis());
		    stmtReceita.setDate(7, dataFinal);
		    stmtReceita.setInt(8, investimento.getClienteIdReceita());

		    stmtReceita.executeUpdate();

		    generatedKeys = stmtReceita.getGeneratedKeys();

		    if (generatedKeys.next()) {
		        int idReceita = generatedKeys.getInt(1);
		        investimento.setIdReceita(idReceita);
		    }

		    String sqlInvestimento = "INSERT INTO T_INVESTIMENTO (valor_aporte_mensal, tipo_investimento, num_aportes_mensais, receita_id_receita)"
		            + " VALUES (?, ?, ?, ?)";

		    stmtInvestimento = conexao.prepareStatement(sqlInvestimento);

		    stmtInvestimento.setFloat(1, investimento.getValorAporteMensal());
		    stmtInvestimento.setString(2, investimento.getTipoInvestimento());
		    stmtInvestimento.setInt(3, investimento.getNumAportesMensais());
		    stmtInvestimento.setInt(4, investimento.getIdReceita());;

		    stmtInvestimento.executeUpdate();

		    conexao.commit();
		} catch (SQLException e) {
		    if (conexao != null) {
		        try {
		            conexao.rollback();
		        } catch (SQLException rollbackException) {
		            rollbackException.printStackTrace();
		        }
		    }
		    e.printStackTrace();
		    throw new DBException("Erro ao cadastrar Receita/Investimento.");
		} finally {
		    if (stmtReceita != null) {
		        try {
		            stmtReceita.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    if (stmtInvestimento != null) {
		        try {
		        	stmtInvestimento.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    if (generatedKeys != null) {
		        try {
		            generatedKeys.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    if (conexao != null) {
		        try {
		            conexao.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

	}

	@Override
	public void atualizar(Investimento investimento) throws DBException {
		try (Connection conexao = ConnectionManager.getConnection();
		         PreparedStatement stmtReceita = conexao.prepareStatement("UPDATE T_RECEITA SET data_inicio = ?, valor = ?, tipo_receita = ?, descricao = ?, nome = ?, valor_total = ?, data_final = ?, cliente_id_cliente = ? WHERE id_receita = ?")) {

		        conexao.setAutoCommit(false);

		        stmtReceita.setDate(1, new java.sql.Date(investimento.getDataInicio().getTimeInMillis()));
		        stmtReceita.setFloat(2, investimento.getValor());
		        stmtReceita.setString(3, investimento.getTipoReceita());
		        stmtReceita.setString(4, investimento.getDescricao());
		        stmtReceita.setString(5, investimento.getNome());
		        stmtReceita.setFloat(6, investimento.getValorTotal());
		        stmtReceita.setDate(7, new java.sql.Date(investimento.getDataFinal().getTimeInMillis()));
		        stmtReceita.setInt(8, investimento.getClienteIdReceita());
		        stmtReceita.setInt(9, investimento.getIdReceita());

		        stmtReceita.executeUpdate();

		        try (PreparedStatement stmtInvestimento = conexao.prepareStatement("UPDATE T_INVESTIMENTO SET valor_aporte_mensal = ?, tipo_investimento = ?, num_aportes_mensais = ?, receita_id_receita = ? WHERE id_investimento = ?")) {
		        	stmtInvestimento.setFloat(1, investimento.getValorAporteMensal());
		        	stmtInvestimento.setString(2, investimento.getTipoInvestimento());
		        	stmtInvestimento.setInt(3, investimento.getNumAportesMensais());
		        	stmtInvestimento.setInt(4, investimento.getIdReceita());
		        	stmtInvestimento.setInt(5, investimento.getIdInvestimento());
		        	stmtInvestimento.executeUpdate();
		        }

		        conexao.commit();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new DBException("Erro ao atualizar.");
		    }
		
	}

	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;

		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			
			String sql = "DELETE FROM T_INVESTIMENTO WHERE ID_INVESTIMENTO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao remover.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Investimento buscar(int id) {
		Investimento investimento = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "SELECT T_RECEITA.*, T_INVESTIMENTO.* FROM T_RECEITA INNER JOIN T_INVESTIMENTO ON T_INVESTIMENTO.receita_id_receita = T_RECEITA.id_receita WHERE id_investimento = ?";
    		stmt = conexao.prepareStatement(sql);
    		stmt.setInt(1, id);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			java.sql.Date dataInicioDB = rs.getDate("data_inicio");
    			Calendar dataInicio = Calendar.getInstance();
    			dataInicio.setTimeInMillis(dataInicioDB.getTime());
    			
    			java.sql.Date dataFinalDB = rs.getDate("data_final");
    			Calendar dataFinal = Calendar.getInstance();
    			dataFinal.setTimeInMillis(dataFinalDB.getTime());
    			
    			String tipoReceita = rs.getString("tipo_receita");
    			String descricao = rs.getString("descricao");
    			String nome = rs.getString("nome");
    			
    			float valorTotal = rs.getFloat("valor_total");
    			float valor = rs.getFloat("valor");
    			float valorAporteMensal = rs.getFloat("VALOR_APORTE_MENSAL");
    			
    			int idCliente = rs.getInt("cliente_id_cliente");
    			int idReceita = rs.getInt("id_receita");
    			int idInvestimento = rs.getInt("id_investimento");
    			int numAportesMensais = rs.getInt("num_aportes_mensais");
    			
    			String tipoInvestimento = rs.getString("tipo_investimento");
    			
    			investimento = new Investimento(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente, 
    					idInvestimento, valorAporteMensal, tipoInvestimento, numAportesMensais);
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return investimento;
	}

	@Override
	public List<Investimento> listar(int id) {
		List<Investimento> lista = new ArrayList<Investimento>();
		PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		conexao = ConnectionManager.getConnection();
    		
    		String sql = "SELECT T_RECEITA.*, T_INVESTIMENTO.* FROM T_RECEITA INNER JOIN T_INVESTIMENTO ON T_INVESTIMENTO.receita_id_receita = T_RECEITA.id_receita WHERE cliente_id_cliente = ?";
    		stmt = conexao.prepareStatement(sql);
    		stmt.setInt(1, id);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			
    			java.sql.Date dataInicioDB = rs.getDate("data_inicio");
    			Calendar dataInicio = Calendar.getInstance();
    			dataInicio.setTimeInMillis(dataInicioDB.getTime());
    			
    			java.sql.Date dataFinalDB = rs.getDate("data_final");
    			Calendar dataFinal = Calendar.getInstance();
    			dataFinal.setTimeInMillis(dataFinalDB.getTime());
    			
    			String tipoReceita = rs.getString("tipo_receita");
    			String descricao = rs.getString("descricao");
    			String nome = rs.getString("nome");
    			
    			float valorTotal = rs.getFloat("valor_total");
    			float valor = rs.getFloat("valor");
    			float valorAporteMensal = rs.getFloat("valor_aporte_mensal");
    			
    			int idCliente = rs.getInt("cliente_id_cliente");
    			int idReceita = rs.getInt("id_receita");
    			int idInvestimento = rs.getInt("id_investimento");
    			int numAportesMensais = rs.getInt("num_aportes_mensais");
    			
    			String tipoInvestimento = rs.getString("tipo_investimento");
    			
    			Investimento investimento = new Investimento(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente, 
    					idInvestimento, valorAporteMensal, tipoInvestimento, numAportesMensais);
    			
    			lista.add(investimento);
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return lista;
	}

}
