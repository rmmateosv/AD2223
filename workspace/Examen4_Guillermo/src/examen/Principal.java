package examen;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Principal {
	
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {

		if (ad.getEm() != null) {

			int opcion;

			do {
				System.out.println("MENÚ");
				System.out.println("0 - Salir");
				System.out.println("1 - Crear Partido");
				System.out.println("2 - Mostrar Datos Partido");
				System.out.println("3 - Registrar Datos Partido");
				System.out.println("4 - Borrar Partido");
				System.out.print("Introduce una opción: ");
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
					registrarDatosPartido();
					break;

				case 4:
					borrarPartido();
					break;

				}

			} while (opcion != 0);

			// Cerrar conexión

			ad.cerrar();

		} else {
			System.out.println("Error, no hay conexión con la base de datos");
		}
	}

	private static void borrarPartido() {
		mostrarPartidos();
		
		System.out.print("Introduce el código del partido: ");
		int codP = t.nextInt(); t.nextLine();
		
		Partido p = ad.obtenerPartido(codP);
		
		if(p != null) {
			
			int borrados = ad.borrarPartido(p);
			
			if(borrados != -1) {
				
				System.out.println("Partido borrado. Se han borrado " + borrados + " elementos en la tabla jugador_partido");
				
			} else {
				
				System.out.println("Error al intentar borrar el partido.");
				
			}
			
			
		} else {
			
			System.out.println("Error, no existe ningún partido con ese código.");
			
		}
	}

	private static void registrarDatosPartido() {
		mostrarPartidos();
		
		System.out.print("Introduce el código del partido: ");
		int codP = t.nextInt(); t.nextLine();
		
		Partido p = ad.obtenerPartido(codP);
		
		if(p != null) {
			
			for(Jugador_Partido jp: p.getJugadorespartido()) {
				System.out.println("\nRESULTADOS JUGADOR: "+jp.getClave().getJugador().getNombre());
				
				System.out.print("¿Es el ganador?(true/false): ");
				if(Boolean.parseBoolean(t.nextLine())) {
					jp.getClave().getPartido().setGanador(jp.getClave().getJugador());
				}
				
				jp.setResultado("");
				for(int i = 1; i <= jp.getClave().getPartido().getNumSets(); i++) {
					System.out.print("Juegos ganados en el set " + i + ": ");
					int gan = t.nextInt(); t.nextLine();
					jp.setResultado(jp.getResultado() + "Set " + i + ": " + gan +
							(i == jp.getClave().getPartido().getNumSets() ? "" : " "));
				}
				
			}
			
			if(ad.registrarResultadoPartido(p)) {
				
				System.out.println("Datos agregados.");
				
			} else {
				
				System.out.println("Error al intentar agregar los resultados del partido.");
				
			}
			
			
		} else {
			
			System.out.println("Error, no existe ningún partido con ese código.");
			
		}
	}

	private static void mostrarDatosPartido() {
		mostrarPartidos();
		
		System.out.print("Introduce el código del partido: ");
		int codP = t.nextInt(); t.nextLine();
		
		Partido p = ad.obtenerPartido(codP);
		
		if(p != null) {
			
			System.out.println("\nPartido: " + p.getCodigo() + 
					"\tFecha: " + formato.format(p.getFecha()) + 
					"\tNumero de sets: " + p.getNumSets() + 
					"\tGanador: " + (p.getGanador() == null ? "No hay ganador" : p.getGanador().getNombre()));
			System.out.println("--------------------------------------------------\n");
			for(Jugador_Partido jp: p.getJugadorespartido()) {
				System.out.println("Jugador: " + jp.getClave().getJugador().getNombre() + 
						"\tResultado: " + (jp.getResultado() == null ? "No hay resultado" : jp.getResultado()));
			}
			System.out.println("--------------------------------------------------\n");
			
		} else {
			
			System.out.println("Error, no existe ningún partido con ese código.");
			
		}
	}

	private static void mostrarPartidos() {
		List<Partido> partidos = ad.obtenerPartidos();
		for(Partido p: partidos) {
			p.mostrar();
		}
	}

	private static void crearPartido() {
		mostrarJugadores();
		
		System.out.print("Introduce el código del primer jugador: ");
		int codJ1 = t.nextInt(); t.nextLine();
		
		Jugador j1 = ad.obtenerJugador(codJ1);
		
		if(j1 != null) {
			
			mostrarJugadores();
			
			System.out.print("Introduce el código del segundo jugador: ");
			int codJ2 = t.nextInt(); t.nextLine();
			
			Jugador j2 = ad.obtenerJugador(codJ2);
			
			if(j2 != null && !j2.equals(j1)) {
				
				try {
					
					Partido p = new Partido();
					
					System.out.print("Introduzca la fecha del partido(dd/MM/yyyy): ");
					p.setFecha(formato.parse(t.nextLine()));
					
					
					System.out.print("Introduzca el nº de sets(3/5): ");
					int sets = t.nextInt(); t.nextLine();
					
					if(sets != 3 && sets != 5)
						sets = 3;
						
					p.setNumSets(sets);
					
					p.setGanador(null);
					p.getJugadorespartido().add(new Jugador_Partido(new ClaveJP(p, j1), null));
					p.getJugadorespartido().add(new Jugador_Partido(new ClaveJP(p, j2), null));
					
					if(ad.crearPartido(p)) {
						
						System.out.println("Partido creado con código " + p.getCodigo());
						
					} else {
						
						System.out.println("Error al intentar crear el partido.");
						
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			} else {
				
				System.out.println("Error, no existe ningún jugador con ese código o ha introducido el mismo jugador dos veces.");
				
			}
			
		} else {
			
			System.out.println("Error, no existe ningún jugador con ese código.");
			
		}
	}

	private static void mostrarJugadores() {
		List<Jugador> jugadores = ad.obtenerJugadores();
		for(Jugador j: jugadores) {
			j.mostrar();
		}
	}
	
	
}
