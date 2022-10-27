package examen;

import java.io.Serializable;

public class Ventas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845532018876315930L;
	private int codigo;
	private int cantidad;
	private float total;
	
	
	public Ventas() {
		super();
	}
	public Ventas(int codigo, int cantidad, float total) {
		super();
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.total = total;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	
	public void mostrar() {
		System.out.println("Codigo: " +codigo +
							"\tCantidad: " +cantidad +
							"\tTotal: " +total);
	}
}
