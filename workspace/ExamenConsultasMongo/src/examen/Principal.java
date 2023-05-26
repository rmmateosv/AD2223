package examen;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		mostrarPacientes();
		System.out.println("Introduce el dni del paciente: ");
		String dni = t.nextLine();
		Paciente p = ad.obtenerPaciente(dni);
		if(p!=null) {
			
			mostrarMedicos();
			System.out.println("Introduce el numero del colegiado del medico: ");
			int numCol = t.nextInt();t.nextLine();
			Medico m = ad.obtenerMedico(numCol);
			if(m!=null) {
				
				Consulta c = new Consulta();
				c.setCodigo(ad.obtenerIdConsulta());
				c.setMedico(m.getNumColegiado());
				c.setFecha(new Date());
				
				p.getConsultas().add(c);
				
				if(ad.crearConsulta(p, c)) {
					
					System.out.println("Se ha creado la consulta para el paciente con dni " + p.getDni());
					
				}else {
					System.out.println("Error al crear la consulta");
				}
				
			}else {
				System.out.println("Error, el médico no existe");
			}
			
		}else {
			System.out.println("Error, el paciente no existe");
		}

	}

	private static void mostrarPacientes() {
		List<Paciente> pacientes = ad.obtenerPacientes();
		for(Paciente p:pacientes) {
			System.out.println(p.toString());
		}
		
	}

	private static void registrarDiagnosticos() {
		mostrarConsultas();
		System.out.println("Introduce el id de la consulta: ");
		int id = t.nextInt();t.nextLine();
		Consulta c = ad.obtenerConsulta(id);
		if(c!=null) {
			
			System.out.println("Introduce el diagnostico: ");
			c.setDiagnostico(t.nextLine());
			if(ad.registrarDiagnostico(c)) {
				
				System.out.println("Diagnóstico registrado");
				
			}else {
				
				System.out.println("Error al registrar el diagnostico");
			}
			
		}else {
			System.out.println("Error, la consulta no existe");
		}
		
		

	}

	private static void mostrarConsultas() {
		ArrayList<Object[]> consultas = ad.obtenerConsultas();
		for(Object[] o:consultas) {
			System.out.println("Codigo: "+o[0]+
								"\tPaciente: " +o[1] +
								"\tMedico: " + o[2] + 
								"\tFecha: " + o[3]);
		}
		
	}

	private static void borrarConsultas() {
		mostrarConsultas();
		System.out.println("Introduce el id de la consulta: ");
		int id = t.nextInt();t.nextLine();
		Consulta c = ad.obtenerConsulta(id);
		if(c!=null) {
			
			if(ad.borrarConsulta(c)) {
				System.out.println("Consulta eliminada");
			}else {
				System.out.println("Error al borrar la consulta");
			}
			
			
		}else {
			System.out.println("Error, la consulta no existe");
		}
		

	}

	private static void mostrarConsulta() {
		mostrarConsultas();
		System.out.println("Introduce el id de la consulta: ");
		int id = t.nextInt();t.nextLine();
		Consulta c = ad.obtenerConsulta(id);
		if(c!=null) {
			
			Object[] o = ad.mostrarConsultas(c.getCodigo());
			if(o!=null) {
				System.out.println(
									"\tPaciente[Nif: " +o[0]+ " ,Nombre: " + o[1] +"]" +
									"\tMedico[Numero colegiado: " + o[2]+ " ,Nombre: " + o[5]+ "]"+
									"\tFecha: " + o[3] + 
									"\tDiagnostico: " + o[4]);
			
			}
		}else {
			System.out.println("Error, la consulta no existe");
		}
		

	}
}