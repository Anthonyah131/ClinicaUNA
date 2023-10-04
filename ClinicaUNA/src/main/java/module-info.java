module clinicauna {
    requires javafx.controls;
    requires javafx.fxml;

    opens cr.ac.una.clinicauna to javafx.fxml;
    exports cr.ac.una.clinicauna;
}
