package examen;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AccesoDatos {

	private EntityManager em = null;

	public AccesoDatos() {

		try {

			em = Persistence.createEntityManagerFactory("UPTenis").createEntityManager();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void cerrar() {

		try {

			if (em != null) {
				em.close();
			}

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

	@SuppressWarnings("unchecked")
	public List<Jugador> obtenerJugadores() {
		List<Jugador> resultado = new ArrayList<>();

		try {

			Query c = em.createQuery("from Jugador");

			resultado = c.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resultado;
	}

	public Jugador obtenerJugador(int cod) {
		Jugador resultado = null;

		try {

			resultado = em.find(Jugador.class, cod);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearPartido(Partido p) {
		boolean resultado = false;
		EntityTransaction t = null;

		try {

			t = em.getTransaction();
			t.begin();

			em.persist(p);

			t.commit();
			em.clear();
			resultado = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<Partido> obtenerPartidos() {
		List<Partido> resultado = new ArrayList<>();

		try {

			Query c = em.createQuery("from Partido");

			resultado = c.getResultList();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return resultado;
	}

	public Partido obtenerPartido(int cod) {
		Partido resultado = null;

		try {

			resultado = em.find(Partido.class, cod);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return resultado;
	}

	public boolean registrarResultadoPartido(Partido p) {
		boolean resultado = false;
		EntityTransaction t = null;

		try {

			t = em.getTransaction();
			t.begin();

			em.merge(p);

			t.commit();
			em.clear();
			resultado = true;

		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		}

		return resultado;
	}

	public int borrarPartido(Partido p) {
		int resultado = -1;
		EntityTransaction t = null;

		try {

			t = em.getTransaction();
			t.begin();

			Query c = em.createQuery("delete from Jugador_Partido where clave.partido.codigo = ?1");
			c.setParameter(1, p.getCodigo());

			int jp = c.executeUpdate();

			if(jp >= 0) {

				c = em.createQuery("delete from Partido where codigo = ?1");
				c.setParameter(1, p.getCodigo());

				int r = c.executeUpdate();

				if(r == 1) {
					t.commit();
					em.clear();
					resultado = jp;
				} else {
					t.rollback();
				}

			} else {
				t.rollback();
			}



		} catch (Exception ex) {
			ex.printStackTrace();
			t.rollback();
		}

		return resultado;
	}

}
