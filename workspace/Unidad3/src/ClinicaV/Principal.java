package ClinicaV;

import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Cliente");
				System.out.println("2-Crear Mascota");
				System.out.println("3-Crear Tratamiento");
				System.out.println("4-Mostrar tratamiento");
				System.out.println("5-Borrar tratamiento");
				System.out.println("6-Modificar tratamiento");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearCliente();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
		}
	}
	private static void crearCliente() {
		// TODO Auto-generated method stub
		System.out.println("Email:");
		String email = t.nextLine();				
		Cliente c = ad.obtenerCliente(email);
		if(c==null) {
			c = new Cliente();
			c.setEmail(email);
			System.out.println("Nombre");
			c.setNombre(t.nextLine());
			c.setCodigo(ad.obtenerCodigoCliente());
			if(ad.crearCliente(c)) {
				System.out.println("Cliente creado con código:"+c.getCodigo());
			}
			else {
				System.out.println("Error al crear el cliente");
			}
		}
		else {
			System.out.println("Error, el cliente ya existe");
		}
		
	}
}
