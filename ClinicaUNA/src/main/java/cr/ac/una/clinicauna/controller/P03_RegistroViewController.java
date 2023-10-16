package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P03_RegistroViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfCedula;
    @FXML
    private JFXTextField txfCorreo;
    @FXML
    private JFXTextField txfUsuario;
    @FXML
    private JFXTextField txfContrasena;
    @FXML
    private JFXTextField txfPapellido;
    @FXML
    private JFXTextField txfSapellido;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private MFXButton btnLimpiarCampos;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnRegistrar;
    @FXML
    private JFXComboBox<String> cboxTipoUsuario;
    @FXML
    private JFXComboBox<String> cboxIdioma;
    @FXML
    private JFXCheckBox chkActivo;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO.
        fillCbox();
        iniciarScena();
    }

    @Override
    public void initialize() {
        iniciarScena();
    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        cleanNodes();
    }

    @FXML
    private void onActionBtnEliminarUsuario(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    public void iniciarScena() {
        String padre = (String) AppContext.getInstance().get("Padre");

        if (padre.equals("P01_LogInView")) {
            btnSalir.setVisible(false);
            btnEliminar.setDisable(true);
            btnBuscar.setDisable(true);
        } else {
            root.setPrefWidth(1280);
            root.getStyleClass().add("fondo-registro-completa");
            btnSalir.setVisible(true);
            btnEliminar.setDisable(false);
            btnBuscar.setDisable(false);
        }
    }

    public void fillCbox() {
        cboxTipoUsuario.getItems().clear();
        cboxIdioma.getItems().clear();

        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();
        String admin = resourceBundle.getString("key.admin");
        String doctor = resourceBundle.getString("key.doctor");
        String receptionist = resourceBundle.getString("key.receptionist");

        ObservableList<String> tiposUsuarios = FXCollections.observableArrayList();
        tiposUsuarios.addAll(admin, doctor, receptionist);
        cboxTipoUsuario.setItems(tiposUsuarios);

        ObservableList<String> idiomas = FXCollections.observableArrayList();
        idiomas.addAll("Español", "English");
        cboxIdioma.setItems(idiomas);
    }

    public void cleanNodes() {
        txfCedula.clear();
        txfContrasena.clear();
        txfCorreo.clear();
        txfNombre.clear();
        txfPapellido.clear();
        txfSapellido.clear();
        txfUsuario.clear();
        chkActivo.setSelected(false);
        fillCbox();
    }

}
