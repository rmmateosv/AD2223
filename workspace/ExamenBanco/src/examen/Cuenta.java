package examen;

import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable{
	private int numero;
	private float saldo;
	private ArrayList<Movimiento> movimientos = new ArrayList<>();
	public Cuenta() {
	}
	public Cuenta(int numero, float saldo, ArrayList<Movimiento> movimientos) {
		this.numero = numero;
		this.saldo = saldo;
		this.movimientos = movimientos;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public ArrayList<Movimiento> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(ArrayList<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	@Override
	public String toString() {
		String resultado = "Cuenta [numero=" + numero + ", saldo=" + saldo;
		resultado += "\nMovimientos:";
		for(Movimiento m: movimientos) {
			resultado += m.toString() + "\n";
		}
		return  resultado;
	}
	
	
}
