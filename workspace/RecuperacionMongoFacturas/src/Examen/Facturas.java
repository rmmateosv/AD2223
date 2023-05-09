package Examen;

import java.util.ArrayList;
import java.util.Date;

public class Facturas {

	private int numero;
	private Date fecha;
	private String cliente;
	private ArrayList<Detalle> detalle= new ArrayList();
	private int facturaAnulacion;
	
	public Facturas() {
	}
	
	public Facturas(int numero, Date fecha, String cliente, ArrayList<Detalle> detalle, int facturaAnulacion) {
		this.numero = numero;
		this.fecha = fecha;
		this.cliente = cliente;
		this.detalle = detalle;
		this.facturaAnulacion = facturaAnulacion;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Detalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(ArrayList<Detalle> detalle) {
		this.detalle = detalle;
	}
	public int getFacturaAnulacion() {
		return facturaAnulacion;
	}
	public void setFacturaAnulacion(int facturaAnulacion) {
		this.facturaAnulacion = facturaAnulacion;
	}
	@Override
	public String toString() {
		String resultado=  "Facturas [numero=" + numero + ", fecha=" + 
	fecha + ", cliente=" + cliente +  ", facturaAnulacion=" + facturaAnulacion + "]";
		float totalFactura=0;
		for(Detalle d : detalle) {
			resultado+="\n"+d.toString();
			totalFactura+=d.getCantidad()*d.getPrecioUnidad();
		}
		resultado+="Total Factura= "+totalFactura+"************";
		return resultado;
		
	}
	
	
	
}
