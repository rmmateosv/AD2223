package gimnasio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
	static SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
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
				borrarActividad();
				break;
			case 4:
				verRecibos();
				break;

			}
		} while (opcion != 0);
	}


	private static void verRecibos()
	{
		//El mejor método
		for(Recibo r : cnx.obtenerRecibos(cnx.obtenerCliente(uLogeado.getId(), "N")))
			System.out.println(r.toString());
	}

	private static void borrarActividad() {

		ArrayList<Actividad> actividades = cnx.obtenerActividades(uLogeado);

		for (Actividad actividad : actividades) {
			actividad.mostrar();

		}
		System.out.println("introduce id Actividad: ");
		int idA = sc.nextInt();
		sc.nextLine();

		if (cnx.borrarActividad(idA, uLogeado)) {

			System.out.println("usuario desapuntado con exito");

		}else {
			System.out.println("usuario no desapuntado");
		}

	}

	private static void inscribirseActividad() {
		// TODO Auto-generated method stub
		ArrayList<Actividad> actividad = cnx.obtenerActividades();
		for (Actividad a : actividad) {
			if (a.getActiva().equalsIgnoreCase("Activa")) {
				a.mostrar();
			}
		}
		System.out.println("Id actividad a inscribir: ");
		int idA = sc.nextInt();
		sc.nextLine();
		Actividad a = cnx.obtenerActividad(idA);
		if (a != null && a.getActiva().equalsIgnoreCase("Activa")) {
			ArrayList<Actividad> act = cnx.obtenerActividades(uLogeado);

			Optional<Actividad> temp = act.stream().filter(value -> value.getId() == a.getId()).findFirst();
			if (!temp.isEmpty()) {
				System.out.println("Error ya estás inscrito.");
			} else {
				if (cnx.inscribirActividad(uLogeado, a)) {
					System.out.println("Actividad registrada.");
				} else {
					System.out.println("Error al inscribirte.");
				}
			}
		} else {
			System.out.println("La actividad no se encuentra activa o no existe");
		}

	}

	private static void verActividades() {
		// TODO Auto-generated method stub
		ArrayList<Actividad> actividades = cnx.obtenerActividades(uLogeado);

		for (Actividad a : actividades) {
			a.mostrar();
		}
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
				gestionarActividades();
				break;
			case 3:
				generarRecibos();
				break;
			case 4:
				pagarRecibos();
				break;
			case 5:
				mostrarRecibos();
				break;

			}
		} while (opcion != 0);
	}

	private static void mostrarRecibos() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el año");
		int anio = sc.nextInt();
		ArrayList<Object[]> estadistica = cnx.obtenerEstadistica(anio);
		float suma = 0;
		for (Object[] o : estadistica) {
			suma += (float) o[2];
			System.out.println("Id_cliente: " + o[0] + "\t Dni: " + o[1] + "\t Facturado: " + o[2]);
		}
		System.out.println("Total: " + suma);
	}

	private static void pagarRecibos() {
		// TODO Auto-generated method stub
		try {
			mostrarClientes();
			System.out.println("Introduce id del cliente");
			int id = sc.nextInt();
			sc.nextLine();
			Cliente c = cnx.obtenerCliente(id);
			if (c != null) {
				ArrayList<Recibo> recibos = cnx.obtenerRecibos(c);
				for (Recibo r : recibos) {
					if (!r.isPagado()) {
						System.out.println(r.toString());

					}
				}
				System.out.println("Introduce fecha del recibo a pagar");
				Date fecha;

				fecha = s.parse(sc.nextLine());

				if (cnx.pagarRecibo(c, fecha)) {
					System.out.println("Recibo pagado");
				} else {
					System.out.println("Error recibo no pagado");
				}

			} else {
				System.out.println("Error cliente no existe");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error fecha incorrecta");
		}
	}

	private static void generarRecibos() {
		System.out.println("Introduce mes: ");
		int mes = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce año: ");
		int anio = sc.nextInt();
		sc.nextLine();

		if (cnx.generarRecibos(mes, anio) == 0) {
			System.out.println("Los recibos ya están generados");
		} else {
			System.out.println("Los recibos se han generado correctamente");
		}

	}

	private static void gestionarActividades() {
		// TODO Auto-generated method stub
		crearActividad();
		mostrarActividades();

	}

	private static void mostrarActividades() {
		ArrayList<Actividad> actividades = cnx.obtenerActividades();
		if (!actividades.isEmpty()) {
			for (Actividad a : actividades) {
				a.mostrar();
			}
		}

	}

	private static void crearActividad() {
		System.out.print("\nNombre: ");
		String nombre = sc.nextLine();
		Actividad a = cnx.obtenerActividad(nombre);
		if (a == null) {
			a = new Actividad();
			a.setNombre(nombre);
			System.out.print("\nCoste mensual: ");
			a.setCoste(sc.nextFloat());
			sc.nextLine();

			if (cnx.crearActividad(a)) {
				System.out.println("Actividad creada");
			} else {
				System.out.println("Error al crear");
			}
		} else {
			System.out.println("Error, actividad repetida");
		}

	}

	private static void gestionarCliente() {
		System.out.print("Introduce nick de usuario: ");
		String nick = sc.nextLine();

		if (cnx.obtenerCliente(nick, "N") == null) {
			Cliente c = new Cliente();
			c.setUsuario(nick);

			System.out.print("Introduce dni de usuario: ");
			c.setDni(sc.nextLine());

			if (cnx.obtenerCliente(c.getDni(), "D") == null) {
				System.out.print("Introduce nombre de usuario: ");
				c.setNombre(sc.nextLine());

				System.out.print("Introduce apellidos de usuario: ");
				c.setApellidos(sc.nextLine());

				System.out.print("Introduce teléfono de usuario: ");
				c.setTelefono(sc.nextLine());

				if (cnx.crearCliente(c)) {
					System.out.println("\nEl cliente se ha registrado correctamente.");
					mostrarClientes();
				} else
					System.out.println("\nNo se ha podido registrar el cliente.");
			} else
				System.out.println("\nYa existe un cliente con este DNI.");
		} else
			System.out.println("\nYa existe este usuario.");
	}

	private static void mostrarClientes() {
		for (Cliente c : cnx.obtenerClientes())
			System.out.println(c.toString());
	}

}