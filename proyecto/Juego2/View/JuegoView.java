package view; // Paquete donde está ubicada la clase

// Importación de librerías JavaFX necesarias
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Importación de las clases del modelo que contienen la lógica del juego
import model.*;

public class JuegoView extends Application { // Clase principal que extiende Application para usar JavaFX

    // Variables para manejar la lógica y la interfaz
    private Juego juego;           // Objeto que maneja el modelo del juego
    private Label mensaje;         // Etiqueta que muestra el turno actual o el ganador
    private GridPane tableroGrid;  // Cuadrícula visual que representa el tablero

    // Variables para guardar los nombres de los jugadores ingresados en la pantalla inicial
    private String nombreJ1 = "Jugador 1";
    private String nombreJ2 = "Jugador 2";

    @Override
    public void start(Stage primaryStage) { // Método que se ejecuta al iniciar la aplicación

        // ----------- ESCENA DE INICIO -----------
        VBox inicioRoot = new VBox(15); // Contenedor vertical con espacio de 15px entre elementos
        inicioRoot.setAlignment(Pos.CENTER); // Centra todos los elementos
        inicioRoot.setStyle("-fx-padding: 20px; -fx-background-color: #F0F8FF;"); // Margen y color de fondo

        // Título del juego
        Label titulo = new Label("4 en Línea");
        titulo.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        // Campo de texto para el nombre del Jugador 1
        TextField nombre1Field = new TextField();
        nombre1Field.setPromptText("Nombre del Jugador 1 (Fichas Rojas)");

        // Campo de texto para el nombre del Jugador 2
        TextField nombre2Field = new TextField();
        nombre2Field.setPromptText("Nombre del Jugador 2 (Fichas Blancas)");

        // Botón para comenzar el juego
        Button comenzarBtn = new Button("Comenzar Juego");
        comenzarBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");

        // Agregar los elementos al contenedor de inicio
        inicioRoot.getChildren().addAll(titulo, nombre1Field, nombre2Field, comenzarBtn);

        // Crear la escena de inicio
        Scene escenaInicio = new Scene(inicioRoot, 400, 300);

        // Acción cuando se presiona el botón "Comenzar Juego"
        comenzarBtn.setOnAction(e -> {
            // Guardar los nombres si no están vacíos
            if (!nombre1Field.getText().trim().isEmpty()) {
                nombreJ1 = nombre1Field.getText().trim();
            }
            if (!nombre2Field.getText().trim().isEmpty()) {
                nombreJ2 = nombre2Field.getText().trim();
            }
            // Cambiar a la escena del juego
            mostrarEscenaJuego(primaryStage);
        });

        // Configuración inicial de la ventana
        primaryStage.setTitle("Juego 4 en Línea");
        primaryStage.setScene(escenaInicio); // Mostrar escena de inicio
        primaryStage.show();
    }

