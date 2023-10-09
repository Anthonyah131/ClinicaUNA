module clinicauna {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.logging;
    requires com.jfoenix;
    requires MaterialFX;
    requires java.base;

    opens cr.ac.una.clinicauna to javafx.fxml, com.jfoenix;
    opens cr.ac.una.clinicauna.controller to javafx.fxml;
    opens cr.ac.una.clinicauna.util to javafx.fxml;
    
    exports cr.ac.una.clinicauna;
    exports cr.ac.una.clinicauna.controller;
    exports cr.ac.una.clinicauna.util;
}
