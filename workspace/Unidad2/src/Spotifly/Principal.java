package Spotifly;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
				System.out.println("9-Valorar canción");
				System.out.println("10-Borrar canción");

				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					crearArtista();
					break;
				case 2:
					mostrarArtistas();
					break;
				case 3:
					crearAlbum();
					break;
				case 4:
					mostrarAlbumes();
					break;
				case 5:
					crearCancion();
					break;
				case 8:
					infoArtista();
					break;
				}
			} while (opcion != 0);
			//Cerrar conexión
			sf.cerrar();
		} else {
			System.out.println("Error, no hay conexión con SpotiFly");
		}
	}

	private static void infoArtista() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Id:");
		int id = t.nextInt();t.nextLine();
		Artista a = sf.obtenerArtista(id);
		if(a!=null) {
			ArrayList<Object[]> datosArtista = sf.infoArtista(id);
			for(Object[] o:datosArtista) {
				System.out.println("Título:"+ o[0] + 
						"\tNºCanciones:" + o[1] + 
						"\tValoración Media:" + o[2]);
			}
		}
		else {
			System.out.println("Error, artista no existe");
		}
	}

	private static void crearCancion() {
		// TODO Auto-generated method stub
		Cancion c = new Cancion();
		System.out.println("Título");
		c.setTitulo(t.nextLine());				
		c.setValoracion(0);

		mostrarAlbumes();
		System.out.println("Id de Álbum(0->Single)");
		int album = t.nextInt();t.nextLine();
		if(album==0) {
			// Se crea canción y álbum
			Album al = new Album();
			c.setAlbum(al);
			al.setTitulo(c.getTitulo());
			//Pedir artista
			mostrarArtistas();
			System.out.println("Introduce id de artista");
			int idA = t.nextInt();t.nextLine();
			Artista a = sf.obtenerArtista(idA);
			if(a!=null) {
				al.setArtista(a);
				//Pedir año
				System.out.println("Año:");
				al.setAnio(t.nextInt());t.nextLine();
				if(sf.crearCancionYAlbum(c)) {
					System.out.println("Album y canción creados");
				}
				else {
					System.out.println("No se han creado ni el álbum ni la canción");
				}
			}
			else {
				System.out.println("Error, artista no existe");
			}
			
		}
		else {
			//La canción se va a crear en un album existente
			Album al = sf.obtenerAlbum(album);
			if(al!=null) {				
				c.setAlbum(al);				
				if(sf.crearCancion(c)) {
					System.out.println("Canción creada");
				}
				else {
					System.out.println("Error al crear la canción");
				}
			}
			else {
				System.out.println("Error, el álbum no existe");
			}
		}
	}

	private static void mostrarAlbumes() {
		// TODO Auto-generated method stub
		ArrayList<Album> albumes = sf.obtenerAlbumes();
		for(Album al: albumes) {
			al.mostrar();
		}
	}

	private static void crearAlbum() {
		// TODO Auto-generated method stub
		mostrarArtistas();
		System.out.println("Introduce el id del artista:");
		int id = t.nextInt();t.nextLine();
		Artista a = sf.obtenerArtista(id);
		if(a!=null) {
			System.out.println("Título Álbum");
			String titulo = t.nextLine();
			Album al = sf.obtenerAlbum(a,titulo);
			if(al==null) {
				al = new Album();
				al.setArtista(a);
				al.setTitulo(titulo);
				System.out.println("Año");
				al.setAnio(t.nextInt()); t.nextLine();
				if(sf.crearAlbum(al)) {
					System.out.println("Álbum "+al.getId() + " creado");
				}
				else {
					System.out.println("Error al crear el álbum");
				}
			}
			else {
				System.out.println("Error, el artista ya tiene un álbum con ese título");
			}
		}
		else {
			System.out.println("Error, artista no existe");
		}
	}

	private static void mostrarArtistas() {
		// TODO Auto-generated method stub
		ArrayList<Artista> artistas = sf.obtenerArtistas();
		for(Artista a: artistas) {
			a.mostrar();
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