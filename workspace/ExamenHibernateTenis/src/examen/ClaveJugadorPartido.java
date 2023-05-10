package examen;



import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class ClaveJugadorPartido  {
	
	@ManyToOne
	@JoinColumn(name = "partido",referencedColumnName = "codigo")
	private Partido partido;
	
	@ManyToOne
	@JoinColumn(name = "jugador",referencedColumnName = "codigo")
	private Jugador jugador;
	
	public ClaveJugadorPartido() {
	}
	
	public ClaveJugadorPartido(Partido partido, Jugador jugador) {
		this.partido = partido;
		this.jugador = jugador;
	}
	
	public Partido getPartido() {
		return partido;
	}
	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	
}
