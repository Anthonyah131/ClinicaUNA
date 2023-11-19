package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliAgendaDto;
import cr.ac.una.clinicauna.model.CliAntecedenteDto;
import cr.ac.una.clinicauna.model.CliAtencionDto;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliExpedienteDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliParametroconsultaDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliAgendaService;
import cr.ac.una.clinicauna.service.CliAntecedenteService;
import cr.ac.una.clinicauna.service.CliAtencionService;
import cr.ac.una.clinicauna.service.CliExpedienteService;
import cr.ac.una.clinicauna.service.CliMedicoService;
import cr.ac.una.clinicauna.service.CliParametroconsultaService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
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
    private JFXTextArea txaAntPatologicos;
    @FXML
    private JFXTextArea txaTratamientosActuales;
    @FXML
    private TableView<CliAtencionDto> tbvHistorialCitas;
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
    private MFXButton btnGuardarExpediente;
    @FXML
    private TableView<CliAntecedenteDto> tbvAntecedentes;
    @FXML
    private TableView<?> tbvArchivos;
    @FXML
    private VBox vxAntecedentesFamiliares;
    @FXML
    private VBox vxAtencion;
    @FXML
    private VBox vxExamenes;
    @FXML
    private MFXButton btnLimpiarAnte;
    @FXML
    private MFXButton btnLimpiarAte;

    CliExpedienteDto expedienteDto;
    CliPacienteDto pacienteDto;
    CliUsuarioDto usuarioDto;
    CliAntecedenteDto antecedenteDto;
    CliAtencionDto atencionDto;
    List<Node> requeridosAntecedentes = new ArrayList<>();

    ResourceBundle resourceBundle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfCantHospitalizaciones.setTextFormatter(Formato.getInstance().twoDecimalFormat());
        txfCantOperaciones.setTextFormatter(Formato.getInstance().twoDecimalFormat());
        txfCantAlergias.setTextFormatter(Formato.getInstance().twoDecimalFormat());
        txaAntPatologicos.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txaAlergias.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txaTratamientosActuales.setTextFormatter(Formato.getInstance().maxLengthFormat(500));

        this.antecedenteDto = new CliAntecedenteDto();
        this.atencionDto = new CliAtencionDto();

        requeridosAntecedentes.addAll(Arrays.asList(txfAntTipo, txfAntParentesco, txfAntDescripcion));
        resourceBundle = FlowController.getInstance().getIdioma();
        cargarTablaAntecedentes();
        fillTableViewCitasPaciente();
        nuevoAntecedente();
        nuevaAtencion();
        listenerNodos();
    }

    @Override
    public void initialize() {
    }

    @FXML // Poner idioma
    private void onActionBtnAgregarAntecedente(ActionEvent event) {
        try {
//            String invalidos = ValidarRequeridos.validarRequeridos(requeridosParametro);
//            if (!invalidos.isEmpty()) {
//                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
//                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), mensaje);
//            } else {
            CliAntecedenteService antecedenteService = new CliAntecedenteService();
            Respuesta respuesta = antecedenteService.guardarAntecedente(this.antecedenteDto);
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), respuesta.getMensaje());
            } else {
                unbindAntecedente();
                this.antecedenteDto = (CliAntecedenteDto) respuesta.getResultado("Antecedente");
                
                List<CliAntecedenteDto> antecedentes = expedienteDto.getCliAntecedenteList();
                Predicate<CliAntecedenteDto> tieneMismoId = antecedente -> Objects.equals(antecedente.getAntId(), antecedenteDto.getAntId());
                boolean antecedenteEncontrado = antecedentes.stream().anyMatch(tieneMismoId);
                if (!antecedenteEncontrado) {
                    this.antecedenteDto.setModificado(true);
                    expedienteDto.getCliAntecedenteList().add(this.antecedenteDto);
                    onActionBtnGuardarExpediente(event);
                } else {
                    CliExpedienteService expedienteService = new CliExpedienteService();
                    respuesta = expedienteService.getExpediente(expedienteDto.getExpId());
                    this.expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");
                    cargarAntecedentes();
                }
                
                this.antecedenteDto = new CliAntecedenteDto();
                bindAntecedente();
                new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveParameterR", getStage(), "key.updatedParameterR");
            }
