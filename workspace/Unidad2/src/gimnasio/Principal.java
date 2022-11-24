package gimnasio;

import java.util.Scanner;

public class Principal {

	static Scanner sc = new Scanner(System.in);
	static AccesoBD cnx = new AccesoBD();
	static Usuario uLogeado = null;

	public static void main(String[] args) {
		if (cnx.getCnx() != null) {
			System.out.print("Usuario: ");
			String usuario = sc.nextLine();
			System.out.print("Contraseña: ");
			String pwd = sc.nextLine();

			uLogeado = cnx.obtenerUsuario(usuario, pwd);

			if (uLogeado != null) {
				if (uLogeado.getTipo().equalsIgnoreCase("A")) {
					menuAdmin();
				} else {
					menuUsuario();
				}
			} else {
				System.err.print("\nError, usuario incorrecto");
			}
		} else {
			System.err.print("\nError, no se puede conectar con la base de datos");
		}
	}

	private static void menuUsuario() {
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1- Ver actividades");
			System.out.println("2- Inscribirse en actividad");
			System.out.println("3- Borrarse de actividad");
			System.out.println("4- Ver recibos");
			opcion = sc.nextInt();
			sc.nextLine();
			switch (opcion) {
			case 1:
				verActividades();
				break;
			case 2:
				inscribirseActividad();
				break;
			case 3:
				borrarseActividad();
				break;
			case 4:
				verRecibos();
				break;

			}
		} while (opcion != 0);
	}

	private static void verRecibos() {
		// TODO Auto-generated method stub
		
	}

	private static void borrarseActividad() {
		// TODO Auto-generated method stub
		
	}

	private static void inscribirseActividad() {
		// TODO Auto-generated method stub
		
	}

	private static void verActividades() {
		// TODO Auto-generated method stub
		
	}

	private static void menuAdmin() {
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1- Gestionar clientes");
			System.out.println("2- Gestionar actividades");
			System.out.println("3- Generar recibos");
			System.out.println("4- Pagar recibos");
			System.out.println("5- Mostrar recibos");
			opcion = sc.nextInt();
			sc.nextLine();
			switch (opcion) {
			case 1:
				gestionarCliente();
				break;
			case 2:
				
				break;
			case 3:
				
				break;

			}
		} while (opcion != 0);
	}

	private static void gestionarCliente()
	{
		System.out.print("Introduce nick de usuario: ");
		String nick = sc.nextLine();

		if (cnx.obtenerCliente(nick, "N") == null)
		{
			Cliente c = new Cliente();
			c.setUsuario(nick);
			
			System.out.print("Introduce dni de usuario: ");
			c.setDni(sc.nextLine());
			
			if (cnx.obtenerCliente(c.getDni(), "D") == null)
			{
				System.out.print("Introduce nombre de usuario: ");
				c.setNombre(sc.nextLine());
				
				System.out.print("Introduce apellidos de usuario: ");
				c.setApellidos(sc.nextLine());
				
				System.out.print("Introduce teléfono de usuario: ");
				c.setTelefono(sc.nextLine());
				
				if (cnx.crearCliente(c))
				{
					System.out.println("\nEl cliente se ha registrado correctamente.");
					mostrarClientes();
				}
				else
					System.out.println("\nNo se ha podido registrar el cliente.");
			}
			else
				System.out.println("\nYa existe un cliente con este DNI.");
		}
		else 
			System.out.println("\nYa existe este usuario.");
	}

	private static void mostrarClientes()
	{
		for (Cliente c : cnx.obtenerClientes())
			System.out.println(c.toString());
	}

	
}