package teste.conexao.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.UsuarioDao;
import model.Usuario;

public class TesteUsuarioJDBC {
	
	@Test
	public void inserir() {
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = new Usuario();
		
		usuario.setNome("Carla");
		usuario.setEmail("carla@teste.com");
		
		usuarioDao.salvar(usuario);
	}
	
	@Test
	public void listar() {
		UsuarioDao usuarioDao = new UsuarioDao();
		try {
			List<Usuario> listUsuarios = usuarioDao.listar();
			
			 for (Usuario usuario : listUsuarios) {
				 System.out.println(usuario);
				 System.out.println("---------------------------------------");
			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void buscar() {
		UsuarioDao usuarioDao = new UsuarioDao();
		try {
			Usuario usuario = usuarioDao.buscar(5L);
			
			if (usuario == null) {
				System.out.println("ID não encontrado!");
			} else {
				System.out.println(usuario);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void atualizar() {
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
			Usuario usuario = usuarioDao.buscar(2L);
			
			if (usuario == null ) {
				System.out.println("ID não encontrado");
			} else {
				usuario.setNome("Nay");
				usuarioDao.atualizar(usuario);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void deletar() {
		try {
			UsuarioDao usuarioDao = new UsuarioDao();
			Usuario usuario = usuarioDao.buscar(5L);
			
			if (usuario == null) {
				System.out.println("ID não encontrado!");
			} else {
				usuarioDao.deletar(usuario.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
