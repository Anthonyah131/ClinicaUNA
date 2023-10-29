package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliMedicoService;
import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfCedula.setTextFormatter(Formato.getInstance().cedulaFormat(9));
        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfPapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfSapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfUsuario.setTextFormatter(Formato.getInstance().letrasFormat(20));
        txfContrasena.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        this.usuarioDto = new CliUsuarioDto();
        nuevoUsuario();
        iniciarScena();
        indicarRequeridos();
        fillCbox();

    }

    @Override
    public void initialize() {
//        nuevoUsuario();
        iniciarScena();
    }

    @FXML
    private void onActionBtnRegistrar(ActionEvent event) {
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridos);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
            } else {
                CliUsuarioService usuarioService = new CliUsuarioService();
                if (cboxTipoUsuario.getValue() != null) {
                    String tipo = cboxTipoUsuario.getValue();
                    if (tipo != null) {
                        switch (tipo) {
                            case "Médico", "Doctor" ->
                                tipo = "M";
                            case "Recepcionista", "Receptionist" ->
                                tipo = "R";
                            case "Administrador", "Administrator" ->
                                tipo = "A";
                        }
                    }
                    usuarioDto.setUsuTipousuario(tipo);
                }
                if (cboxIdioma.getValue() != null) {
                    String idioma = cboxIdioma.getValue();
                    if ("Español".equals(idioma)) {
                        idioma = "E";
                    } else if ("English".equals(idioma)) {
                        idioma = "I";
                    }
                    usuarioDto.setUsuIdioma(idioma);
                }
                if (chkActivo.isSelected()) {
                    usuarioDto.setUsuActivo("A");
                } else {
                    usuarioDto.setUsuActivo("I");
                }

                Respuesta respuesta = usuarioService.guardarUsuario(usuarioDto);

                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                } else {
                    unbindUsuario();
                    this.usuarioDto = (CliUsuarioDto) respuesta.getResultado("Usuario");
                    bindUsuario();
                    crearBorrarMedico();
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
        FlowController.getInstance().goViewInWindowModal("P03_RegistroBuscadorView", stage, Boolean.FALSE);
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoUsuario();
            cleanNodes();
        }
    }

    @FXML
    private void onActionBtnEliminarUsuario(ActionEvent event) {
        try {
            if (this.usuarioDto.getUsuId() == null) {
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteUser", getStage(), "key.loadUserDelete");
            } else {
                CliUsuarioService service = new CliUsuarioService();
                Respuesta respuesta = service.eliminarUsuario(this.usuarioDto.getUsuId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteUser", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.deleteUser", getStage(), "key.deleteUserSuccess");
                    nuevoUsuario();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error eliminando el usuario.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteUser", getStage(), "key.deleteUserError");
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private void crearBorrarMedico() {
        if ("M".equals(usuarioDto.getUsuTipousuario()) && usuarioDto.getCliMedicoList().isEmpty()) {
            CliUsuarioService usuarioService = new CliUsuarioService();
            CliMedicoService medicoService = new CliMedicoService();
            
            CliMedicoDto medicoDto = new CliMedicoDto();
            medicoDto.setMedCodigo("Ingrese" + usuarioDto.getUsuId());
            medicoDto.setMedFolio("Ingrese" + usuarioDto.getUsuId());
            medicoDto.setMedCarne("Ingrese" + usuarioDto.getUsuId());
            medicoDto.setMedEstado("I");
            
            Respuesta respuestaMedico = medicoService.guardarMedico(medicoDto);
            medicoDto = (CliMedicoDto) respuestaMedico.getResultado("Medico");
            medicoDto.setModificado(true);
            usuarioDto.getCliMedicoList().add(medicoDto);
            Respuesta respuesta = usuarioService.guardarUsuario(usuarioDto);
            
            unbindUsuario();
            this.usuarioDto = (CliUsuarioDto) respuesta.getResultado("Usuario");
            bindUsuario();
        } else if(!"M".equals(usuarioDto.getUsuTipousuario()) && !usuarioDto.getCliMedicoList().isEmpty()){
            CliMedicoService medicoService = new CliMedicoService();
            CliUsuarioService usuarioService = new CliUsuarioService();
            
            Long medicoId = this.usuarioDto.getCliMedicoList().get(0).getMedId();
            usuarioDto.getCliMedicoListEliminados().add(this.usuarioDto.getCliMedicoList().get(0));
            Respuesta respuesta = usuarioService.guardarUsuario(usuarioDto);
            
            unbindUsuario();
            this.usuarioDto = (CliUsuarioDto) respuesta.getResultado("Usuario");
            bindUsuario();
            medicoService.eliminarMedico(medicoId);
        }
    }

    private void cargarUsuario(Long id) {
        try {
            CliUsuarioService service = new CliUsuarioService();
            Respuesta respuesta = service.getUsuario(id);
            if (respuesta.getEstado()) {
                unbindUsuario();
                this.usuarioDto = (CliUsuarioDto) respuesta.getResultado("Usuario");
                bindUsuario();
                ValidarRequeridos.validarRequeridos(requeridos);
            } else {
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.loadUser", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error consultando el usuario.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.loadUser", getStage(), "key.errorQuerying");
        }
    }

    public void iniciarScena() {
        resourceBundle = FlowController.getInstance().getIdioma();
        String padre = (String) AppContext.getInstance().get("Padre");

        if (padre.equals("P01_LogInView")) {
            btnSalir.setVisible(false);
            btnEliminar.setDisable(true);
            btnBuscar.setDisable(true);
            chkActivo.setDisable(true);
        } else if (padre.equals("P06_MenuPrincipalView")) {
            root.setPrefWidth(1280);
            root.getStyleClass().add("fondo-registro-completa");
            btnSalir.setVisible(true);
            btnEliminar.setDisable(false);
            btnBuscar.setDisable(false);

//            usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
//            if (!usuarioDto.getUsuTipousuario().equals("A")) {
//                bindUsuario();
//                cboxTipoUsuario.setDisable(true);
//                btnEliminar.setDisable(true);
//                btnBuscar.setDisable(true);
//                chkActivo.setDisable(true);
//            }
        }
    }

    public void fillCbox() {
        cboxTipoUsuario.getItems().clear();
        cboxIdioma.getItems().clear();

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
        requeridos.addAll(Arrays.asList(txfCedula, txfNombre, txfPapellido, txfSapellido, txfCorreo, txfUsuario, cboxTipoUsuario, txfContrasena));
    }

    private void seleccionarTipoPorNombre(String nombreTipo) {
        OUTER:
        for (String tipo : cboxTipoUsuario.getItems()) {
            switch (nombreTipo) {
                case "M" -> {
                    if ("Médico".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    } else if ("Doctor".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    }
                    break;
                }
                case "R" -> {
                    if ("Recepcionista".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    } else if ("Receptionist".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    }
                    break;
                }
                case "A" -> {
                    if ("Administrador".equals(tipo)) {
                        cboxTipoUsuario.getSelectionModel().select(tipo);
                        break OUTER; // Termina el bucle una vez que se encuentra una coincidencia.
                    } else if ("Administrator".equals(tipo)) {
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
                    if ("English".equals(idioma)) {
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
        if ("A".equals(usuarioDto.getUsuActivo())) {
            chkActivo.setSelected(true);
        } else {
            chkActivo.setSelected(false);
        }
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
    }

    public void bindBuscar() {
        P03_RegistroBuscadorViewController buscadorRegistroController = (P03_RegistroBuscadorViewController) FlowController.getInstance().getController("P03_RegistroBuscadorView");
        unbindUsuario();
        usuarioDto = (CliUsuarioDto) buscadorRegistroController.getSeleccionado();
        bindUsuario();
    }

}
