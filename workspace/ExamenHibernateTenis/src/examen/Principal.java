package examen;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
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
		mostrarPartidos();
		System.out.println("Introduce el codigo de un partido");
		int codigo = t.nextInt(); t.nextLine();
		Partido p = ad.obtenerPartido(codigo);
		if(p!=null) {
			boolean pedirGanador = true;
			for(JugadorPartido jp :p.getJugadores()) {
				if(pedirGanador ) {
					System.out.println("Introduce si el jugador "+jp.getCjp().getJugador().getNombre()
							+" ha ganado (0-no 1-si)");
					int ganador = t.nextInt(); t.nextLine();
					if(ganador==1) {
						p.setGanador(jp.getCjp().getJugador());					
					}
					pedirGanador = false;
				}else {
					if(p.getGanador()==null) {
						p.setGanador(jp.getCjp().getJugador());
					}
					
				}
				
				jp.setResultado("");
				for(int i=1;i<=p.getNum_Set();i++) {
					System.out.println("Introduce los juegos ganados de "+jp.getCjp().getJugador().getNombre()+
							" en el set "+i);
					jp.setResultado(jp.getResultado()+ " Set "+i+": "+ t.nextInt()); t.nextLine();
				}	
			}
			if(ad.crearPartido(p)) {
				System.out.println("Se ha modificado correctamente el partido");
				System.out.println(p.toString());
			}else {
				System.out.println("Error al modificar el partido");
			}
			
		}else {
			System.out.println("El partido con el codigo "+codigo+ " no existe");
		}
		
	}

	private static void mostrarDatosPartido() {
		mostrarPartidos();
		System.out.println("Introduce el codigo de un partido");
		int codigo = t.nextInt(); t.nextLine();
		Partido p = ad.obtenerPartido(codigo);
		if(p!=null) {
			System.out.println(p.toString());
			for(JugadorPartido jp :p.getJugadores()) {
				System.out.println("Jugador: "+jp.getCjp().getJugador().getNombre()
						+"\tResultado: "+jp.getResultado());		
			}
		}else {
			System.out.println("El partido con el codigo "+codigo+ " no existe");
		}
	}

	private static void mostrarPartidos() {
		
		List<Partido> partidos = ad.obtenerPartidos();
		for(Partido p: partidos) {
			System.out.println(p.toString());
		}
	}

	private static void crearPartido() {
		mostrarJugadores();
		System.out.println("Introduce el codigo del primer jugador");
		int codigo = t.nextInt(); t.nextLine();
		Jugador j1 = ad.obtenerJugador(codigo);
		try {
			
			if(j1!=null) {			
				mostrarJugadores();
				System.out.println("Introduzca el codigo del segundo jugador");
				codigo = t.nextInt(); t.nextLine();
				Jugador j2 = ad.obtenerJugador(codigo);
				if(j2!=null && j2.getCodigo()!=j1.getCodigo()) {
					Partido p = new Partido();
					System.out.println("Introduce la fecha del partido DD-MM-YYYY");
					p.setFecha(formato.parse(t.nextLine()));
					int sets;
					do {
						System.out.println("Introduce el número de sets (3 o 5)");
						sets = t.nextInt(); t.nextLine();
						
					}while(sets!=3 && sets!=5);
					p.setNum_Set(sets);
					
					p.getJugadores().add(new JugadorPartido(new ClaveJugadorPartido(p, j1), null));
					p.getJugadores().add(new JugadorPartido(new ClaveJugadorPartido(p, j2), null));
					if(ad.crearPartido(p)) {
						System.out.println("Partido creado correctamente");
					}else {
						System.out.println("Error al crear el partido");
					}
				}else {
					System.out.println("Jugador con el código "+codigo+ " no existe o coincide con el primero");
				}
				
				
			}else {
				System.out.println("Error, no existe el jugador con el código: "+codigo);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void mostrarJugadores() {
		
		List<Jugador> jugadores = ad.obtenerJugadores();
		for(Jugador j: jugadores) {
			System.out.println(j.toString());
		}
	}
}
