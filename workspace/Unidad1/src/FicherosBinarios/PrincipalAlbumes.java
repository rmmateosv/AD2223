package FicherosBinarios;
import java.util.ArrayList;
import java.util.Scanner;

public class PrincipalAlbumes {
	static Scanner t = new Scanner(System.in);
	static ADBinario fAlbumes = new ADBinario();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");	
			System.out.println("1-Mostrar Álbumes");	
			System.out.println("2-Crear Álbum");	
			System.out.println("3-Modificar Artista");	
			System.out.println("4-Borrar Álbum");
			System.out.println("5-Mostrar Álbum de artista");
			System.out.println("6-Desactivar Álbum");
			opcion = t.nextInt(); t.nextLine();
			switch(opcion) {
			case 1:
				mostrarAlbumes();
				break;
			}
			
		}while(opcion!=0);
		
	}

	private static void mostrarAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = fAlbumes.obtenerAlbumes();
		for(Album a:albumes) {
			a.mostrar();
		}
	}

}
