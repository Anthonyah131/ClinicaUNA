package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P04_RecuperarClaveViewController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfCorreo;
    @FXML
    private MFXButton btnAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
     }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        /*try {
            if (txfCorreo.getText() == null || txfCorreo.getText().isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperación de clave", getStage(), "Es necesario digitar un correo para ingresar al sistema.");
            } else {
                CliUsuarioService cliUsuarioService = new CliUsuarioService();
                Respuesta respuesta = cliUsuarioService.recuperarClave(txfCorreo.getText());
                if (respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Recuperación de clave", getStage(), "El correo fue enviado correctamente.");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Recuperación de clave", getStage(), "Ocurrio un error enviando el correo de recuperacion.");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P04_RecuperarClaveViewController.class.getName()).log(Level.SEVERE, "Error enviando el correo.", ex);
        }*/
    }
    
}
