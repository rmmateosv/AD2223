package Examen;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table
public class Prestamo {
	@Temporal(TemporalType.DATE)
	private Date fechaDevolPrevista;
	@Temporal(TemporalType.DATE)
	@Column(nullable=true)
	private Date fechaDevolReal;
	@EmbeddedId
	private ClavePrestamo cp;
	
	public void mostrar() {
		System.out.println("Prestamo [fechaDevolPrevista=" + fechaDevolPrevista +
				", fechaDevolReal=" + (fechaDevolReal==null?"pendiente":fechaDevolReal) + 
				", Fecha Prestamo="+ cp.getFechaP() +
				", Socio="+ cp.getSocio().getNombre() +
				", Libro="+ cp.getLibro().getTitulo() +"-"+cp.getLibro().getIsbn() +"]");
	}
	public Prestamo() {
	}
	public Prestamo(Date fechaDevolPrevista, Date fechaDevolReal, ClavePrestamo cp) {
		this.fechaDevolPrevista = fechaDevolPrevista;
		this.fechaDevolReal = fechaDevolReal;
		this.cp = cp;
	}
	public Date getFechaDevolPrevista() {
		return fechaDevolPrevista;
	}
	public void setFechaDevolPrevista(Date fechaDevolPrevista) {
		this.fechaDevolPrevista = fechaDevolPrevista;
	}
	public Date getFechaDevolReal() {
		return fechaDevolReal;
	}
	public void setFechaDevolReal(Date fechaDevolReal) {
		this.fechaDevolReal = fechaDevolReal;
	}
	public ClavePrestamo getCp() {
		return cp;
	}
	public void setCp(ClavePrestamo cp) {
		this.cp = cp;
	}
}
