package Examen;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Principal {
	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			// TODO Auto-generated method stub
			if (ad.getEm() != null) {
				int opcion;
				do {
					System.out.println("Introduce opción:");
					System.out.println("0-Salir");
					System.out.println("1-Crear Prestamo");
					System.out.println("2-Mostrar prestamos de un socio");
					System.out.println("3-Devolver un prestamo");
					System.out.println("4-Borrar libro");
	
					opcion = t.nextInt();
					t.nextLine();
					switch (opcion) {
					case 1:
						crearPrestamo();
						break;
					case 2:
						
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					
					}
				} while (opcion != 0);
				//Cerrar conexión
				ad.cerrar();
			} else {
				System.out.println("Error, no hay conexión con biblioteca");
			}
	}
	private static void crearPrestamo() {
		
		mostrarSocios();
		System.out.println("Introduce el id del socio: ");
		int id = t.nextInt(); t.nextLine();
		Socio s = ad.obtenerSocio(id);
		if(s!=null) {
			
			if((s.getFechaSancion() == null) || (s.getFechaSancion().getTime() <= new Date().getTime())) {
				
				long pendientes = ad.obtenerPrestamosPendientes(s);
				if(pendientes < 2) {
					 mostrarLibros();
					 System.out.println("Introduce un isbn: ");
					 String isbn = t.nextLine();
					 
					 Libro l = ad.obtenerLibro(isbn);
					 if(l!=null) {
						 
						 if(l.getNum_Ejemplares()>0) {
							 
							 Prestamo p = new Prestamo();
							 p.setFechaDevolPrevista(new Date(new Date().getTime()+(15*24*60*60*1000)));
							 p.setCp(new ClavePrestamo(new Date(), s, l));
							 l.setNum_Ejemplares(l.getNum_Ejemplares()-1);
							 if(ad.crearPrestamo(p)) {
								 System.out.println("Préstamo registrado al socio con dni "+s.getNif());
							 }else {
								 System.out.println("Error al crear el prestamo");
							 }
							 
						 }else {
							 System.out.println("Error, no hay ejemplares del libro");
						 }
						 
					 }else {
						 System.out.println("Error, el libro no existe");
					 }
					 
				}else {
					System.out.println("Error, el socio tiene mas de un prestamo");
				}
				
			}else {
				System.out.println("Error, el socio está sancionado");
			}
			
		}else {
			System.out.println("Error, el socio no existe");
		}
		
	}
	private static void mostrarLibros() {
		List<Libro> libros = ad.obtenerLibros();
		for(Libro l:libros) {
			l.mostrar();
		}
		
	}
	private static void mostrarSocios() {
		
		List<Socio> socios = ad.obtenerSocios();
		for(Socio s:socios) {
			s.mostrar();
		}
		
	}
}
