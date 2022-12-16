package Spotifly;

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
				System.out.println("1-Crear Artista");
				System.out.println("2-Mostrar Artistas");
				System.out.println("3-Mostrar Nombres Artistas por genero");
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
		ArrayList<Artista> artistas =  sf.obtenerArtistas();
		for(Artista a:artistas) {
			a.mostrar();
		}
	}
	private static void crearArtista() {
		// TODO Auto-generated method stub
		
		System.out.println("Nombre Artístico:");		
		String nombre = t.nextLine();
		
		Artista a = sf.obtenerArtista(nombre);
		if(a==null) {			
			a= new Artista();
			a.setNombre(nombre);
			a.setFechaC(new Date());
			a.setSeguir(true);
			//Pedir géneros
			String g;
			do {
				System.out.println("Introduce Género");
				g=t.nextLine();
				if(!a.getGenero().contains(g)) {
					if(!g.equals("0")){
						a.getGenero().add(g);
					}
				}
				else {
					System.out.println("Ya existe el género");
				}
			}while(!g.equals("0"));
			if(sf.crearArtista(a)) {
				System.out.println("Artista creado");
			}
			else {
				System.out.println("Error al crear el artista");
			}
		}
		else {
			System.out.println("Error, ya existe el artista");
		}
	}

}
