package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import conexao.jdbc.SingleConnection;
import model.Agenda;

public class AgendaDao {
	
	private Connection connection;
	
	public AgendaDao() {
		connection = SingleConnection.getConnection();
	}
	
	public List<Agenda> listarAgenda(Long id) {
		List<Agenda> listAgendas = new ArrayList<>();
			
		try {
			String sql = "SELECT numero, nome, email FROM telefone AS tel ";
			sql +=  "INNER JOIN posjava AS pj ";
			sql += "ON tel.id = pj.id ";
			sql += "WHERE pj.id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Agenda agenda = new Agenda();
				agenda.setNome(resultSet.getString("nome"));
				agenda.setNumero(resultSet.getString("numero"));
				agenda.setEmail(resultSet.getString("email"));
				listAgendas.add(agenda);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listAgendas;
	}
}
