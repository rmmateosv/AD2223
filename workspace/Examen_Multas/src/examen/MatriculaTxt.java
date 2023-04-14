package examen;

public class MatriculaTxt {
	private String puntoKm;
	private int velocidad;
	private String matricula, hora;
	private int velocidadC;
	
	public MatriculaTxt() {
	}
	
	public MatriculaTxt(String puntoKm, int velocidad, String matricula, String hora, int velocidadC) {
		this.puntoKm = puntoKm;
		this.velocidad = velocidad;
		this.matricula = matricula;
		this.hora = hora;
		this.velocidadC = velocidadC;
	}
	
	public String getPuntoKm() {
		return puntoKm;
	}
	public void setPuntoKm(String puntoKm) {
		this.puntoKm = puntoKm;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getVelocidadC() {
		return velocidadC;
	}
	public void setVelocidadC(int velocidadC) {
		this.velocidadC = velocidadC;
	}
	
}
