package instituto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
		return null;
	}
	
}
