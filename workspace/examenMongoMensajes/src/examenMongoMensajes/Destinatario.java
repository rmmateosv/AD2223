package examenMongoMensajes;

public class Destinatario {
	private String dni;
	private boolean leido;
 
	
@Override
	public String toString() {
		return "Destinatario [dni=" + dni + ", leido=" + leido + "]";
	}

public Destinatario() {
}

public Destinatario(String dni, boolean leido) {
	this.dni = dni;
	this.leido = leido;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public boolean isLeido() {
	return leido;
}
public void setLeido(boolean leido) {
	this.leido = leido;
}
 
 
}
