package examen;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"hora","velocidad"})
public class Multa {

	private String matricula;
	private String hora;
	private int velocidad;
	public Multa() {
	}
	public Multa(String matricula, String hora, int velocidad) {
		this.matricula = matricula;
		this.hora = hora;
		this.velocidad = velocidad;
	}
	@XmlAnyAttribute
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	@XmlElement
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	@XmlElement
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	
}
