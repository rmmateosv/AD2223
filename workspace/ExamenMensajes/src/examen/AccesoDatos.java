package examen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AccesoDatos {

	private Connection cnx;
	private String url = "jdbc:mysql://localhost:3306/mensajes";
	private String usuario = "root";
	private String clave = "root";
	
	public AccesoDatos() {
		try {
			cnx=DriverManager.getConnection(url,usuario,clave);
			
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

	public int comprobarUsuario(int usuario, String clave) {
		
		int resultado = 0;

		try {
			CallableStatement funcion = cnx.prepareCall("{?=call login(?, ?)}");
			funcion.setInt(2, usuario);
			funcion.setString(3, clave);	
			funcion.registerOutParameter(1, Types.INTEGER);
			
			funcion.executeUpdate();
			
			resultado = funcion.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public boolean modificarClave(String clave, int usuario) {
		
		boolean resultado = false;
		
		try {
			PreparedStatement modificar = cnx.prepareStatement("update empleado "
					+ "set ps = sha2(?,0), cambiarPs = false where codigo = ?");
			modificar.setString(1, clave);
			modificar.setInt(2, usuario);
			
			int retorno = modificar.executeUpdate();  
			
			if(retorno == 1 ) {
				resultado = true;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public Departamento obtenerDepartamento(int departamento) {
		Departamento resultado = null;
		
		try {
			PreparedStatement obtener = cnx.prepareStatement("select * from departemento "
					+ "where codigo = ?");
			
			obtener.setInt(1, departamento);
			
			ResultSet retorno = obtener.executeQuery();
			
			if(retorno.next()) {
				resultado = new Departamento(retorno.getInt(1), retorno.getString(2));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
	
}
