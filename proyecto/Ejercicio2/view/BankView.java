package view; // Paquete al que pertenece esta clase (carpeta 'view')

// Importación de clases de JavaFX para construir la interfaz gráfica
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Importa las clases del modelo (Cliente, Cuenta, etc.)
import model.*;

import java.util.ArrayList; 
import java.util.List;

public class BankView {

    // Lista de clientes y cuentas que estarán en memoria
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();

    // Área de texto donde se muestran los mensajes del sistema
    private TextArea outputArea;

    // Contadores para asignar IDs únicos a clientes y cuentas
    private int clienteIdCounter = 1;
    private int cuentaIdCounter = 1000;

    /**
     * Método principal que muestra toda la interfaz
     */
    public void show(Stage stage) {
        stage.setTitle("Sistema Bancario - JavaFX"); // Título de la ventana

        // Contenedor principal vertical con espacio entre componentes
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 15; -fx-background-color: #f0f0f0;"); // Espaciado y color de fondo

        // Área de texto donde se muestra la información del sistema
        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setPrefHeight(200); // Altura preferida

        // Secciones principales de la interfaz
        VBox clienteSection = crearSeccionCliente();       // Crear cliente
        VBox cuentaSection = crearSeccionCuenta();         // Crear cuenta
        VBox transaccionesSection = crearSeccionTransacciones(); // Realizar transacciones
        
 

        // Agrega todas las secciones al contenedor principal
        root.getChildren().addAll(
            clienteSection,
            cuentaSection,
            transaccionesSection,
            new Label("Registro de operaciones:"),
            outputArea
        );

        // Crear y mostrar la escena
        Scene scene = new Scene(root, 650, 600);
        stage.setScene(scene);
        stage.show();
        
        
        Button btnSalir = new Button("Salir");

		 // Cuando haces clic, se cierra la ventana o el programa
		 btnSalir.setOnAction(e -> {
		     System.exit(0);  // Cierra toda la aplicación
		 });

			   VBox rootw = new VBox();
			   root.getChildren().add(btnSalir);
    }

    /**
     * Sección para crear un cliente nuevo
     */
    private VBox crearSeccionCliente() {
        VBox box = new VBox(5);
        box.getChildren().add(new Label("Crear Cliente"));

        TextField nombreField = new TextField();
        nombreField.setPromptText("Nombre del cliente");

        TextField telefonoField = new TextField();
        telefonoField.setPromptText("Teléfono");

        Button btnCrearCliente = new Button("Crear Cliente");
        btnCrearCliente.setOnAction(e -> {
            String nombre = nombreField.getText().trim();
            String telefono = telefonoField.getText().trim();

            if (!nombre.isEmpty() && !telefono.isEmpty()) {
                // Crear cliente con ID automático
                Cliente cliente = new Cliente(clienteIdCounter++, nombre, telefono);
                clientes.add(cliente);
                mostrarMensaje("Cliente creado: " + cliente.getNombre() + " (ID: " + cliente.getID() + ")");
                nombreField.clear();
                telefonoField.clear();
            } else {
                mostrarMensaje("⚠️ Por favor, completa todos los campos del cliente.");
            }
        });

        // Agrega los campos y botón al panel
        box.getChildren().addAll(nombreField, telefonoField, btnCrearCliente);
        box.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
        return box;
    }

    /**
     * Sección para crear una cuenta bancaria
     */
    private VBox crearSeccionCuenta() {
        VBox box = new VBox(5);
        box.getChildren().add(new Label("Crear Cuenta"));

        TextField idClienteField = new TextField();
        idClienteField.setPromptText("ID del Cliente");

        ComboBox<String> tipoCuentaBox = new ComboBox<>();
        tipoCuentaBox.getItems().addAll("Corriente", "Caja de Ahorro");
        tipoCuentaBox.setValue("Corriente");

        Button btnCrearCuenta = new Button("Crear Cuenta");
        btnCrearCuenta.setOnAction(e -> {
            try {
                int idCliente = Integer.parseInt(idClienteField.getText());
                Cliente cliente = buscarClientePorId(idCliente);
                if (cliente == null) {
                    mostrarMensaje("❌ Cliente no encontrado.");
                    return;
                }

                // Crea una cuenta del tipo seleccionado con saldo inicial 0.0
                Cuenta cuenta;
                if (tipoCuentaBox.getValue().equals("Corriente")) {
                    cuenta = new Corriente(cuentaIdCounter++, 0.0, cliente);
                } else {
                    cuenta = new CajaAhorro(cuentaIdCounter++, 0.0, cliente);
                }

                cuentas.add(cuenta);
                mostrarMensaje("✅ Cuenta creada: " + cuenta.getNumero() + " para " + cliente.getNombre());
                idClienteField.clear();
            } catch (NumberFormatException ex) {
                mostrarMensaje("⚠️ El ID del cliente debe ser numérico.");
            }
        });

        box.getChildren().addAll(idClienteField, tipoCuentaBox, btnCrearCuenta);
        box.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
        return box;
    }

    /**
     * Sección para hacer depósitos o retiros
     */
    private VBox crearSeccionTransacciones() {
        VBox box = new VBox(5);
        box.getChildren().add(new Label("Transacciones (Depósito / Retiro)"));

        TextField cuentaIdField = new TextField();
        cuentaIdField.setPromptText("Número de cuenta");

        TextField montoField = new TextField();
        montoField.setPromptText("Monto");

        ComboBox<String> tipoTransaccionBox = new ComboBox<>();
        tipoTransaccionBox.getItems().addAll("Depositar", "Retirar");
        tipoTransaccionBox.setValue("Depositar");

        Button btnTransaccion = new Button("Ejecutar");
        btnTransaccion.setOnAction(e -> {
            try {
                int cuentaId = Integer.parseInt(cuentaIdField.getText());
                double monto = Double.parseDouble(montoField.getText());

                Cuenta cuenta = buscarCuentaPorNumero(cuentaId);
                if (cuenta == null) {
                    mostrarMensaje("❌ Cuenta no encontrada.");
                    return;
                }

                // Realiza la operación según la opción elegida
                if (tipoTransaccionBox.getValue().equals("Depositar")) {
                    cuenta.deposito(monto);
                    mostrarMensaje("💰 Depósito exitoso. Nuevo saldo: $" + cuenta.getSaldo());
                } else {
                    boolean ok = cuenta.retiro(monto);
                    if (ok) {
                        mostrarMensaje("💸 Retiro exitoso. Nuevo saldo: $" + cuenta.getSaldo());
                    } else {
                        mostrarMensaje("❌ Retiro fallido. Fondos insuficientes o límite.");
                    }
                }

                cuentaIdField.clear();
                montoField.clear();

            } catch (NumberFormatException ex) {
                mostrarMensaje("⚠️ Verifica que el número de cuenta y el monto sean válidos.");
            }
        });

        box.getChildren().addAll(cuentaIdField, montoField, tipoTransaccionBox, btnTransaccion);
        box.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-padding: 10;");
        return box;
    }

    /**
     * Busca un cliente por su ID
     */
    private Cliente buscarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getID() == id) return c;
        }
        return null;
    }

    /**
     * Busca una cuenta por su número
     */
    private Cuenta buscarCuentaPorNumero(int numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    /**
     * Muestra un mensaje en el área de texto
     */
    private void mostrarMensaje(String msg) {
        outputArea.appendText(msg + "\n");
    }
    
    

    

}
