package teste;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.UsuarioRepositorio;
import model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTeste {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Test
	public void inserir() {
		Usuario usuario = new Usuario();

		usuario.setLogin("emnie");
		usuario.setSenha("aei");
		usuario.setNome("Emnie");
		usuario.setEmail("emnie@teste.com");
		usuario.setIdade(26);
		
		usuarioRepositorio.save(usuario);
	}
	
	@Test
	public void consultarPorId() {
		Optional<Usuario> usuario = usuarioRepositorio.findById(1L);
		
		if (usuario.isEmpty()) {
			System.out.println("Usuário não encontrado!");
		} else {
			System.out.println(usuario);
		}
	}
	
	@Test
	public void consultarTodos() {
		Iterable<Usuario> usuarios = usuarioRepositorio.findAll();
		
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}
	
	@Test
	public void consultarPorNome() {
		List<Usuario> usuarios = usuarioRepositorio.consultarPorNome("Nay");
		
		if (usuarios.isEmpty()) {
			System.out.println("Usuário não encontrado!");
		} else {
			for (Usuario usuario : usuarios) {
				System.out.println(usuario);
			}
		}
	}
	
	@Test
	public void consultarNomePorParametro() {
		List<Usuario> usuarios = usuarioRepositorio.consultarNomePorParametro("Ezandro");
		
		if (usuarios.isEmpty()) {
			System.out.println("Usuário não encontrado!");
		} else {
			for (Usuario usuario : usuarios) {
				System.out.println(usuario);
			}
		}
	}
	
	@Test
	public void atualizar() {
		Optional<Usuario> usuario = usuarioRepositorio.findById(3L);
		Usuario atualiza = usuario.get();
		
		atualiza.setEmail("emnie@teste.com");
		usuarioRepositorio.save(atualiza);
	}
	
	@Test
	public void atualizarEmailPorNome() {
		List<Usuario> usuarios = usuarioRepositorio.consultarPorNome("Nayara");

		if (usuarios.isEmpty()) {
			System.out.println("Usuário não encontrado!");
		} else {
			Usuario usuario = new Usuario();
			usuario.setEmail("nayara@teste.com");
			String nome = usuarios.get(0).getNome();
			usuarioRepositorio.atualizarEmailPorNome(usuario.getEmail(), nome);
		}
	}
	
	@Test
	public void deletar() {
		Optional<Usuario> usuario = usuarioRepositorio.findById(3L);
		
		if (usuario.isPresent()) {
			usuarioRepositorio.deleteById(usuario.get().getId());
		} else {
			System.out.println("Exclusão não realizada! MOTIVO: ID não encontrado!");
		}
	}
	
	@Test
	public void deletarPorNome() {
		usuarioRepositorio.deletarPorNome("Nayara Balarotti");
	}
}
