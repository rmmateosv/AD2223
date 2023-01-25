package clinicaV;

import jakarta.persistence.EntityManager;

public class AccesoDatos {
	private EntityManager em = null;

	public AccesoDatos() {
	}
	
	public void cerrar() {
		try {
			if(em!=null) {
				em.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}
