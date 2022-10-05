package FicherosObjetos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import FicherosBinarios.ADBinario;
import FicherosBinarios.Album;
import FicherosTexto.ADTexto;
import FicherosTexto.Artista;

public class PrincipalCancion {
	static Scanner t = new Scanner(System.in);
	static ADObjetos fCanciones = new ADObjetos();
	static ADBinario fAlbumes = new ADBinario();
	
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
				mostrarCanciones();
				break;
			case 2:
				crearCancion();
				break;
			}	

		} while (opcion != 0);

	}

	private static void crearCancion() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = fAlbumes.obtenerAlbumes();
		for(Album a:albumes) {
			a.mostrar();
		}
		System.out.println("Introduce Álbum:");
		int id = t.nextInt(); t.nextLine();
		Album al = fAlbumes.obtenerAlbum(id);
		if(al!=null) {
			System.out.println("Introduce título");
			String titulo = t.nextLine();
			//Comprobar que no hay otra canción con el mismo título y album
			if(fCanciones.obtenerCancion(titulo,al)==null) {
				Cancion c = new Cancion(
						fCanciones.obtenerUltimoID()+1,titulo,al);
				if(fCanciones.crearCancion(c)) {
					System.out.println("Canción creada");
				}
				else {
					System.out.println("Error, al crear canción");
				}
			}
			else {
				System.out.println("Error, ya existe");
			}
		}
		else {
			System.out.println("Error, no existe el álbum");
		}
	}

	private static void mostrarCanciones() {
		// TODO Auto-generated method stub
		ArrayList<Cancion> canciones = fCanciones.obtenerCanciones();
		for(Cancion c: canciones) {
			c.mostrar();
		}
	}

	}
