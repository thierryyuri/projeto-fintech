package br.com.vocealcanca.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static ConnectionManager connectionManager;
	
	private ConnectionManager() {
	}
	
	public static ConnectionManager getInstance() {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}
	
	public static Connection getConnection() {
		Connection conexao = null;
		
		try {
		  	  
	        Class.forName("oracle.jdbc.driver.OracleDriver");
	  
	        conexao = DriverManager.getConnection(
	            "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "rm98038", "290405");
	        
	         
		} catch (SQLException e) {
	        System.err.println("Não foi possível conectar no Banco de Dados");
	        e.printStackTrace();
		} catch (ClassNotFoundException e) {
	        System.err.println("O Driver JDBC não foi encontrado!");
	        e.printStackTrace();
		}
		
		return conexao;
	}
}