package util.teste;

import java.util.List;

import org.junit.Test;

import dao.DaoGenerico;
import model.Telefone;
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void listarParametro() {
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
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
	
	@Test
	public void salvarTelefone() {
		DaoGenerico<Object> daoGenerico = new DaoGenerico<Object>();
		
		Usuario usuario = new Usuario();
		usuario.setId(2L);
		
		daoGenerico.consultar(usuario);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Celular");
		telefone.setNumero("(11) 99999-1111");
		telefone.setUsuario(usuario);
		
		daoGenerico.salvar(telefone);
	}
	
	@Test
	public void pesquisarTelefone() {
//		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
//		
//		Usuario usuario = (Usuario) daoGenerico.pesquisar(1L, Usuario.class);
//		
//		for (Telefone telefone : usuario.getTelefones()) {
//			System.out.println(telefone.getTipo());
//			System.out.println(telefone.getNumero());
//			System.out.println(telefone.getUsuario());
//		}
		
		DaoGenerico<Usuario> daoGenerico = new DaoGenerico<Usuario>();
		Usuario usuario = new Usuario();
		usuario.setId(2L);
		usuario = daoGenerico.consultar(usuario);

		if (!usuario.getTelefones().isEmpty()) {
			for (Telefone telefone : usuario.getTelefones()) {
				System.out.println(telefone.getTipo());
				System.out.println(telefone.getNumero());
				System.out.println(telefone.getUsuario());
			}
		} else {
			System.out.println("O usuário com ID: " + usuario.getId() + " não possui telefone cadastrado!");
		}
	}
}
