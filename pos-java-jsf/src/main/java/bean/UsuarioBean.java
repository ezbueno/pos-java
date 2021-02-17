package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.DaoGenerico;
import model.Usuario;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private DaoGenerico<Usuario> daoGenerico = new DaoGenerico<>();
	private List<Usuario> lista = new ArrayList<>();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLista() {
		lista = daoGenerico.listar(Usuario.class);
		return lista;
	}

	public String salvar() {
		daoGenerico.salvar(usuario);
		return "";
	}
	
	public String excluir() {
		daoGenerico.deletar(usuario);
		usuario = new Usuario();
		return "";
	}

	public String novo() {
		usuario = new Usuario();
		return "";
	}
}
