package examen;

import java.util.Scanner;

public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	
	public static void main(String[] args) {

		if(ad.getCnx()!=null) {
			System.out.println("Introduce el usuario: ");
			int usuario = t.nextInt(); t.nextLine();
			
			System.out.println("Introduce la contraseña: ");
			String clave = t.nextLine();
			
			//comprobar si existe un empleado con ese usuario y contraseña
			int retorno = ad.comprobarUsuario(usuario, clave);
			
			switch(retorno) {
			case 0:
				System.out.println("Error, datos incorrectos");
				break;
			case 1:
				menu();
				
				break;
			case 2:
				System.out.println("Cambiar contraseña");
				System.out.println("Introduce una nueva contraseña");
				clave = t.nextLine();
				
				if(ad.modificarClave(clave, usuario)) {
					menu();
				}else {
					System.out.println("Se ha producido un error al modificar la contraseña");
				}
				break;
			}
			
		} else {
			System.out.println("Error al conectar con la base de datos.");
		}
		
		
		
		
	}

	private static void menu() {
		// TODO Auto-generated method stub
		
	}

}
