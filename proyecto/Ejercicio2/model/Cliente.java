package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	//Atributos
	protected int ID; 
	protected String nombre;
	protected String telefono;
	protected List<Cuenta> cuentas; 
	
	//Constructor
	public Cliente(int iD, String nombre, String telefono) {
		this.ID = iD;
		this.nombre = nombre;
		this.telefono = telefono;
		this.cuentas = new ArrayList<>();
	}



	//Getters y setters
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

	public List<Cuenta> getCuentas() {
		return cuentas;
	}



	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}



	//Metodos para agregar y eliminar una cuenta
	public void agregarCuenta(Cuenta cuenta) {
		cuentas.add(cuenta);
	}
	
	public void eliminarCuenta(Cuenta cuenta) {
		cuentas.remove(cuenta);
	}
	
	
	
	@Override
	public String toString() {
		return "Cliente [ID=" + ID + ", nombre=" + nombre + ", telefono=" + telefono + "]";
	} 
	
	
	
	
	
	
	

}
