package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.service.CliMedicoService;
import cr.ac.una.clinicauna.service.CliMedicoService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P08_MantenimientoMedicosViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnSalir;
    @FXML
    private JFXTextField txfBuscarCodigo;
    @FXML
    private JFXTextField txfBuscarFolio;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXTextField txfBuscarPapellido;
    @FXML
    private JFXCheckBox chkBuscarActivas;
    @FXML
    private JFXCheckBox chkBuscarTodos;
    @FXML
    private MFXButton btnLimpiarBusquedaMedico;
    @FXML
    private JFXTextField txfCodigoMedico;
    @FXML
    private JFXTextField txfFolioMedico;
    @FXML
    private JFXTextField txfLicencia;
    @FXML
    private JFXTimePicker tpkHoraInicio;
    @FXML
    private JFXTimePicker tpkHoraLlegada;
    @FXML
    private JFXComboBox<Integer> cboxCantidadCitas;
    @FXML
    private JFXCheckBox chkActivo;
    @FXML
    private MFXButton btnLimpiarCampos;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<CliMedicoDto> tbvResultados;
    @FXML
    private MFXButton btnFiltrar;

    CliMedicoDto medicoDto;
    private ObservableList<CliMedicoDto> medicos = FXCollections.observableArrayList();
    List<Node> requeridos = new ArrayList<>();
    ResourceBundle resourceBundle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfBuscarCodigo.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfBuscarFolio.setTextFormatter(Formato.getInstance().letrasFormat(10));
        txfBuscarNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfBuscarPapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));

        txfCodigoMedico.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfFolioMedico.setTextFormatter(Formato.getInstance().letrasFormat(10));
        txfLicencia.setTextFormatter(Formato.getInstance().letrasFormat(15));
        medicoDto = new CliMedicoDto();
        fillCbox();
        fillTableView();
        nuevoMedico();
