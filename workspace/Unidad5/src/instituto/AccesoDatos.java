package instituto;

import java.sql.Connection;
import java.sql.DriverManager;
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
					+ "(dir).tipoVia, (dir).nombre, (dir).cp, "
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
	
}
