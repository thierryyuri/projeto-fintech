package br.com.vocealcanca.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.vocealcanca.bean.Cliente;
import br.com.vocealcanca.dao.ClienteDAO;
import br.com.vocealcanca.exception.DBException;
import br.com.vocealcanca.singleton.ConnectionManager;

public class OracleClienteDAO implements ClienteDAO {
	Connection conexao = null;

	@Override
	public void cadastrar(Cliente cliente) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getConnection();
		    conexao.setAutoCommit(false);
		    
		    if(cliente.getCpf() != null) {
		    	String sqlClienteCPF = "INSERT INTO T_CLIENTE (cpf, data_nascimento, nome, senha, telefone, email, endereco)"
		    			+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		    	
		    	stmt = conexao.prepareStatement(sqlClienteCPF);
		    	stmt.setString(1, cliente.getCpf());		
		    	
		    }

			
			java.sql.Date dataNascimento = new java.sql.Date(cliente.getDataNascimento().getTimeInMillis());
		    stmt.setDate(2, dataNascimento);

			stmt.setString(3, cliente.getNome());
			stmt.setString(4, cliente.getSenha());
			stmt.setString(5, cliente.getTelefone());
			stmt.setString(6, cliente.getEmail());
			stmt.setString(7, cliente.getEndereco());

			stmt.executeUpdate();

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
		    throw new DBException("Erro ao cadastrar Cliente.");
		} finally {
		    if (stmt != null) {
		        try {
		        	stmt.close();
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
	public void atualizar(Cliente cliente) throws DBException {
		PreparedStatement stmt = null;
		
		try {
			Connection conexao = ConnectionManager.getConnection();
			conexao.setAutoCommit(false);
			
			stmt = conexao.prepareStatement("UPDATE T_CLIENTE SET data_nascimento = ?, nome = ?, telefone = ?, email = ?, endereco = ?, senha = ? WHERE id_cliente = ?");
			
			java.sql.Date dataNascimento = new java.sql.Date(cliente.getDataNascimento().getTimeInMillis());
			stmt.setDate(1, dataNascimento);
			
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getTelefone());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(5, cliente.getEndereco());
			stmt.setString(6, cliente.getSenha());
			stmt.setInt(7, cliente.getIdCliente());
			
			stmt.executeUpdate();
			
			conexao.commit();
		} catch (Exception e) {
			e.printStackTrace();
	        throw new DBException("Erro ao atualizar.");
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
	public void remover(int codigo) throws DBException {
		PreparedStatement stmt = null;

		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			
			String sql = "DELETE FROM T_CLIENTE WHERE ID_CLIENTE = ?";
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
	public Cliente buscar(int id) {
		Cliente cliente = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "SELECT T_CLIENTE.* FROM T_CLIENTE WHERE id_cliente = ?";
    		stmt = conexao.prepareStatement(sql);
    		stmt.setInt(1, id);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			int idCliente = rs.getInt("id_cliente");
    			
    			String cpf = rs.getString("cpf");
    			String endereco = rs.getString("endereco");
    			String telefone = rs.getString("telefone");
    			String nome = rs.getString("nome");

    			java.sql.Date datadataNascimentoDB = rs.getDate("data_nascimento");
    			Calendar dataNascimento = Calendar.getInstance();
    			dataNascimento.setTimeInMillis(datadataNascimentoDB.getTime());
    			
    			String email = rs.getString("email");
    			String senha = rs.getString("senha");
    			
    			cliente = new Cliente(idCliente, dataNascimento, nome, telefone, email, senha, endereco, cpf);
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
    	return cliente;
	}
	
	@Override
	public Cliente buscarPorCpf(String cpfVar) {
		Cliente cliente = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			ConnectionManager.getInstance();
			conexao = ConnectionManager.getConnection();
			String sql = "SELECT T_CLIENTE.* FROM T_CLIENTE WHERE cpf = ?";
    		stmt = conexao.prepareStatement(sql);
    		stmt.setString(1, cpfVar);
    		rs = stmt.executeQuery();
    		
    		while(rs.next()) {
    			int idCliente = rs.getInt("id_cliente");
    			
    			String cpf = rs.getString("cpf");
    			String endereco = rs.getString("endereco");
    			String telefone = rs.getString("telefone");
    			String nome = rs.getString("nome");

    			java.sql.Date datadataNascimentoDB = rs.getDate("data_nascimento");
    			Calendar dataNascimento = Calendar.getInstance();
    			dataNascimento.setTimeInMillis(datadataNascimentoDB.getTime());
    			
    			String email = rs.getString("email");
    			String senha = rs.getString("senha");
    			
    			cliente = new Cliente(idCliente, dataNascimento, nome, telefone, email, senha, endereco, cpf);
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
    	return cliente;
	}

	@Override
	public List<Cliente> listar() {
		List<Cliente> lista = new ArrayList<Cliente>();
    	Statement stmt = null;
    	ResultSet rs = null;
    	Cliente cliente;
    	
    	try {
    		conexao = ConnectionManager.getConnection();
    		
    		stmt = conexao.createStatement();
    		rs = stmt.executeQuery("SELECT * FROM T_CLIENTE");
    		
    		while(rs.next()) {
    			int idCliente = rs.getInt("id_cliente");
    			
    			String cpf = rs.getString("cpf");
    			String endereco = rs.getString("endereco");
    			String telefone = rs.getString("telefone");
    			String nome = rs.getString("nome");

    			java.sql.Date datadataNascimentoDB = rs.getDate("data_nascimento");
    			Calendar dataNascimento = Calendar.getInstance();
    			dataNascimento.setTimeInMillis(datadataNascimentoDB.getTime());
    			
    			String email = rs.getString("email");
    			String senha = rs.getString("senha");
    			
    			cliente = new Cliente(idCliente, dataNascimento, nome, telefone, email, senha, endereco, cpf);
    			lista.add(cliente);
    			
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
	
	@Override
	public boolean validarUsuario(Cliente cliente) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_CLIENTE WHERE cpf = ? AND senha = ?");
			System.out.println(cliente.getCpf());
			System.out.println(cliente.getSenha());
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}


