package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    CliPacienteDto pacienteDto;
    CliCitaDto citaDto;
    CliUsuarioDto usuarioDto;
    @FXML
    private Label lblFechaHora;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCbox();
//        nuevaCita();
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        citaDto.setCitMotivo(txfMotivo.getText());
        citaDto.setCliCantespacios(Long.valueOf(cboxEspacioHora.getValue()));
        citaDto.setCitEstado(estadoCita());

        P10_AgendaViewController agendaController = (P10_AgendaViewController) FlowController.getInstance().getController("P10_AgendaView");
        agendaController.cargarCita(citaDto);
        stage.close();
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
            citaDto.cliPacienteDto.getNombreCompleto();
            lblNombrePac.textProperty().bind(citaDto.cliPacienteDto.pacNombreCompleto);
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

    public void cargarDefecto(CliCitaDto cita, CliUsuarioDto usuario, LocalDateTime fechaHora) {
        usuarioDto = usuario;

        citaDto = cita;
        if (citaDto.getCitUsuarioRegistra() == null && citaDto.getCitFechaHora() == null) {
            citaDto.setCitUsuarioRegistra(usuarioDto.getNombreApellido());
            citaDto.setCitFechaHora(fechaHora);
        }
        bindCita();
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
}
