package examen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Principal {

	static Scanner t = new Scanner(System.in);
	static AccesoDatos ad = new AccesoDatos();
	// Definimos el formato con el que vamos
	// a pintar/pedir fechas
	static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (ad.getBd() != null) {
			int opcion;
			do {
				System.out.println("Introduce opción:");
				System.out.println("0-Salir");
				System.out.println("1-Alta Paciente");
				System.out.println("2-Crear Medico");
				System.out.println("3-Añadir especialidad");
				System.out.println("4-Crear consulta");
				System.out.println("5-Registrar diagnóstico");
				System.out.println("6-Borrar consultas");
				System.out.println("7-Mostrar consultas");
				opcion = t.nextInt();
				t.nextLine();
				switch (opcion) {
				case 1:
					altaPaciente();
					break;
				case 2:
					altaMedico();
					break;
				case 3:
					aEspecialidad();
					break;
				case 4:
					crearConsulta();
					break;
				case 5:
					registrarDiagnosticos();
					break;
				case 6:
					borrarConsultas();
					break;

				case 7:
					mostrarConsulta();
					break;
				}
			} while (opcion != 0);
			// Cerrar conexión
			ad.cerrar();
		} else {
			System.out.println("Error, no hay conexión con Clínica");
		}

	}

	private static void altaPaciente() {
		// TODO Auto-generated method stub
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Indique el DNI");
		String dni = t.nextLine();
		Paciente p = ad.obtenerPaciente(dni);
		if (p == null) {
			try {
				p = new Paciente();
				p.setDni(dni);
				System.out.println("Indique el nombre ");
				p.setNombre(t.nextLine());
				System.out.println("Indique la fecha de nacimiento (dd/MM/yyyy):");
				p.setFechaN(formato.parse(t.nextLine()));

				if (ad.crearPaciente(p)) {
					System.out.println("Paciente creado");
				} else {
					System.out.println("Error al crear el paciente");
				}

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("ya existe");
		}
	}

	private static void altaMedico() {
		
		System.out.println("Introduce el numero del colegiado: ");
		int numCol = t.nextInt();t.nextLine();
		Medico m = ad.obtenerMedico(numCol);
		if(m==null) {
			
			m = new Medico();
			m.setNumColegiado(numCol);
			System.out.println("Introduce el nombre del médico: ");
			m.setNombre(t.nextLine());
			System.out.println("Introduce la especialidad del médico: ");
			m.getEspecialidades().add(t.nextLine());
			
			if(ad.crearMedico(m)) {
				System.out.println("Medico creado");
			}else {
				System.out.println("Error al crear el médico");
			}
			
		}else {
			System.out.println("El médico ya existe");
		}

	}

	private static void aEspecialidad() {
		mostrarMedicos();
		System.out.println("Introduce el numero del colegiado: ");
		int numCol = t.nextInt();t.nextLine();
		Medico m = ad.obtenerMedico(numCol);
		if(m!=null) {
			
			System.out.println("Introduce la especialidad: ");
			String especialidad = t.nextLine();
			
			if(ad.aniadirEspecialidad(numCol, especialidad)) {
				
				
			}else {
				System.out.println("Error al añadir la especialidad");
			}
			
		}else {
			System.out.println("Error, el medico no existe");
		}
		

	}

	private static void mostrarMedicos() {
		List<Medico> medicos = ad.obtenerMedicos();
		for(Medico m:medicos) {
			System.out.println(m.toString());
		}
		
	}

	private static void crearConsulta() {
		// TODO Auto-generated method stub

	}

	private static void registrarDiagnosticos() {
		// TODO Auto-generated method stub

	}

	private static void borrarConsultas() {
		// TODO Auto-generated method stub

	}

	private static void mostrarConsulta() {
		// TODO Auto-generated method stub

	}
}