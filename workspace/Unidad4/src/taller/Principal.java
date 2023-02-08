package taller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Reparación");
				
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearReparacion();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}


	private static void crearReparacion() {
		// TODO Auto-generated method stub
		mostrarVehiculos();
		System.out.println("Codigo Vehículo:");
		int codigo  = t.nextInt();t.nextLine();
		Vehiculo v = ad.obtenerVehiculo(codigo);
		if(v!=null) {
			Reparacion r = new Reparacion();
			r.setVehiculo(v);
			r.setFecha(new Date());			
			if(ad.crearReparacion(r)) {
				System.out.println("Reparacion creada con código "+r.getId());
			}
			else {
				System.out.println("Error al crear reparación");
			}
		}
		else {
			System.out.println("Error, vehículo no existe");
		}
	}


	private static void mostrarVehiculos() {
		// TODO Auto-generated method stub
		List<Vehiculo> coches = ad.obtenerVehiculos();
		for(Vehiculo v: coches) {
			v.mostrar(true);
		}
	}
	
}
