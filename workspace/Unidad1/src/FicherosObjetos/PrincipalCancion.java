package FicherosObjetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import FicherosTexto.ADTexto;
import FicherosTexto.Artista;

public class PrincipalCancion {
	static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Mostrar Canciones");
			System.out.println("2-Crear Canción");
			System.out.println("3-Modificar Álbum");
			System.out.println("4-Borrar Canción");
			System.out.println("5-Mostrar canciones de un álbum");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				
				break;
			
			}	

		} while (opcion != 0);

	}

	}
