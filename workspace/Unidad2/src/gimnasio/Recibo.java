package gimnasio;

import java.util.Date;

public class Recibo {
	private Cliente cliente;
	private Date fEmision, fPagado;
	private float cuantia;
	private boolean pagado;
	
	public Recibo() {
	}
	
	public Recibo(Cliente cliente, Date fEmision, Date fPagado, float cuantia, boolean pagado) {
		this.cliente = cliente;
		this.fEmision = fEmision;
		this.fPagado = fPagado;
		this.cuantia = cuantia;
		this.pagado = pagado;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getfEmision() {
		return fEmision;
	}
	public void setfEmision(Date fEmision) {
		this.fEmision = fEmision;
	}
	public Date getfPagado() {
		return fPagado;
	}
	public void setfPagado(Date fPagado) {
		this.fPagado = fPagado;
	}
	public float getCuantia() {
		return cuantia;
	}
	public void setCuantia(float cuantia) {
		this.cuantia = cuantia;
	}
	public boolean isPagado() {
		return pagado;
	}
	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
	@Override
	public String toString() {
		return "Recibo [cliente=" + cliente + ", fEmision=" + fEmision + ", fPagado=" + fPagado + ", cuantia=" + cuantia
				+ ", pagado=" + pagado + "]";
	}
	
	
}
