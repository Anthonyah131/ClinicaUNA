package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliAntecedenteDto;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P13_ExpedienteViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnSalir;
    @FXML
    private Label lblNombreUsuario;
    @FXML
    private Label lblCedula;
    @FXML
    private Label lblGenero;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblPacienteNombre;
    @FXML
    private Label lblPapellido;
    @FXML
    private Label lblSapellido;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblCorreo;
    @FXML
    private JFXTextField txfCantHospitalizaciones;
    @FXML
    private JFXTextField txfCantOperaciones;
    @FXML
    private JFXTextField txfCantAlergias;
    @FXML
    private JFXTextArea txaAlergias;
    @FXML
    private JFXTextField txfAntTipo;
    @FXML
    private JFXTextField txfAntParentesco;
    @FXML
    private JFXTextField txfAntDescripcion;
    @FXML
    private MFXButton btnAgregarAntecedente;
    @FXML
    private JFXListView<CliAntecedenteDto> ltvAntecedentes;
    @FXML
    private JFXTextArea txaAntPatologicos;
    @FXML
    private JFXTextArea txaTratamientosActuales;
    @FXML
    private TableView<CliCitaDto> tbvHistorialCitas;
    @FXML
    private JFXTextField txfPresionArterial;
    @FXML
    private JFXTextField txfFrecuenciaCar;
    @FXML
    private JFXTextField txfPeso;
    @FXML
    private JFXTextField txfTalla;
    @FXML
    private JFXTextField txfTemperatura;
    @FXML
    private Label lblIMC;
    @FXML
    private JFXTextArea txaAnotacionesEnfermeria;
    @FXML
    private JFXTextArea txfRazonConsulta;
    @FXML
    private JFXTextArea txaPlanAtencion;
    @FXML
    private JFXTextArea txaExamenFisico;
    @FXML
    private JFXTextArea txaObservaciones;
    @FXML
    private JFXTextArea txaTratamiento;
    @FXML
    private MFXButton btnGuardarAtencion;
    @FXML
    private LineChart<?, ?> gfrEvolucion;
    @FXML
    private JFXTextField txfURLAdjunto;
    @FXML
    private MFXButton btnCargarArchivos;
    @FXML
    private JFXTextField txfNombreArchivo;
    @FXML
    private JFXDatePicker dpkFechaArchivo;
    @FXML
    private JFXTextArea txaComentarioArchivo;
    @FXML
    private JFXListView<?> ltvArchivos;
    @FXML
    private MFXButton btnGuardarExpediente;

    CliPacienteDto pacienteDto;
    CliUsuarioDto usuarioDto;
    CliAntecedenteDto antecedenteDto;
    List<CliAntecedenteDto> listaAntecedenteDto = new ArrayList<>();
    List<Node> requeridosAntecedentes = new ArrayList<>();

    ObservableList<CliCitaDto> listaCitas = FXCollections.observableArrayList();
    ResourceBundle resourceBundle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfCantHospitalizaciones.setTextFormatter(Formato.getInstance().twoDecimalFormat());
        txfCantOperaciones.setTextFormatter(Formato.getInstance().twoDecimalFormat());
        txfCantAlergias.setTextFormatter(Formato.getInstance().twoDecimalFormat());
        this.antecedenteDto = new CliAntecedenteDto();

        requeridosAntecedentes.addAll(Arrays.asList(txfAntTipo, txfAntParentesco, txfAntDescripcion));
        resourceBundle = FlowController.getInstance().getIdioma();
        nuevoAntecedente();

        ltvAntecedentes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Acciones que deseas realizar cuando se selecciona un ítem
            System.out.println("Elemento seleccionado: " + newValue);
            antecedenteDto = newValue;
            bindAntecedente();
        });
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnAgregarAntecedente(ActionEvent event) {
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridosAntecedentes);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
            } else {
                actualizarListasAntecedentes();

            }
        } catch (Exception ex) {
            Logger.getLogger(P13_ExpedienteViewController.class.getName()).log(Level.SEVERE, "Error guardando el antecedente.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
        nuevoAntecedente();
    }

    @FXML
    private void onActionBtnGuardarAtencion(ActionEvent event) {
    }

    @FXML
    private void onActionBtnCargarArchivos(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardarExpediente(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }

    public void cargarPaciente(CliPacienteDto paciente, CliUsuarioDto usuario) {
        pacienteDto = paciente;
        usuarioDto = usuario;
        bindPaciente();
        fillTableViewCitasPaciente();

        tbvHistorialCitas.getItems().clear();
        listaCitas.clear();
        listaCitas.addAll(pacienteDto.getCliCitaList());
        tbvHistorialCitas.setItems(listaCitas);
        tbvHistorialCitas.refresh();
    }

    public void bindPaciente() {
        lblNombreUsuario.setText(usuarioDto.nombreDosApellidos());

        lblCedula.textProperty().bind(pacienteDto.pacCedula);
        String genero = ("M".equals(pacienteDto.getPacGenero())) ? "Masculino" : "Femenino";
        lblGenero.setText(genero);
        lblFecha.textProperty().bind(pacienteDto.pacFnacimiento.asString());
        lblPacienteNombre.textProperty().bind(pacienteDto.pacNombre);
        lblPapellido.textProperty().bind(pacienteDto.pacPapellido);
        lblSapellido.textProperty().bind(pacienteDto.pacSapellido);
        lblTelefono.textProperty().bind(pacienteDto.pacTelefono);
        lblCorreo.textProperty().bind(pacienteDto.pacCorreo);

    }

    public void fillTableViewCitasPaciente() {
        tbvHistorialCitas.getItems().clear();

        TableColumn<CliCitaDto, String> tbcFecha = new TableColumn<>(/*resourceBundle.getString("key.identification")*/"Fecha");
        tbcFecha.setPrefWidth(150);
        tbcFecha.setCellValueFactory(cd -> {
            LocalDateTime fecha = cd.getValue().getCitFechaHora();
            String fechaString = fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
            return new SimpleStringProperty(fechaString);
        });

        TableColumn<CliCitaDto, String> tbcHora = new TableColumn<>(/*resourceBundle.getString("key.usertype")*/"Hora");
        tbcHora.setPrefWidth(150);
        tbcHora.setCellValueFactory(cd -> {
            LocalDateTime fecha = cd.getValue().getCitFechaHora();
            int hora = fecha.getHour();
            int minuto = fecha.getMinute();

            String horaFormateada;

            String aux = (hora < 12) ? " am." : " pm.";
            String minutes = (minuto == 0) ? ":0" : ":";
            int hora12 = (hora <= 12) ? hora : hora - 12;

            horaFormateada = (hora12 < 10) ? "0" : "";
            horaFormateada += hora12 + minutes + minuto + aux;

            return new SimpleStringProperty(horaFormateada);
        });

        TableColumn<CliCitaDto, String> tbcNombre = new TableColumn<>(/*resourceBundle.getString("key.name")*/"Nombre paciente");
        tbcNombre.setPrefWidth(200);
        tbcNombre.setCellValueFactory(cd -> {
            String nombrePac = cd.getValue().nombrePacienteCompleto();
            return new SimpleStringProperty(nombrePac);
        });

        TableColumn<CliCitaDto, String> tbcMotivo = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Motivo");
        tbcMotivo.setPrefWidth(150);
        tbcMotivo.setCellValueFactory(cd -> cd.getValue().citMotivo);

        tbvHistorialCitas.getColumns().addAll(tbcFecha, tbcHora, tbcNombre, tbcMotivo);
        tbvHistorialCitas.refresh();
    }

    private void nuevoAntecedente() {
        unbindAntecedente();
        this.antecedenteDto = new CliAntecedenteDto();
        bindAntecedente();
    }

    private void bindAntecedente() {
        txfAntTipo.textProperty().bindBidirectional(antecedenteDto.antTipo);
        txfAntParentesco.textProperty().bindBidirectional(antecedenteDto.antParentesco);
        txfAntDescripcion.textProperty().bindBidirectional(antecedenteDto.antDescripcion);
    }

    private void unbindAntecedente() {
        txfAntTipo.textProperty().unbindBidirectional(antecedenteDto.antTipo);
        txfAntParentesco.textProperty().unbindBidirectional(antecedenteDto.antParentesco);
        txfAntDescripcion.textProperty().unbindBidirectional(antecedenteDto.antDescripcion);
    }

    private void actualizarListasAntecedentes() {
        listaAntecedenteDto.add(antecedenteDto);

        ltvAntecedentes.getItems().clear();

//        List<String> antecedenteStrings = listaAntecedenteDto.stream()
//                .map(CliAntecedenteDto::getAntTipo)
//                .collect(Collectors.toList());
        ltvAntecedentes.getItems().addAll(listaAntecedenteDto);

        ltvAntecedentes.setCellFactory((ListView<CliAntecedenteDto> listView) -> new ListCell<CliAntecedenteDto>() {
            @Override
            protected void updateItem(CliAntecedenteDto item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getAntTipo());
                }
            }
        });
    }

}
