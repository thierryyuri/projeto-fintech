package br.com.vocealcanca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.bean.Renda;
import br.com.vocealcanca.dao.RendaDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.singleton.ConnectionManager;

public class OracleRendaDAO implements RendaDAO {
	Connection conexao = null;

	@Override
	public void cadastrar(Renda renda) throws DBException {
		Connection conexao = null;
		PreparedStatement stmtReceita = null;
		PreparedStatement stmtRenda = null;
		ResultSet generatedKeys = null;

		try {
		    conexao = ConnectionManager.getConnection();
		    conexao.setAutoCommit(false);

		    String sqlReceita = "INSERT INTO T_RECEITA (data_inicio, valor, tipo_receita, descricao, nome, valor_total, data_final, cliente_id_cliente)"
		            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		    stmtReceita = conexao.prepareStatement(sqlReceita, new String[] { "id_receita" });

		    java.sql.Date dataInicio = new java.sql.Date(renda.getDataInicio().getTimeInMillis());
		    stmtReceita.setDate(1, dataInicio);
		    stmtReceita.setFloat(2, renda.getValor());
		    stmtReceita.setString(3, renda.getTipoReceita());
		    stmtReceita.setString(4, renda.getDescricao());
		    stmtReceita.setString(5, renda.getNome());
		    stmtReceita.setFloat(6, renda.getValorTotal());
		    java.sql.Date dataFinal = new java.sql.Date(renda.getDataFinal().getTimeInMillis());
		    stmtReceita.setDate(7, dataFinal);
		    stmtReceita.setInt(8, renda.getClienteIdReceita());

		    stmtReceita.executeUpdate();

		    generatedKeys = stmtReceita.getGeneratedKeys();

		    if (generatedKeys.next()) {
		        int idReceita = generatedKeys.getInt(1);
		        renda.setIdReceita(idReceita);
		    }

		    String sqlGasto = "INSERT INTO T_RENDA (fonte_renda, tipo_renda, periodicidade, receita_id_receita)"
		            + " VALUES (?, ?, ?, ?)";

		    stmtRenda = conexao.prepareStatement(sqlGasto);

		    stmtRenda.setString(1, renda.getFonteRenda());
		    stmtRenda.setString(2, renda.getTipoRenda());
		    stmtRenda.setString(3, renda.getPeriodicidade());
		    stmtRenda.setInt(4, renda.getIdReceita());

		    stmtRenda.executeUpdate();

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
		    throw new DBException("Erro ao cadastrar Receita/Renda.");
		} finally {
		    if (stmtReceita != null) {
		        try {
		            stmtReceita.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    if (stmtRenda != null) {
		        try {
		        	stmtRenda.close();
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
	public void atualizar(Renda renda) throws DBException {
		try (Connection conexao = ConnectionManager.getConnection();
		         PreparedStatement stmtReceita = conexao.prepareStatement("UPDATE T_RECEITA SET data_inicio = ?, valor = ?, tipo_receita = ?, descricao = ?, nome = ?, valor_total = ?, data_final = ?, cliente_id_cliente = ? WHERE id_receita = ?")) {

		        conexao.setAutoCommit(false);

		        stmtReceita.setDate(1, new java.sql.Date(renda.getDataInicio().getTimeInMillis()));
		        stmtReceita.setFloat(2, renda.getValor());
		        stmtReceita.setString(3, renda.getTipoReceita());
		        stmtReceita.setString(4, renda.getDescricao());
		        stmtReceita.setString(5, renda.getNome());
		        stmtReceita.setFloat(6, renda.getValorTotal());
		        stmtReceita.setDate(7, new java.sql.Date(renda.getDataFinal().getTimeInMillis()));
		        stmtReceita.setInt(8, renda.getClienteIdReceita());
		        stmtReceita.setInt(9, renda.getIdReceita());

		        stmtReceita.executeUpdate();

		        try (PreparedStatement stmtRenda = conexao.prepareStatement("UPDATE T_RENDA SET fonte_renda = ?, tipo_renda = ?, periodicidade = ? WHERE id_renda = ?")) {
		        	stmtRenda.setString(1, renda.getFonteRenda());
		        	stmtRenda.setString(2, renda.getTipoRenda());
		        	stmtRenda.setString(3, renda.getPeriodicidade());
		        	stmtRenda.setInt(4, renda.getIdRenda());
		        	stmtRenda.executeUpdate();
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
			
			String sql = "DELETE FROM T_RENDA WHERE ID_RENDA = ?";
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
	public Renda buscar(int id) {
		Renda renda = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "SELECT T_RECEITA.*, T_RENDA.* FROM T_RECEITA INNER JOIN T_RENDA ON T_RENDA.receita_id_receita = T_RECEITA.id_receita WHERE id_renda = ?";
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
    			String fonte_renda = rs.getString("fonte_renda");
    			
    			float valorTotal = rs.getFloat("valor_total");
    			float valor = rs.getFloat("valor");
    			
    			int idCliente = rs.getInt("cliente_id_cliente");
    			int idReceita = rs.getInt("id_receita");
    			int idRenda = rs.getInt("id_renda");
    			
    			String tipoRenda = rs.getString("tipo_renda");
    			String periodicidade = rs.getString("periodicidade");
    			
    			renda = new Renda(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente, 
    					idRenda, fonte_renda, tipoRenda, periodicidade);
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
    	return renda;
	}

	@Override
	public List<Renda> listar(int id) {
		List<Renda> lista = new ArrayList<Renda>();
		PreparedStatement stmt = null;
    	ResultSet rs = null;
    	
    	try {
    		conexao = ConnectionManager.getConnection();
    		
    		
    		String sql = "SELECT T_RECEITA.*, T_RENDA.* FROM T_RECEITA INNER JOIN T_RENDA ON T_RENDA.receita_id_receita = T_RECEITA.id_receita WHERE cliente_id_cliente = ?";
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
    			String tipoRenda = rs.getString("tipo_renda");
    			String fonteRenda = rs.getString("fonte_renda");
    			String periodicidade = rs.getString("periodicidade");
    			
    			float valorTotal = rs.getFloat("valor_total");
    			float valor = rs.getFloat("valor");
    			
    			int idCliente = rs.getInt("cliente_id_cliente");
    			int idReceita = rs.getInt("id_receita");
    			int idRenda = rs.getInt("id_renda");
    			
    			Renda renda = new Renda(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente, 
    					idRenda, fonteRenda, tipoRenda, periodicidade);
    			
    			lista.add(renda);
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
