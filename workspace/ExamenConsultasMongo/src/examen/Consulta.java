package examen;

import java.util.Date;

public class Consulta {
	private int codigo;
	private int medico;
	private Date fecha;
	private String diagnostico;
	
	public Consulta() {
	}

	public Consulta(int id, int medico, Date fecha, String diagnostico) {
		this.codigo = id;
		this.medico = medico;
		this.fecha = fecha;
		this.diagnostico = diagnostico;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int id) {
		this.codigo = id;
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
		return "Consulta [id=" + codigo + ", medico=" + medico + ", fecha=" + fecha + ", diagnostico=" + diagnostico + "]";
	}
	
	
}
