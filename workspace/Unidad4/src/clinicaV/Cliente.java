package clinicaV;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="cliente")
//Atributo name es para indicar el nombre de la tabla
//Si es el mismo se puede obviar
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Cliente es pk autonumérica
	private int codigo;
	
	@Column(nullable = false)
	private String nombre;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String telefono;
	
	
	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Cliente() {
	}


	public Cliente(int codigo, String nombre, String email, String telefono) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
	}

	public void mostrar() {
		System.out.println("Codigo:"+codigo + 
				"\tNombre:"+ nombre + 
				"\tDireccion:" + email + 
				"\tTeléfono:"+telefono);
	}
	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
