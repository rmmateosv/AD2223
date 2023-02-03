package taller;

public class PiezaReparacion {
	
	private Reparacion reparacion;
	private Pieza pieza;
	private float importe;
	private int cantidad;
	
	public PiezaReparacion() {
		
	}

	public PiezaReparacion(Reparacion reparacion, Pieza pieza, float importe, int cantidad) {
		this.reparacion = reparacion;
		this.pieza = pieza;
		this.importe = importe;
		this.cantidad = cantidad;
	}
	
	public void mostrar() {
		System.out.println("Reparacion: " +reparacion
							+"\tPieza: " +pieza
							+"\tImporte: " +importe
							+"\tCantidad. " +cantidad);
	}

	public Reparacion getReparacion() {
		return reparacion;
	}

	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}

	public Pieza getPieza() {
		return pieza;
	}

	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
