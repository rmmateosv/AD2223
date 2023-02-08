package taller;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

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

	public List<Vehiculo> obtenerVehiculos() {
		// TODO Auto-generated method stub
		List<Vehiculo> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("from Vehiculo order by matricula");
			resultado = c.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Vehiculo obtenerVehiculo(int codigo) {
		// TODO Auto-generated method stub
		Vehiculo resultado=null;
		try {
			resultado = em.find(Vehiculo.class, codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearReparacion(Reparacion r) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			t= em.getTransaction();
			t.begin();
			em.persist(r);
			t.commit();
			em.clear();
			resultado = true;
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}
}
