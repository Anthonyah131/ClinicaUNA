package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliAntecedenteDto;
import cr.ac.una.clinicauna.model.CliAtencionDto;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliExamenDto;
import cr.ac.una.clinicauna.model.CliExpedienteDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliAntecedenteService;
import cr.ac.una.clinicauna.service.CliAtencionService;
import cr.ac.una.clinicauna.service.CliCitaService;
import cr.ac.una.clinicauna.service.CliExamenService;
import cr.ac.una.clinicauna.service.CliExpedienteService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

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
    private TableView<CliExamenDto> tbvArchivos;
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
    @FXML
    private MFXButton btnLimpiarE;

    CliExpedienteDto expedienteDto;
    CliPacienteDto pacienteDto;
    CliUsuarioDto usuarioDto;
    CliAntecedenteDto antecedenteDto;
    CliAtencionDto atencionDto;
    CliExamenDto examenDto;
    CliCitaDto citaDto;

    List<Node> requeridosExpediente = new ArrayList<>();
    List<Node> requeridosAntecedentes = new ArrayList<>();
    List<Node> requeridosAtencion = new ArrayList<>();
    List<Node> requeridosExamen = new ArrayList<>();

    ResourceBundle resourceBundle;
    @FXML
    private MFXButton btnGuardarE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfCantHospitalizaciones.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txfCantOperaciones.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txfCantAlergias.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txaAntPatologicos.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txaAlergias.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txaTratamientosActuales.setTextFormatter(Formato.getInstance().maxLengthFormat(500));

        txfAntTipo.setTextFormatter(Formato.getInstance().maxLengthFormat(10));
        txfAntParentesco.setTextFormatter(Formato.getInstance().maxLengthFormat(20));
        txfAntDescripcion.setTextFormatter(Formato.getInstance().maxLengthFormat(20));

        txfPresionArterial.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txfFrecuenciaCar.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txfPeso.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txfTalla.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txfTemperatura.setTextFormatter(Formato.getInstance().maxIntegerFormat(5));
        txaAnotacionesEnfermeria.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txfRazonConsulta.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txaPlanAtencion.setTextFormatter(Formato.getInstance().maxLengthFormat(1));
        txaObservaciones.setTextFormatter(Formato.getInstance().maxLengthFormat(80));
        txaTratamiento.setTextFormatter(Formato.getInstance().maxLengthFormat(50));

        txfNombreArchivo.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txaComentarioArchivo.setTextFormatter(Formato.getInstance().maxLengthFormat(80));

        this.antecedenteDto = new CliAntecedenteDto();
        this.atencionDto = new CliAtencionDto();
        this.examenDto = new CliExamenDto();

        indicarRequeridos();
        resourceBundle = FlowController.getInstance().getIdioma();
        cargarTablaAntecedentes();
        fillTableViewCitasPaciente();
        cargarTablaArchivos();
        nuevoAntecedente();
        nuevaAtencion();
        nuevoExamen();
        listenerNodos();
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
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveAntece", getStage(), mensaje);
            } else {
                CliAntecedenteService antecedenteService = new CliAntecedenteService();
                Respuesta respuesta = antecedenteService.guardarAntecedente(this.antecedenteDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveAntece", getStage(), respuesta.getMensaje());
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
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveAntece", getStage(), "key.anteceActualizada");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveAntece", getStage(), "key.errorSavingAntece");
        }
    }

    @FXML
    private void onActionBtnGuardarAtencion(ActionEvent event) {
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridosAtencion);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveAtencion", getStage(), mensaje);
            } else {
                if (atencionDto.getAteId() == null || atencionDto.getAteId() <= 0) {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveAtencion", getStage(), "key.loadAtencionEdit");
                } else {
                    CliAtencionService atencionService = new CliAtencionService();
                    if (atencionDto.getAtePeso() != null && atencionDto.getAteTalla() != null
                            && !atencionDto.getAtePeso().isBlank() && !atencionDto.getAteTalla().isBlank()) {
                        int peso = Integer.parseInt(atencionDto.getAtePeso());
                        double talla = Double.parseDouble(atencionDto.getAteTalla()) / 100;
                        int imc = (int) (peso / (talla * talla));
                        atencionDto.setAteImc("" + imc);
                    } else {
                        atencionDto.setAteImc("0");
                    }
                    Respuesta respuesta = atencionService.guardarAtencion(this.atencionDto);
                    if (!respuesta.getEstado()) {
                        new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveAtencion", getStage(), respuesta.getMensaje());
                    } else {
                        unbindAtencion();
                        this.atencionDto = (CliAtencionDto) respuesta.getResultado("Atencion");

                        CliExpedienteService expedienteService = new CliExpedienteService();
                        respuesta = expedienteService.getExpediente(expedienteDto.getExpId());
                        this.expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");

                        this.atencionDto = new CliAtencionDto();
                        cargarAtenciones();
                        bindAtencion();
                        citaDto.setCitEstado("A");
                        actualizarCita(citaDto);
                        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveAtencion", getStage(), "key.atencionActualizada");
                    }
                }
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(P13_ExpedienteViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveAtencion", getStage(), "key.errorSavingAtencion");
        }
    }

    @FXML
    private void onActionBtnCargarArchivos(ActionEvent event) {
        //Inicializa el FileChooser y le da un titulo a la nueva ventana
        FileChooser fileChooser = new FileChooser();
        String fileString = resourceBundle.getString("key.selectFile");
        fileChooser.setTitle(fileString);

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif", "PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txfURLAdjunto.setText(file.getAbsolutePath());
        }
        try {
            if (file != null) {
                examenDto.setExaArchivo(fileToByte(file));
            }
        } catch (IOException ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionBtnGuardarE(ActionEvent event) {
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridosExamen);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.loadFilesExp", getStage(), mensaje);
            } else {
                CliExamenService examenService = new CliExamenService();
                Respuesta respuesta = examenService.guardarExamen(this.examenDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.loadFilesExp", getStage(), respuesta.getMensaje());
                } else {
                    unbindExamen();
                    this.examenDto = (CliExamenDto) respuesta.getResultado("Examen");

                    List<CliExamenDto> examenes = expedienteDto.getCliExamenList();

                    List<CliAtencionDto> atenciones = expedienteDto.getCliAtencionList();
                    LocalDateTime fechaHoraCita = citaDto.getCitFechaHora();
                    Optional<CliAtencionDto> atencionActual = atenciones.stream()
                            .filter(atencion -> atencion.getAteFechahora().equals(fechaHoraCita))
                            .findFirst();

                    // Verificar si se encontró una atención con la misma fecha y hora
                    if (atencionActual.isPresent()) {
                        atencionDto = atencionActual.get();
                        this.examenDto.setModificado(true);
                        atencionDto.getCliExamenList().add(this.examenDto);
                        onActionBtnGuardarAtencion(event);
                    }

                    Predicate<CliExamenDto> tieneMismoId = antecedente -> Objects.equals(antecedente.getExaId(), examenDto.getExaId());
                    boolean antecedenteEncontrado = examenes.stream().anyMatch(tieneMismoId);
                    if (!antecedenteEncontrado) {
                        this.examenDto.setModificado(true);
                        expedienteDto.getCliExamenList().add(this.examenDto);
                        onActionBtnGuardarExpediente(event);
                    } else {
                        CliExpedienteService expedienteService = new CliExpedienteService();
                        respuesta = expedienteService.getExpediente(expedienteDto.getExpId());
                        this.expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");
                        cargarExamenes();
                    }

                    this.examenDto = new CliExamenDto();
                    bindExamen();
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.loadFilesExp", getStage(), "key.filesActualizados");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.loadFilesExp", getStage(), "key.errorSavingFiles");
        }
    }

    @FXML
    private void onActionBtnGuardarExpediente(ActionEvent event) {
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridosExpediente);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveExpediente", getStage(), mensaje);
            } else {
                CliExpedienteService expedienteService = new CliExpedienteService();
                Respuesta respuesta = expedienteService.guardarExpediente(expedienteDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveExpediente", getStage(), respuesta.getMensaje());
                } else {
                    unbindExpediente();
                    this.expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");
                    cargarAntecedentes();
                    cargarAtenciones();
                    cargarExamenes();
                    bindExpediente();
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveExpediente", getStage(), "key.expedienteActualizado");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P08_MantenimientoMedicosViewController.class.getName()).log(Level.SEVERE, "Error guardando el medico.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveExpediente", getStage(), "key.errorSavingExpediente");
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }

    @FXML
    private void onActionBtnLimpiarAnte(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoAntecedente();
        }
    }

    @FXML
    private void onActionBtnLimpiarAte(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevaAtencion();
        }
    }

    @FXML
    private void onActionBtnLimpiarE(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoExamen();
        }
    }

    private void indicarRequeridos() {
        requeridosExpediente.clear();
        requeridosAntecedentes.clear();
        requeridosAtencion.clear();
        requeridosExamen.clear();

        requeridosExpediente.addAll(Arrays.asList());
        requeridosAntecedentes.addAll(Arrays.asList());
        requeridosAtencion.addAll(Arrays.asList());
        requeridosExamen.addAll(Arrays.asList(txfNombreArchivo));
    }

    private void bindExpediente() {
        txfCantHospitalizaciones.textProperty().bindBidirectional(expedienteDto.expHospitalizaciones);
        txfCantOperaciones.textProperty().bindBidirectional(expedienteDto.expOperaciones);
        txfCantAlergias.textProperty().bindBidirectional(expedienteDto.expAlergias);
        txaAntPatologicos.textProperty().bindBidirectional(expedienteDto.expPatologicos);
        txaAlergias.textProperty().bindBidirectional(expedienteDto.expTiposalergias);
        txaTratamientosActuales.textProperty().bindBidirectional(expedienteDto.expTratamientos);
    }

    private void unbindExpediente() {
        txfCantHospitalizaciones.textProperty().unbindBidirectional(expedienteDto.expHospitalizaciones);
        txfCantOperaciones.textProperty().unbindBidirectional(expedienteDto.expOperaciones);
        txfCantAlergias.textProperty().unbindBidirectional(expedienteDto.expAlergias);
        txaAntPatologicos.textProperty().unbindBidirectional(expedienteDto.expPatologicos);
        txaAlergias.textProperty().unbindBidirectional(expedienteDto.expTiposalergias);
        txaTratamientosActuales.textProperty().unbindBidirectional(expedienteDto.expTratamientos);
        
        txfCantHospitalizaciones.clear();
        txfCantOperaciones.clear();
        txfCantAlergias.clear();
    }

    public void cargarPaciente(CliPacienteDto paciente, CliUsuarioDto usuario, CliCitaDto citaDto) {
        pacienteDto = paciente;
        usuarioDto = usuario;
        this.citaDto = citaDto;
        bindPaciente();
        CliExpedienteService service = new CliExpedienteService();
        Respuesta respuesta = service.getExpediente(pacienteDto.getCliExpedienteList().get(0).getExpId());
        expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");
        bindExpediente();

        cargarAntecedentes();
        cargarAtenciones();
        cargarExamenes();
    }

    private void actualizarCita(CliCitaDto cita) {
        try {
            CliCitaService citaService = new CliCitaService();
            Respuesta respuesta = citaService.guardarCita(cita);

            if (!respuesta.getEstado()) {
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveCitas", getStage(), respuesta.getMensaje());
            } else {
                new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveCitas", getStage(), "key.appoActualizada");
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveCitas", getStage(), "key.errorSavingAppo");
        }
    }

    public void bindPaciente() {
        lblNombreUsuario.setText(usuarioDto.nombreDosApellidos());

        lblCedula.textProperty().bind(pacienteDto.pacCedula);
        String genero = ("M".equals(pacienteDto.getPacGenero())) ? resourceBundle.getString("key.man") : resourceBundle.getString("key.women");
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

        TableColumn<CliAtencionDto, String> tbcFecha = new TableColumn<>(resourceBundle.getString("key.date"));
        tbcFecha.setPrefWidth(150);
        tbcFecha.setCellValueFactory(cd -> {
            LocalDateTime fecha = cd.getValue().getAteFechahora();
            String fechaString = fecha.getDayOfMonth() + "/" + fecha.getMonthValue() + "/" + fecha.getYear();
            return new SimpleStringProperty(fechaString);
        });

        TableColumn<CliAtencionDto, String> tbcHora = new TableColumn<>(resourceBundle.getString("key.attenHora"));
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

        TableColumn<CliAtencionDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.namePacient"));
        tbcNombre.setPrefWidth(200);
        tbcNombre.setCellValueFactory(cd -> {
            String nombrePac = pacienteDto.getPacNombre();
            return new SimpleStringProperty(nombrePac);
        });

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

        TableColumn<CliAntecedenteDto, String> tbcTipo = new TableColumn<>(resourceBundle.getString("key.typeHistory"));
        tbcTipo.setPrefWidth(130);
        tbcTipo.setCellValueFactory(cd -> cd.getValue().antTipo);

        TableColumn<CliAntecedenteDto, String> tbcParent = new TableColumn<>(resourceBundle.getString("key.relationHistory"));
        tbcParent.setPrefWidth(150);
        tbcParent.setCellValueFactory(cd -> cd.getValue().antParentesco);

        TableColumn<CliAntecedenteDto, Boolean> tbcEliminar = new TableColumn<>(resourceBundle.getString("key.delete"));
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

    public void llenarGrafico() {
        ObservableList<CliAtencionDto> atenciones = this.expedienteDto.getCliAtencionList();
        List<CliAtencionDto> atencionesFiltradas = new ArrayList<>();
        if (citaDto != null) {
            LocalDateTime fechaCita = citaDto.getCitFechaHora();
            atencionesFiltradas = atenciones.stream()
                    .filter(atencion -> atencion.getAteFechahora().isBefore(fechaCita) || atencion.getAteFechahora().isEqual(fechaCita))
                    .collect(Collectors.toList());
            atencionesFiltradas.sort(Comparator.comparing(CliAtencionDto::getAteFechahora));
        } else {
            atencionesFiltradas.addAll(atenciones);
        }

        gfrEvolucion.getData().clear();
        XYChart.Series series = new XYChart.Series();

        for (CliAtencionDto atencionDto : atencionesFiltradas) {
            if (atencionDto.getAtePeso() != null && atencionDto.getAteTalla() != null
                    && !atencionDto.getAtePeso().isBlank() && !atencionDto.getAteTalla().isBlank()) {
                double peso = Double.parseDouble(atencionDto.getAtePeso());
                double talla = Double.parseDouble(atencionDto.getAteTalla()) / 100;
                int imc = (int) (peso / (talla * talla));

                LocalDateTime fecha = atencionDto.getAteFechahora();
                String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                series.getData().add(new XYChart.Data(fechaFormateada, imc));
            }
        }

        gfrEvolucion.getData().add(series);
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
        lblIMC.setText(atencionDto.getAteImc());
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
        lblIMC.setText("IMC");
        txfPresionArterial.clear();
        txfFrecuenciaCar.clear();
        txfPeso.clear();
        txfTalla.clear();
        txfTemperatura.clear();
    }

    private void cargarAtenciones() {
        ObservableList<CliAtencionDto> atenciones = this.expedienteDto.getCliAtencionList();
        atenciones.sort(Comparator.comparing(CliAtencionDto::getAteFechahora));
        if (citaDto != null) {
            LocalDateTime fechaCita = citaDto.getCitFechaHora();
            List<CliAtencionDto> atencionesFiltradas = atenciones.stream()
                    .filter(atencion -> atencion.getAteFechahora().isBefore(fechaCita) || atencion.getAteFechahora().isEqual(fechaCita))
                    .collect(Collectors.toList());
            tbvHistorialCitas.getItems().clear();
            tbvHistorialCitas.setItems(FXCollections.observableArrayList(atencionesFiltradas));
            tbvHistorialCitas.refresh();
        } else {
            tbvHistorialCitas.getItems().clear();
            tbvHistorialCitas.setItems(FXCollections.observableArrayList(atenciones));
            tbvHistorialCitas.refresh();
        }

        llenarGrafico();
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

        tbvArchivos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindExamen();
                examenDto = newValue;
                bindExamen();
            } else {
                nuevoExamen();
            }
        });

        txfPeso.textProperty().addListener((observable, oldValue, newValue) -> {
            // Aquí puedes realizar acciones basadas en el cambio del texto
        });
        txfTalla.textProperty().addListener((observable, oldValue, newValue) -> {
            // Aquí puedes realizar acciones basadas en el cambio del texto
        });
    }

    private void cargarTablaArchivos() {
        tbvArchivos.getItems().clear();

        TableColumn<CliExamenDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(50);
        tbcId.setCellValueFactory(cd -> cd.getValue().exaId);

        TableColumn<CliExamenDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.name"));
        tbcNombre.setPrefWidth(150);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().exaNombre);

        tbvArchivos.getColumns().addAll(tbcId, tbcNombre);
        tbvArchivos.refresh();
    }

    private void nuevoExamen() {
        unbindExamen();
        this.examenDto = new CliExamenDto();
        bindExamen();
    }

    private void bindExamen() {
        txfNombreArchivo.textProperty().bindBidirectional(examenDto.exaNombre);
        txaComentarioArchivo.textProperty().bindBidirectional(examenDto.exaAnotacionesmed);
        dpkFechaArchivo.valueProperty().bindBidirectional(examenDto.exaFecha);
    }

    private void unbindExamen() {
        txfNombreArchivo.textProperty().unbindBidirectional(examenDto.exaNombre);
        txaComentarioArchivo.textProperty().unbindBidirectional(examenDto.exaAnotacionesmed);
        dpkFechaArchivo.valueProperty().unbindBidirectional(examenDto.exaFecha);
    }

    private void cargarExamenes() {
        tbvArchivos.getItems().clear();
        tbvArchivos.setItems(this.expedienteDto.getCliExamenList());
        tbvArchivos.refresh();
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

    private byte[] fileToByte(File file) throws IOException {//En teoria este deberia tomar cualquier archivo y convertirlo a byte[]
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] filesToBytes = IOUtils.toByteArray(fiStream);
        return filesToBytes;
    }

    private static boolean isPDF(byte[] bytes) {
        String header = new String(bytes, 0, Math.min(bytes.length, 4));
        return header.startsWith("%PDF");
    }

    private static boolean isImage(byte[] bytes) {
        // Verificar si los primeros bytes coinciden con los encabezados de formatos de imagen conocidos
        if (bytes.length >= 2) {
            if ((bytes[0] == (byte) 0xFF && bytes[1] == (byte) 0xD8) // JPEG
                    || (bytes[0] == (byte) 0x89 && bytes[1] == (byte) 0x50 && bytes[2] == (byte) 0x4E && bytes[3] == (byte) 0x47)) // PNG
            // Agrega más verificaciones según sea necesario para otros formatos
            {
                return true;
            }
        }
        return false;
    }

    private Image byteToImage(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new Image(bis);
    }

    private void savePdfFileChooser(byte[] pdfBytes) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
        File selectedFile = fileChooser.showSaveDialog(new Stage());

        if (selectedFile != null) {
            try {
                FileUtils.writeByteArrayToFile(selectedFile, pdfBytes);
            } catch (IOException e) {
                e.printStackTrace();
                Logger.getLogger(P13_ExpedienteViewController.class.getName()).log(Level.SEVERE, "Error guardando el pdf.", e);
            }
        }
    }
}
