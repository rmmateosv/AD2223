package examenMongoMensajes;

import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.xdevapi.DatabaseObject.DbObjectType;

public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static int usuario;

	public static void main(String[] args) {
		
		if (ad.getCnx() != null) {
			menu();
		} else {
			System.out.println("Error al conectar con la base de datos.");
		}

	}

	private static void menu() {

		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1- Crear empleado");
			System.out.println("2- Enviar mensajes");
			System.out.println("3- Leer mensajes y borrar");
			System.out.println("4- Estadisticas mensaje");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				CrearEmpleado();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;

			}
		} while (opcion != 0);
	}

	private static void CrearEmpleado() {
		// TODO Auto-generated method stub
		
	}

	

}
