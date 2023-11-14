/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

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
    private MFXButton btnLimpiarU;
    @FXML
    private MFXButton btnAceptarU;
    @FXML
    private ComboBox<?> cbxTipoUsuario;
    @FXML
    private MFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnBuscarMedico(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnLimpiarM(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnAceptarM(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscarPaciente(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnLimpiaP(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnAceptarP(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnLimpiaU(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnAceptarU(ActionEvent event) {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }
    
}
