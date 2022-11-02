package Spotifly;

import java.sql.Connection;
import java.sql.SQLException;

public class AccesoDatos {
	private Connection conexion=null;

	public AccesoDatos() {
		
	}
	
	public void cerrar() {
		if(conexion!=null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
