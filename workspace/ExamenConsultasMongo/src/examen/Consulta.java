package examen;

import java.util.Date;

public class Consulta {
	private int id;
	private int medico;
	private Date fecha;
	private String diagnostico;
	
	public Consulta() {
	}

	public Consulta(int id, int medico, Date fecha, String diagnostico) {
		this.id = id;
		this.medico = medico;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMedico() {
		return medico;
	}

	public void setMedico(int medico) {
		this.medico = medico;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	@Override
	public String toString() {
		return "Consulta [id=" + id + ", medico=" + medico + ", fecha=" + fecha + ", diagnostico=" + diagnostico + "]";
	}
	
	
}
