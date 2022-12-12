package com.andretaveira90.agenda.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.andretaveira90.agenda.factory.ConnectionFactory;
import com.andretaveira90.agenda.model.Contato;
import com.mysql.jdbc.PreparedStatement;

public class ContatoDAO {
	
	public void salvar(Contato contato) {
		String sql = "INSERT INTO contatos(nome, email, telefone) VALUES(?, ?, ?)";
		
		Connection conexao = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conexao = ConnectionFactory.createConnectionToMySQL();
			preparedStatement = (PreparedStatement) conexao.prepareStatement(sql);
			
			preparedStatement.setString(1, contato.getNome());
			preparedStatement.setString(2, contato.getEmail());
			preparedStatement.setString(3, contato.getTelefone());
			
			preparedStatement.execute();
			
			System.out.println("Salvo com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> listar() {
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<>();
		
		Connection conexao = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			conexao = ConnectionFactory.createConnectionToMySQL();
			preparedStatement = (PreparedStatement) conexao.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Contato contato = new Contato();
				contato.setId(resultSet.getInt("id"));
				contato.setNome(resultSet.getString("nome"));
				contato.setEmail(resultSet.getString("email"));
				contato.setTelefone(resultSet.getString("telefone"));
				
				contatos.add(contato);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}
	
	public void atualizar(Contato contato) {

		String sql = "UPDATE contatos SET nome = ?, email = ?, telefone = ?" +
				"WHERE id = ?" ;
		
		Connection conexao = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conexao = ConnectionFactory.createConnectionToMySQL();
			preparedStatement = (PreparedStatement) conexao.prepareStatement(sql);
			
			preparedStatement.setString(1, contato.getNome());		
			preparedStatement.setString(2, contato.getEmail());
			preparedStatement.setString(3, contato.getTelefone());
			
			preparedStatement.setInt(4, contato.getId());
			
			preparedStatement.execute();
			
			System.out.println("Atualizado com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void excluir(int id) {
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conexao = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conexao = ConnectionFactory.createConnectionToMySQL();
			preparedStatement = (PreparedStatement) conexao.prepareCall(sql);
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.execute();
			
			System.out.println("Removido com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}