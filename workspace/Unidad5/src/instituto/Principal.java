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
				

				}
			} while (opcion != 0);
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
					Nota n = ad.obtenerNota(a, asig);
					if(n==null) {
						//Insert
					}
					else {
						//Update
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
