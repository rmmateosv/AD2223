package examenMongoMensajes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {

	static Empleado usuario;
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();

	public static void main(String[] args) {

		if (ad.getCnx() != null) {
			menu();
		} else {
			System.out.println("Error al conectar con la base de datos.");
		}

	}

	private static void menu() {

		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1- Crear empleado");
			System.out.println("2- Enviar mensajes");
			System.out.println("3- Leer mensajes y borrar");
			System.out.println("4- Estadisticas mensaje");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				CrearEmpleado();
				break;
			case 2:EnviarMensaje();
				break;
			case 3:leerBorrarMensajes();
				break;
			case 4:
				break;

			}
		} while (opcion != 0);
	}

	private static void leerBorrarMensajes() {
		// TODO Auto-generated method stub
		if(IndentificarEmpleado()){
			ArrayList<Object[]>recibidos=ad.obtenerMensajes(usuario);
			if(recibidos.isEmpty()) {
				System.out.println("No hay mensajes");
			}else {
				for(Object []o:recibidos) {
					System.out.println("De:"+o[0]+
					"\t Asunto: "+o[1]+
					"\t Mensaje:"+o[2]);
				}
				if(ad.borrarMensajes(usuario)) {
					System.out.println("Mensajes borrados correctamente");
				}else {
					System.out.println("Error al borrar los mensajes de "+usuario);
				}
			}
			
		}
		
	}

	private static void EnviarMensaje() {
		// TODO Auto-generated method stub
		if(IndentificarEmpleado()){
			System.out.println("Introduzca el departamento al cual se envia el mensaje");
			String departamento= t.nextLine();
			ArrayList<Empleado> empleados=ad.obtenerEmpleados(departamento);
			if(empleados.isEmpty()) {
				System.out.println("No hay empleados en el departamento ");
				
			}
			else {
				//Crear los destinatarios del mensaje
				ArrayList<Destinatario> destinatarios=new ArrayList();
				for(Empleado e :empleados) {
					destinatarios.add(new Destinatario(e.getDni(), true));
					
				}
				Mensaje m= new Mensaje();
				m.setDestinatarios(destinatarios);
				System.out.println("Indique el asunto");
				m.setAsunto(t.nextLine());
				System.out.println("Escribe el mensaje");
				m.setMensaje(t.nextLine());
				m.setFecha(new Date());
				m.setCodigo(ad.obtenerCodigo());
				m.setDeEmpleado(usuario.getDni());
				m.setParaDepartamento(departamento);
				if(ad.crearMensaje(m)) {
					System.out.println("Mensaje enviado");
				}
			}
			
		}
		
	}

	private static void CrearEmpleado() {
		// TODO Auto-generated method stub
		System.out.println("Introduce DNI: ");
		String dni = t.nextLine();

		Empleado e = ad.obtenerEmpleado(dni);
		if (e == null) {
			e = new Empleado();
			e.setDni(dni);
			System.out.println("Nombre");
			e.setNombre(t.nextLine());
			System.out.println("Departamento");
			e.setDepartamento(t.nextLine());
			if (ad.crearEmpleado(e)) {
				System.out.println("Empleado creado");
			} else {
				System.out.println("Error al crear empleado");
			}
		} else {
			System.out.println("Error ya existe el empleado");
		}
	}

	private static boolean IndentificarEmpleado() {

		boolean resultado = false;
		if (usuario == null) {
			System.out.println("Introduce dni: ");

			usuario = ad.obtenerEmpleado(t.nextLine());
			if (usuario != null) {
				System.out.println("Usuario identificado");
				resultado = true;
			}

		} else {
			resultado = true;
		}

		return resultado;
	}

}
