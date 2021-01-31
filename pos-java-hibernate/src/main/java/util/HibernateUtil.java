package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	public static EntityManagerFactory emf = null;
	
	static {
		iniciar();
	}
	
	private static void iniciar() {
		try {
			if (emf == null) {
				emf = Persistence.createEntityManagerFactory("pos-java-hibernate");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
