package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P06_MenuPrincipalViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnGenerales;
    @FXML
    private MFXButton btnEvaluaciones;
    @FXML
    private MFXButton btnCerrarSesion;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXButton btnMantenimientoMedicos;
    @FXML
    private MFXButton btnRegistroPacientes;
    @FXML
    private MFXButton btnMantUsuarios;

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
    private void onActionBtnMantUsuarios(ActionEvent event) {
        AppContext.getInstance().set("Padre", "P06_MenuPrincipalView");
        FlowController.getInstance().goView("P03_RegistroView");
    }

    @FXML
    private void onActionBtnGenerales(ActionEvent event) {
          FlowController.getInstance().goView("P07_MantenimientoGeneralesView");
    }

    @FXML
    private void onActionBtnMantenimientoMedicos(ActionEvent event) {
        FlowController.getInstance().goView("P08_MantenimientoMedicosView");
    }

    @FXML
    private void onActionBtnRegistroPacientes(ActionEvent event) {
        FlowController.getInstance().goView("P09_MantenimientoPacientesView");
    }

    @FXML
    private void onActionBtnCerrarSesion(ActionEvent event) {
        FlowController.getInstance().clearHashMap();
        FlowController.getInstance().goViewInWindow("P01_LogInView", false);
        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().salir();
    }

}
