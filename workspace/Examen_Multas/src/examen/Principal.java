package examen;

import java.util.Scanner;

public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad= new AccesoDatos();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1-Ejercicio1");
			
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				Ejercicio1();
				break;
			}	

		} while (opcion != 0);

	}


	private static void Ejercicio1() {
		// TODO Auto-generated method stub
		System.out.println("Introduce el nombre del fichero a cargar");
		String nombre= t.nextLine();
		Radar radar = ad.cargarXml(nombre);
		
		if(radar!=null) {
		for(Multa m :radar.getMultas()) {
			float precio=calcularImporte(m.getVelocidad(),radar.getMaximo());
			//chequear si hay un registro en el fichero binario
			//para la matricula de esta multa 
			if(ad.existeMultaCoche(m.getMatricula())) {
				//modificar  registro del fichero binario 
				
			}
			else {
				//crear registro en el fichero binario
				if(ad.crearMulta(m,precio)) {
					System.out.println("Multa cargada para vehículo :"+m.getMatricula());
				}
				else
				{
					System.out.println("Error al cargar la matricula :"+m.getMatricula());
				}
			}
			
			
			
		}
		}
		else {
			System.out.println("Error al cargar el fichero ");
		}
	}


	private static float calcularImporte(int velocidad, int maximo) {
		// TODO Auto-generated method stub
		if(velocidad-maximo<=10) {
			return 100;
		}
		if(velocidad-maximo<=30) {
			return 300;
		}else {
			return 500;
		}
	}

}
