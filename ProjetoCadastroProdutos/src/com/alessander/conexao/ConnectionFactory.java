package com.alessander.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/db_cadastro?useSSL=true";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Erro de conexao aqui: " + e.getMessage()); 
		}	
	}
	
	public static void closeConnection(Connection con) {
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar Connection: " + e.getMessage());
			}
		}	
	}
	
	public static void closeConnection(Connection con, PreparedStatement pstmt) {
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar PreparedStatement: " + e.getMessage());
			}
		}
		closeConnection(con);
	}
	
	public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
				e.printStackTrace();
			}
		}
		closeConnection(con, pstmt);
	}
}
