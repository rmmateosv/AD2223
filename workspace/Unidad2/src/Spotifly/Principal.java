package Spotifly;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Principal {

	static Scanner t = new Scanner(System.in);
	//Definimos el formato con el que vamos 
	//a pintar/pedir fechas
	static SimpleDateFormat formato = 
			new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Crear Artista");
			System.out.println("2-Mostrar Artistas");
			System.out.println("3-Crear Álbum");
			System.out.println("4-Mostrar Álbum");
			System.out.println("5-Crear Canción (¡Single!)");
			System.out.println("6-Mostrar Canciones");
			System.out.println("7-Dejar de seguir artista");
			System.out.println("8-Info Artista");
			System.out.println("9-Borrar canción");

			opcion = t.nextInt(); t.nextLine();
			switch(opcion) {
			case 1:
				
				break;
			}
		}while(opcion!=0);
	}

}
