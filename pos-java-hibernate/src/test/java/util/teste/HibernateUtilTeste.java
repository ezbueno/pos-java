package util.teste;

import java.util.List;

import org.junit.Test;

import dao.DaoGenerico;
import model.Usuario;

public class HibernateUtilTeste {
	
	@Test
	public void inserir() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		
		Usuario usuario = new Usuario();
		usuario.setNome("Camila");
		usuario.setSobrenome("Roma");
		usuario.setEmail("camila@teste.com");
		usuario.setLogin("ca");
		usuario.setSenha("321");
		usuario.setIdade(29);
		
		daoGenerico.salvar(usuario);		
	}
	
	@Test
	public void consultar() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		
		Usuario usuario = new Usuario();
		usuario.setId(2L);
		
		usuario = daoGenerico.consultar(usuario);
		
		System.out.println(usuario);
	}
	
	@Test
	public void listar() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		
		List<Usuario> usuarios = daoGenerico.listar(Usuario.class);
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
			System.out.println("=======================");
		}
	}
	
	@Test
	public void atualizar() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		
		Usuario usuario = new Usuario();
		usuario.setId(6L);
		usuario.setNome("Alinne");
		usuario.setSobrenome("Fanelli");
		usuario.setEmail("fanelli@teste.com");
		usuario.setLogin("alifanelly");
		usuario.setSenha("123");
		usuario.setIdade(31);
		
		usuario = daoGenerico.atualizar(usuario);
		
		System.out.println(usuario);
	}
	
	@Test
	public void deletar() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		
		Usuario usuario = new Usuario();
		usuario.setId(3L);
		
		daoGenerico.deletar(usuario);
		
		System.out.println(usuario);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarLista() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		List<Usuario> usuarios = daoGenerico.getEntityManager()
				.createQuery("FROM Usuario WHERE nome = 'Nay'").getResultList();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarListaMax() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		List<Usuario> usuarios = daoGenerico.getEntityManager()
				.createQuery("FROM Usuario ORDER BY nome")
				.setMaxResults(2)
				.getResultList();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	@Test
	public void listarParametro() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = daoGenerico.getEntityManager()
				.createQuery("FROM Usuario WHERE nome = :nome")
				.setParameter("nome", "Ezandro")
				.getResultList();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	@Test
	public void somarIdade() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		
		Long soma = (Long) daoGenerico.getEntityManager()
				.createQuery("SELECT SUM (u.idade) FROM Usuario u")
				.getSingleResult();
		
		System.out.println("A soma de todas as idades é = " + soma);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarTodosUsuarios() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		List<Usuario> usuarios = daoGenerico.getEntityManager()
				.createNamedQuery("Usuario.findAll")
				.getResultList();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void consultarPorNome() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		List<Usuario> usuarios = daoGenerico.getEntityManager()
				.createNamedQuery("Usuario.findByName")
				.setParameter("nome", "Nay")
				.getResultList();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
}
