package view1;

import javafx.geometry.Pos; 
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model1.Movimiento;
import model1.Tablero;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class GameView extends VBox {

    private GridPane grid;
    private Tablero tablero;
    private Movimiento movimiento;
    private Label statusLabel;
    private int movimientos;

    public GameView() {
    	
    	//Cambia el tipo de letra 
    	Font pirateFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PirataOne-Regular.ttf"), 20);

        tablero = new Tablero();
        movimiento = new Movimiento(tablero);
        movimientos = 0;

        grid = new GridPane();
        statusLabel = new Label("Movimientos: 0");
        statusLabel.setFont(pirateFont);
       
        
        Image marcaDeAguaImg = new Image(getClass().getResourceAsStream("/imagenes/c9ka_vgac_210518.jpg"));
        ImageView marcaDeAguaView = new ImageView(marcaDeAguaImg);
        marcaDeAguaView.setFitWidth(400);  // Ajusta el tamaño como quieras
        marcaDeAguaView.setPreserveRatio(true);
        marcaDeAguaView.setOpacity(0.50);  // Para que sea tenue, marca de agua
        
        //Junta el tablero y la imagen en un solo panel 
        StackPane tableroConMarca = new StackPane();
        tableroConMarca.getChildren().addAll(marcaDeAguaView, grid);
        StackPane.setAlignment(grid, Pos.CENTER);


        Button btnMover = new Button("Mover Pirata");
        btnMover.setOnAction(e -> moverPirata());
        btnMover.setFont(pirateFont);
        
        //Boton para salir del juego
        Button btnSalir = new Button("Salir");
        btnSalir.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        btnSalir.setFont(pirateFont);

        btnSalir.setOnAction(e -> {
            Stage stage = (Stage) this.getScene().getWindow();
            stage.close();
            
     
        });


        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(statusLabel, tableroConMarca, btnMover,btnSalir);

        dibujarTablero();
    }
    public void showStartScreen(Stage primaryStage) {

        // Carga la imagen desde la carpeta de recursos como fondo/marca de agua
        Image backgroundImage = new Image(getClass().getResourceAsStream("/imagenes/pirataTesoro.PNG"));

        // Crea un ImageView (componente visual) para mostrar la imagen
        ImageView backgroundView = new ImageView(backgroundImage);
        
        // Ajusta el ancho de la imagen de fondo para que encaje con la ventana
        backgroundView.setFitWidth(400);
        
        // Mantiene la proporción de la imagen al redimensionar
        backgroundView.setPreserveRatio(true);
        
        // Define opacidad baja para que actúe como una marca de agua (semi-transparente)
        backgroundView.setOpacity(0.20); 

        // Crea un BorderPane como contenedor principal del contenido
        BorderPane contentPane = new BorderPane();
        contentPane.setPrefSize(400, 200); // Tamaño base de la ventana

        // Crea una etiqueta de bienvenida
        Label welcomeLabel = new Label("¡Bienvenido a la Isla del Tesoro!");

        // Intenta cargar una fuente personalizada desde recursos
        Font pirateFont = Font.loadFont(getClass().getResourceAsStream("/fonts/PirataOne-Regular.ttf"), 24);

        // Verifica si la fuente se cargó correctamente
        if (pirateFont == null) {
            System.out.println("No se cargó la fuente");
        } else {
            // Si se carga, imprime su nombre y la aplica al texto
            System.out.println("Fuente cargada: " + pirateFont.getName());
            welcomeLabel.setFont(pirateFont);
        }

        // Crea el botón de iniciar juego y le da estilo
        Button startButton = new Button("Iniciar Juego");
        startButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        startButton.setFont(pirateFont); // Aplica la fuente personalizada

        // Crea el botón de salir con otro color
        Button exitButton = new Button("Salir");
        exitButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
        exitButton.setFont(pirateFont);

        // Acción al hacer clic en "Iniciar Juego": cambia a la escena del juego
        startButton.setOnAction(e -> {
            Scene gameScene = new Scene(new GameView(), 500, 600); // Vista del juego
            primaryStage.setScene(gameScene); // Cambia la escena
        });

        // Acción para cerrar la aplicación al hacer clic en "Salir"
        exitButton.setOnAction(e -> primaryStage.close());

        // Agrupa los botones horizontalmente con espacio entre ellos
        HBox buttons = new HBox(20, startButton, exitButton);
        buttons.setAlignment(Pos.CENTER); // Centra horizontalmente

        // Coloca el texto de bienvenida en la parte superior del BorderPane
        contentPane.setTop(welcomeLabel);
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER); // Centra el texto

        // Coloca los botones en el centro del BorderPane
        contentPane.setCenter(buttons);

        // Crea un StackPane para poder superponer la imagen de fondo y el contenido
        StackPane root = new StackPane();

        // Agrega primero la imagen (fondo) y luego el contenido encima
        root.getChildren().addAll(backgroundView, contentPane);

        // Crea la escena con el contenedor final
        Scene scene = new Scene(root);

        // Establece la escena en el escenario (ventana)
        primaryStage.setScene(scene);
        primaryStage.setTitle("Juego del Pirata"); // Título de la ventana
        primaryStage.show(); // Muestra la ventana
    }

    //Dibuja el tablero en la pantalla 
    private void dibujarTablero() {
        grid.getChildren().clear();
        char[][] matriz = tablero.getMatriz();

        for (int i = 0; i < Tablero.N; i++) {
            for (int j = 0; j < Tablero.N; j++) {
                Label lbl = new Label(String.valueOf(matriz[i][j]));
                lbl.setMinSize(40, 40);
                lbl.setAlignment(Pos.CENTER);
                lbl.setStyle("-fx-border-color: black; -fx-font-size: 18px;");

                switch (matriz[i][j]) {
                    case 'A': lbl.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null))); break;
                    case 'B': lbl.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, null, null))); break;
                    case 'P': lbl.setBackground(new Background(new BackgroundFill(Color.GOLD, null, null))); break;
                    case 'T': lbl.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null, null))); break;
                    default:  lbl.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
                }

                grid.add(lbl, j, i);
            }
        }
    }

    private void moverPirata() {
        if (movimientos >= 20) {
            mostrarMensaje("¡Se acabaron los movimientos!");
            return;
        }

        String resultado = movimiento.mover();
        movimientos++;
        statusLabel.setText("Movimientos: " + movimientos);
        dibujarTablero();

        switch (resultado) {
            case "agua":
                mostrarMensaje("¡Te ahogaste! 💀");
                break;
            case "tesoro":
                mostrarMensaje("¡Encontraste el tesoro! 🏆");
                break;
        }
    }
    

    private void mostrarMensaje(String texto) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Resultado");
        alert.setHeaderText(null);
        alert.setContentText(texto);

        // Si se ahogó, agregar botón de reinicio
        if (texto.contains("ahogaste")) {
            Button reiniciarBtn = new Button("Reiniciar Juego");

            reiniciarBtn.setOnAction(e -> {
                Stage stage = (Stage) this.getScene().getWindow();
                GameView nuevoJuego = new GameView();
                Scene nuevaEscena = new Scene(nuevoJuego, 500, 600);
                stage.setScene(nuevaEscena);
            });

            VBox dialogPaneContent = new VBox(10);
            dialogPaneContent.setAlignment(Pos.CENTER);
            dialogPaneContent.getChildren().addAll(new Label(texto), reiniciarBtn);

            alert.getDialogPane().setContent(dialogPaneContent);
        }

        alert.showAndWait();
    }
}

