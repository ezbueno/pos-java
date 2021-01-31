package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.jdbc.SingleConnection;
import model.Telefone;

public class TelefoneDao {
	
	private Connection connection;

	public TelefoneDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Telefone telefone) {
		try {
			String sql = "INSERT INTO telefone (numero, tipo, usuario) VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, telefone.getNumero());
			preparedStatement.setString(2, telefone.getTipo());
			preparedStatement.setLong(3, telefone.getUsuario());
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}	
	
	public List<Telefone> listar() {
		List<Telefone> listTelefones = new ArrayList<>();

		try {
			String sql = "SELECT * FROM telefone";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Telefone telefone = new Telefone();
				telefone.setId(resultSet.getLong("id"));
				telefone.setNumero(resultSet.getString("numero"));
				telefone.setTipo(resultSet.getString("tipo"));
				telefone.setUsuario(resultSet.getLong("usuario"));
				listTelefones.add(telefone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTelefones;
	}

	public Telefone buscar(Long id) {
		Telefone telefone = null;
		try {
			String sql = "SELECT * FROM telefone WHERE id = " + id;

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				telefone = new Telefone();
				telefone.setId(resultSet.getLong("id"));
				telefone.setNumero(resultSet.getString("numero"));
				telefone.setTipo(resultSet.getString("tipo"));
				telefone.setUsuario(resultSet.getLong("usuario"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return telefone;
	}

	public void atualizar(Telefone telefone) {
		try {
			String sql = "UPDATE telefone SET numero = ? WHERE id = " + telefone.getId();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, telefone.getNumero());
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public void deletar(Long id) {
		try {
			String sql = "DELETE FROM telefone WHERE id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void deletarUsuarioTelefone(Long id) {
		try {
			String sqlTelefone = "DELETE FROM telefone WHERE usuario = " + id;
			String sqlUsuario = "DELETE FROM posjava WHERE id = " + id;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlTelefone);
			preparedStatement.execute();
			connection.commit();
			
			preparedStatement = connection.prepareStatement(sqlUsuario);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
