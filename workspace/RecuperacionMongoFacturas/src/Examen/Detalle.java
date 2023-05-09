package Examen;

public class Detalle {

	private String producto;
	private int cantidad;
	private float precioUnidad;
	public Detalle() {
	}
	public Detalle(String producto, int cantidad, float precioUnidad) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioUnidad = precioUnidad;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecioUnidad() {
		return precioUnidad;
	}
	public void setPrecioUnidad(float precioUnidad) {
		this.precioUnidad = precioUnidad;
	}
	@Override
	public String toString() {
		return "Detalle [producto=" + producto + ", cantidad=" + cantidad + ", precioUnidad=" + precioUnidad + ", total linea: "+precioUnidad*cantidad+"]";
	}
	
	
}
