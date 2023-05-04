package Examen;

import java.util.Date;
import java.util.Scanner;

public class Principal {
static Scanner t = new Scanner(System.in);
static AccesoDatos ad = new AccesoDatos();
	 
public static  void main(String[]args) {
		int opcion;
		
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");
			System.out.println("1- Crear producto");
			System.out.println("2- Crear factura");
			System.out.println("3- Anular factura");
			System.out.println("4- Informe de facturación");
			opcion = t.nextInt();
			t.nextLine();
			switch (opcion) {
			case 1:
				CrearProducto();
				break;
			case 2:
				CrearFactura();
				break;
			case 3:
				AnularFactura();
				break;
			case 4:
				InformeDeFacturacion();
				break;

			}
		} while (opcion != 0);
	}

	private static void InformeDeFacturacion() {
		// TODO Auto-generated method stub
		
	}

	private static void AnularFactura() {
		// TODO Auto-generated method stub
		
	}

	private static void CrearFactura() {
		// TODO Auto-generated method stub
		Facturas fact= new Facturas();
		
		System.out.println("Indique el DNI del cliente");
		fact.setCliente(t.nextLine());
		fact.setFecha(new Date());
		fact.setFacturaAnulacion(0);
		fact.setNumero(ad.obtenerNumeroFactura());
		String elec;
		do {
			
			mostrarProductos();
			
			
			System.out.println("Deseas introducir mas productos(0-no)");
			elec=t.nextLine();
		}while(!elec.equals("0"));
	}

	private static void mostrarProductos() {
		// TODO Auto-generated method stub
		
	}

	private static void CrearProducto() {
		// TODO Auto-generated method stub
		System.out.println("Indique el codigo del producto");
		String cod=t.nextLine();
		//procedemos a comprobar si ya exisite el producto
		Productos pro =ad.obtenerProducto(cod);
		if(pro==null) {
			pro= new Productos();
			pro.setCodigo(cod);
			System.out.println("Indique el nombre del producto");
			pro.setNombre(t.nextLine());
			System.out.println("Indique el precio");
			pro.setPrecio(t.nextFloat());t.nextLine();
			System.out.println("Indique el stock");
			pro.setStock(t.nextInt());t.nextLine();
			
			if(ad.crearProducto(pro)) {
				System.out.println("El producto indicado se ha creado exitosamente");
				
			}
			else {
				System.out.println("Error al crear el producto");
			}
		}
		
		else {
			System.out.println("Error el producto indicado ya existe ");
		}
		
	}
}
