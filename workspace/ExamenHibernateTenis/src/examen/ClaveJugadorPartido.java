package examen;

public class ClaveJugadorPartido {
	private Partido partido;
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
