package spotifly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				System.out.println("1-Crear Álbum");
				System.out.println("2-Crear Canción");
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearAlbum();
					break;
				case 2:
					crearCancion();
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			sf.cerrar();
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
		}
	}
	private static void crearCancion() {
		// TODO Auto-generated method stub
		mostrarAlbumes();
		System.out.println("Introduce artista:");
		String artista = t.nextLine();
		System.out.println("Introduce título:");
		String titulo = t.nextLine();
		Album al = sf.obtenerAlbum(artista, titulo);
		if(al!=null) {
			System.out.println("Introduce el título de la canción");
			String titC = t.nextLine();
			Cancion c = sf.obtenerCancion(al, new Cancion(titC,0));
			if(c==null) {
				//Añadimos la canción al álbum
				al.getCanciones().add(new Cancion(titC, 0));
				if(sf.modificarAlbum(al)) {
					System.out.println("Canción añadida");
				}
				else {
					System.out.println("Error al añadir la canción");
				}
			}
			else {
				System.out.println("Error, ya existe la canción");
			}
		}
		else {
			System.out.println("Error, álbum no existe");
		}
	}
	private static void mostrarAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = sf.obtenerAlbumes();
		for(Album al:albumes) {
			al.mostrar(false);
		}
	}
	private static void crearAlbum() {
		// TODO Auto-generated method stub
		System.out.println("Artista");
		String artista = t.nextLine();
		System.out.println("Título del álbum");
		String titulo = t.nextLine();
		
		Album a = sf.obtenerAlbum(artista, titulo);
		if(a==null) {
			System.out.println("Año del álbum");
			a = new Album(artista, titulo, t.nextInt(), new ArrayList<>());
			t.nextLine();
			if(sf.crearAlbum(a)) {
				System.out.println("Album creado");
			}
			else {
				System.out.println("Error al crear el álbum");
			}
			
		}
		else {
			System.out.println("Error, álbum ya existe");
		}
	}
	

}
