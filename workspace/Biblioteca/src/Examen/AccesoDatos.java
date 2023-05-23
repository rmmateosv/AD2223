package Examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class AccesoDatos {
	private EntityManager em= null;

	public AccesoDatos() {
	
		try {
			em=Persistence.createEntityManagerFactory("UPBiblioteca").createEntityManager();
			
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

	public List<Socio> obtenerSocios() {
		List<Socio> resultado = new ArrayList();
		try {
			
			Query consulta = em.createQuery("from Socio");
			resultado = consulta.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Socio obtenerSocio(int id) {
		Socio resultado = null;
		try {
			
			
			resultado = em.find(Socio.class, id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public long obtenerPrestamosPendientes(Socio s) {
		long resultado = 0;
		
		try {
			
			Query consulta = em.createQuery("select count(*) from Prestamo where cp.socio=?1 "
					+ "and fechaDevolReal is null");
			consulta.setParameter(1, s);
			
			Long i =  (Long) consulta.getResultList().get(0);
			resultado = i;
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultado;
	}

	public List<Libro> obtenerLibros() {
		List<Libro> resultado = new ArrayList();
		try {
			
			Query consulta = em.createQuery("from Libro");
			resultado = consulta.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public Libro obtenerLibro(String isbn) {
		Libro resultado = null;
		try {
			resultado = em.find(Libro.class, isbn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearPrestamo(Prestamo p) {
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

	public boolean devolverPrestamo(Prestamo p) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			p.setFechaDevolReal(new Date());
			if(p.getFechaDevolReal().getTime()>p.getFechaDevolPrevista().getTime()) {
				p.getCp().getSocio().setFechaSancion(new Date(new Date().getTime()+(15*24*60*60*1000)));
			}
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

	public boolean borrarLibro(Libro l) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		EntityTransaction t = em.getTransaction();
		try {
			t.begin();
			if(!l.getPrestamos().isEmpty()) {
				Query consulta = em.createQuery("delete from Prestamo where cp.libro = ?1");
				consulta.setParameter(1, l);
				int r = consulta.executeUpdate();
				if(r==0) {
					t.rollback();
					return false;
				}
			}
			Query consulta2 = em.createQuery("delete from Libro where isbn = ?1");
			consulta2.setParameter(1, l.getIsbn());
			int r = consulta2.executeUpdate();
			if(r==0) {
				t.rollback();
				return false;
			}
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
