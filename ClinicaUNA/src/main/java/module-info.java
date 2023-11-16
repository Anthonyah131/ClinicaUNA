module clinicauna {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.logging;
    requires com.jfoenix;
    requires MaterialFX;
    requires java.base;
    requires jakarta.ws.rs;
    requires jakarta.json;
    requires java.sql;
    requires jakarta.xml.bind;
    requires org.apache.commons.compress;
    requires jasperreports;

    opens cr.ac.una.clinicauna to javafx.fxml, com.jfoenix;
    opens cr.ac.una.clinicauna.controller to javafx.fxml;
    opens cr.ac.una.clinicauna.util to javafx.fxml;
    
    exports cr.ac.una.clinicauna;
    exports cr.ac.una.clinicauna.controller;
    exports cr.ac.una.clinicauna.util;
    exports cr.ac.una.clinicauna.model;
}
