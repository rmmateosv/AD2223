package Examen;

import java.util.ArrayList;
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
		mostrarFacturas();
		
		System.out.println("Inroduce el numero de la factura: ");
		int numFactura = t.nextInt(); t.nextLine();
		
		Facturas f = ad.obtenerFactura(numFactura);
		if(f!=null) {
		
			//cambio de las cantidades a negativo
			for(Detalle d:f.getDetalle()) {
				d.setCantidad(d.getCantidad()*-1);
				
			}
			//cambiamos la fecha
			f.setFecha(new Date());
			//cambiamos el numero de la factura
			f.setNumero(ad.obtenerNumeroFactura());
			
			if(!f.getDetalle().isEmpty()) {
				if(ad.crearFactura(f)) {
					//decrementar el stock de los productos de la factura
					for(Detalle d: f.getDetalle()) {
						if(!ad.actualizarStock(d)) {
							System.out.println("Error al actualizar el stock del producto "+
									d.getProducto());
						}
					}
					//Modificar la factura original para poner la factura de anulación
					if(ad.ponerFAnulacion(numFactura, f.getNumero())) {
						System.out.println("Factura anulada");
					}
				}
			}else {
				System.out.println("Error");
			}
		}else {
			System.out.println("Error, no existe la factura");
		}
		
	}

	private static void mostrarFacturas() {
		ArrayList<Facturas> f = ad.obtenerFacturas();
		for(Facturas fact: f) {
			System.out.println(fact.toString());
		}
		
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
			System.out.println("Introduce el codigo del producto");
			String cod = t.nextLine();
			Productos p = ad.obtenerProducto(cod);
			if(p==null) {
				System.out.println("El producto no existe");
				
			}else {
				Detalle d = new Detalle();
				d.setProducto(cod);
				d.setPrecioUnidad(p.getPrecio());
				System.out.println("Introduce la cantidad: ");	
				d.setCantidad(t.nextInt());t.nextLine();
				
				if(p.getStock()>=d.getCantidad()) {
					fact.getDetalle().add(d);
					
					
				}else {
					System.out.println("Error, no hay suficiente stock");
				}
			}
			
			
			
			System.out.println("Deseas introducir mas productos(0-no)");
			elec=t.nextLine();
		}while(!elec.equals("0"));
		
		if(!fact.getDetalle().isEmpty()) {
			if(ad.crearFactura(fact)) {
				//decrementar el stock de los productos de la factura
				for(Detalle d: fact.getDetalle()) {
					if(!ad.actualizarStock(d)) {
						System.out.println("Error al actualizar el stock del producto "+
								d.getProducto());
					}
				}
			}
		}else {
			System.out.println("Error");
		}
	}

	private static void mostrarProductos() {
		ArrayList<Productos> p = ad.obtenerProductos();
		for(Productos prod: p) {
			System.out.println(prod.toString());
		}
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
