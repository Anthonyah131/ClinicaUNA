/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.clinicauna.util;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

/**
 *
 * @author ccarranza
 */
public class Mensaje {
    
    private ResourceBundle resourceBundle;
    
    public Mensaje(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }
    
    public void showi18n(Alert.AlertType tipo, String tituloKey, String mensajeKey, String aceptarButtonKey) {
        Alert alert = new Alert(tipo);
        alert.setTitle(resourceBundle.getString(tituloKey));
        alert.setHeaderText(null);
        alert.setContentText(resourceBundle.getString(mensajeKey));
        
        ButtonType aceptarButton = new ButtonType(resourceBundle.getString(aceptarButtonKey), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().set(0, aceptarButton);

        alert.show();
    }

    public void show(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.show();
    }

    public void showModal(AlertType tipo, String titulo, Window padre, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.initOwner(padre);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public Boolean showConfirmation(String titulo, Window padre, String mensaje) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.initOwner(padre);
        alert.setContentText(mensaje);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get() == ButtonType.OK;
    }
}
