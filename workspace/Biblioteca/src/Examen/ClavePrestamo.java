package Examen;

import java.util.Date;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class ClavePrestamo {
	@Temporal(TemporalType.DATE)
	private Date fechaP;
	@ManyToOne
	@JoinColumn(name = "socio",referencedColumnName = "id",nullable = false)
	private Socio socio;
	@ManyToOne
	@JoinColumn(name = "libro",referencedColumnName = "isbn",nullable = false)
	private Libro libro;
	
	public void mostrar() {
		System.out.println("ClavePrestamo [fechaP=" + fechaP +
				", socio=" + socio + ", libro=" + libro + "]");
	}
	public ClavePrestamo() {
	}
	public ClavePrestamo(Date fechaP, Socio socio, Libro libro) {
		this.fechaP = fechaP;
		this.socio = socio;
		this.libro = libro;
	}
	public Date getFechaP() {
		return fechaP;
	}
	public void setFechaP(Date fechaP) {
		this.fechaP = fechaP;
	}
	public Socio getSocio() {
		return socio;
	}
	public void setSocio(Socio socio) {
		this.socio = socio;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}
