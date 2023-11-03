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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fillCbox();
        nuevaCita();
        usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        citaDto.setCitMotivo(txfMotivo.getText());
        citaDto.setCliPacienteDto(pacienteDto);
        citaDto.setCitUsuarioRegistra(usuarioDto.getUsuNombre() + " " + usuarioDto.getUsuPapellido());
        citaDto.setCliCantespacios(Long.valueOf(cboxEspacioHora.getValue()));
        citaDto.setCitEstado(estadoCita());

        P10_AgendaViewController agendaController = (P10_AgendaViewController) FlowController.getInstance().getController("P10_AgendaView");
        agendaController.cargarCita(citaDto);
        stage.close();
//      
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
    private void onActionBtnAgragarPaciente(ActionEvent event) {
        AppContext.getInstance().set("PadrePacientes", "P11_NuevaCitaView");
        FlowController.getInstance().delete("P09_MantenimientoPacientesView");
        FlowController.getInstance().goViewInWindowModal("P09_MantenimientoPacientesView", stage, false);
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

    private void nuevaCita() {
        // unbindUsuario();
        this.citaDto = new CliCitaDto();
        //bindUsuario();
    }

    public void bindBuscar() {
        P09_MantenimientoPacientesViewController pacienteRegistroController = (P09_MantenimientoPacientesViewController) FlowController.getInstance().getController("P09_MantenimientoPacientesView");
//        unbindUsuario();
        pacienteDto = (CliPacienteDto) pacienteRegistroController.getSeleccionado();
//        bindUsuario();
        cargarLabels();
    }

    private void cargarLabels() {
        lblNombrePac.setText(pacienteDto.getNombreString());
        lblNumero.setText(pacienteDto.getPacTelefono());
        lblNombreUsu.setText(usuarioDto.getUsuNombre() + " " + usuarioDto.getUsuPapellido());
        lblCorreo.setText(pacienteDto.getPacCorreo());
    }
    
    
}
