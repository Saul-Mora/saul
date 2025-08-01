package model;

import java.time.LocalDateTime;

public class Transaccion {
	//Atributos
	private double monto;
	private String fecha;
	private TipoTransaccion tipo;
	private Cliente cliente;
	
	//Constructor
	public Transaccion(double monto, String fecha, TipoTransaccion tipo, Cliente cliente) {
		this.monto = monto;
		this.fecha = fecha;
		this.tipo = tipo;
		this.cliente = cliente;
	}

	//getters necesarios
	public double getMonto() {
		return monto;
	}

	public String getFecha() {
		return fecha;
	}

	public TipoTransaccion getTipo() {
		return tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	@Override
	public String toString() {
		
		return "Transaccion{" + "tipo= " + tipo + ", monto= " + monto + ", fecha= " + fecha + ", cliente= " + cliente.getNombre() + '}';
	}

}
