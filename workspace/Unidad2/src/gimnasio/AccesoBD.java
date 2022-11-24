package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AccesoBD {
	private String url = "jdbc:mysql://localhost:3306/gimnasio";
	private String usuario = "root";
	private String pwd = "root";
	private Connection cnx;

	public AccesoBD() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection(url, usuario, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCnx() {
		return cnx;
	}

	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}

	public Usuario obtenerUsuario(String user, String password) {
		return null;
	}

	public Cliente obtenerCliente(String identificador, String tipo)
	{
		try 
		{
			PreparedStatement consulta;
			
			if (tipo.equals("N"))
				consulta = cnx.prepareStatement("select * from cliente where usuario = ?");
			else
				consulta = cnx.prepareStatement("select * from cliente where dni = ?");
			
			consulta.setString(1, identificador);
			
			ResultSet rs = consulta.executeQuery();
			
			if (rs.next())
				return new Cliente(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getInt(1));
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean crearCliente(Cliente c)
	{
		try
		{
			cnx.setAutoCommit(false);
			
			PreparedStatement consulta = cnx.prepareStatement(
					"insert into usuario values (?, sha2(?, 0), 'C')");
			
			consulta.setString(1, c.getUsuario());
			consulta.setString(2, c.getUsuario());
			
			if (consulta.executeUpdate() == 1)
			{
				consulta = cnx.prepareStatement(
						"insert into cliente values (null, ?, ?, ?, ?, ?, false)");
				
				consulta.setString(1, c.getUsuario());
				consulta.setString(2, c.getDni());
				consulta.setString(3, c.getApellidos());
				consulta.setString(4, c.getNombre());
				consulta.setString(5, c.getTelefono());
				
				if (consulta.executeUpdate() == 1)
				{
					cnx.commit();
					return true;
				}
				else
					cnx.rollback();
			}
		} 
		catch (SQLException e)
		{
			try
			{
				cnx.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		return false;
	}

	public ArrayList<Cliente> obtenerClientes()
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try
		{
			Statement consulta = cnx.createStatement();
			
			ResultSet rs = consulta.executeQuery("select * from cliente");
			
			while (rs.next())
				clientes.add(new Cliente(rs.getString(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getBoolean(7),
										rs.getInt(1)));
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return clientes;
	}

}
