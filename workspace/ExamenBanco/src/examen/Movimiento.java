package examen;

import java.io.Serializable;
import java.util.Date;

public class Movimiento implements Serializable {
	private Date fecha;
	private String desc;
	private float importe;

	public Movimiento() {
	}

	public Movimiento(Date fecha, String desc, float importe) {
		this.fecha = fecha;
		this.desc = desc;
		this.importe = importe;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Movimiento [fecha=" + fecha + ", desc=" + desc + ", importe=" + importe + "]";
	}

}
