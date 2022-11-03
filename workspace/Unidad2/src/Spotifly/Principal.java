package Spotifly;

import java.text.ParseException;
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
		if (sf.getConexion() != null) {
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

				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearArtista();
					break;
				}
			} while (opcion != 0);
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
		}
	}

	private static void crearArtista() {
		// TODO Auto-generated method stub
		try {
			// Pedir nombre
			System.out.println("Nombre Artístico:");
			String nombre = t.nextLine();

			// Comprobar si existe
			Artista a = sf.obtenerArtista(nombre);
			if (a == null) {
				a = new Artista();
				a.setNombre(nombre);
				// Pedir género
				System.out.println("Género:1-POP, 2-ROCK, X-OTROS");
				String genero = t.nextLine();
				switch (genero.trim()) {
				case "1":
					a.setGenero("POP");
					break;
				case "2":
					a.setGenero("ROCK");
					break;
				default:
					a.setGenero("OTROS");
					break;
				}
				// Fecha de lanzamiento
				System.out.println("Fecha Lanzamiento (dd/MM/yyyy):");
				a.setFechaL(formato.parse(t.nextLine()));
				
				if(sf.crearArtista(a)) {
					System.out.println("Artista Creado con éxito");
				}
				else {
					System.out.println("Error al crear el artista");
				}
			} else {
				System.out.println("Error, ya existe el artista");
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Error, fecha incorrecta");
		}
	}

}
