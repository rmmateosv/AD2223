package taller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
				System.out.println("2-Añadir Pieza a Reparación");
				
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearReparacion();
					break;
				case 2:
					crearPiezaReparacion();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}


	private static void crearPiezaReparacion() {
		// TODO Auto-generated method stub
		mostrarReparaciones();
		System.out.println("Introduce id de reparación");
		int id = t.nextInt(); t.nextLine();
		Reparacion r = ad.obtenerReparacion(id);
		if(r!=null && !r.isPagado()) {
			mostrarPiezas();
			System.out.println("Introudce código pieza:");
			String codigo = t.nextLine();
			Pieza p = ad.obtenerPieza(codigo);
			if(p!=null && p.getStock()>0) {
				System.out.println("Introduce cantidad");
				int cantidad = t.nextInt();t.nextLine();
				if(p.getStock()>=cantidad) {
					//Ver si ya se ha metido la pieza
					PiezaReparacion pr = ad.obtenerPR(new clavePR(r,p));
					if(pr==null) {
						pr = new PiezaReparacion(
								new clavePR(r, p), 
								cantidad* p.getPrecio(), cantidad);
						p.setStock(p.getStock()-cantidad);
					}
					else {
						p.setStock(p.getStock()+pr.getCantidad()-cantidad);						
						pr.setCantidad(cantidad);
						pr.setImporte(cantidad*p.getPrecio());						
					}
					if(ad.guardarPR(pr)) {
						System.out.println("Pieza guarda");
					}
					else {
						System.out.println("Error, al guardar la pieza en la reparación");
					}
				}
				else {
					System.out.println("Error no hay stock suficiente");
				}
			}
			else {
				System.out.println("No existe pieza o no hay stock");
			}
		}
		else {
			System.out.println("Error, no existe la reparación o está pagada");
		}
	}


	private static void mostrarPiezas() {
		// TODO Auto-generated method stub
		List<Pieza> piezas = ad.obtenerPiezas();
		for(Pieza p:piezas) {
			p.mostrar();
		}
	}


	private static void mostrarReparaciones() {
		// TODO Auto-generated method stub
		List<Reparacion> reparaciones = ad.obtenerReparaciones();
		for(Reparacion r:reparaciones) {
			r.mostrar(false);
		}
	}


	private static void crearReparacion() {
		// TODO Auto-generated method stub
		mostrarVehiculos();
		System.out.println("Codigo Vehículo:");
		int codigo  = t.nextInt();t.nextLine();
		Vehiculo v = ad.obtenerVehiculo(codigo);
		if(v!=null) {
			boolean abierta = false;
			//Vamos a chequear si el vehículo tiene alguna reparación no pagada
			for(Reparacion r:v.getReparaciones()) {
				if(!r.isPagado()) {
					System.out.println("Error, el vehículo tiene una reparación abierta: "+r.getId());
					abierta = true;
					break;
				}
			}
			if(!abierta) {
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
