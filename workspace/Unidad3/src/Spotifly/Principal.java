package Spotifly;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos sf = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (sf.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Crear Artista");
				System.out.println("2-Mostrar Artistas");				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearArtista();
					break;
				case 2:
					mostrarArtistas();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			sf.cerrar();
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
		}
	}
	private static void mostrarArtistas() {
		// TODO Auto-generated method stub
		
	}
	private static void crearArtista() {
		// TODO Auto-generated method stub
		
	}

}
