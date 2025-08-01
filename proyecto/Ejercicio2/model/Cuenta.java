package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
	//Atributos
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected List<Transaccion> transacciones;
	
	//Constructor
	public Cuenta(int numero, double saldo, Cliente cliente) {
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
		this.transacciones = new ArrayList<>();

	}
	
	//Getters
	public int getNumero() {
		return numero;
	}
	public double getSaldo() {
		return saldo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public List<Transaccion> getTransacciones() {
		return transacciones;
	}
	
	//Metodo para realizaar un deposito
	public double deposito(double monto) {
		saldo+= monto;
		
		Transaccion t = new Transaccion(monto,Hora.getInstancia().fechaActual(),TipoTransaccion.Deposito,cliente);
		transacciones.add(t);
		
		return saldo;
				
	}
	
	//Metodo para realizar el retiro de dinero
	public boolean retiro(double monto) {
		if(!checkCuenta(monto)) {
			throw new RuntimeException("Saldo insufieciente, no se ha podido realizar el retiro :( ");
		}
		
		saldo -= monto;
		
		Transaccion t = new Transaccion(monto,Hora.getInstancia().fechaActual(),TipoTransaccion.Deposito,cliente);
		transacciones.add(t);
		
		return true;
	}
	
	public abstract boolean checkCuenta(double monto);
	
}
