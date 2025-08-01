package viewww; // Paquete donde se encuentra esta clase

// Importaciones necesarias de JavaFX
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

// Importaciones de las clases del modelo
import Modeel.Colaborador;
import Modeel.Donacion;
import Modeel.Investigador;
import Modeel.Proyecto;
import Modeel.ProyectoEmprendimiento;

public class RegistroProyectoView extends Application {

    // Lista de investigadores disponibles para elegir
    private final ObservableList<Investigador> investigadoresDisponibles = FXCollections.observableArrayList();

    // Lista de proyectos registrados
    private final ObservableList<Proyecto> proyectosRegistrados = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registro de Proyectos de Emprendimiento");

        // ===== PANTALLA DE INICIO =====
        VBox inicioLayout = new VBox(20);
        inicioLayout.setAlignment(Pos.CENTER);
        inicioLayout.setPadding(new Insets(50));
        inicioLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #2193b0, #6dd5ed);");

        Label tituloInicio = new Label("Bienvenido al Sistema");
        tituloInicio.setFont(new Font("Arial", 36));
        tituloInicio.setTextFill(Color.WHITE);

        Button btnIrFormulario = new Button("Crear un Proyecto");
        btnIrFormulario.setFont(new Font("Arial", 20));
        btnIrFormulario.setStyle("-fx-background-color: #ff9800; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 10 20;");

        inicioLayout.getChildren().addAll(tituloInicio, btnIrFormulario);
        Scene inicioScene = new Scene(inicioLayout, 600, 400);

        // ===== FORMULARIO =====
        TextField txtNombreResponsable = new TextField();
        TextField txtNombreProyecto = new TextField();
        TextArea txtDescripcion = new TextArea();
        TextField txtUrlVideo = new TextField();
        TextField txtMonto = new TextField();

        ComboBox<Investigador> comboInvestigadores = new ComboBox<>(investigadoresDisponibles);
        comboInvestigadores.setPromptText("Seleccionar investigador asociado");

        ListView<Investigador> listaAsociados = new ListView<>();
        ObservableList<Investigador> seleccionados = FXCollections.observableArrayList();
        listaAsociados.setItems(seleccionados);
        listaAsociados.setPrefHeight(100);

        Button btnAgregarInvestigador = new Button("Agregar Investigador");
        btnAgregarInvestigador.setOnAction(e -> {
            Investigador inv = comboInvestigadores.getValue();
            if (inv != null && !seleccionados.contains(inv) && seleccionados.size() < 4) {
                seleccionados.add(inv);
            }
        });

        Button btnRegistrar = new Button("Registrar Proyecto");
        btnRegistrar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 10;");

        // ===== TABLA DE PROYECTOS =====
        TableView<Proyecto> tablaProyectos = new TableView<>(proyectosRegistrados);
        TableColumn<Proyecto, String> colNombre = new TableColumn<>("Nombre Proyecto");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Proyecto, Double> colMonto = new TableColumn<>("Monto");
        colMonto.setCellValueFactory(new PropertyValueFactory<>("montoObjetivo"));

        TableColumn<Proyecto, String> colResponsable = new TableColumn<>("Responsable");
        colResponsable.setCellValueFactory(cellData ->
                Bindings.createStringBinding(() -> cellData.getValue().getResponsable().getNombre()));

        tablaProyectos.getColumns().addAll(colNombre, colMonto, colResponsable);
        tablaProyectos.setPrefHeight(200);

