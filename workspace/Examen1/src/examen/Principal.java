package examen;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static Ad_txt_bin fCuentas = new Ad_txt_bin();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Ejercicio1");
			System.out.println("2-Ejercicio2");
			System.out.println("3-Ejercicio3");
			System.out.println("4-Ejercicio4");
			System.out.println("5-Ejercicio5");
			System.out.println("6-Ejercicio6");
			System.out.println("7-Ejercicio7");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				ejercicio1();
				break;
			case 2:
				
				break;
			
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			}	

		} while (opcion != 0);

	}


	private static void ejercicio1() {
		// TODO Auto-generated method stub
		//Obtener las cuentas del fichero de texto
		ArrayList<Cuenta> cuentas = fCuentas.obtenerCuentasTxt();
		//Generar el fichero binario
		if(fCuentas.crearBianario(cuentas)) {
			System.out.println("Cuentas creadas");
		}
		else {
			System.out.println("Error al crear las cuentas");
		}
	}


}
