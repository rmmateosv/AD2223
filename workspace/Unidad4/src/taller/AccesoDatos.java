package taller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AccesoDatos {
	private EntityManager em = null;

	public AccesoDatos() {
		try {
			em = Persistence.createEntityManagerFactory("UPTaller")
					.createEntityManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void cerrar() {
		try {
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
