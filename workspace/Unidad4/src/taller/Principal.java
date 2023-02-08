package taller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getEm() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				
				
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					
					break;
				
				}
			} while (opcion != 0);
			//Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}
	}
	
}
