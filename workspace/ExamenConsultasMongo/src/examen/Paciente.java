package examen;

import java.util.ArrayList;
import java.util.Date;

public class Paciente {
	private String dni;
	private String nombre;
	private Date fechaN;
	private ArrayList<Consulta> consultas =  new ArrayList<>();
	
	public Paciente() {
	}
	
	
	public Paciente(String dni, String nombre, Date fechaN, ArrayList<Consulta> consultas) {
		this.dni = dni;
		this.nombre = nombre;
		this.fechaN = fechaN;
		this.consultas = consultas;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFechaN() {
		return fechaN;
	}


	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}


	public ArrayList<Consulta> getConsultas() {
		return consultas;
	}


	public void setConsultas(ArrayList<Consulta> consultas) {
		this.consultas = consultas;
	}


	@Override
	public String toString() {
		String resultado = "Paciente [dni=" + dni + ", nombre=" + nombre + ", fechaN=" + fechaN;
		resultado+= "\nConsultas";
		for(Consulta c: consultas) {
			resultado += c.toString() + "\n";
		}
		return resultado;
	}
	
	
	
}
