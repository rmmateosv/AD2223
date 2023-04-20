package examen;

import java.util.Date;

public class Empleado {
	
	private int codigo;
	private String dni, nombre;
	private Date fechaN;
	private int departamento;
	private boolean cambiarPs;
	
	
	public Empleado() {
	}


	public Empleado(int codigo, String dni, String nombre, Date fechaN, int departamento, boolean cambiarPs) {
		this.codigo = codigo;
		this.dni = dni;
		this.nombre = nombre;
		this.fechaN = fechaN;
		this.departamento = departamento;
		this.cambiarPs = cambiarPs;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
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


	public int getDepartamento() {
		return departamento;
	}


	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}


	public boolean isCambiarPs() {
		return cambiarPs;
	}


	public void setCambiarPs(boolean cambiarPs) {
		this.cambiarPs = cambiarPs;
	}


	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", dni=" + dni + ", nombre=" + nombre +
				 ", departamento=" + departamento +  ", fechaN=" + fechaN + "]";
	}
	
	

}
