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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

/**
 *
 * @author ccarranza
 */
public class Mensaje {

    private ResourceBundle resourceBundle;
//    

    public Mensaje() {
        this.resourceBundle = resourceBundle = FlowController.getInstance().getIdioma();
    }

    public void showi18n(Alert.AlertType tipo, String tituloKey, String mensajeKey) {
        Alert alert = new Alert(tipo);
        alert.setTitle(resourceBundle.getString(tituloKey));
        alert.setHeaderText(null);

        String mensaje = comprobarMensaje(mensajeKey);
        alert.setContentText(mensaje);

        ButtonType aceptarButton = new ButtonType(resourceBundle.getString("key.accept"), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().set(0, aceptarButton);

        alert.show();
    }

    public void showModali18n(AlertType tipo, String tituloKey, Window padre, String mensajeKey) {
        Alert alert = new Alert(tipo);
        alert.setTitle(resourceBundle.getString(tituloKey));
        alert.setHeaderText(null);
        alert.initOwner(padre);

        String mensaje = comprobarMensaje(mensajeKey);
        alert.setContentText(mensaje);

        ButtonType aceptarButton = new ButtonType(resourceBundle.getString("key.accept"), ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().set(0, aceptarButton);

        alert.showAndWait();
    }

    public Boolean showConfirmationi18n(String tituloKey, Window padre, String mensajeKey) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(resourceBundle.getString(tituloKey));
        alert.setHeaderText(null);
        alert.initOwner(padre);

        String mensaje = comprobarMensaje(mensajeKey);
        alert.setContentText(mensaje);

        // Crea botones personalizados con los textos obtenidos del ResourceBundle
        ButtonType okButton = new ButtonType(resourceBundle.getString("key.accept"), ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType(resourceBundle.getString("key.cancel"), ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(okButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == okButton;
    }

    public String comprobarMensaje(String mensaje) {

        if (mensaje.equals("") || mensaje.length() < 3) {
            return mensaje;
        }

        String iniMensaje = mensaje.substring(0, 3);
        if (iniMensaje.equals("key")) {
            return resourceBundle.getString(mensaje);
        }
        return mensaje;
    }
//
//    public void show(AlertType tipo, String titulo, String mensaje) {
//        Alert alert = new Alert(tipo);
//        alert.setTitle(titulo);
//        alert.setHeaderText(null);
//        alert.setContentText(mensaje);
//        alert.show();
//    }
//
//    public void showModal(AlertType tipo, String titulo, Window padre, String mensaje) {
//        Alert alert = new Alert(tipo);
//        alert.setTitle(titulo);
//        alert.setHeaderText(null);
//        alert.initOwner(padre);
//        alert.setContentText(mensaje);
//        alert.showAndWait();
//    }
//
//    public Boolean showConfirmation(String titulo, Window padre, String mensaje) {
//        Alert alert = new Alert(AlertType.CONFIRMATION);
//        alert.setTitle(titulo);
//        alert.setHeaderText(null);
//        alert.initOwner(padre);
//        alert.setContentText(mensaje);
//        Optional<ButtonType> result = alert.showAndWait();
//
//        return result.get() == ButtonType.OK;
//    }
}
