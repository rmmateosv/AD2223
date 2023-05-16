package Examen;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table
public class Socio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.DATE)
	private Date fechaSancion;
	@Column(nullable = false)
	private String nif;
	@Column(nullable = false)
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cp.socio")
	private List<Prestamo>prestamos=new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaSancion() {
		return fechaSancion;
	}
	public void setFechaSancion(Date fechaSancion) {
		this.fechaSancion = fechaSancion;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	public Socio(int id, Date fechaSancion, String nif, String nombre, List<Prestamo> prestamos) {
		this.id = id;
		this.fechaSancion = fechaSancion;
		this.nif = nif;
		this.nombre = nombre;
		this.prestamos = prestamos;
	}
	public Socio() {
	}

	public void mostrar() {
		System.out.println("Socio [id=" + id + ", fechaSancion=" + fechaSancion +
				", nif=" + nif + ", nombre=" + nombre + "]");
	}

}


