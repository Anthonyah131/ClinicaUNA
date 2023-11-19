package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliAgendaDto;
import cr.ac.una.clinicauna.model.CliAtencionDto;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliExpedienteDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliAgendaService;
import cr.ac.una.clinicauna.service.CliAtencionService;
import cr.ac.una.clinicauna.service.CliCitaService;
import cr.ac.una.clinicauna.service.CliExpedienteService;
import cr.ac.una.clinicauna.service.CliMedicoService;
import cr.ac.una.clinicauna.service.CliPacienteService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P11_NuevaCitaViewController extends Controller implements Initializable {

    @FXML
    private JFXComboBox<String> cboxEstadoCita;
    @FXML
    private JFXComboBox<Integer> cboxEspacioHora;
    @FXML
    private Label lblNombrePac;
    @FXML
    private MFXButton btnAgragarPaciente;
    @FXML
    private Label lblNombreUsu;
    @FXML
    private JFXTextField txfMotivo;
    @FXML
    private Label lblNumero;
    @FXML
    private Label lblCorreo;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private Label lblFechaHora;
    @FXML
    private MFXButton btnMoverCita;

    CliCitaDto citaDto;
    CliUsuarioDto usuarioDto;
    CliPacienteDto pacienteDto;
    CliAgendaDto agendaDto;
    CliMedicoDto medicoDto;
    CliCitaDto citasVector[];
    int posVec;
    List<Node> requeridos = new ArrayList<>();
    LocalDateTime fechaHoraCita;
    LocalDateTime horaAntigua;

    ResourceBundle resourceBundle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        resourceBundle = FlowController.getInstance().getIdioma();
        fillCbox();
        requeridos.addAll(Arrays.asList(cboxEstadoCita, cboxEspacioHora));

    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {

        String invalidos = ValidarRequeridos.validarRequeridos(requeridos);
        if (!invalidos.isEmpty()) {
            String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
        } else {
            int cboxEspacios = cboxEspacioHora.getValue();

            //Si la cita no existe intenta crear una nueva si cumple los espacios
            if (citasVector[posVec] == null) {
                if (comprobarEspacios(posVec, 0)) {
                    asignarDatosCita();
                } else {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "No hay suficientes campos libres");
                }
            } // si los espacios del cbox son mayores a los que ya tenia la cita se comprueba el espacio
            else if (cboxEspacioHora.getValue() > citaDto.getCliCantespacios()) {
                int inicio = citaDto.getCliCantespacios().intValue();
                int posAux = posVec + inicio;

                if (comprobarEspacios(posAux, inicio)) {
                    asignarDatosCita();
                } else {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "No hay suficientes campos libres");
                }
            } else {
                asignarDatosCita();
            }
        }
    }

    private void asignarDatosCita() {
        citaDto.setCitMotivo(txfMotivo.getText());
        citaDto.setCliCantespacios(Long.valueOf(cboxEspacioHora.getValue()));
        citaDto.setCitEstado(estadoCita());
        guardarCita();

        P10_AgendaViewController agendaController = (P10_AgendaViewController) FlowController.getInstance().getController("P10_AgendaView");
        agendaController.cargarCita(citaDto, agendaDto, medicoDto);
        stage.close();
    }

    private Boolean comprobarEspacios(int posV, int inicio) {
        int tamano = cboxEspacioHora.getValue();
        int indiceVector = posV;
        for (int i = inicio; i < tamano; i++) {
            if (indiceVector == citasVector.length) {
                return false;
            }
            if (citasVector[indiceVector] != null) {
                return false;
            }
            indiceVector++;
        }
        return true;
    }
    
    // Poner idioma
    private void guardarCita() {
        Boolean banderaNueva = false;
        try {
            if (agendaDto.getAgeId() == null || agendaDto.getAgeId() <= 0) {
                CliAgendaService agendaService = new CliAgendaService();
                Respuesta respuesta = agendaService.guardarAgenda(agendaDto);
                agendaDto = (CliAgendaDto) respuesta.getResultado("Agenda");
            }
            CliCitaService citaService = new CliCitaService();
            if (citaDto.getCitId() == null || citaDto.getCitId() <= 0) {
                banderaNueva = true;
            }
            Respuesta respuesta = citaService.guardarCita(citaDto);

            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
            } else {
                this.citaDto = (CliCitaDto) respuesta.getResultado("Cita");

                if (citaDto.getCliPacienteDto() == null) {
                    CliPacienteService pacienteService = new CliPacienteService();
                    citaDto.setModificado(true);
                    pacienteDto.getCliCitaList().add(citaDto);
                    pacienteService.guardarPaciente(pacienteDto);
                }

                if (citaDto.getCliAgendaDto() == null) {
                    CliAgendaService agendaService = new CliAgendaService();
                    citaDto.setModificado(true);
                    agendaDto.getCliCitaList().add(citaDto);
                    respuesta = agendaService.guardarAgenda(agendaDto);
                    agendaDto = (CliAgendaDto) respuesta.getResultado("Agenda");
                }

                if (agendaDto.getCliMedicoDto() == null) {
                    CliMedicoService medicoService = new CliMedicoService();
                    agendaDto.setModificado(true);
                    medicoDto.getCliAgendaList().add(agendaDto);
                    respuesta = medicoService.guardarMedico(medicoDto);
                    medicoDto = (CliMedicoDto) respuesta.getResultado("Medico");
                }

                respuesta = citaService.getCita(citaDto.getCitId());
                this.citaDto = (CliCitaDto) respuesta.getResultado("Cita");

                if (!citaDto.getCliPacienteDto().getCliExpedienteList().isEmpty() && banderaNueva) { // Crea nueva atencion si es cita nueva
                    CliExpedienteService expedienteService = new CliExpedienteService();
                    CliAtencionService atencionService = new CliAtencionService();

                    CliAtencionDto atencionDto = new CliAtencionDto();
                    atencionDto.setAteFechahora(citaDto.getCitFechaHora());
                    CliExpedienteDto expedienteDto = citaDto.getCliPacienteDto().getCliExpedienteList().get(0);
                    respuesta = expedienteService.getExpediente(expedienteDto.getExpId());
                    expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");

                    Respuesta respuestaAtencion = atencionService.guardarAtencion(atencionDto);
                    atencionDto = (CliAtencionDto) respuestaAtencion.getResultado("Atencion");
                    atencionDto.setModificado(true);
                    expedienteDto.getCliAtencionList().add(atencionDto);
                    expedienteService.guardarExpediente(expedienteDto);
                } else { // Actualiza atencion si es cita existente
                    CliExpedienteService expedienteService = new CliExpedienteService();
                    CliAtencionService atencionService = new CliAtencionService();

                    CliExpedienteDto expedienteDto = citaDto.getCliPacienteDto().getCliExpedienteList().get(0);
                    respuesta = expedienteService.getExpediente(expedienteDto.getExpId());
                    expedienteDto = (CliExpedienteDto) respuesta.getResultado("Expediente");

                    List<CliAtencionDto> atenciones = expedienteDto.getCliAtencionList();
                    List<CliAtencionDto> atencionesFiltradas = atenciones.stream()
                            .filter(atencion -> atencion.getAteFechahora().isEqual(horaAntigua))
                            .collect(Collectors.toList());
                    Optional<CliAtencionDto> atencionEncontrada = atencionesFiltradas.stream().findFirst();

                    // Verificar si se encontró alguna atención
                    if (atencionEncontrada.isPresent()) {
                        CliAtencionDto atencion = atencionEncontrada.get();
                        atencion.setAteFechahora(citaDto.getCitFechaHora());
                        atencionService.guardarAtencion(atencion);
                    }
                }
                new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    @FXML
    private void onActionBtnAgragarPaciente(ActionEvent event) {
        AppContext.getInstance().set("PadrePacientes", "P11_NuevaCitaView");
        FlowController.getInstance().delete("P09_MantenimientoPacientesView");
        FlowController.getInstance().goViewInWindowModal("P09_MantenimientoPacientesView", stage, false);
    }

    public void bindBuscarPaciente() {
        P09_MantenimientoPacientesViewController pacienteRegistroController = (P09_MantenimientoPacientesViewController) FlowController.getInstance().getController("P09_MantenimientoPacientesView");
        pacienteDto = (CliPacienteDto) pacienteRegistroController.getSeleccionado();
        if (pacienteDto != null) {
            citaDto.setCliPacienteDto(pacienteDto);
            bindCita();
        }
    }

    private void bindCita() {
        txfMotivo.textProperty().bindBidirectional(citaDto.citMotivo);
        lblNombreUsu.textProperty().bind(citaDto.citUsuarioRegistra);
        lblFechaHora.textProperty().bind(citaDto.citFechaHora.asString());
        if (citaDto.getCliPacienteDto() != null) {
            citaDto.cliPacienteDto.getPacNombre();
            lblNombrePac.setText(citaDto.nombrePacienteCompleto());
            lblNumero.textProperty().bind(citaDto.cliPacienteDto.pacTelefono);
            lblCorreo.textProperty().bind(citaDto.cliPacienteDto.pacCorreo);
        }
        if (citaDto.getCitEstado() != null) {
            switch (citaDto.getCitEstado()) {
                case "P" -> {
                    cboxEstadoCita.getSelectionModel().select(0);
                }
                case "A" -> {
                    cboxEstadoCita.getSelectionModel().select(1);
                }
                case "C" -> {
                    cboxEstadoCita.getSelectionModel().select(2);
                }
                case "U" ->
                    cboxEstadoCita.getSelectionModel().select(3);
            }
        }
        if (citaDto.getCliCantespacios() != null) {
            cboxEspacioHora.getSelectionModel().select(citaDto.getCliCantespacios().intValue() - 1);
        }
    }

    public void cargarDefecto(CliCitaDto cita, CliUsuarioDto usuario, CliAgendaDto agenda, CliMedicoDto medico, LocalDateTime fechaHora, CliCitaDto citasVec[], int pos) {
        citaDto = cita;
        //---------------
        if (citaDto.getCitId() != null /*|| citaDto.getCitId() > 0*/) {
            CliCitaService citaService = new CliCitaService();
            Respuesta respuesta = citaService.getCita(citaDto.getCitId());
            this.citaDto = (CliCitaDto) respuesta.getResultado("Cita");
        }
        //----------------------
        usuarioDto = usuario;
        agendaDto = agenda;
        medicoDto = medico;
        fechaHoraCita = fechaHora;
        citasVector = citasVec;
        posVec = pos;
        if (citaDto.getCitUsuarioRegistra() == null && citaDto.getCitFechaHora() == null) {
            citaDto.setCitUsuarioRegistra(usuarioDto.nombreUnApellido());
            citaDto.setCitFechaHora(fechaHora);
        }
        bindCita();
        if (citaDto.getCitId() != null) {
            btnMoverCita.setDisable(false);
            btnMoverCita.setText("Mover cita");
        } else {
            if (AppContext.getInstance().get("CitaMover") == null) {
                btnMoverCita.setDisable(true);
            } else {
                btnMoverCita.setDisable(false);
                btnMoverCita.setText("Pegar cita");
            }

        }
    }

    public void fillCbox() {
        cboxEstadoCita.getItems().clear();
        cboxEspacioHora.getItems().clear();

//        String admin = resourceBundle.getString("key.admin");
//        String doctor = resourceBundle.getString("key.doctor");
//        String receptionist = resourceBundle.getString("key.receptionist");
        ObservableList<String> estados = FXCollections.observableArrayList();
        estados.addAll("Programada", "Atendida", "Cancelada", "Ausente");
        cboxEstadoCita.setItems(estados);

        ObservableList<Integer> numeros = FXCollections.observableArrayList();
        numeros.addAll(1, 2, 3, 4);
        cboxEspacioHora.setItems(numeros);
    }

    public String estadoCita() {
        return switch (cboxEstadoCita.getValue()) {
            case "Ausente" ->
                "U";
            case "Atendida" ->
                "A";
            case "Cancelada" ->
                "C";
            default ->
                "P";
        };
    }

    @FXML
    private void onActionBtnMoverCita(ActionEvent event) {

        if (AppContext.getInstance().get("CitaMover") == null) {
            AppContext.getInstance().set("CitaMover", citaDto);
            AppContext.getInstance().set("AgendaAntigua", agendaDto);

            System.out.println("Cita copeada");
        } else {
            citaDto = (CliCitaDto) AppContext.getInstance().get("CitaMover");
            CliAgendaDto agendaAntigua = (CliAgendaDto) AppContext.getInstance().get("AgendaAntigua");
            
            agendaAntigua.getCliCitaListEliminados().add(citaDto);
            CliAgendaService agendaService = new CliAgendaService();
            agendaService.guardarAgenda(agendaAntigua);
                        
            citaDto.setCliAgendaDto(agendaDto);
            horaAntigua = citaDto.getCitFechaHora();
            citaDto.setCitFechaHora(fechaHoraCita);
            bindCita();
            System.out.println("Cita movida");
        }
    }
}
