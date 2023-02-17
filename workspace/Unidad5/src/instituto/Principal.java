package instituto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
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
				

				}
			} while (opcion != 0);
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
