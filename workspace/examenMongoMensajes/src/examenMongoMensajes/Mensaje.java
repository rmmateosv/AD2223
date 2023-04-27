package examenMongoMensajes;

import java.util.ArrayList;
import java.util.Date;

public class Mensaje {
	
	private int codigo;
	private String deEmpleado;
	private String paraDepartamento;
	private String asunto;
	private Date fecha;
	private String mensaje;
	private ArrayList<Destinatario> destinatarios = new ArrayList<>();
	
	
	
	@Override
	public String toString() {
		return "Mensaje [codigo=" + codigo + ", deEmpleado=" + deEmpleado + ", paraDepartamento=" + paraDepartamento
				+ ", asunto=" + asunto + ", fecha=" + fecha + ", mensaje=" + mensaje + ", destinatarios="
				+ destinatarios + "]";
	}
	public Mensaje() {
	}
	public Mensaje(int codigo, String deEmpleado, String paraDepartamento, String asunto, Date fecha, String mensaje,
			ArrayList<Destinatario> destinatarios) {
		this.codigo = codigo;
		this.deEmpleado = deEmpleado;
		this.paraDepartamento = paraDepartamento;
		this.asunto = asunto;
		this.fecha = fecha;
		this.mensaje = mensaje;
		this.destinatarios = destinatarios;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDeEmpleado() {
		return deEmpleado;
	}
	public void setDeEmpleado(String deEmpleado) {
		this.deEmpleado = deEmpleado;
	}
	public String getParaDepartamento() {
		return paraDepartamento;
	}
	public void setParaDepartamento(String paraDepartamento) {
		this.paraDepartamento = paraDepartamento;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public ArrayList<Destinatario> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(ArrayList<Destinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}
	
	
}
