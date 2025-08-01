package model;

public class Corriente extends Cuenta{

	//Constructor
	public Corriente(int numero, double saldo, Cliente cliente) {
		super(numero, saldo, cliente);
	}

	@Override
	public boolean checkCuenta(double monto) {
		return monto <= saldo;
	}
	
}
