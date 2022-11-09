package Spotifly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoDatos {
	private Connection conexion = null;
	private String bd = "spotifly", puerto = "3306", servidor = "localhost";
	private String url = "jdbc:mysql://" + servidor + ":" + puerto + "/" + bd;
	private String us = "root";
	private String ps = "root";

	public AccesoDatos() {
		try {
			// Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Crear la conexión
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
		if (conexion != null) {
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
		Artista resultado = null;

		try {
			// Creo una consulta con parámetros
			PreparedStatement consulta = conexion.prepareStatement("select * from artista where nombre = ?");
			// Relleno los parámetros
			consulta.setString(1, nombre);
			// Ejecuto consulta
			ResultSet r = consulta.executeQuery();
			// Compruebo si se devuelve algo
			// Como máximo va a devolver 1
			if (r.next()) {
				resultado = new Artista(r.getInt(1), r.getString(2), r.getString(3),
						// new java.util.Date(r.getDate(4).getTime()),
						r.getDate(4), r.getBoolean(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearArtista(Artista a) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		try {
			PreparedStatement consulta = conexion.prepareStatement("insert into artista values (null,?,?,?,true)");
			consulta.setString(1, a.getNombre());
			consulta.setString(2, a.getGenero());
			consulta.setDate(3, new java.sql.Date(a.getFechaL().getTime()));
			int filas = consulta.executeUpdate();
			if (filas == 1) {
				resultado = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public ArrayList<Artista> obtenerArtistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> resultado = new ArrayList<>();

		Statement consulta;
		try {
			consulta = conexion.createStatement();
			ResultSet r = consulta.executeQuery("select * from artista");
			while (r.next()) {
				resultado.add(new Artista(r.getInt(1), r.getString(2), r.getString(3),
						// new java.util.Date(r.getDate(4).getTime()),
						r.getDate(4), r.getBoolean(5)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public Artista obtenerArtista(int id) {
		// TODO Auto-generated method stub
		Artista resultado = null;

		try {
			// Creo una consulta con parámetros
			PreparedStatement consulta = conexion.prepareStatement("select * from artista where id = ?");
			// Relleno los parámetros
			consulta.setInt(1, id);
			// Ejecuto consulta
			ResultSet r = consulta.executeQuery();
			// Compruebo si se devuelve algo
			// Como máximo va a devolver 1
			if (r.next()) {
				resultado = new Artista(r.getInt(1), r.getString(2), r.getString(3),
						// new java.util.Date(r.getDate(4).getTime()),
						r.getDate(4), r.getBoolean(5));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Album obtenerAlbum(Artista a, String titulo) {
		// TODO Auto-generated method stub
		Album resultado = null;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from album where titulo=? and artista=?");
			sentencia.setString(1, titulo);
			sentencia.setInt(2, a.getId());
			
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Album(r.getInt(1), titulo, a, r.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearAlbum(Album al) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"insert into album values (null,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1, al.getTitulo());
			sentencia.setInt(2, al.getArtista().getId());
			sentencia.setInt(3, al.getAnio());
			
			int r = sentencia.executeUpdate();
			if(r==1) {
				resultado=true;
				ResultSet idS = sentencia.getGeneratedKeys();
				if(idS.next()) {
					//Rellenamos el id del álbum que se acaba de insertar
					al.setId(idS.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Album> obtenerAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> resultado = new ArrayList<>();
	
		try {
			Statement sentencia = conexion.createStatement();
			ResultSet r = sentencia.executeQuery(
					"select * from album al inner join artista ar "
					+ "on al.artista = ar.id");
			while(r.next()) {
				Album al = new Album(r.getInt(1), 
						r.getString(2), 
						new Artista(r.getInt(3), r.getString(6), r.getString(7), 
								    r.getDate(8), r.getBoolean(9)), 
						r.getInt(4));
				resultado.add(al);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Album obtenerAlbum(int album) {
		// TODO Auto-generated method stub
		Album resultado = null;
		
		try {
			PreparedStatement sentencia = conexion.prepareStatement(
					"select * from album inner join artista "
					+ "on album.artista = artista.id "
					+ "where id=?");
			sentencia.setInt(1, album);
			
			ResultSet r = sentencia.executeQuery();
			if(r.next()) {
				resultado = new Album(r.getInt(1), r.getString(2), 
						new Artista(r.getInt(3), 
								    r.getString(6), 
								    r.getString(7), 
								    r.getDate(8), 
								    r.getBoolean(9)), 
						r.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public boolean crearCancion(Cancion c) {
		// TODO Auto-generated method stub
		return false;
	}

}