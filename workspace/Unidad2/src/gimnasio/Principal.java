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
				crearUsuarios();
				break;
			case 2:
				borrarUsuario();
				break;
			case 3:
				mostrarUsuarios();
				break;

			}
		} while (opcion != 0);
	}
}