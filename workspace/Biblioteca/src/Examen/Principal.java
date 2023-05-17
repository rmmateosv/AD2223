package Examen;

import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			// TODO Auto-generated method stub
			if (ad.getEm() != null) {
				int opcion;
				do {
					System.out.println("Introduce opción:");
					System.out.println("0-Salir");
					System.out.println("1-Crear Prestamo");
					System.out.println("2-Mostrar prestamos de un socio");
					System.out.println("3-Devolver un prestamo");
					System.out.println("4-Borrar libro");
	
					opcion = t.nextInt();
					t.nextLine();
					switch (opcion) {
					case 1:
						crearPrestamo();
						break;
					case 2:
						
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					
					}
				} while (opcion != 0);
				//Cerrar conexión
				ad.cerrar();
			} else {
				System.out.println("Error, no hay conexión con biblioteca");
			}
	}
	private static void crearPrestamo() {
		
		
	}
}
