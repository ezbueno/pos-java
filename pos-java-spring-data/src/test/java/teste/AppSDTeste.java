package teste;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.TelefoneRepositorio;
import dao.UsuarioRepositorio;
import model.Telefone;
import model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSDTeste {
	
	@Autowired
	TelefoneRepositorio telefoneRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
		
	@Test
	public void inserir() {
		Optional<Usuario> usuario = usuarioRepositorio.findById(10L);
		
		if (!usuario.isPresent()) {
			System.out.println("Usuário não encontrado!");
		} else {
			Telefone telefone = new Telefone();
			telefone.setTipo("Celular");
			telefone.setNumero("(11) 99999-2222");
			telefone.setUsuario(usuario.get());
			telefoneRepositorio.save(telefone);
		}
	}
	
	@Test
	public void consultarPorId() {
		Optional<Usuario> usuarios = usuarioRepositorio.findById(1L);
		
		if (!usuarios.isPresent()) {
			System.out.println("Usuário não encontrado!");
		} else {
			for (Telefone telefone : usuarios.get().getTelefones()) {
				System.out.println(telefone);
			}
		}
	}
}
