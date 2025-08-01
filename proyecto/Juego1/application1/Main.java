package application1;

import javafx.application.Application; 
import javafx.stage.Stage;
import view1.GameView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameView gameView = new GameView();
        gameView.showStartScreen(primaryStage);  // Muestra la pantalla de bienvenida
    }

    public static void main(String[] args) {
        launch(args);
    }
}
