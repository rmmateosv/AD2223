package FicherosBinarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import FicherosTexto.ADTexto;
import FicherosTexto.Artista;

public class PrincipalAlbumes {
	static Scanner t = new Scanner(System.in);
	static ADBinario fAlbumes = new ADBinario();
	static ADTexto fArtistas = new ADTexto();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

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
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				mostrarAlbumes();
				break;
			case 2:
				crearAlbum();
				break;
			}

		} while (opcion != 0);

	}

	private static void crearAlbum() {
		// TODO Auto-generated method stub
		try {
			// Mostrar Artistas
			ArrayList<Artista> artistas = fArtistas.obtenerArtistas();
			for (Artista a : artistas) {
				a.mostrar();
			}

			// Pedir datos que idenfican al álbum
			System.out.println("Introduce el artista:");
			String nombreA = t.nextLine();
			// Comprobar que existe el artista
			Artista a = fArtistas.obtenerArtista(nombreA);
			if (a != null) {
				System.out.println("Título del álbum:");
				String titulo = t.nextLine();
				// Comprobar que no existe otro álbum con el mismo nombre y el
				// mismo artista
				Album al = fAlbumes.obtenerAlbum(titulo, nombreA);
				if (al == null) {
					// Creamos el objeto álbum
					al = new Album();
					al.setTitulo(titulo);
					al.setArtista(a);
					al.setActivo(true);
					al.setId(fAlbumes.obtenerUltimoId() + 1);
					System.out.println("Fecha último álbum (dd/MM/yyyy)");
					al.setFechaP(formato.parse(t.nextLine()));
					// Crear el álbum en el fichero
					if (fAlbumes.crearAlbum(al)) {
						System.out.println("Álbum " + al.getId() + " creado");
					} else {
						System.out.println("Error al crear el álbum");
					}
				} else {
					System.out.println("Error, ya hay un álbum " + "con ese título para ese artista");
				}

			} else {
				System.out.println("Error, no existe el artista");
			}
		} catch (ParseException e) {
			// TODO: handle exception
			System.out.println("Error, fecha incorrecta");
		}
	}

	private static void mostrarAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = fAlbumes.obtenerAlbumes();
		for (Album a : albumes) {
			a.mostrar();
		}
	}

}
