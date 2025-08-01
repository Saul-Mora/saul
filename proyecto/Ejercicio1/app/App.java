package appp; // Paquete principal de arranque

import javafx.application.Application;
import viewww.RegistroProyectoView; // Importamos la vista principal

public class app1 {
    public static void main(String[] args) {
        // Lanza la aplicación JavaFX usando la clase de vista que creaste
        Application.launch(RegistroProyectoView.class, args);
    }
}

