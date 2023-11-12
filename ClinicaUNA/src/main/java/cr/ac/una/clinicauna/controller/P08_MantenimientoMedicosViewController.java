package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliMedicoService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.SoundUtil;
import cr.ac.una.clinicauna.util.Utilidades;
import cr.ac.una.clinicauna.util.ValidarRequeridos;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P08_MantenimientoMedicosViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnSalir;
    @FXML
    private JFXTextField txfBuscarCodigo;
    @FXML
    private JFXTextField txfBuscarFolio;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXTextField txfBuscarPapellido;
    @FXML
    private JFXCheckBox chkBuscarActivas;
    @FXML
    private JFXCheckBox chkBuscarTodos;
    @FXML
    private MFXButton btnLimpiarBusquedaMedico;
    @FXML
    private JFXTextField txfCodigoMedico;
    @FXML
    private JFXTextField txfFolioMedico;
    @FXML
    private JFXTextField txfLicencia;
    @FXML
    private JFXTimePicker tpkHoraInicio;
    @FXML
    private JFXTimePicker tpkHoraSalida;
    @FXML
    private JFXComboBox<Integer> cboxCantidadCitas;
    @FXML
    private JFXCheckBox chkActivo;
    @FXML
    private MFXButton btnLimpiarCampos;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<CliMedicoDto> tbvResultados;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private MFXButton btnAddToAgenda;
    @FXML
    private Label lblJornadaT;
    @FXML
    private Label lblCantidadCitas;

    CliMedicoDto medicoDto;
    CliUsuarioDto usuario;
    private ObservableList<CliMedicoDto> medicos = FXCollections.observableArrayList();
    List<Node> requeridos = new ArrayList<>();
    ResourceBundle resourceBundle;
    Object resultado;

    /**
     * Initializes the controller class.
     *
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resourceBundle = FlowController.getInstance().getIdioma();
        txfBuscarCodigo.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        txfBuscarFolio.setTextFormatter(Formato.getInstance().maxLengthFormat(10));
        txfBuscarNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfBuscarPapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));

        txfCodigoMedico.setTextFormatter(Formato.getInstance().maxLengthFormat(15));
        txfFolioMedico.setTextFormatter(Formato.getInstance().maxLengthFormat(10));
        txfLicencia.setTextFormatter(Formato.getInstance().maxLengthFormat(15));

        medicoDto = new CliMedicoDto();
        fillTableView();
        iniciarEscena();
        indicarRequeridos();
        fillCbox();

        nuevoMedico();
        cargarListeners();
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        cargarMedicos();
    }

    @FXML
    private void onActionBtnLimpiarBusquedaMedico(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        cleanNodesSearch();
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        if (new Mensaje().showConfirmationi18n("key.clear", getStage(), "key.cleanRegistry")) {
            nuevoMedico();
            cleanNodes();
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        try {
            String invalidos = ValidarRequeridos.validarRequeridos(requeridos);
            if (!invalidos.isEmpty()) {
                String mensaje = resourceBundle.getString("key.invalidFields") + invalidos;
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), mensaje);
            } else {
                if (medicoDto.getMedId() != null) {
                    CliMedicoService medicoService = new CliMedicoService();
                    if (cboxCantidadCitas.getValue() != null) {
                        medicoDto.setMedEspaciosxhora((long) cboxCantidadCitas.getValue());
                    }
                    if (chkActivo.isSelected()) {
                        medicoDto.setMedEstado("A");
                    } else {
                        medicoDto.setMedEstado("I");
                    }
                    if (tpkHoraInicio.getValue() != null) {
                        medicoDto.setMedFiniTime(tpkHoraInicio.getValue());
                    }
                    if (tpkHoraSalida.getValue() != null) {
                        medicoDto.setMedFfinTime(tpkHoraSalida.getValue());
                    }

                    Respuesta respuesta = medicoService.guardarMedico(medicoDto);
                    if (!respuesta.getEstado()) {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
                    } else {
                        unbindMedico();
                        this.medicoDto = (CliMedicoDto) respuesta.getResultado("Medico");
                        bindMedico();
                        new Mensaje().showModali18n(Alert.AlertType.INFORMATION, "key.saveUser", getStage(), "key.updatedUser");
                    }
                } else {
                    System.out.println("Error guardando");
                    // Se pone un mensaje que se debe cargar un medico para actualizarlo
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(P08_MantenimientoMedicosViewController.class.getName()).log(Level.SEVERE, "Error guardando el medico.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    @FXML
    private void onActionBuscarTodos(ActionEvent event) {
        if (chkBuscarTodos.isSelected()) {
            chkBuscarActivas.setDisable(true);
        } else {
            chkBuscarActivas.setDisable(false);
        }
    }

    @FXML
    private void onActionBtnAddToAgenda(ActionEvent event) {
        cargarMedicoAgenda();
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private void nuevoMedico() {
        unbindMedico();
        this.medicoDto = new CliMedicoDto();
        bindMedico();
    }
    int[] horas;

    private void calcJornada() {
        if (tpkHoraInicio.getValue() != null && tpkHoraSalida.getValue() != null) {
            horas = Utilidades.calcularJornada(tpkHoraInicio.getValue(), tpkHoraSalida.getValue());
            lblJornadaT.setText(horas[0] + "h " + horas[1] + "m");
        }
    }

    private void calcCitasDia() {
        if (tpkHoraInicio.getValue() != null && tpkHoraSalida.getValue() != null && cboxCantidadCitas.getValue() != null) {
            int cantCitas = Utilidades.calcularCitasJornada(horas[0], horas[1], cboxCantidadCitas.getValue());
            lblCantidadCitas.setText(cantCitas + ".");
        }
    }

    private void seleccionarTipoPorCantidad(Integer cantXjora) {
        for (Integer tipo : cboxCantidadCitas.getItems()) {
            if (Objects.equals(tipo, cantXjora)) {
                cboxCantidadCitas.getSelectionModel().select(tipo);
            }
        }
    }

    private void bindMedico() {
        txfCodigoMedico.textProperty().bindBidirectional(medicoDto.medCodigo);
        txfFolioMedico.textProperty().bindBidirectional(medicoDto.medFolio);
        txfLicencia.textProperty().bindBidirectional(medicoDto.medCarne);
        if (medicoDto.getMedFini() != null) {
            tpkHoraInicio.setValue(medicoDto.getMedFiniTime());
        }
        if (medicoDto.getMedFini() != null) {
            tpkHoraSalida.setValue(medicoDto.getMedFfinTime());
        }
        if ("A".equals(medicoDto.getMedEstado())) {
            chkActivo.setSelected(true);
        } else {
            chkActivo.setSelected(false);
        }
        if (medicoDto.getMedEspaciosxhora() != null) {
            seleccionarTipoPorCantidad(Math.toIntExact(medicoDto.getMedEspaciosxhora()));
        } else {
            cboxCantidadCitas.getSelectionModel().clearSelection();
        }
    }

    private void unbindMedico() {
        txfCodigoMedico.textProperty().unbindBidirectional(medicoDto.medCodigo);
        txfFolioMedico.textProperty().unbindBidirectional(medicoDto.medFolio);
        txfLicencia.textProperty().unbindBidirectional(medicoDto.medCarne);
        tpkHoraInicio.setValue(null);
        tpkHoraSalida.setValue(null);
    }

    public void cargarMedicos() {
        CliMedicoService service = new CliMedicoService();
        Respuesta respuesta = service.getMedicos(txfBuscarCodigo.getText(), txfBuscarFolio.getText(), txfBuscarNombre.getText(), txfBuscarPapellido.getText(), chkBuscarActivas.isSelected(), chkBuscarTodos.isSelected());

        if (respuesta.getEstado()) {
            tbvResultados.getItems().clear();
            medicos.clear();
            medicos.addAll((List<CliMedicoDto>) respuesta.getResultado("Medicos"));
            tbvResultados.setItems(medicos);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Medicos", getStage(), respuesta.getMensaje());
        }
    }

    public void cargarMedico() {
        CliMedicoService service = new CliMedicoService();

        Respuesta respuesta = service.getMedicos(usuario.getUsuId());

        if (respuesta.getEstado()) {
            tbvResultados.getItems().clear();
            medicos.clear();

            medicos.addAll((List<CliMedicoDto>) respuesta.getResultado("Medicos"));

            tbvResultados.setItems(medicos);
            tbvResultados.refresh();

        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Medicos", getStage(), respuesta.getMensaje());
        }
    }

    public void cleanNodes() {
        txfCodigoMedico.clear();
        txfFolioMedico.clear();
        txfLicencia.clear();
        tpkHoraInicio.setValue(null);
        tpkHoraSalida.setValue(null);
        fillCbox();
        chkActivo.setSelected(false);
    }

    public void cleanNodesSearch() {
        tbvResultados.getItems().clear();
        txfBuscarCodigo.clear();
        txfBuscarFolio.clear();
        txfBuscarNombre.clear();
        txfBuscarPapellido.clear();
        chkBuscarActivas.setSelected(false);
        chkBuscarTodos.setSelected(false);
        medicos.clear();
    }

    private void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txfCodigoMedico, txfFolioMedico, txfLicencia, tpkHoraInicio, tpkHoraSalida, cboxCantidadCitas));
    }

    public void fillCbox() {
        cboxCantidadCitas.getItems().clear();

        ObservableList<Integer> citasHora = FXCollections.observableArrayList();
        citasHora.addAll(1, 2, 3, 4);
        cboxCantidadCitas.setItems(citasHora);
    }

    public void fillTableView() {
        resourceBundle = FlowController.getInstance().getIdioma();

        tbvResultados.getItems().clear();

        TableColumn<CliMedicoDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(50);
        tbcId.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuId);

        TableColumn<CliMedicoDto, String> tbcCedula = new TableColumn<>(resourceBundle.getString("key.identification"));
        tbcCedula.setPrefWidth(100);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuCedula);

        TableColumn<CliMedicoDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.name"));
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuNombre);

        TableColumn<CliMedicoDto, String> tbcApellido = new TableColumn<>(resourceBundle.getString("key.papellido"));
        tbcApellido.setPrefWidth(150);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().getCliUsuarioDto().usuPapellido);

        TableColumn<CliMedicoDto, String> tbcCodigo = new TableColumn<>(resourceBundle.getString("key.code"));
        tbcCodigo.setPrefWidth(150);
        tbcCodigo.setCellValueFactory(cd -> cd.getValue().medCodigo);

        TableColumn<CliMedicoDto, String> tbcFolio = new TableColumn<>(resourceBundle.getString("key.folio"));
        tbcFolio.setPrefWidth(150);
        tbcFolio.setCellValueFactory(cd -> cd.getValue().medFolio);

        tbvResultados.getColumns().addAll(tbcId, tbcCodigo, tbcFolio, tbcCedula, tbcNombre, tbcApellido);

        tbvResultados.refresh();
    }

    private void cargarListeners() {

        //Listener para el datepicker
        tpkHoraInicio.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                calcJornada();
                calcCitasDia();
            }
        });
        //Listener para el datepicker
        tpkHoraSalida.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                calcJornada();
                calcCitasDia();
            }
        });
        cboxCantidadCitas.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                calcJornada();
                calcCitasDia();
            }
        });
        //Listener para la tabla
        tbvResultados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                unbindMedico();
                medicoDto = newValue;
                bindMedico();
            } else {
                nuevoMedico();
            }
        });
    }

    private void cargarMedicoAgenda() {
        resultado = tbvResultados.getSelectionModel().getSelectedItem();

        if (resultado != null) {
            medicoDto = (CliMedicoDto) resultado;
            if (medicoDto.getMedEstado().equals("A")) {
                P10_AgendaViewController agendaController = (P10_AgendaViewController) FlowController.getInstance().getController("P10_AgendaView");
                agendaController.bindBuscar();
            } else {
                new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.deleteUser", getStage(), "Medico inactivo");
                return;
            }
        }
        getStage().close();
    }

    public Object getSeleccionado() {
        return resultado;
    }

    private void iniciarEscena() {
        String padre = (String) AppContext.getInstance().get("PadreMedicos");
        usuario = (CliUsuarioDto) AppContext.getInstance().get("Usuario");

        if (padre.equals("P06_MenuPrincipalView")) {
            btnAddToAgenda.setVisible(false);
            if (usuario.getUsuTipousuario().equals("M")) {
                unbindMedico();
                cargarMedico();
                bindMedico();
                txfBuscarCodigo.setDisable(true);
                txfBuscarFolio.setDisable(true);
                txfBuscarNombre.setDisable(true);
                txfBuscarPapellido.setDisable(true);
                chkBuscarActivas.setDisable(true);
                chkBuscarTodos.setDisable(true);
                btnFiltrar.setDisable(true);
                btnLimpiarBusquedaMedico.setDisable(true);
            }
        } else if (padre.equals("P10_AgendaView")) {
            btnAddToAgenda.setVisible(true);
            btnSalir.setVisible(false);

            txfCodigoMedico.setEditable(false);
            txfFolioMedico.setEditable(false);
            txfLicencia.setEditable(false);
            tpkHoraInicio.setEditable(false);
            tpkHoraSalida.setEditable(false);
            cboxCantidadCitas.setDisable(true);
            chkActivo.setDisable(true);

            btnLimpiarCampos.setVisible(false);
            btnGuardar.setVisible(false);
        }
    }
}
