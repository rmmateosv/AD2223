package examen;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "partido")
public class Partido implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@Column(nullable = false)
	private int numSets;
	@ManyToOne
	@JoinColumn(nullable = true, name = "ganador", referencedColumnName = "codigo")
	private Jugador ganador;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "clave.partido")
	private List<Jugador_Partido> jugadorespartido = new ArrayList<>();
	
	public Partido() {}

	public Partido(int codigo, Date fecha, int numSets, Jugador ganador, List<Jugador_Partido> jugadorespartido) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.numSets = numSets;
		this.ganador = ganador;
		this.jugadorespartido = jugadorespartido;
	}
	
	public void mostrar() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Código: " + codigo + 
				"\tFecha: " + formato.format(fecha) + 
				"\tNum sets: " + numSets + 
				"\tGanador: " + (ganador == null ? "No hay ganador" : ganador.getCodigo()));
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getNumSets() {
		return numSets;
	}

	public void setNumSets(int numSets) {
		this.numSets = numSets;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public List<Jugador_Partido> getJugadorespartido() {
		return jugadorespartido;
	}

	public void setJugadorespartido(List<Jugador_Partido> jugadorespartido) {
		this.jugadorespartido = jugadorespartido;
	}
	
}
