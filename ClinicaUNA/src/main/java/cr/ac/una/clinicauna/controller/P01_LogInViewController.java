package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private MFXPasswordField txfCrontasena;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();
        new Mensaje(resourceBundle);
    }

    @FXML
    private void onActionBtnIngresar(ActionEvent event) {
//        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();
//        Mensaje mensaje = new Mensaje(resourceBundle);
//        mensaje.showi18n(Alert.AlertType.ERROR, "key.invalidUser", "key.mensajeInvalidUser");
        FlowController.getInstance().goMain();
        ((Stage) btnIngresar.getScene().getWindow()).close();
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        ((Stage) btnSalir.getScene().getWindow()).close();
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
        FlowController.getInstance().delete("P05_CambioClaveView");
        FlowController.getInstance().goViewInWindowModal("P05_CambioClaveView", stage, false);

//        AppContext.getInstance().set("Padre", "");
//        FlowController.getInstance().goViewInWindowModal("P03_RegistroView", stage, false);
    }

    @FXML
    private void onActionMitSpanish(ActionEvent event) {
        ResourceBundle idioma = ResourceBundle.getBundle("/cr/ac/una/clinicauna/resources/i18n/traduccion_es");
        FlowController.setIdioma(idioma);

        FlowController.getInstance().delete("P01_LogInView");
        FlowController.getInstance().goViewInWindow("P01_LogInView", false);

        ((Stage) mbtnI18n.getScene().getWindow()).close();

    }

    @FXML
    private void onActionMitEnglish(ActionEvent event) {
        ResourceBundle idioma = ResourceBundle.getBundle("/cr/ac/una/clinicauna/resources/i18n/traduccion_en");
        FlowController.setIdioma(idioma);

        FlowController.getInstance().delete("P01_LogInView");
        FlowController.getInstance().goViewInWindow("P01_LogInView", false);

        ((Stage) mbtnI18n.getScene().getWindow()).close();
    }

}
