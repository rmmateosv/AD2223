package examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(nullable = false)
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ganador")
	List<Partido> partidosganados = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clave.jugador")
	List<Jugador_Partido> partidosjugados = new ArrayList<>();
	
	public Jugador() {}

	public Jugador(int codigo, String nombre, List<Partido> partidosganados, List<Jugador_Partido> partidosjugados) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.partidosganados = partidosganados;
		this.partidosjugados = partidosjugados;
	}
	
	public void mostrar() {
		System.out.println("Código: " + codigo +
				"\tNombre: " + nombre);
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Partido> getPartidosganados() {
		return partidosganados;
	}

	public void setPartidosganados(List<Partido> partidosganados) {
		this.partidosganados = partidosganados;
	}

	public List<Jugador_Partido> getPartidosjugados() {
		return partidosjugados;
	}

	public void setPartidosjugados(List<Jugador_Partido> partidosjugados) {
		this.partidosjugados = partidosjugados;
	}
	
}
