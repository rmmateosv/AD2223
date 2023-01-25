package clinicaV;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ConsultaClave {
	@ManyToOne
	@JoinColumn(referencedColumnName = "codigo")
	//referencedColumnName contiene el nombre del campo
	// de la clase Mascota que hace de pk
	private Mascota mascota;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	public ConsultaClave(Mascota mascota, Date fecha) {
		this.mascota = mascota;
		this.fecha = fecha;
	}
	public ConsultaClave() {
	}
	public Mascota getMascota() {
		return mascota;
	}
	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	

}
