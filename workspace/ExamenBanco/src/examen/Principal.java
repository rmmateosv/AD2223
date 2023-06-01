package examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	public static void main(String[] args) {
		int opcion;
		do {
			System.out.println("Introduce opción:");
			System.out.println("0-Salir");	
			System.out.println("1-Crear cliente");
			System.out.println("2-Registrar movimiento");
			System.out.println("3-Generar OBJ");
			System.out.println("4-Borrar movimiento");
			System.out.println("5-Anular movimiento");
			opcion = t.nextInt(); t.nextLine();
			switch(opcion) {
			case 1:
				crearCliente();
				break;
			case 2:
				
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
				

			}
		}while(opcion!=0);

	}
	private static void crearCliente() {
		CuentaTxt cuenta = new CuentaTxt();
		System.out.println("Introduzca el dni del cliente");
		cuenta.setDni(t.nextLine());
		System.out.println("Introduzca el nombre del cliente");
		cuenta.setNombre(t.nextLine());
		System.out.println("Introduzca el saldo inicial");
		cuenta.setSaldo(t.nextFloat()); t.nextLine();
		cuenta.setCuenta(ad.obtenerNumCuenta());
		if(ad.crearCuenta(cuenta)) {
			System.out.println("La cuenta ha sido creada");
			MovimientosBin m = new MovimientosBin();
			m.setId(ad.obtenerNumMovimiento());
			m.setDesc("Saldo inicial");
			m.setCuenta(cuenta.getCuenta());
			m.setFecha(new Date());
			m.setImporte(cuenta.getSaldo());
			if(ad.crearMovimiento(m)) {
				System.out.println("Movimiento creado correctamente");
				mostrarCuentas();
			}else {
				System.out.println("Error al crear el movimiento");
			}
			
			
		}else {
			System.out.println("Error al crear la cuenta");
		}
		
	}
	private static void mostrarCuentas() {
		ArrayList<CuentaTxt> cuentas = ad.obtenerCuentas();
		for(CuentaTxt c: cuentas) {
			System.out.println(c.toString());
			ArrayList<MovimientosBin> movimiento = ad.obtenerMovimiento(c.getCuenta());
			for(MovimientosBin m: movimiento) {
				System.out.println(m.toString());
			}
		}
		
	}

}
