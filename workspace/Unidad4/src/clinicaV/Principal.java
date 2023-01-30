package clinicaV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Cliente");
				System.out.println("2-Mostrar Clientes");
				System.out.println("3-Modificar Cliente");
				System.out.println("4-Borrar Cliente");
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearCliente();
					break;
				case 2:
					mostrarClientes();
					break;
				case 3:
					modificarCliente();
					break;
				case 4:
					borrarCliente();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}
	private static void borrarCliente() {
		// TODO Auto-generated method stub
		mostrarClientes();
		System.out.println("Código");
		int codigo = t.nextInt();t.nextLine();		
		Cliente c = ad.obtenerCliente(codigo);
		if(c!=null) {			
			if(ad.borrarCliente(c)) {
				System.out.println("Cliente borrad");
			}
			else {
				System.out.println("Error al borrar el cliente");
			}
		}
		else {
			System.out.println("Error, cliente no existe");
		}
	}
	private static void modificarCliente() {
		// TODO Auto-generated method stub
		mostrarClientes();
		System.out.println("Código");
		int codigo = t.nextInt();t.nextLine();		
		Cliente c = ad.obtenerCliente(codigo);
		if(c!=null) {
			System.out.println("Nuevo Nombre:");
			c.setNombre(t.nextLine());
			System.out.println("Nuevo teléfono:");
			c.setTelefono(t.nextLine());
			if(ad.modificarCliente(c)) {
				System.out.println("Cliente modificado");
			}
			else {
				System.out.println("Error al modificar el cliente");
			}
		}
		else {
			System.out.println("Error, cliente no existe");
		}
	}
	private static void mostrarClientes() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = ad.obtenerClientes();
		for(Cliente c:clientes) {
			c.mostrar(true);
		}
	}
	private static void crearCliente() {
		// TODO Auto-generated method stub
		System.out.println("Introduce email:");
		String email = t.nextLine();
		Cliente c = ad.obtenerCliente(email);
		if(c==null) {
			c =new Cliente();
			c.setEmail(email);
			System.out.println("Nombre:");
			c.setNombre(t.nextLine());
			System.out.println("Teléfono:");
			c.setTelefono(t.nextLine());
			if(ad.crearCliente(c)) {
				System.out.println("Cliente creado con código:" + c.getCodigo());
			}
			else {
				System.out.println("Error al crear el cliente");
			}
		}
		else {
			System.out.println("Error, cliente no existe");
		}
	}
	
}
