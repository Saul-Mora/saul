package model;

import java.util.List;
import java.util.ArrayList;

import java.util.ArrayList;

public class Banco {
    private String nombre;
    private ArrayList<Cliente> clientes;
    private ArrayList<Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public String getNombre() {
        return nombre;
    }

    // Buscar una cuenta por nombre de cliente
    public Cuenta buscarCuentaPorCliente(String nombreCliente) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.cliente.getNombre().equalsIgnoreCase(nombreCliente)) {
                return cuenta;
            }
        }
        return null;
    }
}

	
	
	

	


