package clinicaV;

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
			em = Persistence.createEntityManagerFactory("UPClinicaV").createEntityManager();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
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

	public Cliente obtenerCliente(String email) {
		// TODO Auto-generated method stub
		Cliente resultado = null;
		try {
			Query consulta = em.createQuery("from Cliente where email = ?1");
			
			consulta.setParameter(1, email);
			
			List<Cliente> r = consulta.getResultList();
			if(!r.isEmpty()) {
				resultado = r.get(0);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = null;
		try {
			//Siempre que hagamos una modificación en los datos
			//hay que hacerlo dentro de una transacción
			t = em.getTransaction();
			t.begin();
			//Hacemos el insert
			em.persist(c);
			t.commit();
			resultado = true;
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public List<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		List<Cliente> resultado = new ArrayList<>();
		try {
			Query c = em.createQuery("from Cliente");
			resultado = c.getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public Cliente obtenerCliente(int codigo) {
		// TODO Auto-generated method stub
		Cliente resultado =null;
		try {
			resultado = em.find(Cliente.class, codigo);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarCliente(Cliente c) {
		// TODO Auto-generated method stub
		boolean resultado =false;
		
		EntityTransaction t  = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.persist(c);
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean borrarCliente(Cliente c) {
		// TODO Auto-generated method stub
boolean resultado =false;
		
		EntityTransaction t  = null;
		try {
			t = em.getTransaction();
			t.begin();
			em.remove(c);
			t.commit();
			resultado=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			t.rollback();
			e.printStackTrace();
		}
		return resultado;
	}
	
	
}