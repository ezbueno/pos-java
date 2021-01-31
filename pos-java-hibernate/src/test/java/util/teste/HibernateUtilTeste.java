package util.teste;

import org.junit.Test;

import util.HibernateUtil;

public class HibernateUtilTeste {
	
	@Test
	public void hibernateUtilTeste() {
		HibernateUtil.getEntityManager();
	}
}
