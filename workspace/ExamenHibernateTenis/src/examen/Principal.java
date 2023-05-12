package examen;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			// TODO Auto-generated method stub
			if (ad.getEm() != null) {
				int opcion;
				do {
					System.out.println("Introduce opción:");
					System.out.println("0-Salir");
					System.out.println("1-Crear Partido");
					System.out.println("2-Mostrar Datos de Partido");
					System.out.println("3-Registrar Datos de Partido");
					System.out.println("4-Borrar Partido");
	
					opcion = t.nextInt();
					t.nextLine();
					switch (opcion) {
					case 1:
						crearPartido();
						break;
					case 2:
						mostrarDatosPartido();
						break;
					case 3:
						registrarDatosPartidos();
						break;
					case 4:
						borrarPartidos();
						break;
					
					}
				} while (opcion != 0);
				//Cerrar conexión
				ad.cerrar();
			} else {
				System.out.println("Error, no hay conexión con Clínica");
			}
	}

	private static void borrarPartidos() {
		// TODO Auto-generated method stub
		
	}

	private static void registrarDatosPartidos() {
		// TODO Auto-generated method stub
		
	}

	private static void mostrarDatosPartido() {
		// TODO Auto-generated method stub
		
	}

	private static void crearPartido() {
		mostrarJugadores();
		System.out.println("Introduce el codigo del primer jugador");
		int codigo = t.nextInt(); t.nextLine();
		Jugador j1 = ad.obtenerJugador(codigo);
		if(j1!=null) {
			
			mostrarJugadores();
			System.out.println("Introduzca el codigo del segundo jugador");
			
			
		}else {
			System.out.println("Error, no existe el jugador con el código: "+codigo);
		}
	}

	private static void mostrarJugadores() {
		
		List<Jugador> jugadores = ad.obtenerJugadores();
		for(Jugador j: jugadores) {
			System.out.println(j.toString());
		}
	}
}
