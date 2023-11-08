/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliCorreodestinoDto;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.model.CliParametroconsultaDto;
import cr.ac.una.clinicauna.model.CliReporteDto;
import cr.ac.una.clinicauna.model.CliParametroconsultaDto;
import cr.ac.una.clinicauna.model.CliReporteDto;
import cr.ac.una.clinicauna.model.CliParametroconsultaDto;
import cr.ac.una.clinicauna.model.CliReporteDto;
import cr.ac.una.clinicauna.service.CliParametroconsultaService;
import cr.ac.una.clinicauna.service.CliReporteService;
import cr.ac.una.clinicauna.util.BindingUtils;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
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
    private MFXButton btnLimpiar;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private HBox hbxParametroView;
    @FXML
    private JFXTextField txfParametro;
    @FXML
    private JFXTextField txfValor;
    @FXML
    private MFXButton btnAgregar;
    @FXML
    private TableView tbvParametros;

    CliReporteDto reporteDto;
    CliCorreodestinoDto correoDto;
    CliParametroconsultaDto parametroDto;
    List<Node> requeridos = new ArrayList<>();
    List<Node> requeridosParametro = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txfTitulo.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txaDescripcion.setTextFormatter(Formato.getInstance().maxLengthFormat(150));
        txaConsulta.setTextFormatter(Formato.getInstance().maxLengthFormat(500));
        txfDia.setTextFormatter(Formato.getInstance().letrasFormat(30));

        txfParametro.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txfValor.setTextFormatter(Formato.getInstance().maxLengthFormat(30));

        reporteDto = new CliReporteDto();
        correoDto = new CliCorreodestinoDto();
        parametroDto = new CliParametroconsultaDto();
        fillTableView();
        nuevoReporte();
        indicarRequeridos();
        indicarRequeridosParametro();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnIngresarCorreo(ActionEvent event) {
    }

    @FXML // Poner idioma
    private void onActionBtnAgregar(ActionEvent event) {
        /*ObservableList<CliParametroconsultaDto> parametros = tbvParametros.getItems();
        if (tbvParametros.getItems() == null || !parametros.stream().anyMatch(e -> e.getParcParametro().equals(this.parametroDto.getParcParametro()))) {
            try {
                String invalidos = ValidarRequeridos.validarRequeridos(requeridosParametro);
                if (!invalidos.isEmpty()) {
                    String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
                } else {
                    CliParametroconsultaService parametroService = new CliParametroconsultaService();
                    Respuesta respuesta = parametroService.guardarParametroconsulta(parametroDto);
                    if (!respuesta.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                    } else {
                        unbindParametro();
                        this.parametroDto = (CliParametroconsultaDto) respuesta.getResultado("Parametroconsulta");
                        bindParametro();
                        this.parametroDto.setModificado(true);
                        tbvParametros.getItems().add(parametroDto);
                        tbvParametros.refresh();
                        nuevoParametro();
                        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
            }
        }*/
    }

    @FXML // Poner idioma
    private void onActionBtnLimpiar(ActionEvent event) {
        /*if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoReporte();
            cleanNodes();
        }*/
    }

    @FXML // Poner idioma
    private void onActionBtnGuardar(ActionEvent event) {
        /*try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridos);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
            } else {
                CliReporteService reporteService = new CliReporteService();
                Respuesta respuesta = reporteService.guardarReporte(reporteDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                } else {
                    unbindReporte();
                    this.reporteDto = (CliReporteDto) respuesta.getResultado("Reporte");
                    bindReporte();
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el reporte.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }*/
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
    }

    public void cleanNodes() {
        tbvParametros.getItems().clear();
        tbvParametros.refresh();
        hbxCorreos.getChildren().clear();
    }

    public void fillTableView() {

        tbvParametros.getItems().clear();

        TableColumn<CliParametroconsultaDto, String> tbcParametro = new TableColumn<>("Parametro");
        tbcParametro.setPrefWidth(150);
        tbcParametro.setCellValueFactory(cd -> cd.getValue().parcParametro);

        TableColumn<CliParametroconsultaDto, String> tbcValor = new TableColumn<>("Valor");
        tbcValor.setPrefWidth(100);
        tbcValor.setCellValueFactory(cd -> cd.getValue().parcValor);

        TableColumn<CliParametroconsultaDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(75);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<CliParametroconsultaDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<CliParametroconsultaDto, Boolean> p) -> new ButtonCell());

        tbvParametros.getColumns().addAll(tbcParametro, tbcValor, tbcEliminar);
        tbvParametros.refresh();
    }

    private void nuevoReporte() {
        unbindReporte();
        this.reporteDto = new CliReporteDto();
        bindReporte();
        nuevoParametro();
    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txaConsulta, txfTitulo, txfDia));
    }

    private void bindReporte() {
        txfNombre.textProperty().bindBidirectional(reporteDto.repNombre);
        txfTitulo.textProperty().bindBidirectional(reporteDto.repTitulo);
        txaDescripcion.textProperty().bindBidirectional(reporteDto.repDescripcion);
        txaConsulta.textProperty().bindBidirectional(reporteDto.repConsulta);
        txfDia.textProperty().bindBidirectional(reporteDto.repPeriodicidad);
        if (reporteDto.getRepId() != null || reporteDto.getRepId() > 0) {
            hbxCorreoView.setDisable(false);
            hbxParametroView.setDisable(false);
        } else {
            hbxCorreoView.setDisable(true);
            hbxParametroView.setDisable(true);
        }
    }

    private void unbindReporte() {
        txfNombre.textProperty().unbindBidirectional(reporteDto.repNombre);
        txfTitulo.textProperty().unbindBidirectional(reporteDto.repTitulo);
        txaDescripcion.textProperty().unbindBidirectional(reporteDto.repDescripcion);
        txaConsulta.textProperty().unbindBidirectional(reporteDto.repConsulta);
        txfDia.textProperty().unbindBidirectional(reporteDto.repPeriodicidad);
    }

    private void nuevoParametro() {
        unbindParametro();
        this.parametroDto = new CliParametroconsultaDto();
        bindParametro();
    }
    
    private void indicarRequeridosParametro() {
        requeridosParametro.clear();
        requeridosParametro.addAll(Arrays.asList(txfParametro, txfValor));
    }

    private void bindParametro() {
        txfParametro.textProperty().bindBidirectional(parametroDto.parcParametro);
        txfValor.textProperty().bindBidirectional(parametroDto.parcValor);
    }

    private void unbindParametro() {
        txfParametro.textProperty().unbindBidirectional(parametroDto.parcParametro);
        txfValor.textProperty().unbindBidirectional(parametroDto.parcValor);
    }

    private class ButtonCell extends TableCell<CliParametroconsultaDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.setText("X");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                CliParametroconsultaDto par = (CliParametroconsultaDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                tbvParametros.getItems().remove(par);
                tbvParametros.refresh();
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
