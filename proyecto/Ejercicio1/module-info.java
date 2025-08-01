module javafx.win {
    requires javafx.controls;
    requires javafx.graphics;

    exports javafx.win.ordenpedidos;
    exports viewww;

    opens Modeel to javafx.base;
    
    exports model;
    exports view;
    exports app;
}
