package examen;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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

	public Jugador obtenerJugador(int codigo) {
		Jugador j = null;
		
		try {
			//No se pone Query cuando hay que recuperar un objeto por su clave primaria
			j = em.find(Jugador.class, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return j;
	}

	public boolean crearPartido(Partido p) {
		boolean resultado = false;
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			em.persist(p);
			t.commit();
			em.clear();
			resultado = true;
			
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();			
		}
		
		return resultado;
	}

	public List<Partido> obtenerPartidos() {
		List<Partido> resultado = new ArrayList<>();
		
		try {
			
			Query consulta = em.createQuery("from Partido");
			resultado = consulta.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Partido obtenerPartido(int codigo) {
		Partido p = null;
		
		try {
			//No se pone Query cuando hay que recuperar un objeto por su clave primaria
			p = em.find(Partido.class, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	
}
