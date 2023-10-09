package cr.ac.una.clinicauna.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private MFXPasswordField txfCrontasena;
    @FXML
    private MFXButton btnIngresar;
    @FXML
    private MFXButton btnNuevaCuenta;
    @FXML
    private MFXButton btnRecuperarContra;
    @FXML
    private MFXButton btnAcercaDe;

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
    private void onActionBtnSalir(ActionEvent event) {
    }

    @FXML
    private void onActionBtnIngresar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnNuevaCuenta(ActionEvent event) {
    }

    @FXML
    private void onActionBtnRecuperarContra(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAcercaDe(ActionEvent event) {
    }

}
