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
import cr.ac.una.clinicauna.service.CliExamenService;
import cr.ac.una.clinicauna.service.CliExpedienteService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
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
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
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
    List<Node> requeridosAntecedentes = new ArrayList<>();

    ResourceBundle resourceBundle;
    @FXML
    private MFXButton btnGuardarE;

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
        this.examenDto = new CliExamenDto();

        requeridosAntecedentes.addAll(Arrays.asList(txfAntTipo, txfAntParentesco, txfAntDescripcion));
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
        //Inicializa el FileChooser y le da un titulo a la nueva ventana
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"),
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

    @FXML // Poner idioma
    private void onActionBtnGuardarE(ActionEvent event) {
        try {
//            String invalidos = ValidarRequeridos.validarRequeridos(requeridosParametro);
//            if (!invalidos.isEmpty()) {
//                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
//                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), mensaje);
//            } else {
            CliExamenService examenService = new CliExamenService();
            Respuesta respuesta = examenService.guardarExamen(this.examenDto);
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), respuesta.getMensaje());
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
                new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveParameterR", getStage(), "key.updatedParameterR");
            }
//                }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), "key.errorSavingParameterR");
        }
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
                    cargarExamenes();
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

    @FXML // Poner idioma
    private void onActionBtnLimpiarE(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoExamen();
        }
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

    public void llenarGrafico() {
        ObservableList<CliAtencionDto> atenciones = this.expedienteDto.getCliAtencionList();
        atenciones.sort(Comparator.comparing(CliAtencionDto::getAteFechahora));
        // Limpiar el gráfico
        gfrEvolucion.getData().clear();

        // Crear una nueva serie de datos
        XYChart.Series series = new XYChart.Series();

        // Recorrer la lista de atenciones y agregar datos al gráfico
        for (CliAtencionDto atencionDto : atenciones) {
            if (atencionDto.getAtePeso() != null && atencionDto.getAteTalla() != null
                    && !atencionDto.getAtePeso().isBlank() && !atencionDto.getAteTalla().isBlank()) {
                // Calcular el IMC
                double peso = Double.parseDouble(atencionDto.getAtePeso());
                double talla = Double.parseDouble(atencionDto.getAteTalla()); // Convertir a metros
                double imc = (int) (peso / (talla * talla));

                // Obtener la fecha de la atención
                LocalDateTime fecha = atencionDto.getAteFechahora();
                // Obtener la fecha de la atención como una cadena formateada
                String fechaFormateada = fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                // Agregar el punto al gráfico
                series.getData().add(new XYChart.Data(fechaFormateada, imc));
            }
        }

        // Agregar la serie al gráfico
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
        if (atencionDto.getAtePeso() != null && atencionDto.getAteTalla() != null
                && !atencionDto.getAtePeso().isBlank() && !atencionDto.getAteTalla().isBlank()) {
            lblIMC.setText("" + (Double.parseDouble(atencionDto.getAtePeso()) / (Double.parseDouble(atencionDto.getAteTalla()) * Double.parseDouble(atencionDto.getAteTalla()))));
        }
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
    }

    // Poner idioma
    private void cargarTablaArchivos() {
        tbvArchivos.getItems().clear();

        TableColumn<CliExamenDto, String> tbcId = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Id");
        tbcId.setPrefWidth(50);
        tbcId.setCellValueFactory(cd -> cd.getValue().exaId);

        TableColumn<CliExamenDto, String> tbcNombre = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Nombre");
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
        ObservableList<CliExamenDto> examenes = this.expedienteDto.getCliExamenList();
        LocalDateTime fechaActual = LocalDateTime.now();

//        List<CliAtencionDto> atencionesFiltradas = atenciones.stream() // Descomentar esto para traer las atenciones con fechas ya pasadas
//                .filter(atencion -> atencion.getAteFechahora().isBefore(fechaActual) || atencion.getAteFechahora().isEqual(fechaActual))
//                .collect(Collectors.toList());
        tbvArchivos.getItems().clear();
//        tbvHistorialCitas.setItems((ObservableList<CliAtencionDto>) atencionesFiltradas);
        tbvArchivos.setItems(examenes);
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
