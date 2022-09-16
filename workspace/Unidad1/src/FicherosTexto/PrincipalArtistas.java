package FicherosTexto;

import java.util.Scanner;

public class PrincipalArtistas {
	static Scanner t = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");	
			System.out.println("1-Crear Artista");
			System.out.println("2-Mostrar Artistas");
			System.out.println("3-Modificar Artista");
			System.out.println("4-Borrar Artista");
			System.out.println("5-Mostrar algunos Artista");
			opcion = t.nextInt(); t.nextLine();
			switch(opcion) {
			case '1':
				
				break;
			}
		}while(opcion!=0);
	}
		

}
