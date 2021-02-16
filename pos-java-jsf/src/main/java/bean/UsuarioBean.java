package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.DaoGenerico;
import model.Usuario;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	private DaoGenerico<Usuario> daoGenerico = new DaoGenerico<>();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
