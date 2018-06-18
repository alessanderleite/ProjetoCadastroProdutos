package com.alessander.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.alessander.conexao.ConnectionFactory;
import com.alessander.model.bean.Produto;

/*
 * interface entre usuário e banco de dados
 */

public class ProdutoDao {
	
	private String sqlCadastrar = "INSERT INTO produtos (descricao,quantidade,preco) VALUES (?,?,?)";
	private String sqlLer = "SELECT * FROM produtos";
	private String sqlExcluir = "DELETE FROM produtos WHERE id = ?";
	private String sqlAtualizar = "UPDATE produtos SET descricao = ?, quantidade = ?, preco = ? WHERE id = ?";
	
	// para cadastrar itens no banco de dados
	public void cadastrar(Produto p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sqlCadastrar);
			pstmt.setString(1, p.getDescricao());
			pstmt.setInt(2, p.getQuantidade());
			pstmt.setDouble(3, p.getPreco());
			
			pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Cadastrado realizado com sucesso");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, pstmt);
		}
	}
	
	// para exibir na tabela itens cadastrados no banco
	public List<Produto> ler() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Produto> produtos = new ArrayList<>();
		
		try {
			pstmt = con.prepareStatement(sqlLer);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Produto produto = new Produto();
				
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPreco(rs.getDouble("preco"));
				
				produtos.add(produto);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao ler: " + e.getMessage()); // erro ao trazer os dados do banco e mostrar na tabela
		} finally {
			ConnectionFactory.closeConnection(con, pstmt, rs);
		}
		return produtos;	
	}
	
	// para excluir itens no banco de dados
	public void excluir(Produto p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sqlExcluir);
			pstmt.setInt(1, p.getId());
			pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, pstmt);
		}
	}
	
	// para atualizar itens cadastrados no banco de dados
	public void atualizar(Produto p) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sqlAtualizar);
			pstmt.setString(1, p.getDescricao());
			pstmt.setInt(2, p.getQuantidade());
			pstmt.setDouble(3, p.getPreco());
			pstmt.setInt(4, p.getId());
			
			pstmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e.getMessage());
		} finally {
			ConnectionFactory.closeConnection(con, pstmt);
		}
	}
}
