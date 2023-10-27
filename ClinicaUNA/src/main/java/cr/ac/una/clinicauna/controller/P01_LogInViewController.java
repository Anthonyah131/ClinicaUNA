package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P01_LogInViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXTextField txfUsuario;
    @FXML
    private MFXPasswordField txfContrasena;
    @FXML
    private MFXButton btnIngresar;
    @FXML
    private MFXButton btnNuevaCuenta;
    @FXML
    private MFXButton btnRecuperarContra;
    @FXML
    private MFXButton btnAcercaDe;
    @FXML
    private MenuButton mbtnI18n;

    ResourceBundle resourceBundle;
    Mensaje mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciarScena();
        resourceBundle = FlowController.getInstance().getIdioma();
        mensaje = new Mensaje(resourceBundle);
    }

    @Override
    public void initialize() {
        iniciarScena();
    }

    @FXML
    private void onActionBtnIngresar(ActionEvent event) {

        SoundUtil.mouseEnterSound();
        try {
            if (txfUsuario.getText() == null || txfUsuario.getText().isEmpty()) {
                mensaje.showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "key.noEnterUser");
            } else if (txfContrasena.getText() == null || txfContrasena.getText().isEmpty()) {
                mensaje.showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "key.noEnterPass");
            } else {
                CliUsuarioService cliUsuarioService = new CliUsuarioService();
                Respuesta respuesta = cliUsuarioService.getUsuario(txfUsuario.getText(), txfContrasena.getText());
                if (respuesta.getEstado()) {
                    CliUsuarioDto cliUsuarioDto = (CliUsuarioDto) respuesta.getResultado("Usuario");
                    AppContext.getInstance().set("Token", cliUsuarioDto.getToken());
                    AppContext.getInstance().set("Usuario", cliUsuarioDto);
                    if (cliUsuarioDto.getUsuClave().equals(cliUsuarioDto.getUsuTempclave())) {
                        mensaje.showModali18n(Alert.AlertType.WARNING, "key.userValidation", getStage(), "key.changePassLog");
                        FlowController.getInstance().goViewInWindowModal("P05_CambioClaveView", stage, false);
                    } else {
                        if ("A".equals(cliUsuarioDto.getUsuActivo())) {//compruba que el usuario este activo
                            if (getStage().getOwner() == null) {
                                FlowController.getInstance().goMain();
                            }
                            getStage().close();
                        } else {
                            mensaje.showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "key.activeAccount");
                        }
                    }
                } else {
                    mensaje.showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "key.wrongUser");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P01_LogInViewController.class.getName()).log(Level.SEVERE, "Error ingresando.", ex);
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        getStage().close();
    }

    @FXML
    private void onActionBtnNuevaCuenta(ActionEvent event) {
        FlowController.getInstance().delete("P03_RegistroView");
        AppContext.getInstance().set("Padre", "P01_LogInView");
        FlowController.getInstance().goViewInWindowModal("P03_RegistroView", stage, false);
    }

    @FXML
    private void onActionBtnRecuperarContra(ActionEvent event) {
        FlowController.getInstance().delete("P04_RecuperarClaveView");
        FlowController.getInstance().goViewInWindowModal("P04_RecuperarClaveView", stage, false);
    }

    @FXML
    private void onActionBtnAcercaDe(ActionEvent event) {
        if (getStage().getOwner() == null) {
            FlowController.getInstance().goMain();
        }
        getStage().close();
//        FlowController.getInstance().delete("P05_CambioClaveView");
//        FlowController.getInstance().goViewInWindowModal("P05_CambioClaveView", stage, false);

//        AppContext.getInstance().set("Padre", "");
//        FlowController.getInstance().goViewInWindowModal("P03_RegistroView", stage, false);
    }

    @FXML
    private void onActionMitSpanish(ActionEvent event) {
        ResourceBundle idioma = ResourceBundle.getBundle("/cr/ac/una/clinicauna/resources/i18n/traduccion_es");
        FlowController.setIdioma(idioma);

        FlowController.getInstance().delete("P01_LogInView");
        FlowController.getInstance().goViewInWindow("P01_LogInView", false);
        getStage().close();

    }

    @FXML
    private void onActionMitEnglish(ActionEvent event) {
        ResourceBundle idioma = ResourceBundle.getBundle("/cr/ac/una/clinicauna/resources/i18n/traduccion_en");
        FlowController.setIdioma(idioma);

        FlowController.getInstance().delete("P01_LogInView");
        FlowController.getInstance().goViewInWindow("P01_LogInView", false);
        getStage().close();
    }

    public void iniciarScena() {
        String padre = (String) AppContext.getInstance().get("PadreLogin");

        if (padre != null) {
            btnSalir.setVisible(false);
            mbtnI18n.setVisible(false);
            btnNuevaCuenta.setVisible(false);
            btnRecuperarContra.setVisible(false);
            btnAcercaDe.setVisible(false);
        }
    }

}
