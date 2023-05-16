package Examen;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Libro {
	@Id
	private String isbn;
	@Column(nullable = false,name = "numEjemplares")
	private int num_Ejemplares;
	@Column(nullable = false)
	private String titulo;
	@Column(nullable = false)
	private String autor;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cp.libro")
	private List<Prestamo>prestamos=new ArrayList<>();
	
	
	
	public void mostrar() {
		System.out.println("Libro [isbn=" + isbn + ", num_Ejemplares="
	+ num_Ejemplares + ", titulo=" + titulo + ", autor=" + autor
				);
	}
	public Libro() {
	}
	public Libro(String isbn, int num_Ejemplares, String titulo, String autor, List<Prestamo> prestamos) {
		this.isbn = isbn;
		this.num_Ejemplares = num_Ejemplares;
		this.titulo = titulo;
		this.autor = autor;
		this.prestamos = prestamos;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getNum_Ejemplares() {
		return num_Ejemplares;
	}
	public void setNum_Ejemplares(int num_Ejemplares) {
		this.num_Ejemplares = num_Ejemplares;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public List<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(List<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	
}
