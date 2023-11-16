package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P06_MenuPrincipalViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnGenerales;
    @FXML
    private MFXButton btnCerrarSesion;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXButton btnMantenimientoMedicos;
    @FXML
    private MFXButton btnRegistroPacientes;
    @FXML
    private MFXButton btnMantUsuarios;
    @FXML
    private MFXButton btnAgenda;
    @FXML
    private MFXButton btnAtencion;
    @FXML
    private MFXButton btnReporteDinamico;
    @FXML
    private MFXButton btnReportes;

    CliUsuarioDto usuarioDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnMantUsuarios(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        if (usuarioDto.getUsuTipousuario().equals("A")) {
            AppContext.getInstance().set("Padre", "P06_MenuPrincipalView");
            FlowController.getInstance().goView("P03_RegistroView");
        } else {
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "No tiene permisos para ingresar a esta pantalla");
        }
    }

    @FXML
    private void onActionBtnGenerales(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        if (usuarioDto.getUsuTipousuario().equals("A")) {
            FlowController.getInstance().goView("P07_MantenimientoGeneralesView");
        } else {
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "No tiene permisos para ingresar a esta pantalla");
        }
    }

    @FXML
    private void onActionBtnMantenimientoMedicos(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        if (!usuarioDto.getUsuTipousuario().equals("R")) {
            AppContext.getInstance().set("PadreMedicos", "P06_MenuPrincipalView");
            FlowController.getInstance().delete("P08_MantenimientoMedicosView");
            FlowController.getInstance().goView("P08_MantenimientoMedicosView");
        } else {
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "No tiene permisos para ingresar a esta pantalla");
        }
    }

    @FXML
    private void onActionBtnRegistroPacientes(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        AppContext.getInstance().set("PadrePacientes", "P06_MenuPrincipalView");
        FlowController.getInstance().delete("P09_MantenimientoPacientesView");
        FlowController.getInstance().goView("P09_MantenimientoPacientesView");
    }

    @FXML
    private void onActionBtnCerrarSesion(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        //limpiar el hashmap del AppContext
        //AppContext.getInstance().clearHashMap();
        //limpiar el hashmap del FlowController
        FlowController.getInstance().clearHashMap();
        FlowController.getInstance().goViewInWindow("P01_LogInView", false);
        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().salir();
    }

    @FXML
    private void onActionBtnAgenda(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P10_AgendaView");
    }

    @FXML
    private void onActionBtnAtencion(ActionEvent event) {
         SoundUtil.mouseEnterSound();
        if (!usuarioDto.getUsuTipousuario().equals("R")) {
            //AppContext.getInstance().set("PadreMedicos", "P06_MenuPrincipalView");
            //FlowController.getInstance().delete("P08_MantenimientoMedicosView");
            FlowController.getInstance().goView("P12_AtencionCitasView");
        } else {
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.userValidation", getStage(), "No tiene permisos para ingresar a esta pantalla");
        }
    }
    
    @FXML
    private void onActionBtnReportes(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P15_ReportesView");
    }

    @FXML
    private void onActionBtnReporteDinamico(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P16_ReporteDinamicoView");
    }

}
