package examen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

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
@Table
public class Partido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@Column(nullable = false,name = "numSets")
	private int num_Set;
	
	@ManyToOne
	@JoinColumn(name = "ganador",referencedColumnName = "codigo",nullable = true)
	private Jugador ganador;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "cjp.partido")
	private List<JugadorPartido> jugadores= new ArrayList();
	
	
	public Partido() {
	}
	public Partido(int codigo, Date fecha, int num_Set, Jugador ganador, List<JugadorPartido> jugadores) {
		this.codigo = codigo;
		this.fecha = fecha;
		this.num_Set = num_Set;
		this.ganador = ganador;
		this.jugadores = jugadores;
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
	public int getNum_Set() {
		return num_Set;
	}
	public void setNum_Set(int num_Set) {
		this.num_Set = num_Set;
	}
	public Jugador getGanador() {
		return ganador;
	}
	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}
	public List<JugadorPartido> getJugadores() {
		return jugadores;
	}
	public void setJugadores(List<JugadorPartido> jugadores) {
		this.jugadores = jugadores;
	}
	@Override
	public String toString() {
		
		String resultado=  "Partido [codigo=" + codigo + ", fecha=" + fecha + ", num_Set=" + num_Set + ", ganador=" + ganador.getNombre()
				+ "]";
		resultado += "\n Jugadores:";
		for(JugadorPartido jp :jugadores) {
			resultado+= "\n"+ jp.getCjp().getJugador().getNombre();
		}
		
		return resultado;
	}
	
	
	
	
	
}
