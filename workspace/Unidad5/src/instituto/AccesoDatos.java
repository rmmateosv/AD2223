package instituto;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoDatos {
	private Connection cnx = null;
	
	private String url="jdbc:postgresql://database-1.c6ponazvhiex.us-east-1.rds.amazonaws.com:5432/instituto";
	private String us="postgres";
	private String ps="postgres";

	public AccesoDatos() {
		try {
			Class.forName("org.postgresql.Driver");
			cnx=DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getCnx() {
		return cnx;
	}

	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}
	public void cerrar() {
		if(cnx!=null) {
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Alumno> obtenerAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> resultado = new ArrayList();
		try {
			Statement consulta = cnx.createStatement();
			ResultSet r = consulta.executeQuery("select id, nombre, "
					+ "(dir).*, "
					+ "fechaN, numExpe, curso "
					+ "from alumno");
			while(r.next()) {
				Alumno a = new Alumno(r.getInt(1), 
						r.getString(2), 
						new Direccion(r.getString(3), r.getString(4), r.getInt(5)), 
						r.getDate(6), r.getInt(7), r.getString(8));
				resultado.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Alumno obtenerAlumno(int id) {
		// TODO Auto-generated method stub
		Alumno resultado = null;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select id, nombre, "
					+ "(dir).*, "
					+ "fechaN, numExpe, curso "
					+ "from alumno where id = ?");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Alumno(r.getInt(1), 
						r.getString(2), 
						new Direccion(r.getString(3), r.getString(4), 
						r.getInt(5)), 
						r.getDate(6), r.getInt(7), r.getString(8));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Asignatura> obtenerAsignaturas() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> resultado = new ArrayList();
		try {
			Statement consulta = cnx.createStatement();
			ResultSet r = consulta.executeQuery("select * "
					+ "from asignatura");
			while(r.next()) {
				Asignatura a = new Asignatura(r.getString(1), 
						r.getString(2));
				resultado.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Asignatura obtenerAsignatura(String asig) {
		// TODO Auto-generated method stub
		Asignatura resultado = null;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select * "
					+ "from asignatura where codigo = ?");
			consulta.setString(1, asig);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Asignatura(r.getString(1), r.getString(2));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Nota obtenerNota(Alumno al, Asignatura as) {
		// TODO Auto-generated method stub
		Nota resultado = null;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select * "
					+ "from nota where alumno = ? and asignatura = ?");
			consulta.setInt(1, al.getId());
			consulta.setString(2, as.getCodigo());
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Nota();
				resultado.setAlumno(al);
				resultado.setAsig(as);
				//Rellenar ArrayList de notas
				Array tmp = r.getArray(3);
				//Convierto tmp a una matriz de String
				String[][] tmp2 = (String[][]) tmp.getArray();
				for(String[] nota:tmp2) {
					resultado.getNotas().add(nota);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean crearNota(Nota n) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"insert into nota values "
					+ "(?,?,array[array[?,?,?]])");
			consulta.setInt(1, n.getAlumno().getId());
			consulta.setString(2, n.getAsig().getCodigo());
			consulta.setString(3, n.getNotas().get(0)[0]);
			consulta.setString(4, n.getNotas().get(0)[1]);
			consulta.setString(5, n.getNotas().get(0)[2]);
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean actualizarNota(Nota n, ArrayList<String[]> unaNota) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"update nota set notas = array_cat(notas,array[array[?,?,?]]) "
					+ "where alumno = ? and asignatura = ?");
			consulta.setInt(4, n.getAlumno().getId());
			consulta.setString(5, n.getAsig().getCodigo());
			consulta.setString(1, unaNota.get(0)[0]);
			consulta.setString(2, unaNota.get(0)[1]);
			consulta.setString(3, unaNota.get(0)[2]);
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Nota> obtenerNotasAlumno(int id) {
		// TODO Auto-generated method stub
		ArrayList<Nota> resultado = new ArrayList();
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select *, (dir).* "
					+ "from nota inner join alumno "
					+ "on alumno =id inner join asignatura "
					+ "on codigo = asignatura where alumno = ?");
			consulta.setInt(1, id);
			
			ResultSet r = consulta.executeQuery();
			while(r.next()) {
				Nota n = new Nota();
				n.setAlumno(new Alumno(r.getInt(1),r.getString(5),
						new Direccion(r.getString(12),r.getString(13),r.getInt(14)),
						r.getDate(7),
						r.getInt(8),
						r.getString(9)));
				n.setAsig(new Asignatura(r.getString(2), r.getString(11)));
				//Rellenar ArrayList de notas
				Array tmp = r.getArray(3);
				//Convierto tmp a una matriz de String
				String[][] tmp2 = (String[][]) tmp.getArray();
				for(String[] nota:tmp2) {
					n.getNotas().add(nota);
				}
				resultado.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean modificarNota(Nota n) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"update nota set notas = ?"
					+ "where alumno = ? and asignatura = ?");
			consulta.setArray(1, cnx.createArrayOf("text", n.getNotas().toArray(new String[0][0])));
			consulta.setInt(2, n.getAlumno().getId());
			consulta.setString(3, n.getAsig().getCodigo());			
			int filas = consulta.executeUpdate();
			if(filas==1) {
				resultado=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public ArrayList<Persona> obtenerPersonas() {
		// TODO Auto-generated method stub
		ArrayList<Persona> resultado = new ArrayList();
		try {
			Statement consulta = cnx.createStatement();
			ResultSet r = consulta.executeQuery("select id, nombre, "
					+ "(dir).*, "
					+ "fechaN  "
					+ "from persona");
			while(r.next()) {
				Persona p = new Persona(r.getInt(1), 
						r.getString(2), 
						new Direccion(r.getString(3), r.getString(4), r.getInt(5)), 
						r.getDate(6));
				resultado.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}


	public Persona obtenerPersona(int id) {
		// TODO Auto-generated method stub
		Persona resultado = null;
		try {
			PreparedStatement consulta = cnx.prepareStatement(
					"select id, nombre, "
					+ "(dir).*, "
					+ "fechaN "
					+ "from persona where id = ?");
			consulta.setInt(1, id);
			ResultSet r = consulta.executeQuery();
			if(r.next()) {
				resultado = new Persona(r.getInt(1), 
						r.getString(2), 
						new Direccion(r.getString(3), r.getString(4), 
						r.getInt(5)), 
						r.getDate(6));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
}
