import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				int opcion;
				do {
					System.out.println("Introduce opción:");
					System.out.println("0-Salir");
					System.out.println("1-Ejer1");
					System.out.println("2-Ejer2");
					System.out.println("3-Ejer3");
					System.out.println("4-Ejer4");
					opcion = t.nextInt();
					t.nextLine();
					switch (opcion) {
					case 1:
						ejer1();
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
	private static void ejer1() {
		// TODO Auto-generated method stub
		//Obtener ventas txt
		ArrayList<Venta> ventas = ad.obtenerVentasTxt();

		//Procesar ventas de txt
		for(Venta v: ventas) {
			//Comprobar si existe la venta en el fOBJ
			Venta vOBJ = ad.obtenerVentaObj(v);
			if(vOBJ==null) {
				//No existe=> se añade al fOBJ
			}
			else {
				//Existe=> se modifica
			}
		}
		//Mostrar ventas obj
		mostrarVentasOBJ();
	}

}
