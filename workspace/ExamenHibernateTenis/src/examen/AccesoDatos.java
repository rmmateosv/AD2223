package examen;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AccesoDatos {

	private EntityManager em= null;

	public AccesoDatos() {
	
		try {
			em=Persistence.createEntityManagerFactory("UPTenis").createEntityManager();
			
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

	public List<Jugador> obtenerJugadores() {
		List<Jugador> resultado = new ArrayList<>();
		
		try {
			Query consulta = em.createQuery("from Jugador");
			resultado = consulta.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
}