    // Método que construye la escena del juego principal
    private void mostrarEscenaJuego(Stage stage) {
        // Crear jugadores con los nombres ingresados y sus fichas
        Jugador j1 = new Jugador(nombreJ1, "R");
        Jugador j2 = new Jugador(nombreJ2, "B");

        // Inicializar el juego con los dos jugadores
        juego = new Juego(j1, j2);

        // Contenedor principal
        BorderPane root = new BorderPane();

        // Mensaje superior indicando el turno actual
        mensaje = new Label("Turno de: " + juego.getJugadorActual().getNombre());
        mensaje.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-padding: 10px;");
        root.setTop(mensaje);
        BorderPane.setAlignment(mensaje, Pos.CENTER);

        // Crear tablero visual
        tableroGrid = new GridPane();
        tableroGrid.setAlignment(Pos.CENTER);
        tableroGrid.setStyle("-fx-background-color: #DDEEFF; -fx-padding: 15px; -fx-hgap: 5px; -fx-vgap: 5px;");
        actualizarTablero(); // Dibujar el tablero vacío

        // Crear botones para colocar fichas en cada columna
        HBox botonesColumna = new HBox(5);
        botonesColumna.setAlignment(Pos.CENTER);
        for (int col = 0; col < Tablero.COLUMNAS; col++) {
            int finalCol = col; // Guardar valor para usar en la lambda
            Button boton = new Button("Col " + (col + 1));
            boton.setOnAction(e -> manejarTurno(finalCol)); // Acción al presionar
            boton.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
            botonesColumna.getChildren().add(boton);
        }

        // Botones para iniciar y reiniciar el juego
        HBox botonesControl = new HBox(10);
        botonesControl.setAlignment(Pos.CENTER);
        Button iniciarBtn = new Button("Iniciar Juego");
        Button reiniciarBtn = new Button("Reiniciar Juego");

        // Acción de iniciar juego
        iniciarBtn.setOnAction(e -> {
            juego.reiniciar();
            actualizarTablero();
            mensaje.setText("Juego iniciado. Turno de: " + juego.getJugadorActual().getNombre());
        });

        // Acción de reiniciar juego
        reiniciarBtn.setOnAction(e -> {
            juego.reiniciar();
            actualizarTablero();
            mensaje.setText("Juego reiniciado. Turno de: " + juego.getJugadorActual().getNombre());
        });

        // Estilo de los botones de control
        iniciarBtn.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
        reiniciarBtn.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold;");

        // Agregar los botones de control al contenedor
        botonesControl.getChildren().addAll(iniciarBtn, reiniciarBtn);

        // Contenedor inferior con botones de columnas y control
        VBox panelInferior = new VBox(10, botonesColumna, botonesControl);
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setStyle("-fx-padding: 10px;");

        // Agregar tablero y panel inferior al layout principal
        root.setCenter(tableroGrid);
        root.setBottom(panelInferior);

        // Crear escena del juego y mostrarla
        Scene escenaJuego = new Scene(root, 700, 600);
        stage.setScene(escenaJuego);
    }

    // Método que gestiona la lógica cuando un jugador hace un movimiento
    private void manejarTurno(int columna) {
        // Verificar si la columna está llena antes de jugar
        if (!juego.getTablero().columnaDisponible(columna)) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Columna llena");
            alerta.setHeaderText(null);
            alerta.setContentText("La columna " + (columna + 1) + " ya está llena. Elige otra columna.");
            alerta.showAndWait(); // Mostrar aviso
            return; // Salir sin colocar ficha
        }

        // Si el movimiento es válido, se procede normalmente
        if (juego.jugarTurno(columna)) {
            if (juego.hayGanador()) { // Si hay ganador
                mensaje.setText("Ganó: " + juego.getJugadorActual().getNombre());
            } else if (juego.tableroLleno()) { // Si hay empate
                mensaje.setText("Empate: tablero lleno");
            } else { // Si el juego continúa
                juego.cambiarTurno();
                mensaje.setText("Turno de: " + juego.getJugadorActual().getNombre());
            }
            actualizarTablero(); // Actualizar tablero visual
        }
    }

    // Método para redibujar el tablero
    private void actualizarTablero() {
        tableroGrid.getChildren().clear(); // Limpiar tablero
        Tablero tablero = juego.getTablero();
        for (int fila = 0; fila < Tablero.FILAS; fila++) {
            for (int col = 0; col < Tablero.COLUMNAS; col++) {
                Ficha ficha = tablero.getFicha(fila, col);
                Label celda = new Label();
                celda.setMinSize(50, 50);
                // Definir color según la ficha
                celda.setStyle(
                        "-fx-border-color: black; -fx-alignment: center; -fx-border-radius: 5px;" +
                        (ficha == null
                                ? "-fx-background-color: lightgray;"
                                : (ficha.getColor().equals("R")
                                    ? "-fx-background-color: red;"
                                    : "-fx-background-color: white;"))
                );
                tableroGrid.add(celda, col, fila);
            }
        }
    }

    // Método main para ejecutar la aplicación
    public static void main(String[] args) {
        launch(args);
    }
}