//                }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), "key.errorSavingParameterR");
        }
    }

    @FXML // Poner idioma
    private void onActionBtnGuardarAtencion(ActionEvent event) {
        try {
//            String invalidos = ValidarRequeridos.validarRequeridos(requeridosParametro);
//            if (!invalidos.isEmpty()) {
//                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
//                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), mensaje);
//            } else {
            if (atencionDto.getAteId() == null || atencionDto.getAteId() <= 0) { // poner idioma que diga "Cargue la atencion que quiere editar"
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), "key.errorDateStart");
            } else {
                CliAtencionService atencionService = new CliAtencionService();
                Respuesta respuesta = atencionService.guardarAtencion(this.atencionDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), respuesta.getMensaje());
                } else {
                    unbindAtencion();
                    this.atencionDto = (CliAtencionDto) respuesta.getResultado("Atencion");
                    CliExpedienteService expedienteService = new CliExpedienteService();
                    respuesta = expedienteService.getExpediente(expedienteDto.getExpId());
                    this.expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");
                    this.atencionDto = new CliAtencionDto();
                    cargarAtenciones();
                    bindAtencion();
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveParameterR", getStage(), "key.updatedParameterR");
                }
            }
//                }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), "key.errorSavingParameterR");
        }
    }

    @FXML
    private void onActionBtnCargarArchivos(ActionEvent event) {

    }

    @FXML // Poner idioma
    private void onActionBtnGuardarExpediente(ActionEvent event) {
        try {
            /*String invalidos = ValidarRequeridos.validarRequeridos(requeridos);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
            } else {*/
            if (expedienteDto.getExpId() != null) {
                CliExpedienteService expedienteService = new CliExpedienteService();
                Respuesta respuesta = expedienteService.guardarExpediente(expedienteDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                } else {
                    unbindExpediente();
                    this.expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");
                    cargarAntecedentes();
                    cargarAtenciones();
                    bindExpediente();
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                }
//                } else {
//                    System.out.println("Error guardando");
//                    // Se pone un mensaje que se debe cargar un medico para actualizarlo
//                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P08_MantenimientoMedicosViewController.class.getName()).log(Level.SEVERE, "Error guardando el medico.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }

    @FXML // Poner idioma
    private void onActionBtnLimpiarAnte(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoAntecedente();
        }
    }

    @FXML // Poner idioma
    private void onActionBtnLimpiarAte(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevaAtencion();
        }
    }

    private void bindExpediente() {
        txfCantHospitalizaciones.textProperty().bindBidirectional(expedienteDto.expHospitalizaciones);
        txfCantOperaciones.textProperty().bindBidirectional(expedienteDto.expOperaciones);
        txfCantAlergias.textProperty().bindBidirectional(expedienteDto.expAlergias);
        txaAntPatologicos.textProperty().bindBidirectional(expedienteDto.expPatologicos);
        txaAlergias.textProperty().bindBidirectional(expedienteDto.expTiposalergias);
        txaTratamientosActuales.textProperty().bindBidirectional(expedienteDto.expTratamientos);
        if (this.expedienteDto.getExpId() == null || this.expedienteDto.getExpId() <= 0) {
            vxAntecedentesFamiliares.setDisable(true);
            vxAtencion.setDisable(true);
            vxExamenes.setDisable(true);
        } else {
            vxAntecedentesFamiliares.setDisable(false);
            vxAtencion.setDisable(false);
            vxExamenes.setDisable(false);
        }
    }

    private void unbindExpediente() {
        txfCantHospitalizaciones.textProperty().unbindBidirectional(expedienteDto.expHospitalizaciones);
        txfCantOperaciones.textProperty().unbindBidirectional(expedienteDto.expOperaciones);
        txfCantAlergias.textProperty().unbindBidirectional(expedienteDto.expAlergias);
        txaAntPatologicos.textProperty().unbindBidirectional(expedienteDto.expPatologicos);
        txaAlergias.textProperty().unbindBidirectional(expedienteDto.expTiposalergias);
        txaTratamientosActuales.textProperty().unbindBidirectional(expedienteDto.expTratamientos);
    }

    public void cargarPaciente(CliPacienteDto paciente, CliUsuarioDto usuario) {
        pacienteDto = paciente;
        usuarioDto = usuario;
        bindPaciente();
        CliExpedienteService service = new CliExpedienteService();
        Respuesta respuesta = service.getExpediente(pacienteDto.getCliExpedienteList().get(0).getExpId());
        expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");
        bindExpediente();

        cargarAntecedentes();
        cargarAtenciones();
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

        TableColumn<CliAtencionDto, String> tbcFecha = new TableColumn<>(/*resourceBundle.getString("key.identification")*/"Fecha");
        tbcFecha.setPrefWidth(150);
        tbcFecha.setCellValueFactory(cd -> {
            LocalDateTime fecha = cd.getValue().getAteFechahora();
            String fechaString = fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
            return new SimpleStringProperty(fechaString);
        });

        TableColumn<CliAtencionDto, String> tbcHora = new TableColumn<>(/*resourceBundle.getString("key.usertype")*/"Hora");
        tbcHora.setPrefWidth(150);
        tbcHora.setCellValueFactory(cd -> {
            LocalDateTime fecha = cd.getValue().getAteFechahora();
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

        TableColumn<CliAtencionDto, String> tbcNombre = new TableColumn<>(/*resourceBundle.getString("key.name")*/"Nombre paciente");
        tbcNombre.setPrefWidth(200);
        tbcNombre.setCellValueFactory(cd -> {
            String nombrePac = pacienteDto.getPacNombre();
            return new SimpleStringProperty(nombrePac);
        });

//        TableColumn<CliAtencionDto, String> tbcMotivo = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Motivo");
//        tbcMotivo.setPrefWidth(150);
//        tbcMotivo.setCellValueFactory(cd -> cd.getValue().citMotivo);
        tbvHistorialCitas.getColumns().addAll(tbcFecha, tbcHora, tbcNombre);
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

    private void cargarTablaAntecedentes() {
        tbvAntecedentes.getItems().clear();

        TableColumn<CliAntecedenteDto, String> tbcTipo = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Tipo");
        tbcTipo.setPrefWidth(130);
        tbcTipo.setCellValueFactory(cd -> cd.getValue().antTipo);

        TableColumn<CliAntecedenteDto, String> tbcParent = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Parentesco");
        tbcParent.setPrefWidth(150);
        tbcParent.setCellValueFactory(cd -> cd.getValue().antParentesco);

        TableColumn<CliAntecedenteDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(100);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<CliAntecedenteDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<CliAntecedenteDto, Boolean> p) -> new P13_ExpedienteViewController.ButtonCellAntecedentes());

        tbvAntecedentes.getColumns().addAll(tbcTipo, tbcParent, tbcEliminar);
        tbvAntecedentes.refresh();

    }

    private void cargarAntecedentes() {
        tbvAntecedentes.getItems().clear();
        tbvAntecedentes.setItems(this.expedienteDto.getCliAntecedenteList());
        tbvAntecedentes.refresh();
    }

    private void nuevaAtencion() {
        unbindAtencion();
        this.atencionDto = new CliAtencionDto();
        bindAtencion();
    }

    private void bindAtencion() {
        txfPresionArterial.textProperty().bindBidirectional(atencionDto.atePresion);
        txfFrecuenciaCar.textProperty().bindBidirectional(atencionDto.ateFrecuenciacard);
        txfPeso.textProperty().bindBidirectional(atencionDto.atePeso);
        txfTalla.textProperty().bindBidirectional(atencionDto.ateTalla);
        txfTemperatura.textProperty().bindBidirectional(atencionDto.ateTemperatura);
        txaAnotacionesEnfermeria.textProperty().bindBidirectional(atencionDto.ateAnotacionenfe);
        txfRazonConsulta.textProperty().bindBidirectional(atencionDto.ateRazonconsulta);
        txaPlanAtencion.textProperty().bindBidirectional(atencionDto.atePlanatencion);
        txaObservaciones.textProperty().bindBidirectional(atencionDto.ateObservaciones);
        txaTratamiento.textProperty().bindBidirectional(atencionDto.ateTratamiento);
    }

    private void unbindAtencion() {
        txfPresionArterial.textProperty().unbindBidirectional(atencionDto.atePresion);
        txfFrecuenciaCar.textProperty().unbindBidirectional(atencionDto.ateFrecuenciacard);
        txfPeso.textProperty().unbindBidirectional(atencionDto.atePeso);
        txfTalla.textProperty().unbindBidirectional(atencionDto.ateTalla);
        txfTemperatura.textProperty().unbindBidirectional(atencionDto.ateTemperatura);
        txaAnotacionesEnfermeria.textProperty().unbindBidirectional(atencionDto.ateAnotacionenfe);
        txfRazonConsulta.textProperty().unbindBidirectional(atencionDto.ateRazonconsulta);
        txaPlanAtencion.textProperty().unbindBidirectional(atencionDto.atePlanatencion);
        txaObservaciones.textProperty().unbindBidirectional(atencionDto.ateObservaciones);
        txaTratamiento.textProperty().unbindBidirectional(atencionDto.ateTratamiento);
    }

    private void cargarAtenciones() {
        ObservableList<CliAtencionDto> atenciones = this.expedienteDto.getCliAtencionList();
        LocalDateTime fechaActual = LocalDateTime.now();

//        List<CliAtencionDto> atencionesFiltradas = atenciones.stream() // Descomentar esto para traer las atenciones con fechas ya pasadas
//                .filter(atencion -> atencion.getAteFechahora().isBefore(fechaActual) || atencion.getAteFechahora().isEqual(fechaActual))
//                .collect(Collectors.toList());
        tbvHistorialCitas.getItems().clear();
//        tbvHistorialCitas.setItems((ObservableList<CliAtencionDto>) atencionesFiltradas);
        tbvHistorialCitas.setItems(atenciones);
        tbvHistorialCitas.refresh();
    }

    private void listenerNodos() {
        tbvAntecedentes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindAntecedente();
                antecedenteDto = newValue;
                bindAntecedente();
            } else {
                nuevoAntecedente();
            }
        });

        tbvHistorialCitas.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindAtencion();
                atencionDto = newValue;
                bindAtencion();
            } else {
                nuevaAtencion();
            }
        });
    }

    private void cargarTablaArchivos() {
        tbvAntecedentes.getItems().clear();

        TableColumn<CliAntecedenteDto, String> tbcTipo = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Tipo");
        tbcTipo.setPrefWidth(150);
        tbcTipo.setCellValueFactory(cd -> cd.getValue().antTipo);

        TableColumn<CliAntecedenteDto, String> tbcParent = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Tipo");
        tbcParent.setPrefWidth(150);
        tbcParent.setCellValueFactory(cd -> cd.getValue().antTipo);

        tbvAntecedentes.getColumns().addAll(tbcTipo, tbcParent);
        tbvAntecedentes.refresh();
    }

    private class ButtonCellAntecedentes extends TableCell<CliAntecedenteDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCellAntecedentes() {
            cellButton.setPrefWidth(500);
            cellButton.setText("X");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                CliAntecedenteDto ant = (CliAntecedenteDto) ButtonCellAntecedentes.this.getTableView().getItems().get(ButtonCellAntecedentes.this.getIndex());
                expedienteDto.getCliAntecedenteListEliminados().add(ant);
                tbvAntecedentes.getItems().remove(ant);
                tbvAntecedentes.refresh();
//                try {
//                    if (pac.getPacId() == null) {
//                        new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteUser", getStage(), "key.loadUserDelete");
//                    } else {
//                        CliPacienteService service = new CliPacienteService();
//                        Respuesta respuesta = service.eliminarPaciente(pac.getPacId());
//                        if (!respuesta.getEstado()) {
//                            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteUser", getStage(), respuesta.getMensaje());
//                        } else {
//                            new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.deleteUser", getStage(), "key.deleteUserSuccess");
//                            nuevoPaciente();
//                            tbvResultados.getItems().remove(pac);
//                            tbvResultados.refresh();
//                        }
//                    }
//                } catch (Exception ex) {
//                    Logger.getLogger(P09_MantenimientoPacientesViewController.class.getName()).log(Level.SEVERE, "Error eliminando el paciente.", ex);
//                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteUser", getStage(), "key.deleteUserError");
//                }
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
