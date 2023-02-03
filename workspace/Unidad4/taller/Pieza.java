package taller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name='pieza')
public class Pieza {
	
	private enum tipo {motor, filtro, otros}
	
	@Id
	private String codigo;
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private tipo clase;
	@Column(nullable = false)
	private String descripcion;
	private float precio;
	private int stock;
	
	public Pieza() {
		
	}

	public Pieza(String codigo, tipo clase, String descripcion, float precio, int stock) {
		this.codigo = codigo;
		this.clase = clase;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	}
	
	public void mostrar() {
		System.out.println("Codigo: " +codigo
							+"\tClase: " +clase
							+"\tDescripcion: " +descripcion
							+"\tPrecio: " +precio
							+"\tStock: " +stock);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public tipo getClase() {
		return clase;
	}

	public void setClase(tipo clase) {
		this.clase = clase;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
