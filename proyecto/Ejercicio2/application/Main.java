package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.BankView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        BankView bankView = new BankView();
        bankView.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
