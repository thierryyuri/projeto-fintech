package br.com.vocealcanca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.vocealcanca.bean.Meta;
import br.com.vocealcanca.dao.MetaDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.singleton.ConnectionManager;

public class OracleMetaDAO implements MetaDAO {
	Connection conexao = null;

	@Override
	public void cadastrar(Meta meta) throws DBException {
		Connection conexao = null;
		PreparedStatement stmtMeta = null;

		try {
			conexao = ConnectionManager.getConnection();
		    conexao.setAutoCommit(false);
		    
			String sqlMeta = "INSERT INTO T_META (tipo, descricao, valor, nome, cliente_id_cliente)"
		            + " VALUES (?, ?, ?, ?, ?)";

			stmtMeta = conexao.prepareStatement(sqlMeta);

			stmtMeta.setString(1, meta.getTipoMeta());
			stmtMeta.setString(2, meta.getDescricao());
			stmtMeta.setFloat(3, meta.getValorMeta());
			stmtMeta.setString(4, meta.getNome());
			stmtMeta.setInt(5, meta.getIdCliente());

			stmtMeta.executeUpdate();

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
		    throw new DBException("Erro ao cadastrar Meta.");
		} finally {
		    if (stmtMeta != null) {
		        try {
		        	stmtMeta.close();
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
	public void atualizar(Meta meta) throws DBException {
		PreparedStatement stmtMeta = null;
		
		try {
			Connection conexao = ConnectionManager.getConnection();
			conexao.setAutoCommit(false);
			
			stmtMeta = conexao.prepareStatement("UPDATE T_META SET tipo = ?, descricao = ?, valor = ?, nome = ? WHERE id_meta = ?");
			stmtMeta.setString(1, meta.getTipoMeta());
			stmtMeta.setString(2, meta.getDescricao());
			stmtMeta.setFloat(3, meta.getValorMeta());
			stmtMeta.setString(4, meta.getNome());
			stmtMeta.setInt(5, meta.getIdMeta());
			stmtMeta.executeUpdate();
			
			conexao.commit();
		} catch (Exception e) {
			e.printStackTrace();
	        throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmtMeta.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;

		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			
			String sql = "DELETE FROM T_META WHERE ID_META = ?";
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
	public Meta buscar(int id) {
		Meta meta = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "SELECT T_META.* FROM T_META WHERE id_meta = ?";
    		stmt = conexao.prepareStatement(sql);
    		stmt.setInt(1, id);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			String tipo = rs.getString("tipo");
    			String descricao = rs.getString("descricao");
    			String nome = rs.getString("nome");
    			
    			float valor = rs.getFloat("valor");
    			
    			int idCliente = rs.getInt("cliente_id_cliente");
    			int idMeta = rs.getInt("id_meta");
    			
    			
    			meta = new Meta(idCliente, idMeta, tipo, descricao, valor, nome);
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
    	return meta;
	}

	@Override
	public List<Meta> listar(int id) {
		List<Meta> lista = new ArrayList<Meta>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
    	
    	try {
    		conexao = ConnectionManager.getConnection();
    		String sql = "SELECT * FROM T_META WHERE cliente_id_cliente = ?";
    		
    		stmt = conexao.prepareStatement(sql);
    		stmt.setInt(1, id);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			int idMeta = rs.getInt("id_meta");
    			
    			float valor = rs.getFloat("valor");
    			
    			String nome = rs.getString("nome");
    			String tipoMeta = rs.getString("tipo");
    			String descricao = rs.getString("descricao");
    			int idCliente = rs.getInt("cliente_id_cliente");
    			
    			Meta meta = new Meta(idCliente, idMeta, tipoMeta, descricao, valor, nome);
    			
    			lista.add(meta);
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    	return lista;	
	}

}
