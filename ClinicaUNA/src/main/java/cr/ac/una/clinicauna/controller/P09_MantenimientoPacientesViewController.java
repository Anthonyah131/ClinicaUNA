/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.service.CliPacienteService;
import cr.ac.una.clinicauna.service.CliPacienteService;
import cr.ac.una.clinicauna.util.BindingUtils;
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
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P09_MantenimientoPacientesViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private JFXTextField txfCedula;
    @FXML
    private JFXRadioButton rdbHombre;
    @FXML
    private ToggleGroup tggGenero;
    @FXML
    private JFXRadioButton rdbMujer;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfPapellido;
    @FXML
    private JFXTextField txfSapellido;
    @FXML
    private JFXTextField txfCorreo;
    @FXML
    private JFXTextField txfTelefono;
    @FXML
    private MFXButton btnLimpiarCampos;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private JFXTextField txfBuscarCedula;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXTextField txfBuscarPapellido;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private TableView<CliPacienteDto> tbvResultados;

    CliPacienteDto pacienteDto;
    private ObservableList<CliPacienteDto> pacientes = FXCollections.observableArrayList();
    List<Node> requeridos = new ArrayList<>();

    ResourceBundle resourceBundle;
    Mensaje mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdbHombre.setUserData("M");
        rdbMujer.setUserData("F");
        txfCedula.setTextFormatter(Formato.getInstance().cedulaFormat(9));
        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfPapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfSapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfTelefono.setTextFormatter(Formato.getInstance().integerFormat());

        txfBuscarCedula.setTextFormatter(Formato.getInstance().cedulaFormat(9));
        txfBuscarNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfBuscarPapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));
        pacienteDto = new CliPacienteDto();
        fillTableView();
        nuevoPaciente();
        indicarRequeridos();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        if (mensaje.showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoPaciente();
            cleanNodes();
        }
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                mensaje.showModali18n2(Alert.AlertType.ERROR, "key.saveUser", getStage(), invalidos);
            } else {
                CliPacienteService pacienteService = new CliPacienteService();
                Respuesta respuesta = pacienteService.guardarPaciente(pacienteDto);
                if (!respuesta.getEstado()) {
                    mensaje.showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                } else {
                    unbindPaciente();
                    this.pacienteDto = (CliPacienteDto) respuesta.getResultado("Paciente");
                    bindPaciente();
                    mensaje.showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P09_MantenimientoPacientesViewController.class.getName()).log(Level.SEVERE, "Error guardando el paciente.", ex);
            mensaje.showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        CliPacienteService service = new CliPacienteService();
        Respuesta respuesta = service.getPacientes(txfCedula.getText(), txfNombre.getText(), txfPapellido.getText());

        if (respuesta.getEstado()) {
            tbvResultados.getItems().clear();
            pacientes.clear();
            pacientes.addAll((List<CliPacienteDto>) respuesta.getResultado("Pacientes"));
            tbvResultados.setItems(pacientes);
            tbvResultados.refresh();
        } else {
            //new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Pacientes", getStage(), respuesta.getMensaje());
        }
    }

    public void fillTableView() {
        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();

        tbvResultados.getItems().clear();

        TableColumn<CliPacienteDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(50);
        tbcId.setCellValueFactory(cd -> cd.getValue().pacId);

        TableColumn<CliPacienteDto, String> tbcCedula = new TableColumn<>(resourceBundle.getString("key.identification"));
        tbcCedula.setPrefWidth(100);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().pacCedula);

        TableColumn<CliPacienteDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.name"));
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().pacNombre);

        TableColumn<CliPacienteDto, String> tbcApellido = new TableColumn<>(resourceBundle.getString("key.papellido"));
        tbcApellido.setPrefWidth(150);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().pacPapellido);

        TableColumn<CliPacienteDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(100);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<CliPacienteDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<CliPacienteDto, Boolean> p) -> new ButtonCell());

        tbvResultados.getColumns().addAll(tbcId, tbcCedula, tbcNombre, tbcApellido, tbcEliminar);
        tbvResultados.refresh();
    }

    private void nuevoPaciente() {
        unbindPaciente();
        this.pacienteDto = new CliPacienteDto();
        bindPaciente();
    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfCedula, txfNombre, txfPapellido, txfCorreo));
    }

    private void bindPaciente() {
        txfNombre.textProperty().bindBidirectional(pacienteDto.pacNombre);
        txfCedula.textProperty().bindBidirectional(pacienteDto.pacCedula);
        txfPapellido.textProperty().bindBidirectional(pacienteDto.pacPapellido);
        txfSapellido.textProperty().bindBidirectional(pacienteDto.pacSapellido);
        txfCorreo.textProperty().bindBidirectional(pacienteDto.pacCorreo);
        txfTelefono.textProperty().bindBidirectional(pacienteDto.pacTelefono);
        BindingUtils.bindToggleGroupToProperty(tggGenero, pacienteDto.pacGenero);
    }

    private void unbindPaciente() {
        txfNombre.textProperty().unbindBidirectional(pacienteDto.pacNombre);
        txfCedula.textProperty().unbindBidirectional(pacienteDto.pacCedula);
        txfPapellido.textProperty().unbindBidirectional(pacienteDto.pacPapellido);
        txfSapellido.textProperty().unbindBidirectional(pacienteDto.pacSapellido);
        txfCorreo.textProperty().unbindBidirectional(pacienteDto.pacCorreo);
        txfTelefono.textProperty().unbindBidirectional(pacienteDto.pacTelefono);
        BindingUtils.unbindToggleGroupToProperty(tggGenero, pacienteDto.pacGenero);
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

    public void cleanNodes() {
        tbvResultados.getItems().clear();
        pacientes.clear();
        txfBuscarCedula.clear();
        txfBuscarNombre.clear();
        txfBuscarPapellido.clear();
    }

    private class ButtonCell extends TableCell<CliPacienteDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.setText("X");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                CliPacienteDto car = (CliPacienteDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                if (!car.getModificado()) {
                    //  CliPacienteDto.getTarCaracteristicaEliminados().add(car);
                }
                tbvResultados.getItems().remove(car);
                tbvResultados.refresh();
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
}
