package examen;

import java.util.ArrayList;
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
			System.out.println("2-Ejercicio2");
			System.out.println("3-Ejercicio3");
			
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				Ejercicio1();
				break;
			case 2:
				Ejercicio2();
				break;
			case 3:
				Ejercicio3();
				break;
			}	

		} while (opcion != 0);

	}


	private static void Ejercicio3() {
		float total = 0;
		float importe = 0;
		System.out.println("Introduzca una matrícula");
		String m = t.nextLine();
		System.out.println("Multas Vehículo: "+m);
		MatriculaBin mBin = ad.obtenerMatricula(m);
		if(mBin!=null) {
			System.out.println("Nº multas en radares fijos: "+mBin.getNumMultas()+
					"\t Importe recaudado: "+ mBin.getImporte());
			total += mBin.getImporte();
				
		}
		
		ArrayList<MatriculaTxt> mTxt = ad.obtenerMatriculaTxt(m);

		for(MatriculaTxt mt: mTxt) {		
			importe += calcularImporte(mt.getVelocidadC(), mt.getVelocidad());
		}
		System.out.print("Nº multas en radares móviles: "+ mTxt.size()+
				"\t Importe recaudado: "+importe+ "\n");
		System.out.println("Total: "+ (total+importe)+"€");

		
	}


	private static void Ejercicio2() {
		ad.mostrarBinario();
		System.out.println("Introduzca la matrícula del vehículo que desea borrar");
		String matricula = t.nextLine();
		if(ad.existeMultaCoche(matricula)) {
			//Borramos la matrícula
			if(ad.borrarCoche(matricula)) {
				System.out.println("El coche con la matrícula "+matricula+
						" ha sido eliminado correctamente");
				ad.mostrarBinario();
				
			}else {
				System.out.println("Error al eliminar el coche con la matrícula "+matricula);
			}
		}else {
			System.out.println("Error, no existe la matrícula");
		}
		
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
				if(ad.modificarMulta(m,precio)) {
					System.out.println("Multa cargada para vehículo :"+m.getMatricula());
				}
				else
				{
					System.out.println("Error al cargar la matricula :"+m.getMatricula());
				}
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
		ad.mostrarBinario();
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
