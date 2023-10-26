package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P07_MantenimientoGeneralesViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnSalir;
    @FXML
    private ImageView imvFotoEmpresa;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextArea txaInformacion;
    @FXML
    private JFXTextField txfCorreo;
    @FXML
    private JFXTextField txfClave;
    @FXML
    private JFXTextField txfPlantilla;
    @FXML
    private MFXButton btnAgregarPlantilla;
    @FXML
    private MFXButton btnGuardar;

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
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBtnAgregarPlantilla(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }

}
