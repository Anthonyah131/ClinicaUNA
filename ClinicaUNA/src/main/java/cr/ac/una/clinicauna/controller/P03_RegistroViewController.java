package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    
    CliUsuarioDto usuarioDto;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txfCedula.setTextFormatter(Formato.getInstance().cedulaFormat(40));
        txfCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfUsuario.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfContrasena.setTextFormatter(Formato.getInstance().maxLengthFormat(8));
        txfPapellido.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfSapellido.setTextFormatter(Formato.getInstance().letrasFormat(15));
        this.usuarioDto = new CliUsuarioDto();
        nuevoUsuario();
        indicarRequeridos();
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

    private void nuevoUsuario() {
        unbindUsuario();
        this.usuarioDto = new CliUsuarioDto();
        bindUsuario();

    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfCedula, txfNombre, txfPapellido, txfUsuario, txfContrasena));
    }
    
    private void bindUsuario() {
        txfNombre.textProperty().bindBidirectional(usuarioDto.usuPapellido);
        txfCedula.textProperty().bindBidirectional(usuarioDto.usuCedula);
        txfPapellido.textProperty().bindBidirectional(usuarioDto.usuPapellido);
        txfSapellido.textProperty().bindBidirectional(usuarioDto.usuSapellido);
        txfUsuario.textProperty().bindBidirectional(usuarioDto.usuUsuario);
        txfContrasena.textProperty().bindBidirectional(usuarioDto.usuClave);
        txfCorreo.textProperty().bindBidirectional(usuarioDto.usuCorreo);
        chkActivo.selectedProperty().bindBidirectional(usuarioDto.usuActivo);
    }

    private void unbindUsuario() {
        txfNombre.textProperty().unbindBidirectional(usuarioDto.usuPapellido);
        txfCedula.textProperty().unbindBidirectional(usuarioDto.usuCedula);
        txfPapellido.textProperty().unbindBidirectional(usuarioDto.usuPapellido);
        txfSapellido.textProperty().unbindBidirectional(usuarioDto.usuSapellido);
        txfUsuario.textProperty().unbindBidirectional(usuarioDto.usuUsuario);
        txfContrasena.textProperty().unbindBidirectional(usuarioDto.usuClave);
        txfCorreo.textProperty().unbindBidirectional(usuarioDto.usuCorreo);
        chkActivo.selectedProperty().unbindBidirectional(usuarioDto.usuActivo);
    }
    
    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && (((JFXTextField) node).getText()==null || ((JFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && (((JFXPasswordField) node).getText()==null || ((JFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }
}
