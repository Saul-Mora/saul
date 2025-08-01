module Ejercicio2ProyectoFx {
	requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens application1 to javafx.graphics, javafx.fxml;
    opens application to javafx.graphics, javafx.fxml;
}
