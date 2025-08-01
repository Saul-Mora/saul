package model;

public class CajaAhorro extends Cuenta {
	// Constructor: crea una caja de ahorro con número de cuenta y dueño
    public CajaAhorro(int numero, double saldo, Cliente cliente) {
		super(numero, saldo, cliente);
		// TODO Auto-generated constructor stub
	}

	// Solo se pueden hacer 5 transacciones por año
    private static final int LIMITE_TRANSACCIONES_ANUALES = 5;

    // Verifica si se puede hacer una transacción (como un retiro)
    @Override
    public boolean checkCuenta(double monto) {
        // Saca el año actual usando la clase Hora
        int añoActual = Hora.getInstancia().año(Hora.getInstancia().fechaActual());

        int transaccionesEsteAño = 0;

        // Recorre todas las transacciones que tiene esta cuenta
        for (Transaccion t : this.getTransacciones()) {
            String fecha = t.getFecha(); // Fecha de la transacción
            int añoTransaccion = Hora.getInstancia().año(fecha); // Saca el año de esa fecha

            // Si la transacción fue este mismo año, la contamos
            if (añoTransaccion == añoActual) {
                transaccionesEsteAño++;
            }
        }

        // Si ya tiene 5 transacciones este año, no se permite hacer otra
        if (transaccionesEsteAño >= LIMITE_TRANSACCIONES_ANUALES) {
            return false;
        }

        // También revisa si tiene saldo suficiente para el retiro
        return this.getSaldo() >= monto;
    }
}
