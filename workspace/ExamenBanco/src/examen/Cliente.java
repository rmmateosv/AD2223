package examen;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable{
	private String dni;
	private String nombre;
	private ArrayList<Cuenta> cuentas = new ArrayList<>();
	public Cliente() {
	}
	public Cliente(String dni, String nombre, ArrayList<Cuenta> cuentas) {
		this.dni = dni;
		this.nombre = nombre;
		this.cuentas = cuentas;
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
	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	@Override
	public String toString() {
		String resultado = "Cliente [dni=" + dni + ", nombre=" + nombre;
		resultado +="\nCuentas: ";
		for(Cuenta c: cuentas) {
			resultado += c.toString() + "\n";
		}
		return  resultado;
	}
	
}
