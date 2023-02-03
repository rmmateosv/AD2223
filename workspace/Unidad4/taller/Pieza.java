package taller;

public class Pieza {
	
	private String codigo;
	private String[] clase;
	private String descripcion;
	private float precio;
	private int stock;
	
	public Pieza() {
		
	}

	public Pieza(String codigo, String[] clase, String descripcion, float precio, int stock) {
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

	public String[] getClase() {
		return clase;
	}

	public void setClase(String[] clase) {
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
