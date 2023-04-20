package examen;

import java.util.Date;

public class Mensaje {
	
	private int id;
	private int deEmpleado;
	private int paraDepartamento;
	private String asunto;
	private Date fechaEnvio;
	private String mensaje;
	
	
	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", deEmpleado=" + deEmpleado + ", paraDepartamento=" + paraDepartamento
				+ ", asunto=" + asunto + ", fechaEnvio=" + fechaEnvio + ", mensaje=" + mensaje + "]";
	}


	public Mensaje() {
	}


	public Mensaje(int id, int deEmpleado, int paraDepartamento, String asunto, Date fechaEnvio, String mensaje) {
		this.id = id;
		this.deEmpleado = deEmpleado;
		this.paraDepartamento = paraDepartamento;
		this.asunto = asunto;
		this.fechaEnvio = fechaEnvio;
		this.mensaje = mensaje;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDeEmpleado() {
		return deEmpleado;
	}


	public void setDeEmpleado(int deEmpleado) {
		this.deEmpleado = deEmpleado;
	}


	public int getParaDepartamento() {
		return paraDepartamento;
	}


	public void setParaDepartamento(int paraDepartamento) {
		this.paraDepartamento = paraDepartamento;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public Date getFechaEnvio() {
		return fechaEnvio;
	}


	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}


	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	

}
