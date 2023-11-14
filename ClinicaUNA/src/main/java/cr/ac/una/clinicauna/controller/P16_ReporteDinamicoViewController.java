/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliCorreodestinoDto;
import cr.ac.una.clinicauna.model.CliParametroconsultaDto;
import cr.ac.una.clinicauna.model.CliReporteDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliCorreodestinoService;
import cr.ac.una.clinicauna.service.CliParametroconsultaService;
import cr.ac.una.clinicauna.service.CliReporteService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

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
    private JFXDatePicker dpDiaInicia;
    @FXML
    private JFXDatePicker dpDiaSiguiente;
    @FXML
    private MFXButton btnLimpiar;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private MFXButton btnEliminar;
    @FXML
    private TableView<CliReporteDto> tbvReportes;
    @FXML
    private JFXTextField txfNombreBusqueda;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private HBox hbxParametroView;
    @FXML
    private JFXTextField txfParametro;
    @FXML
    private JFXTextField txfValor;
    @FXML
    private MFXButton btnAgregar;
    @FXML
    private TableView<CliParametroconsultaDto> tbvParametros;

    CliReporteDto reporteDto;
    private ObservableList<CliReporteDto> reportes = FXCollections.observableArrayList();
    CliCorreodestinoDto correoDto;
    CliParametroconsultaDto parametroDto;
    List<Node> requeridos = new ArrayList<>();
    List<Node> requeridosParametro = new ArrayList<>();

    ResourceBundle resourceBundle;
    @FXML
    private JFXButton btnSql;
    @FXML
    private JFXButton btnParameter;
    @FXML
    private JFXButton btnSqlValue;
    @FXML
    private MFXButton btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txfTitulo.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txaDescripcion.setTextFormatter(Formato.getInstance().maxLengthFormat(150));
        txaConsulta.setTextFormatter(Formato.getInstance().maxLengthFormat(500));

        txfParametro.setTextFormatter(Formato.getInstance().maxLengthFormat(50));
        txfValor.setTextFormatter(Formato.getInstance().maxLengthFormat(30));

        txfNombreBusqueda.setTextFormatter(Formato.getInstance().letrasFormat(30));

        this.reporteDto = new CliReporteDto();
        this.correoDto = new CliCorreodestinoDto();
        this.parametroDto = new CliParametroconsultaDto();
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
        P03_RegistroBuscadorViewController registroController = (P03_RegistroBuscadorViewController) FlowController.getInstance().getController("P03_RegistroBuscadorView");
        registroController.cargaDesdeVista("P16_ReporteDinamicoView");
        FlowController.getInstance().goViewInWindowModal("P03_RegistroBuscadorView", stage, Boolean.FALSE);
    }

    @FXML
    private void onActionBtnAgregar(ActionEvent event) {
        ObservableList<CliParametroconsultaDto> parametros = tbvParametros.getItems();
        if (tbvParametros.getItems() == null || !parametros.stream().anyMatch(e -> e.getParcParametro().equals(this.parametroDto.getParcParametro()))) {
            try {
                String invalidos = ValidarRequeridos.validarRequeridos(requeridosParametro);
                if (!invalidos.isEmpty()) {
                    String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), mensaje);
                } else {
                    LocalDate fechaActual = LocalDate.now();
                    if (dpDiaInicia.getValue() == null || dpDiaInicia.getValue().isBefore(fechaActual)) {
                        new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), "key.errorDateStart");
                    } else {
                        if (dpDiaSiguiente.getValue() == null || dpDiaSiguiente.getValue().isBefore(dpDiaInicia.getValue())) {
                            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), "key.errorDateNext");
                        } else {
                            CliParametroconsultaService parametroService = new CliParametroconsultaService();
                            Respuesta respuesta = parametroService.guardarParametroconsulta(this.parametroDto);
                            if (!respuesta.getEstado()) {
                                new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), respuesta.getMensaje());
                            } else {
                                unbindParametro();
                                this.parametroDto = (CliParametroconsultaDto) respuesta.getResultado("Parametroconsulta");
                                this.parametroDto.setModificado(true);
                                reporteDto.getCliParametroconsultaList().add(this.parametroDto);
                                onActionBtnGuardar(event);
                                this.parametroDto = new CliParametroconsultaDto();
                                bindParametro();
                                new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveParameterR", getStage(), "key.updatedParameterR");
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveParameterR", getStage(), "key.errorSavingParameterR");
            }
        }
    }

    @FXML // Poner idioma
    private void onActionBtnLimpiar(ActionEvent event) {
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoReporte();
            txfNombreBusqueda.clear();
            tbvReportes.getItems().clear();
            tbvReportes.refresh();
        }
    }

    @FXML // Poner idioma
    private void onActionBtnGuardar(ActionEvent event) {
        /*try {
            String consulta = "SELECT u.usu_nombre, u.usu_papellido, u.usu_cedula FROM CLI_USUARIO u WHERE u.usu_nombre = nombre";
            CliReporteService reporteService = new CliReporteService();
            Respuesta respuesta = reporteService.generarInformeExcelDesdeConsultaSQL(consulta);
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
            } else {
                new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
            }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el reporte.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }*/
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridos);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveReportD", getStage(), mensaje);
            } else {
                CliReporteService reporteService = new CliReporteService();
                Respuesta respuesta = reporteService.guardarReporte(reporteDto);
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveReportD", getStage(), respuesta.getMensaje());
                } else {
                    unbindReporte();
                    this.reporteDto = (CliReporteDto) respuesta.getResultado("Reporte");
                    cargarParametros();
                    cargarCorreos();
                    bindReporte();
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveReportD", getStage(), "key.updatedReportD");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el reporte.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveReportD", getStage(), "key.errorSavingReportD");
        }
        onActionBtnFiltrar(event);
    }

    @FXML // Poner idioma
    private void onActionBtnEliminar(ActionEvent event) {
        try {
            if (this.reporteDto.getRepId() == null) {
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteReportD", getStage(), "key.loadReportDDelete");
            } else {
                CliReporteService service = new CliReporteService();
                Respuesta respuesta = service.eliminarReporte(this.reporteDto.getRepId());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteReportD", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.deleteReportD", getStage(), "key.deleteReportDSuccess");
                    nuevoReporte();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Reporte.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteReportD", getStage(), "key.deleteReportDError");
        }
        onActionBtnFiltrar(event);
    }

    @FXML // Poner idioma
    private void onActionBtnFiltrar(ActionEvent event) {
        CliReporteService service = new CliReporteService();
        Respuesta respuesta = service.getReportes(txfNombreBusqueda.getText());

        if (respuesta.getEstado()) {
            tbvReportes.getItems().clear();
            reportes.clear();
            reportes.addAll((List<CliReporteDto>) respuesta.getResultado("Reportes"));
            tbvReportes.setItems(reportes);
            tbvReportes.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "key.loadReportsD", getStage(), respuesta.getMensaje());
        }
    }

    public void fillTableView() {
        // Parametros
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

        // Reportes
        tbvReportes.getItems().clear();

        TableColumn<CliReporteDto, String> tbcNombre = new TableColumn<>("Parametro");
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().repNombre);

        TableColumn<CliReporteDto, String> tbcDescripcion = new TableColumn<>("Valor");
        tbcDescripcion.setPrefWidth(150);
        tbcDescripcion.setCellValueFactory(cd -> cd.getValue().repDescripcion);

        tbvReportes.getColumns().addAll(tbcNombre, tbcDescripcion);
        tbvReportes.refresh();

        tbvReportes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindReporte();
                this.reporteDto = newValue;
                bindReporte();
                nuevoParametro();
                nuevoCorreo();
                cargarParametros();
                cargarCorreos();
            }
        });
    }

    private void nuevoReporte() {
        unbindReporte();
        this.reporteDto = new CliReporteDto();
        bindReporte();
        nuevoParametro();
        nuevoCorreo();
        cargarParametros();
        cargarCorreos();
    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txaConsulta, txfTitulo));
    }

    private void bindReporte() {
        txfNombre.textProperty().bindBidirectional(this.reporteDto.repNombre);
        txfTitulo.textProperty().bindBidirectional(this.reporteDto.repTitulo);
        txaDescripcion.textProperty().bindBidirectional(this.reporteDto.repDescripcion);
        txaConsulta.textProperty().bindBidirectional(this.reporteDto.repConsulta);
        dpDiaInicia.valueProperty().bindBidirectional(this.reporteDto.repFfin);
//        dpDiaSiguiente.valueProperty().bindBidirectional(this.reporteDto.repFsiguiente);
        if (this.reporteDto.getRepId() == null || this.reporteDto.getRepId() <= 0) {
            hbxCorreoView.setDisable(true);
            hbxParametroView.setDisable(true);
        } else {
            hbxCorreoView.setDisable(false);
            hbxParametroView.setDisable(false);
        }
    }

    private void unbindReporte() {
        txfNombre.textProperty().unbindBidirectional(this.reporteDto.repNombre);
        txfTitulo.textProperty().unbindBidirectional(this.reporteDto.repTitulo);
        txaDescripcion.textProperty().unbindBidirectional(this.reporteDto.repDescripcion);
        txaConsulta.textProperty().unbindBidirectional(this.reporteDto.repConsulta);
        dpDiaInicia.valueProperty().unbindBidirectional(this.reporteDto.repFfin);
//        dpDiaSiguiente.valueProperty().unbindBidirectional(this.reporteDto.repFsiguiente);
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

    private void cargarParametros() {
        tbvParametros.getItems().clear();
        tbvParametros.setItems(this.reporteDto.getCliParametroconsultaList());
        tbvParametros.refresh();
    }

    private void bindParametro() {
        txfParametro.textProperty().bindBidirectional(this.parametroDto.parcParametro);
        txfValor.textProperty().bindBidirectional(this.parametroDto.parcValor);
    }

    private void unbindParametro() {
        txfParametro.textProperty().unbindBidirectional(this.parametroDto.parcParametro);
        txfValor.textProperty().unbindBidirectional(this.parametroDto.parcValor);
    }

    private void nuevoCorreo() {
        this.correoDto = new CliCorreodestinoDto();
    }

    private void cargarCorreos() {
        hbxCorreos.getChildren().clear();
        for (CliCorreodestinoDto correo : reporteDto.getCliCorreodestinoList()) {
            String correoLb = correo.getCdCorreo();

            Label lbCorreo = new Label(correoLb.length() > 15 ? correoLb.substring(0, 15) + "..." : correoLb);

            MFXButton btnEliminar = new MFXButton();
            btnEliminar.setText("X");

            HBox hbox = new HBox();
            hbox.setMaxWidth(Region.USE_COMPUTED_SIZE);
            hbox.getChildren().addAll(lbCorreo, btnEliminar);

            btnEliminar.setOnAction(e -> {
                Optional<CliCorreodestinoDto> correoEncontrado = reporteDto.getCliCorreodestinoList()
                        .stream().filter(correos -> correos.getCdCorreo().equals(correoLb)).findFirst();

                CliCorreodestinoDto instanciaCorreo = correoEncontrado.get();
                reporteDto.getCliCorreodestinoListEliminados().add(instanciaCorreo);
                hbxCorreos.getChildren().remove(hbox);

            });

            hbxCorreos.getChildren().add(hbox);
        }
    }

    // Poner idioma
    public void bindBuscar() {
        P03_RegistroBuscadorViewController buscadorRegistroController = (P03_RegistroBuscadorViewController) FlowController.getInstance().getController("P03_RegistroBuscadorView");
        CliUsuarioDto usuarioDto = (CliUsuarioDto) buscadorRegistroController.getSeleccionado();
        if (reporteDto.getCliCorreodestinoList() == null || !reporteDto.getCliCorreodestinoList().stream().anyMatch(e -> e.getCdCorreo().equals(usuarioDto.getUsuCorreo()))) {
            correoDto.setCdCorreo(usuarioDto.getUsuCorreo());
            try {
                String invalidos = ValidarRequeridos.validarRequeridos(requeridosParametro);
                if (!invalidos.isEmpty()) {
                    String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveEmailR", getStage(), mensaje);
                } else {
                    CliCorreodestinoService correoService = new CliCorreodestinoService();
                    Respuesta respuesta = correoService.guardarCorreodestino(this.correoDto);
                    if (!respuesta.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveEmailR", getStage(), respuesta.getMensaje());
                    } else {
                        this.correoDto = (CliCorreodestinoDto) respuesta.getResultado("Correodestino");
                        this.correoDto.setModificado(true);
                        reporteDto.getCliCorreodestinoList().add(this.correoDto);
                        ActionEvent event = new ActionEvent();
                        onActionBtnGuardar(event);
                        this.correoDto = new CliCorreodestinoDto();
                        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveEmailR", getStage(), "key.updatedEmailR");
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(P16_ReporteDinamicoViewController.class.getName()).log(Level.SEVERE, "Error guardando el parametro.", ex);
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveEmailR", getStage(), "key.errorSavingEmailR");
            }
        }

    }
    
    @FXML
    private void onActionBtnSql(ActionEvent event) {
        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.consultSql", getStage(), "key.lbQueryR");
    }

    @FXML
    private void onActionBtnParameter(ActionEvent event) {
        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.parameterR", getStage(), "key.lbParameterR");
    }

    @FXML
    private void onActionBtnValue(ActionEvent event) {
        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.valorR", getStage(), "key.lbValoryR");
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private class ButtonCell extends TableCell<CliParametroconsultaDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.setText("X");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                CliParametroconsultaDto par = (CliParametroconsultaDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                reporteDto.getCliParametroconsultaListEliminados().add(par);
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
