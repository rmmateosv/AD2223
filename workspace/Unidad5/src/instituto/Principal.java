package instituto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Principal {
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(ad.getCnx()!=null) {
			System.out.println("conectados");
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1- Mostrar Alumnos");
				System.out.println("2- Poner nota");
				System.out.println("3- Mostrar notas alumno");
				System.out.println("4- Modificar nota de alumno");
				System.out.println("5- Modificar dirección de persona");
				opcion = sc.nextInt();
				sc.nextLine();
				switch (opcion) {
				case 1:
					mostrarAlumnos();
					break;
				case 2:
					ponerNota();
					break;
				case 3:
					mostrarNotasAlumno();
					break;
				case 4:
					modificarNota();
					break;
				case 5:
					modificarDireccion();
					break;

				}
			} while (opcion != 0);
		}
	}

	private static void modificarDireccion() {
		// TODO Auto-generated method stub
		mostrarPersonas();
	}

	private static void mostrarPersonas() {
		// TODO Auto-generated method stub
		ArrayList<Persona> personas = ad.obtenerPersonas();
		for(Persona p: personas) {
			p.mostrar();
		}
	}

	private static void modificarNota() {
		// TODO Auto-generated method stub
		try {
		mostrarAlumnos();
		System.out.println("Introduce id:");
		int id = sc.nextInt(); sc.nextLine();
		Alumno a = ad.obtenerAlumno(id);
		if(a!=null) {
			mostrarAsignaturas();
			System.out.println("Introduce asignatura");
			String asig = sc.nextLine();
			Asignatura as = ad.obtenerAsignatura(asig);
			if(as!=null) {
				Nota n = ad.obtenerNota(a, as);
				if(n!=null) {
					n.mostrar();
					System.out.println("Fecha de la nota");
					String fecha = formato.format(formato.parse(sc.nextLine()));
					//Buscar la nota a modificar
					boolean existe = false;
					for(String[] nota:n.getNotas()) {
						if(nota[0].equals(fecha)) {
							existe = true;
							System.out.println("Introduce nueva nota");
							float nuevaNota = sc.nextFloat();
							nota[1] = String.valueOf(nuevaNota);
							nota[2] = notaAtexto(nuevaNota);
							break;
						}
					}
					if(existe) {
						if(ad.modificarNota(n)) {
							System.out.println("Nota modificada");
						}
						else {
							System.out.println("Error al modificar la nota");
						}
					}
					else {
						System.out.println("No hay nota para esa fecha");
					}
				}
				else {
					System.out.println("No hay notas para el alumno en la asignatura");
				}
				
			}
			else {
				System.out.println("Error, asignatura no existe");
			}
		}
		else {
			System.out.println("Error, alumno no existe");
		}
	} catch (ParseException e) {
		System.out.println("Fecha Incorrectqa");
	}

	}

	private static void mostrarNotasAlumno() {
		// TODO Auto-generated method stub
		mostrarAlumnos();
		System.out.println("Introduce id:");
		int id = sc.nextInt(); sc.nextLine();
		ArrayList<Nota> notas= ad.obtenerNotasAlumno(id);
		if(!notas.isEmpty()) {			
			for(Nota n: notas) {
				n.mostrar();
			}
		}
		
		else {
			System.out.println("No existen notas");
		}
	}

	private static void ponerNota() {
		// TODO Auto-generated method stub
		try {
			mostrarAlumnos();
			System.out.println("Introduce id:");
			int id = sc.nextInt(); sc.nextLine();
			Alumno a = ad.obtenerAlumno(id);
			if(a!=null) {
				mostrarAsignaturas();
				System.out.println("Introduce asignatura");
				String asig = sc.nextLine();
				Asignatura as = ad.obtenerAsignatura(asig);
				if(as!=null) {
					System.out.println("Fecha (dd/mm/yyyy):");
					Date f = formato.parse(sc.nextLine());
					System.out.println("Introduce Nota:");
					float nota = sc.nextInt();sc.nextLine();
					//Comprobar si el alumnos tiene ya alguna nota
					//en esa asignatura
					//Si no tiene ninguna nota, hacemos insert
					//Si tiene alugna nota hacemos update modificando
					//el array (añadiendo un elemento al array)
					Nota n = ad.obtenerNota(a, as);
					
					//Creamos la nota a registrar					
					ArrayList<String[]> unaNota = new ArrayList();
					unaNota.add(new String[] {formato.format(f),
							String.valueOf(nota),notaAtexto(nota)});
					
					if(n==null) {
						//Insert
						n = new Nota(a, as, unaNota);
						if(ad.crearNota(n)) {
							System.out.println("Nota creada");
						}
						else {
							System.out.println("Error, al crear la nota");
						}
					}
					else {
						//Update
						if(ad.actualizarNota(n,unaNota)) {
							System.out.println("Nota actualizada");
						}
						else {
							System.out.println("Error, al actualizar la nota");
						}
					}
				}
				else {
					System.out.println("Error, asignatura no existe");
				}
			}
			else {
				System.out.println("Error, alumno no existe");
			}
		} catch (ParseException e) {
			System.out.println("Fecha Incorrectqa");
		}
		
	}

	private static String notaAtexto(float nota) {
		// TODO Auto-generated method stub
		
		if(nota<5) {
			return "INS";
		}
		if(nota<6) {
			return "SUF";
		}
		if(nota<7) {
			return "BI";
		}
		if(nota<9) {
			return "NOT";
		}
		if(nota<=10) {
			return "SOB";
		}
		
		return null;
		
	}

	private static void mostrarAsignaturas() {
		// TODO Auto-generated method stub
		ArrayList<Asignatura> asig = ad.obtenerAsignaturas();
		for(Asignatura a: asig) {
			a.mostrar();
		}
	}

	private static void mostrarAlumnos() {
		// TODO Auto-generated method stub
		ArrayList<Alumno> alumnos = ad.obtenerAlumnos();
		for(Alumno a: alumnos) {
			a.mostrar();
		}
	}

}
