package teste.conexao.jdbc;

import java.util.List;

import org.junit.Test;

import dao.TelefoneDao;
import model.Telefone;

public class TesteTelefoneJDBC {
	
	@Test
	public void inserir() {
		TelefoneDao telefoneDao  = new TelefoneDao();
		Telefone telefone = new Telefone();
		
		telefone.setNumero("(11) 99999-0000");
		telefone.setTipo("Celular");
		telefone.setUsuario(2L);
		
		telefoneDao.salvar(telefone);
	}
	
	@Test
	public void listar() {
		TelefoneDao telefoneDao = new TelefoneDao();
		try {
			List<Telefone> listTelefone = telefoneDao.listar();
			
			 for (Telefone telefone : listTelefone) {
				 System.out.println(telefone);
				 System.out.println("---------------------------------------");
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void buscar() {
		TelefoneDao telefoneDao = new TelefoneDao();
		try {
			Telefone telefone = telefoneDao.buscar(2L);
			
			if (telefone == null) {
				System.out.println("ID não encontrado!");
			} else {
				System.out.println(telefone);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void atualizar() {
		try {
			TelefoneDao telefoneDao = new TelefoneDao();
			Telefone telefone = telefoneDao.buscar(2L);
			
			if (telefone == null ) {
				System.out.println("ID não encontrado");
			} else {
				telefone.setNumero("(11) 99999-1111");
				telefoneDao.atualizar(telefone);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deletar() {
		try {
			TelefoneDao telefoneDao = new TelefoneDao();
			Telefone telefone = telefoneDao.buscar(5L);
			
			if (telefone == null) {
				System.out.println("ID não encontrado!");
			} else {
				telefoneDao.deletar(telefone.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
