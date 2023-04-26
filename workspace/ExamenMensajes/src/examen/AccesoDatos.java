package examen;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

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
			PreparedStatement obtener = cnx.prepareStatement("select * from departamento "
					+ "where numero = ?");
			
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

	public ArrayList<Departamento> obtenerDepartamentos() {

		ArrayList<Departamento> resultado = new ArrayList();
		
		try {
			
			Statement obtenerDepartamentos = cnx.createStatement();
			ResultSet filas = obtenerDepartamentos.executeQuery("select * from departamento");
			
			while(filas.next()) {
				
				Departamento d = new Departamento(filas.getInt(1), filas.getString(2));
				
				resultado.add(d);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}

	public ArrayList<Empleado> obtenerEmpleados(Departamento d) {

		ArrayList<Empleado> resultado = new ArrayList();
		
		try {
			PreparedStatement obtenerEmpleados = cnx.prepareStatement("select * from empleado "
					+ "where departamento=?");
			
			obtenerEmpleados.setInt(1, d.getNumero());
			
			ResultSet retorno = obtenerEmpleados.executeQuery();
			
			while(retorno.next()) {
				Empleado e = new Empleado(retorno.getInt(1), retorno.getString(2), 
						retorno.getString(4), retorno.getDate(5), retorno.getInt(6), 
						retorno.getBoolean(7));
				
				resultado.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return resultado;
	}

	public int crearMensaje(int usuario2, Departamento d, ArrayList<Empleado> empleados, String asunto,
			String texto) {
		int resultado = -1;
		
		try {
			cnx.setAutoCommit(false);
			//Creamos el mensaje en la tabla mensaje
			PreparedStatement crearMensaje = cnx.prepareStatement("insert into mensaje "
					+ "values(null, ?, ?, ?, curdate(), ?)", Statement.RETURN_GENERATED_KEYS);
			crearMensaje.setInt(1, usuario2);
			crearMensaje.setInt(2, d.getNumero());
			crearMensaje.setString(3, asunto);
			crearMensaje.setString(4, texto);
			
			int filas = crearMensaje.executeUpdate(); 
			//Recuperar el id del mensaje creado
			ResultSet ids = crearMensaje.getGeneratedKeys();
			
			if(ids.next()) {
				resultado = ids.getInt(1);
			}
			
			if(filas == 1) {
				for(Empleado e: empleados) {
					PreparedStatement enviarEmpleado = cnx.prepareStatement("insert into para "
							+ "values(?, ?, false)");
					enviarEmpleado.setInt(1, resultado);
					enviarEmpleado.setInt(2, e.getCodigo());
					
					filas = enviarEmpleado.executeUpdate();
					
					if(filas != 1) {
						cnx.rollback();
						
						return -1;
					}
					
				}
				//Si llegamos aqui es porque todo ha ido bien
				cnx.commit();
				
			} else {
				cnx.rollback();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				cnx.rollback();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		
		
		return resultado;
	}

	public ArrayList<Mensaje> obtenerMensajes(int usuario2) {
		// TODO Auto-generated method stub
		
		ArrayList<Mensaje> resultado= new ArrayList<>();
		
		try {
			PreparedStatement consulta = cnx.prepareStatement("select * from ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		return resultado;
	}

	public boolean marcarLeidos(int usuario2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
