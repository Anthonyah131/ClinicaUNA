/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author ANTHONY
 */
public class P16_ReporteDinamicoViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfTitulo;
    @FXML
    private JFXTextArea txaDescripcion;
    @FXML
    private JFXTextArea txaConsulta;
    @FXML
    private HBox hbxCorreoView;
    @FXML
    private MFXButton btnIngresarCorreo;
    @FXML
    private HBox hbxCorreos;
    @FXML
    private JFXTextField txfDia;
    @FXML
    private JFXDatePicker dpDiaFinaliza;
    @FXML
    private HBox hbxParametroView;
    @FXML
    private JFXTextField txfParametro;
    @FXML
    private JFXTextField txfValor;
    @FXML
    private MFXButton btnAgregar;
    @FXML
    private TableView<?> tbvParametros;
    @FXML
    private TableColumn<?, ?> tbcParametro;
    @FXML
    private TableColumn<?, ?> tbcValor;
    @FXML
    private TableColumn<?, ?> tbcEliminar;

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
    private void onActionBtnIngresarCorreo(ActionEvent event) {
    }

    @FXML
    private void onActionBtnAgregar(ActionEvent event) {
    }
    
}
