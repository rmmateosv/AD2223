package examen;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AD_Examen2 fProductos = new AD_Examen2();
	
	
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
				ejercicio1();
				break;
			case 2:
				ejercicio2();
				break;
			
			case 3:
				ejercicio3();
				break;
			}	

		} while (opcion != 0);

	}


	private static void ejercicio3() {
		// TODO Auto-generated method stub
		
	}


	private static void ejercicio2() {
		// mostrar productos
		ArrayList<Producto> prodBin = fProductos.obtenerProductosBin();
		for(Producto p: prodBin) {
			p.mostrar();
		}
		
		System.out.println("Código:");
		int codigo = t.nextInt(); t.nextLine();
		
		Producto p = fProductos.obtenerProductoBin(codigo);
		
		if(p != null) {
			// Pedir todos los datos
			System.out.println("Cantidad: ");
			int cantidad = t.nextInt(); t.nextLine();
			
			if(p.getStock()>= cantidad) {
				Ventas v = new Ventas(codigo, cantidad, p.getPrecio()*cantidad);
				if(fProductos.generarVenta(v)) {
					if(fProductos.modificarStock(p, cantidad)) {
						System.out.println("Stock modificado correctamente");
						// Falta obtener las ventas
					}else {
						System.out.println("Error al modificar el stock");
					}
				}else {
					System.out.println("Error al crear la venta");
				}
				
			}else {
				System.out.println("No hay suficiente stock");
			}
			
		}else {
			System.out.println("Error no se encuentra producto");
		}
		
	}


	private static void ejercicio1() {
		// Cargar el txt en un bin
		ArrayList<Producto> productos = fProductos.obtenerProductosTxt();
		
		if(fProductos.generarBin(productos)) {
			System.out.println("Archivo generado correctamente.");
			System.out.println("\nLos datos del fichero son:");
			ArrayList<Producto> prodBin = fProductos.obtenerProductosBin();
			for(Producto p: prodBin) {
				p.mostrar();
			}
		}
		else {
			System.out.println("Error al generar el archivo binario.");
		}
		
	}


	

	

}
