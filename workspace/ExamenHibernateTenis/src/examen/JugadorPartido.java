package examen;



import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugador_partido")
public class JugadorPartido {
	
	@EmbeddedId
	private ClaveJugadorPartido cjp;
	
	@Column(nullable = true)
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
		return "JugadorPartido [cjp Jugador=" + cjp.getJugador().getNombre() + "cjp Partido=" +cjp.getPartido().getCodigo()+ ", resultado=" + resultado + "]";
	}
	
}
