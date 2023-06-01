package examen;

import java.util.Date;

public class MovimientosBin {
	private int id; // int 4bytes
	private int cuenta; // int 4bytes
	private Date fecha; // Long 8bytes
	private String desc; // String 2bytes cada caracter, 20car=40bytes
	private float importe; // Float 4bytes
	// Total 60bytes

	public MovimientosBin() {
	}

	public MovimientosBin(int id, int cuenta, Date fecha, String desc, float importe) {
		this.id = id;
		this.cuenta = cuenta;
		this.fecha = fecha;
		this.desc = desc;
		this.importe = importe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
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
		return "MovimientosBin [id=" + id + ", cuenta=" + cuenta + ", fecha=" + fecha + ", desc=" + desc + ", importe="
				+ importe + "]";
	}

}
