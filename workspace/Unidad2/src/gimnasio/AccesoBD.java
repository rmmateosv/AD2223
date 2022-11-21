package gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}
