package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.AppContext;
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
public class P05_CambioClaveViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAcepcli;
    @FXML
    private MFXTextField txfClave;

    CliUsuarioDto cliUsuarioDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cliUsuarioDto = new CliUsuarioDto();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        try {
            if (txfClave.getText() == null || txfClave.getText().isEmpty()) {
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.changePass", getStage(), "key.needEnterPass");
            } else {
                CliUsuarioService cliUsuarioService = new CliUsuarioService();
                cliUsuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
                cliUsuarioDto.setUsuClave(txfClave.getText());
                Respuesta respuesta = cliUsuarioService.guardarUsuario(cliUsuarioDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "key.passRecovery", getStage(), "key.passChangedSuccess");
                    getStage().close();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P05_CambioClaveViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }
    }

}