        // ===== DOBLE CLIC EN LA TABLA PARA VER DETALLES =====
        tablaProyectos.setRowFactory(tv -> {
            TableRow<Proyecto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Proyecto proyecto = row.getItem();
                    mostrarDetallesProyecto((ProyectoEmprendimiento) proyecto);
                }
            });
            return row;
        });

        // ===== CAMPOS DONACIÓN =====
        TextField txtNombreColab = new TextField();
        txtNombreColab.setPromptText("Nombre del colaborador");

        TextField txtMontoDonacion = new TextField();
        txtMontoDonacion.setPromptText("Monto a donar");

        Button btnDonar = new Button("Realizar Donación");
        btnDonar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-background-radius: 10;");

        // ===== BOTÓN DONAR CON MONTO FALTANTE =====
        btnDonar.setOnAction(e -> {
            Proyecto seleccionado = tablaProyectos.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                showAlert("Error", "Debes seleccionar un proyecto de la tabla para donar.");
                return;
            }

            try {
                double monto = Double.parseDouble(txtMontoDonacion.getText());
                String nombreColab = txtNombreColab.getText().trim();

                if (nombreColab.isEmpty()) {
                    throw new IllegalArgumentException("El nombre del colaborador no puede estar vacío.");
                }

                Donacion donacion = Donacion.crearDonacion(monto, new Colaborador(nombreColab), (ProyectoEmprendimiento) seleccionado);

                // Calcular monto faltante
                double faltante = ((ProyectoEmprendimiento) seleccionado).getMontoObjetivo() -
                                  ((ProyectoEmprendimiento) seleccionado).getMontoRecaudado();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Donación registrada con éxito:\n" + donacion +
                        "\n\nMonto faltante para alcanzar el objetivo: $" + String.format("%.2f", faltante)
                );
                alert.showAndWait();

            } catch (NumberFormatException ex) {
                showAlert("Error", "El monto ingresado no es válido.");
            } catch (IllegalArgumentException ex) {
                showAlert("Error", ex.getMessage());
            }
        });

        // ===== FORMULARIO LAYOUT =====
        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setVgap(5);
        form.setHgap(5);
        form.setStyle("-fx-background-color: linear-gradient(to bottom, #ffecd2, #fcb69f);");

        form.add(new Label("Nombre Responsable:"), 0, 0);
        form.add(txtNombreResponsable, 1, 0);
        form.add(new Label("Nombre Proyecto:"), 0, 1);
        form.add(txtNombreProyecto, 1, 1);
        form.add(new Label("Descripción:"), 0, 2);
        form.add(txtDescripcion, 1, 2);
        form.add(new Label("URL Video:"), 0, 3);
        form.add(txtUrlVideo, 1, 3);
        form.add(new Label("Monto Objetivo:"), 0, 4);
        form.add(txtMonto, 1, 4);
        form.add(new Label("Investigadores Asociados:"), 0, 5);
        form.add(comboInvestigadores, 1, 5);
        form.add(btnAgregarInvestigador, 2, 5);
        form.add(listaAsociados, 1, 6);
        form.add(btnRegistrar, 1, 7);

        // Campos donación
        form.add(new Label("Nombre Colaborador:"), 0, 8);
        form.add(txtNombreColab, 1, 8);
        form.add(new Label("Monto Donación:"), 0, 9);
        form.add(txtMontoDonacion, 1, 9);
        form.add(btnDonar, 1, 10);

        VBox layout = new VBox(10, form, tablaProyectos);
        layout.setPadding(new Insets(10));
        Scene formScene = new Scene(layout, 850, 700);

        // ===== BOTÓN REGISTRAR PROYECTO =====
        btnRegistrar.setOnAction(e -> {
            try {
                String nombreResp = txtNombreResponsable.getText();
                String nombreProj = txtNombreProyecto.getText();
                String descripcion = txtDescripcion.getText();
                String url = txtUrlVideo.getText();
                double monto = Double.parseDouble(txtMonto.getText());

                Investigador responsable = new Investigador(nombreResp, nombreProj, descripcion);
                Set<Investigador> asociados = new HashSet<>(seleccionados);

                ProyectoEmprendimiento p = new ProyectoEmprendimiento(
                        nombreProj, descripcion, url, responsable, monto, 0.0
                );
                p.getInvestigadorAsociado().addAll(asociados);

                Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmAlert.setTitle("Confirmar Registro");
                confirmAlert.setHeaderText("¿Deseas registrar este proyecto?");
                confirmAlert.setContentText(
                        "Nombre: " + nombreProj + "\n" +
                        "Responsable: " + nombreResp + "\n" +
                        "Monto Objetivo: $" + monto
                );

                confirmAlert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        proyectosRegistrados.add(p);
                        mostrarVentanaProyecto(p);
                        txtNombreResponsable.clear();
                        txtNombreProyecto.clear();
                        txtDescripcion.clear();
                        txtUrlVideo.clear();
                        txtMonto.clear();
                        seleccionados.clear();
                        comboInvestigadores.getSelectionModel().clearSelection();
                    } else {
                        Alert cancelAlert = new Alert(Alert.AlertType.INFORMATION);
                        cancelAlert.setTitle("Registro Cancelado");
                        cancelAlert.setHeaderText(null);
                        cancelAlert.setContentText("El proyecto no fue registrado.");
                        cancelAlert.showAndWait();
                    }
                });

            } catch (NumberFormatException ex) {
                showAlert("Error", "Monto inválido");
            }
        });

        btnIrFormulario.setOnAction(e -> primaryStage.setScene(formScene));
        primaryStage.setScene(inicioScene);
        primaryStage.show();

        // Lista inicial de investigadores
        investigadoresDisponibles.addAll(
                new Investigador("Ana", "ana@email.com", "ESPOL"),
                new Investigador("Luis", "luis@email.com", "EPN"),
                new Investigador("Lucía", "lucia@email.com", "ESPE"),
                new Investigador("Marco", "marco@email.com", "PUCE")
        );
    }

    // ===== VENTANA CON DETALLES DE UN PROYECTO =====
    private void mostrarDetallesProyecto(ProyectoEmprendimiento p) {
        Stage ventana = new Stage();
        ventana.setTitle("Detalles del Proyecto");

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: linear-gradient(to right, #00b4db, #0083b0);");

        Label titulo = new Label("Detalles del Proyecto");
        titulo.setFont(new Font("Arial", 24));
        titulo.setTextFill(Color.WHITE);

        StringBuilder info = new StringBuilder();
        info.append("Nombre: ").append(p.getNombre()).append("\n")
            .append("Descripción: ").append(p.getDescripcion()).append("\n")
            .append("URL Video: ").append(p.getUrlVideo()).append("\n")
            .append("Responsable: ").append(p.getResponsable().getNombre()).append("\n")
            .append("Monto Objetivo: $").append(p.getMontoObjetivo()).append("\n")
            .append("Monto Recaudado: $").append(p.getMontoRecaudado()).append("\n")
            .append("Investigadores Asociados: ");

        if (p.getInvestigadorAsociado().isEmpty()) {
            info.append("Ninguno");
        } else {
            p.getInvestigadorAsociado().forEach(inv -> info.append("\n - ").append(inv.getNombre()));
        }

        Label lblInfo = new Label(info.toString());
        lblInfo.setTextFill(Color.WHITE);
        lblInfo.setFont(new Font("Arial", 16));
        lblInfo.setWrapText(true);

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setOnAction(e -> ventana.close());
        btnCerrar.setStyle("-fx-background-color: #ff4b1f; -fx-text-fill: white; -fx-background-radius: 10;");

        layout.getChildren().addAll(titulo, lblInfo, btnCerrar);
        Scene scene = new Scene(layout, 450, 400);
        ventana.setScene(scene);
        ventana.show();
    }

    // Mensaje de error genérico
    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Ventana que muestra un proyecto recién registrado
    private void mostrarVentanaProyecto(ProyectoEmprendimiento p) {
        Stage ventana = new Stage();
        ventana.setTitle("Proyecto Registrado");

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: linear-gradient(to right, #8360c3, #2ebf91);");

        Label titulo = new Label("Proyecto Registrado con Éxito");
        titulo.setFont(new Font("Arial", 26));
        titulo.setTextFill(Color.WHITE);

        Label lblInfo = new Label(
                "Nombre: " + p.getNombre() + "\n" +
                "Descripción: " + p.getDescripcion() + "\n" +
                "URL Video: " + p.getUrlVideo() + "\n" +
                "Responsable: " + p.getResponsable().getNombre() + "\n" +
                "Monto Objetivo: $" + p.getMontoObjetivo()
        );
        lblInfo.setTextFill(Color.WHITE);
        lblInfo.setFont(new Font("Arial", 16));

        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setOnAction(e -> ventana.close());
        btnCerrar.setStyle("-fx-background-color: #ff4b1f; -fx-text-fill: white; -fx-background-radius: 10;");

        layout.getChildren().addAll(titulo, lblInfo, btnCerrar);
        Scene scene = new Scene(layout, 400, 300);
        ventana.setScene(scene);
        ventana.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
