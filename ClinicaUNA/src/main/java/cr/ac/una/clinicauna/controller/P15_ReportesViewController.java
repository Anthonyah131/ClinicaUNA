package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.service.ReportesService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.Utilidades;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class P15_ReportesViewController extends Controller implements Initializable {

    @FXML
    private Label lbMedico;
    @FXML
    private MFXButton btnBuscarMedico;
    @FXML
    private JFXDatePicker fdesde;
    @FXML
    private JFXDatePicker fhasta;
    @FXML
    private MFXButton btnLimpiarM;
    @FXML
    private MFXButton btnAceptarM;
    @FXML
    private Label lbPaciente;
    @FXML
    private MFXButton btnBuscarPaciente;
    @FXML
    private MFXButton btnLimpiarP;
    @FXML
    private MFXButton btnAceptarP;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private JFXDatePicker dpDesdeMR;
    @FXML
    private JFXDatePicker dpHastaMR;
    @FXML
    private MFXButton btnLimpiarMR;
    @FXML
    private MFXButton btnAceptarMR;

    CliMedicoDto medicoDto;
    CliPacienteDto pacienteDto;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilidades.ajustarAnchorVentana(root);
        lbMedico.setText("......");
        fdesde.setValue(null);
        fhasta.setValue(null);
        medicoDto = new CliMedicoDto();

        lbPaciente.setText("......");
        pacienteDto = new CliPacienteDto();

        dpDesdeMR.setValue(null);
        dpHastaMR.setValue(null);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnBuscarMedico(ActionEvent event) {
        AppContext.getInstance().set("PadreMedicos", "P15_ReportesView");
        FlowController.getInstance().delete("P08_MantenimientoMedicosView");
        FlowController.getInstance().goViewInWindowModal("P08_MantenimientoMedicosView", stage, false);
    }

    @FXML
    private void OnActionbtnLimpiarM(ActionEvent event) {
        lbMedico.setText("......");
        fdesde.setValue(null);
        fhasta.setValue(null);
        medicoDto = new CliMedicoDto();
    }

    @FXML
    private void OnActionbtnAceptarM(ActionEvent event) {
        if (fdesde.getValue() != null && fhasta.getValue() != null) {
            if (medicoDto.getMedId() != null && medicoDto.getMedId() > 0
                    && fdesde.getValue().compareTo(fhasta.getValue()) <= 0) {
                String desde = fdesde.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
                String hasta = fhasta.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
                Long id = medicoDto.getMedId();
                ReportesService reporteService = new ReportesService();
                Respuesta res = reporteService.getAngendaReport(id, desde, hasta);
                if (res.getEstado()) {
                    JasperPrint jp = (JasperPrint) res.getResultado("Reporte");
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setVisible(true);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "No se encontro el id de medico.");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "Error de formato en las fechas.");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "Selecciona un rango de fechas.");
        }
    }

    @FXML
    private void onActionBtnBuscarPaciente(ActionEvent event) {
        AppContext.getInstance().set("PadrePacientes", "P15_ReportesView");
        FlowController.getInstance().delete("P09_MantenimientoPacientesView");
        FlowController.getInstance().goViewInWindowModal("P09_MantenimientoPacientesView", stage, false);
    }

    @FXML
    private void OnActionbtnLimpiaP(ActionEvent event) {
        lbPaciente.setText("......");
        pacienteDto = new CliPacienteDto();
    }

    @FXML
    private void OnActionbtnAceptarP(ActionEvent event) {
        if (pacienteDto.getPacId() != null && pacienteDto.getPacId() > 0) {
            Long id = pacienteDto.getPacId();
            ReportesService reporteService = new ReportesService();
            Respuesta res = reporteService.getExpedienteReport(id);
            if (res.getEstado()) {
                JasperPrint jp = (JasperPrint) res.getResultado("Reporte");
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "No se encontro el id de paciente.");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "Digite un paciente valido.");
        }
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void OnActionbtnLimpiaMR(ActionEvent event) {
        dpDesdeMR.setValue(null);
        dpHastaMR.setValue(null);
    }

    @FXML
    private void OnActionbtnAceptarMR(ActionEvent event) {
        if (dpDesdeMR.getValue() != null && dpHastaMR.getValue() != null) {
            if (dpDesdeMR.getValue().compareTo(dpHastaMR.getValue()) <= 0) {
                String desde = dpDesdeMR.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
                String hasta = dpHastaMR.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE);
                ReportesService reporteService = new ReportesService();
                Respuesta res = reporteService.getRendimientoMedicos(desde, hasta);
                if (res.getEstado()) {
                    JasperPrint jp = (JasperPrint) res.getResultado("Reporte");
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setVisible(true);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "Error con el reporte.");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "Error de formato en las fechas.");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Alerta Reporte", this.getStage(), "Selecciona un rango de fechas.");
        }
    }

    public void bindBuscar() {
        P08_MantenimientoMedicosViewController buscadorRegistroController = (P08_MantenimientoMedicosViewController) FlowController.getInstance().getController("P08_MantenimientoMedicosView");
        medicoDto = (CliMedicoDto) buscadorRegistroController.getSeleccionado();
        if (medicoDto != null) {
            lbMedico.setText(medicoDto.getCliUsuarioDto().nombreDosApellidos());
        }
    }

    public void bindBuscarPaciente() {
        P09_MantenimientoPacientesViewController pacienteRegistroController = (P09_MantenimientoPacientesViewController) FlowController.getInstance().getController("P09_MantenimientoPacientesView");
        pacienteDto = (CliPacienteDto) pacienteRegistroController.getSeleccionado();
        if (pacienteDto != null) {
            lbPaciente.setText(pacienteDto.getPacNombre());
        }
    }
}
