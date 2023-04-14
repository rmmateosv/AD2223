package examen;

import java.util.Date;

public class MatriculaBin {
	private String matricula;
	private int numMultas;
	private float importe;
	private Date fecha;
	
	public MatriculaBin() {
	}
	
	public MatriculaBin(String matricula, int numMultas, float importe, Date fecha) {
		this.matricula = matricula;
		this.numMultas = numMultas;
		this.importe = importe;
		this.fecha = fecha;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getNumMultas() {
		return numMultas;
	}
	public void setNumMultas(int numMultas) {
		this.numMultas = numMultas;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