//        cargarMedicos();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        cargarMedicos();
    }

    @FXML
    private void onActionBtnLimpiarBusquedaMedico(ActionEvent event) {
        txfBuscarCodigo.clear();
        txfBuscarFolio.clear();
        txfBuscarNombre.clear();
        txfBuscarPapellido.clear();
        chkBuscarActivas.setSelected(false);
        tbvResultados.getItems().clear();
        medicos.clear();
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        nuevoMedico();
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridos);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
            } else {
                if (medicoDto.getMedId() != null) {
                    CliMedicoService medicoService = new CliMedicoService();
                    if (cboxCantidadCitas.getValue() != null) {
                        medicoDto.setMedEspaciosxhora((long) cboxCantidadCitas.getValue());
                    }
                    if (chkActivo.isSelected()) {
                        medicoDto.setMedEstado("A");
                    } else {
                        medicoDto.setMedEstado("I");
                    }
                    if (tpkHoraInicio.getValue() != null) {
                        medicoDto.setMedFiniTime(tpkHoraInicio.getValue());
                    }
                    if (tpkHoraLlegada.getValue() != null) {
                        medicoDto.setMedFfinTime(tpkHoraLlegada.getValue());
                    }

                    Respuesta respuesta = medicoService.guardarMedico(medicoDto);
                    if (!respuesta.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                    } else {
                        unbindMedico();
                        this.medicoDto = (CliMedicoDto) respuesta.getResultado("Medico");
                        bindMedico();
                        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                    }
                } else {
                    // Se pone un mensaje que se debe cargar un medico para actualizarlo
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P08_MantenimientoMedicosViewController.class.getName()).log(Level.SEVERE, "Error guardando el medico.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBuscarTodos(ActionEvent event) {
        if (chkBuscarTodos.isSelected()) {
            chkBuscarActivas.setDisable(true);
        } else {
            chkBuscarActivas.setDisable(false);
        }
    }

    private void nuevoMedico() {
        unbindMedico();
        this.medicoDto = new CliMedicoDto();
        bindMedico();
    }

    private void seleccionarTipoPorCantidad(Integer cantXjora) {
        for (Integer tipo : cboxCantidadCitas.getItems()) {
            if (Objects.equals(tipo, cantXjora)) {
                cboxCantidadCitas.getSelectionModel().select(tipo);
            }
        }
    }

    private void bindMedico() {
        txfCodigoMedico.textProperty().bindBidirectional(medicoDto.medCodigo);
        txfFolioMedico.textProperty().bindBidirectional(medicoDto.medFolio);
        txfLicencia.textProperty().bindBidirectional(medicoDto.medCarne);
        if (medicoDto.getMedFini() != null) {
            tpkHoraInicio.setValue(medicoDto.getMedFiniTime());
        }
        if (medicoDto.getMedFini() != null) {
            tpkHoraLlegada.setValue(medicoDto.getMedFfinTime());
        }

//        tpkHoraInicio.valueProperty().bindBidirectional(medicoDto.medFini.get().toLocalTime());
//        tpkHoraLlegada.valueProperty().bindBidirectional(medicoDto.medFfin);
//        tpkHoraInicio.valueProperty().bindBidirectional((Property<LocalTime>) Bindings.createObjectBinding(
//                () -> LocalTime.of(0, 0),
//                medicoDto.medFini));
//        tpkHoraLlegada.valueProperty().bindBidirectional((Property<LocalTime>) Bindings.createObjectBinding(
//                () -> LocalTime.of(0, 0),
//                medicoDto.medFfin));
        if ("A".equals(medicoDto.getMedEstado())) {
            chkActivo.setSelected(true);
        } else {
            chkActivo.setSelected(false);
        }
        if (medicoDto.getMedEspaciosxhora() != null) {
            seleccionarTipoPorCantidad(Math.toIntExact(medicoDto.getMedEspaciosxhora()));
        } else {
            cboxCantidadCitas.getSelectionModel().clearSelection();
        }
    }

    private void unbindMedico() {
        txfCodigoMedico.textProperty().unbindBidirectional(medicoDto.medCodigo);
        txfFolioMedico.textProperty().unbindBidirectional(medicoDto.medFolio);
        txfLicencia.textProperty().unbindBidirectional(medicoDto.medCarne);
        tpkHoraInicio.setValue(null);
        tpkHoraLlegada.setValue(null);
//        tpkHoraInicio.valueProperty().unbindBidirectional(medicoDto.medFini);
//        tpkHoraLlegada.valueProperty().unbindBidirectional(medicoDto.medFfin);
//        tpkHoraInicio.valueProperty().unbindBidirectional((Property<LocalTime>) Bindings.createObjectBinding(
//                () -> LocalTime.of(0, 0),
//                medicoDto.medFini));
//        tpkHoraLlegada.valueProperty().unbindBidirectional((Property<LocalTime>) Bindings.createObjectBinding(
//                () -> LocalTime.of(0, 0),
//                medicoDto.medFfin));
    }

    public void cargarMedicos() {
        CliMedicoService service = new CliMedicoService();
        Respuesta respuesta = service.getMedicos(txfBuscarCodigo.getText(), txfBuscarFolio.getText(), txfBuscarNombre.getText(), txfBuscarPapellido.getText(), chkBuscarActivas.isSelected(), chkBuscarTodos.isSelected());

        if (respuesta.getEstado()) {
            tbvResultados.getItems().clear();
            medicos.clear();
            medicos.addAll((List<CliMedicoDto>) respuesta.getResultado("Medicos"));
            tbvResultados.setItems(medicos);
            tbvResultados.refresh();
        } else {
            //new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Medicos", getStage(), respuesta.getMensaje());
        }
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && (((JFXTextField) node).getText() == null || ((JFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += ", " + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && (((JFXPasswordField) node).getText() == null || ((JFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += ", " + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += ", " + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += ", " + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            String message = resourceBundle.getString("key.invalidFields") + invalidos + "].";
            return message;
        }
    }

    public void fillCbox() {
        cboxCantidadCitas.getItems().clear();
//
//        String admin = resourceBundle.getString("key.admin");
//        String doctor = resourceBundle.getString("key.doctor");
//        String receptionist = resourceBundle.getString("key.receptionist");

        ObservableList<Integer> citasHora = FXCollections.observableArrayList();
        citasHora.addAll(1, 2, 3, 4);
        cboxCantidadCitas.setItems(citasHora);
    }

    public void fillTableView() {
        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();

        tbvResultados.getItems().clear();

        TableColumn<CliMedicoDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(50);
        tbcId.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuId);

        TableColumn<CliMedicoDto, String> tbcCedula = new TableColumn<>(resourceBundle.getString("key.identification"));
        tbcCedula.setPrefWidth(100);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuCedula);

        TableColumn<CliMedicoDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.name"));
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuNombre);

        TableColumn<CliMedicoDto, String> tbcApellido = new TableColumn<>(resourceBundle.getString("key.papellido"));
        tbcApellido.setPrefWidth(150);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuPapellido);

        TableColumn<CliMedicoDto, String> tbcCodigo = new TableColumn<>(resourceBundle.getString("key.code"));
        tbcCodigo.setPrefWidth(150);
        tbcCodigo.setCellValueFactory(cd -> cd.getValue().medCodigo);

        TableColumn<CliMedicoDto, String> tbcFolio = new TableColumn<>(resourceBundle.getString("key.folio"));
        tbcFolio.setPrefWidth(150);
        tbcFolio.setCellValueFactory(cd -> cd.getValue().medFolio);

        tbvResultados.getColumns().addAll(tbcId, tbcCodigo, tbcFolio, tbcCedula, tbcNombre, tbcApellido);

        tbvResultados.refresh();

        tbvResultados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindMedico();
                medicoDto = newValue;
                bindMedico();
            } else {
                nuevoMedico();
            }
        });
    }
}
