package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    ResourceBundle resourceBundle;
    Mensaje mensaje;

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
        resourceBundle = FlowController.getInstance().getIdioma();
        mensaje = new Mensaje(resourceBundle);
    }

    @Override
    public void initialize() {
        iniciarScena();
        resourceBundle = FlowController.getInstance().getIdioma();
        mensaje = new Mensaje(resourceBundle);
    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                mensaje.showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), invalidos);
            } else {
                CliUsuarioService usuarioService = new CliUsuarioService();
                if (cboxTipoUsuario.getValue() != null) {
                    String tipo = cboxTipoUsuario.getValue();
                    if (tipo != null) {
                        switch (tipo) {
                            case "Medico" ->
                                tipo = "M";
                            case "Recepcionista" ->
                                tipo = "R";
                            case "Admin" ->
                                tipo = "A";
                            default -> {
                            }
                        }
                    }
                    usuarioDto.setUsuTipousuario(tipo);
                }
                if (cboxIdioma.getValue() != null) {
                    String idioma = cboxIdioma.getValue();
                    if ("Español".equals(idioma)) {
                        idioma = "E";
                    } else if ("Ingles".equals(idioma)) {
                        idioma = "I";
                    }
                    usuarioDto.setUsuIdioma(idioma);
                }

                Respuesta respuesta = usuarioService.guardarUsuario(usuarioDto);
                if (!respuesta.getEstado()) {
                    mensaje.showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                } else {
                    unbindUsuario();
                    this.usuarioDto = (CliUsuarioDto) respuesta.getResultado("Usuario");
                    bindUsuario();
                    mensaje.showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            mensaje.showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("P03_RegistroBuscadorView", stage, Boolean.FALSE);
        P03_RegistroBuscadorViewController busquedaController = (P03_RegistroBuscadorViewController) FlowController.getInstance().getController("P03_RegistroBuscadorView");
        FlowController.getInstance().goViewInWindowModal("BusquedaView", getStage(), true);
//        CliUsuarioDto usuarioDto = (CliUsuarioDto) busquedaController.getResultado();
        if (usuarioDto != null) {
            cargarUsuario(usuarioDto.getUsuId());
        }
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        if (mensaje.showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoUsuario();
//            cleanNodes();
        }
    }

    @FXML
    private void onActionBtnEliminarUsuario(ActionEvent event) {
        try {
            if (this.usuarioDto.getUsuId() == null) {
                mensaje.showModali18n(Alert.AlertType.ERROR, "Eliminar usuario", getStage(), "Debe cargar el usuario que desea eliminar.");
            } else {

                CliUsuarioService service = new CliUsuarioService();
                Respuesta respuesta = service.eliminarUsuario(this.usuarioDto.getUsuId());
                if (!respuesta.getEstado()) {
                    mensaje.showModali18n(Alert.AlertType.ERROR, "Eliminar usuario", getStage(), respuesta.getMensaje());
                } else {
                    mensaje.showModali18n(Alert.AlertType.INFORMATION, "Eliminar usuario", getStage(), "Usuario eliminado correctamente.");
                    nuevoUsuario();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error eliminando el usuario.", ex);
            mensaje.showModali18n(Alert.AlertType.ERROR, "Eliminar usuario", getStage(), "Ocurrio un error eliminando el usuario.");
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private void cargarUsuario(Long id) {
        try {
            CliUsuarioService service = new CliUsuarioService();
            Respuesta respuesta = service.getUsuario(id);
            if (respuesta.getEstado()) {
                unbindUsuario();
                this.usuarioDto = (CliUsuarioDto) respuesta.getResultado("Usuario");
                bindUsuario();
                validarRequeridos();
            } else {
                mensaje.showModali18n(Alert.AlertType.ERROR, "Cargar usuario", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error consultando el usuario.", ex);
            mensaje.showModali18n(Alert.AlertType.ERROR, "Cargar Usuario", getStage(), "Ocurrio un error consultando el usuario.");
        }
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

    private void seleccionarTipoPorNombre(String nombreTipo) {
        OUTER:
        for (String tipo : cboxTipoUsuario.getItems()) {
            switch (nombreTipo) {
                case "M" -> {
                    if ("Medico".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    }
                    break;
                }
                case "R" -> {
                    if ("Recepcionista".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    }
                    break;
                }
                case "A" -> {
                    if ("Admin".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    }
                    break;
                }
                default -> {
                }
            }
        }
    }

    private void seleccionarIdiomaPorNombre(String nombreIdioma) {
        OUTER:
        for (String idioma : cboxIdioma.getItems()) {
            switch (nombreIdioma) {
                case "E" -> {
                    if ("Español".equals(idioma)) {
                        cboxIdioma.getSelectionModel().select(idioma);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    }
                    break;
                }
                case "I" -> {
                    if ("Ingles".equals(idioma)) {
                        cboxIdioma.getSelectionModel().select(idioma);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    }
                    break;
                }
                default -> {
                }
            }
        }
    }

    private void bindUsuario() {
        txfNombre.textProperty().bindBidirectional(usuarioDto.usuNombre);
        txfCedula.textProperty().bindBidirectional(usuarioDto.usuCedula);
        txfPapellido.textProperty().bindBidirectional(usuarioDto.usuPapellido);
        txfSapellido.textProperty().bindBidirectional(usuarioDto.usuSapellido);
        txfUsuario.textProperty().bindBidirectional(usuarioDto.usuUsuario);
        txfContrasena.textProperty().bindBidirectional(usuarioDto.usuClave);
        txfCorreo.textProperty().bindBidirectional(usuarioDto.usuCorreo);
        chkActivo.selectedProperty().bindBidirectional(usuarioDto.usuActivo);
        if (usuarioDto.getUsuTipousuario() != null) {
            seleccionarTipoPorNombre(usuarioDto.getUsuTipousuario());
        } else {
            cboxTipoUsuario.getSelectionModel().clearSelection();
        }
        if (usuarioDto.getUsuIdioma() != null) {
            seleccionarIdiomaPorNombre(usuarioDto.getUsuIdioma());
        } else {
            cboxIdioma.getSelectionModel().clearSelection();
        }
    }

    private void unbindUsuario() {
        txfNombre.textProperty().unbindBidirectional(usuarioDto.usuNombre);
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
            if (node instanceof JFXTextField && (((JFXTextField) node).getText() == null || ((JFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && (((JFXPasswordField) node).getText() == null || ((JFXPasswordField) node).getText().isBlank())) {
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

    public void bindBuscar() {
        P03_RegistroBuscadorViewController buscadorRegistroController = (P03_RegistroBuscadorViewController) FlowController.getInstance().getController("P03_RegistroBuscadorView");
        unbindUsuario();
        usuarioDto = (CliUsuarioDto) buscadorRegistroController.getSeleccionado();
        bindUsuario();
    }
}
