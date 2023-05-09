package examen;

public class JugadorPartido {
	private ClaveJugadorPartido cjp;
	private String resultado;
	
	public JugadorPartido() {
	}
	
	public JugadorPartido(ClaveJugadorPartido cjp, String resultado) {
		this.cjp = cjp;
		this.resultado = resultado;
	}
	
	public ClaveJugadorPartido getCjp() {
		return cjp;
	}
	public void setCjp(ClaveJugadorPartido cjp) {
		this.cjp = cjp;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	@Override
	public String toString() {
		return "JugadorPartido [cjp Jugador=" + cjp.getJugador() + "cjp Partido=" +cjp.getPartido()+ ", resultado=" + resultado + "]";
	}
	
}
