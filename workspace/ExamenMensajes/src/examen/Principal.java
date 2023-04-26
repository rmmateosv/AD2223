package examen;

import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.xdevapi.DatabaseObject.DbObjectType;

public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static int usuario;

	public static void main(String[] args) {

		if (ad.getCnx() != null) {
			System.out.println("Introduce el usuario: ");
			usuario = t.nextInt();
			t.nextLine();

			System.out.println("Introduce la contraseña: ");
			String clave = t.nextLine();

			// comprobar si existe un empleado con ese usuario y contraseña
			int retorno = ad.comprobarUsuario(usuario, clave);

			switch (retorno) {
			case 0:
				System.out.println("Error, datos incorrectos");
				break;
			case 1:
				menu();

				break;
			case 2:
				System.out.println("Cambiar contraseña");
				System.out.println("Introduce una nueva contraseña");
				clave = t.nextLine();

				if (ad.modificarClave(clave, usuario)) {
					menu();
				} else {
					System.out.println("Se ha producido un error al modificar la contraseña");
				}
				break;
			}

		} else {
			System.out.println("Error al conectar con la base de datos.");
		}

	}

	private static void menu() {

		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1- Enviar mensajes");
			System.out.println("2- Leer mensajes");
			System.out.println("3- Estadística mensajes");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				Enviar();
				break;
			case 2:
				Leer();
				break;
			case 3:
				Estadistica();
				break;

			}
		} while (opcion != 0);
	}

	private static void Estadistica() {

	}

	private static void Leer() {

		ArrayList<Mensaje> mensajes = ad.obtenerMensajes(usuario);

		if (!mensajes.isEmpty()) {

			for (Mensaje m : mensajes) {
				System.out.println(m.toString());
			}
			if (ad.marcarLeidos(usuario)) {
				System.out.println("Se han marcado todos los mensajes ");

			}
			ArrayList<Object[]> mensajesCompletos = ad.obtenerMensajesCompleto(usuario);

			for (Object[] m : mensajesCompletos) {
				System.out.println("id: "+m[0]+"\tdeEmpleado: "+m[1]+"\tDepartamento: "+
			m[2]+"\tAsunto: "+m[3]+"\tFecha: "+m[4]+"\tMensaje: "+m[5]);
			}
		}

	}

	private static void Enviar() {

		mostrarDepartamentos();

		System.out.println("Introduce el departamento: ");
		int departamento = t.nextInt();
		t.nextLine();

		Departamento d = ad.obtenerDepartamento(departamento);
		if (d != null) {
			System.out.println("Introduce el asunto del mensaje: ");
			String asunto = t.nextLine();

			System.out.println("Introduce el texto del mensaje: ");
			String texto = t.nextLine();

			// Obtener los empleados del departamento d
			ArrayList<Empleado> empleados = ad.obtenerEmpleados(d);

			int numMensaje = ad.crearMensaje(usuario, d, empleados, asunto, texto);

			if (numMensaje == -1) {
				System.out.println("Se ha producido un error al crear mensaje");
			} else {
				System.out.println("Mensaje creado: " + numMensaje);
				System.out.println("Mensaje enviado a :");
				for (Empleado e : empleados) {
					System.out.println(e.toString());
				}
			}

		} else {
			System.out.println("El departamento no existe");
		}

	}

	private static void mostrarDepartamentos() {

		ArrayList<Departamento> d = ad.obtenerDepartamentos();

		for (Departamento depar : d) {
			depar.mostrar();
		}

	}

}
