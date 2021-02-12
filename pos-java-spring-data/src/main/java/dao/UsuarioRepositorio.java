package dao;

import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import model.Usuario;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
	public List<Usuario> consultarPorNome(String nome);
	
	@Lock(LockModeType.READ)
	@Query(value = "SELECT u FROM Usuario u WHERE u.nome = :paramNome")
	public List<Usuario> consultarNomePorParametro(@Param("paramNome") String paramNome);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Usuario u WHERE u.nome = ?1")
	public void deletarPorNome(String nome);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Usuario u SET u.email = ?1 WHERE u.nome = ?2")
	public void atualizarEmailPorNome(String email, String nome);
}
