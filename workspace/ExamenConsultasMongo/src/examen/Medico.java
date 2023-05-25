package examen;

import java.util.ArrayList;

public class Medico {
	private int numColegiado;
	private String nombre;
	private ArrayList<String> especialidades = new ArrayList<>();
	
	public Medico() {
	}

	public Medico(int numColegiado, String nombre, ArrayList<String> especialidades) {
		this.numColegiado = numColegiado;
		this.nombre = nombre;
		this.especialidades = especialidades;
	}

	public int getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(int numColegiado) {
		this.numColegiado = numColegiado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(ArrayList<String> especialidades) {
		this.especialidades = especialidades;
	}

	@Override
	public String toString() {
		return "Medico [numColegiado=" + numColegiado + ", nombre=" + nombre + ", especialidades=" + especialidades
				+ "]";
	}
	
	
	
}
