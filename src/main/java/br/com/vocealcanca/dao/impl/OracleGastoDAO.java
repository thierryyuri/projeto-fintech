package br.com.vocealcanca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.singleton.ConnectionManager;
import br.com.vocealcanca.bean.Gasto;
import br.com.vocealcanca.dao.GastoDAO;
import br.com.vocealcanca.exception.DBException;

public class OracleGastoDAO implements GastoDAO {
	Connection conexao = null;
	
	@Override
	public void cadastrar(Gasto gasto) throws DBException {
		Connection conexao = null;
		PreparedStatement stmtReceita = null;
		PreparedStatement stmtGasto = null;
		ResultSet generatedKeys = null;

		try {
		    conexao = ConnectionManager.getConnection();
		    conexao.setAutoCommit(false);

		    String sqlReceita = "INSERT INTO T_RECEITA (data_inicio, valor, tipo_receita, descricao, nome, valor_total, data_final, cliente_id_cliente)"
		            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		    stmtReceita = conexao.prepareStatement(sqlReceita, new String[] { "id_receita" });

		    java.sql.Date dataInicio = new java.sql.Date(gasto.getDataInicio().getTimeInMillis());
		    stmtReceita.setDate(1, dataInicio);
		    stmtReceita.setFloat(2, gasto.getValor());
		    stmtReceita.setString(3, gasto.getTipoReceita());
		    stmtReceita.setString(4, gasto.getDescricao());
		    stmtReceita.setString(5, gasto.getNome());
		    stmtReceita.setFloat(6, gasto.getValorTotal());
		    java.sql.Date dataFinal = new java.sql.Date(gasto.getDataFinal().getTimeInMillis());
		    stmtReceita.setDate(7, dataFinal);
		    stmtReceita.setInt(8, gasto.getClienteIdReceita());

		    stmtReceita.executeUpdate();

		    generatedKeys = stmtReceita.getGeneratedKeys();

		    if (generatedKeys.next()) {
		        int idReceita = generatedKeys.getInt(1);
		        gasto.setIdReceita(idReceita);
		    }

		    String sqlGasto = "INSERT INTO T_GASTO (fonte_gasto, tipo_gasto, periodicidade, receita_id_receita)"
		            + " VALUES (?, ?, ?, ?)";

		    stmtGasto = conexao.prepareStatement(sqlGasto);

		    stmtGasto.setString(1, gasto.getFonteGasto());
		    stmtGasto.setString(2, gasto.getTipoGasto());
		    stmtGasto.setString(3, gasto.getPeriodicidade());
		    stmtGasto.setInt(4, gasto.getIdReceita());

		    stmtGasto.executeUpdate();

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
		    throw new DBException("Erro ao cadastrar Receita/Gasto.");
		} finally {
		    if (stmtReceita != null) {
		        try {
		            stmtReceita.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    if (stmtGasto != null) {
		        try {
		            stmtGasto.close();
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
	public void atualizar(Gasto gasto) throws DBException {
	    try (Connection conexao = ConnectionManager.getConnection();
	         PreparedStatement stmtReceita = conexao.prepareStatement("UPDATE T_RECEITA SET data_inicio = ?, valor = ?, tipo_receita = ?, descricao = ?, nome = ?, valor_total = ?, data_final = ?, cliente_id_cliente = ? WHERE id_receita = ?")) {

	        conexao.setAutoCommit(false);

	        stmtReceita.setDate(1, new java.sql.Date(gasto.getDataInicio().getTimeInMillis()));
	        stmtReceita.setFloat(2, gasto.getValor());
	        stmtReceita.setString(3, gasto.getTipoReceita());
	        stmtReceita.setString(4, gasto.getDescricao());
	        stmtReceita.setString(5, gasto.getNome());
	        stmtReceita.setFloat(6, gasto.getValorTotal());
	        stmtReceita.setDate(7, new java.sql.Date(gasto.getDataFinal().getTimeInMillis()));
	        stmtReceita.setInt(8, gasto.getClienteIdReceita());
	        stmtReceita.setInt(9, gasto.getIdReceita());

	        stmtReceita.executeUpdate();

	        try (PreparedStatement stmtGasto = conexao.prepareStatement("UPDATE T_GASTO SET fonte_gasto = ?, tipo_gasto = ?, periodicidade = ? WHERE id_gasto = ?")) {
	            stmtGasto.setString(1, gasto.getFonteGasto());
	            stmtGasto.setString(2, gasto.getTipoGasto());
	            stmtGasto.setString(3, gasto.getPeriodicidade());
	            stmtGasto.setInt(4, gasto.getIdGasto());
	            stmtGasto.executeUpdate();
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
				
				String sql = "DELETE FROM T_GASTO WHERE ID_GASTO = ?";
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
	public Gasto buscar(int id) {
		Gasto gasto = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "SELECT T_RECEITA.*, T_GASTO.* FROM T_RECEITA INNER JOIN T_GASTO ON T_GASTO.receita_id_receita = T_RECEITA.id_receita WHERE id_gasto = ?";
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
    			String fonte_gasto = rs.getString("fonte_gasto");
    			
    			float valorTotal = rs.getFloat("valor_total");
    			float valor = rs.getFloat("valor");
    			
    			int idCliente = rs.getInt("cliente_id_cliente");
    			int idReceita = rs.getInt("id_receita");
    			int idGasto = rs.getInt("id_gasto");
    			
    			String tipoGasto = rs.getString("tipo_gasto");
    			String periodicidade = rs.getString("periodicidade");
    			
    			gasto = new Gasto(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente, 
    					idGasto, fonte_gasto, tipoGasto, periodicidade);
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
    	return gasto;
		
	}

	@Override
	public List<Gasto> listar(int id) {
		
		List<Gasto> lista = new ArrayList<Gasto>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
    	
    	try {
    		conexao = ConnectionManager.getConnection();
    		String sql = "SELECT T_RECEITA.*, T_GASTO.* FROM T_RECEITA INNER JOIN T_GASTO ON T_GASTO.receita_id_receita = T_RECEITA.id_receita WHERE cliente_id_cliente = ?";
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
    			String fonte_gasto = rs.getString("fonte_gasto");
    			
    			float valorTotal = rs.getFloat("valor_total");
    			float valor = rs.getFloat("valor");
    			
    			int idCliente = rs.getInt("cliente_id_cliente");
    			int idReceita = rs.getInt("id_receita");
    			int idGasto = rs.getInt("id_gasto");
    			
    			
    			String tipoGasto = rs.getString("tipo_gasto");
    			String periodicidade = rs.getString("periodicidade");
    			
    			Gasto gasto = new Gasto(idReceita, valor, valorTotal, nome, tipoReceita, descricao, dataInicio, dataFinal, idCliente, 
    					idGasto, fonte_gasto, tipoGasto, periodicidade);
    			
    			lista.add(gasto);
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
