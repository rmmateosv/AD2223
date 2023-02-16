package examen;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugador_partido")
public class Jugador_Partido implements Serializable {
	
	@EmbeddedId
	private ClaveJP clave;
	@Column(nullable = true)
	private String resultado;
	
	public Jugador_Partido() {}

	public Jugador_Partido(ClaveJP clave, String resultado) {
		this.clave = clave;
		this.resultado = resultado;
	}
	
	public void mostrar() {
		System.out.println("Código partido: " + clave.getPartido().getCodigo() + 
				"\tCódigo jugador: " + clave.getJugador().getCodigo() + 
				"\tResultado: " + resultado);
	}
	
	public ClaveJP getClave() {
		return clave;
	}

	public void setClave(ClaveJP clave) {
		this.clave = clave;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
}
