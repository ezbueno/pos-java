package teste.conexao.jdbc;

import java.util.List;

import org.junit.Test;

import dao.AgendaDao;
import dao.TelefoneDao;
import model.Agenda;
import model.Telefone;

public class TesteAgendaJDBC {
	
	@Test
	public void listar() {
		AgendaDao agendaDao = new AgendaDao();
		List<Agenda> agendas = agendaDao.listarAgenda(2L);
		
		if (!agendas.isEmpty()) {
			for (Agenda agenda : agendas) {
				System.out.println(agenda);
			}
		} else {
			System.out.println("ID não encontrado!");
		}
	}
	
	@Test
	public void deletar() {
		TelefoneDao telefoneDao = new TelefoneDao();
		Telefone telefone = telefoneDao.buscar(2L);
		
		if (telefone == null) {
			System.out.println("ID não encontrado!");
		} else {
			telefoneDao.deletarUsuarioTelefone(telefone.getId());
		}
	}
}
