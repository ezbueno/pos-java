package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import util.HibernateUtil;

public class DaoGenerico<E> {
	
	private EntityManager em = HibernateUtil.getEntityManager();
	
	public void salvar(E entidade) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(entidade);
		et.commit();
	}
	
	public E atualizar(E entidade) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		E atualizaDados = em.merge(entidade);
		et.commit();
		
		return atualizaDados;
	}
	
	@SuppressWarnings("unchecked")
	public E consultar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		E e = (E) em.find(entidade.getClass(), id);
		return e;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> listar(Class<E> entidade) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<E> lista = em.createQuery("FROM " + entidade.getName()).getResultList();
		et.commit();
		return lista;		
	}
	
	public void deletar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.createNativeQuery("DELETE FROM " + entidade.getClass().getSimpleName().toLowerCase() + " WHERE id = " + id).executeUpdate();
		et.commit();
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
}
