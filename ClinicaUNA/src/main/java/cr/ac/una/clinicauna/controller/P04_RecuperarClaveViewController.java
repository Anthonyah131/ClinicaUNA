package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.SoundUtil;
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
    }    

    @Override
    public void initialize() {
     }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        try {
            if (txfCorreo.getText() == null || txfCorreo.getText().isEmpty()) {
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.passRecovery", getStage(), "key.needEnterMail");
            } else {
                CliUsuarioService cliUsuarioService = new CliUsuarioService();
                Respuesta respuesta = cliUsuarioService.recuperarClave(txfCorreo.getText());
                if (respuesta.getEstado()) {
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.passRecovery", getStage(), "key.mailSend");
                } else {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.passRecovery", getStage(), "key.errorSendMail");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P04_RecuperarClaveViewController.class.getName()).log(Level.SEVERE, "Error enviando el correo.", ex);
        }
    }
    
}
