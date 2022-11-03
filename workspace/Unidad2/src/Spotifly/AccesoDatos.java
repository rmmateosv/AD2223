package Spotifly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDatos {
	private Connection conexion=null;
	private String bd="spotifly", 
			puerto="3306", 
			servidor="localhost";
	private String url = "jdbc:mysql://"+servidor+":"+puerto+"/"+bd;
	private String us = "root";
	private String ps = "root";
	
	public AccesoDatos() {		
		try {
			//Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Crear la conexión
			conexion = DriverManager.getConnection(url, us, ps);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Connection getConexion() {
		return conexion;
	}

	public Artista obtenerArtista(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean crearArtista(Artista a) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
