package taller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reparacion {
	
	private int id;
	private Vehiculo vehiculo;
	private Date fecha;
	private int tiempo;
	private boolean pagado;
	
	public Reparacion() {
		
	}

	public Reparacion(int id, Vehiculo vehiculo, Date fecha, int tiempo, boolean pagado) {
		this.id = id;
		this.vehiculo = vehiculo;
		this.fecha = fecha;
		this.tiempo = tiempo;
		this.pagado = pagado;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyy");
		System.out.println("Id: " +id
							+"\tVehiculo: " +vehiculo
							+"\tFecha: " +formato.format(fecha)
							+"\tTiempo: " +tiempo
							+"\tPagado: " +pagado);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	
}
