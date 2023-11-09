package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P03_RegistroBuscadorViewController extends Controller implements Initializable {

    @FXML
    private JFXTextField txfCedula;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfApellido;
    @FXML
    private JFXComboBox<String> cboxTipoUsuario;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private TableView<CliUsuarioDto> tbvResultados;
    @FXML
    private MFXButton onActionBtnAceptar;
    @FXML
    private MFXButton btnLimpiarCampos;

    Object resultado;
    private ObservableList<CliUsuarioDto> usuarios = FXCollections.observableArrayList();
    String vista;
    ResourceBundle resourceBundle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txfNombre.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txfCedula.setTextFormatter(Formato.getInstance().cedulaFormat(40));
        txfApellido.setTextFormatter(Formato.getInstance().letrasFormat(15));
        cleanNodes();
        fillTableView();
    }

    @Override
    public void initialize() {
        cleanNodes();
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        CliUsuarioService service = new CliUsuarioService();
        Respuesta respuesta = service.getUsuarios(txfCedula.getText(), txfNombre.getText(), txfApellido.getText(), cboxTipoUsuario.getValue());

        if (respuesta.getEstado()) {
            tbvResultados.getItems().clear();
            usuarios.clear();
            usuarios.addAll((List<CliUsuarioDto>) respuesta.getResultado("Usuarios"));
            tbvResultados.setItems(usuarios);
            tbvResultados.refresh();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "key.loadUsers", getStage(), respuesta.getMensaje());
        }
    }

    @FXML
    private void onMousePressenTbvResultados(MouseEvent event) {
        if (event.getClickCount() == 2) {
            SoundUtil.mouseEnterSound();
            if ("P03_RegistroView".equals(vista)) {
                cargarUsuario();
            } else if ("P16_ReporteDinamicoView".equals(vista)) {
                cargarUsuarioCorreo();
            }
        }
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        if ("P03_RegistroView".equals(vista)) {
            cargarUsuario();
        } else if ("P16_ReporteDinamicoView".equals(vista)) {
            cargarUsuarioCorreo();
        }
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        cleanNodes();
    }

    public void cargaDesdeVista(String vista) {
        this.vista = vista;
    }

    private void cargarUsuario() {
        resultado = tbvResultados.getSelectionModel().getSelectedItem();
        if (resultado != null) {
            P03_RegistroViewController registroController = (P03_RegistroViewController) FlowController.getInstance().getController("P03_RegistroView");
            registroController.bindBuscar();
        }
        getStage().close();
    }

    private void cargarUsuarioCorreo() {
        resultado = tbvResultados.getSelectionModel().getSelectedItem();
        if (resultado != null) {
            P16_ReporteDinamicoViewController reporteController = (P16_ReporteDinamicoViewController) FlowController.getInstance().getController("P16_ReporteDinamicoView");
            reporteController.bindBuscar();
        }
        getStage().close();
    }

    public void fillTableView() {
        tbvResultados.getItems().clear();

//        TableColumn<CliUsuarioDto, String> tbcId = new TableColumn<>("Id");
//        tbcId.setPrefWidth(30);
//        tbcId.setCellValueFactory(cd -> cd.getValue().usuId);
        TableColumn<CliUsuarioDto, String> tbcCedula = new TableColumn<>(resourceBundle.getString("key.identification"));
        tbcCedula.setPrefWidth(100);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().usuCedula);

        TableColumn<CliUsuarioDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.name"));
        tbcNombre.setPrefWidth(120);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().usuNombre);

        TableColumn<CliUsuarioDto, String> tbcApellido = new TableColumn<>(resourceBundle.getString("key.papellido"));
        tbcApellido.setPrefWidth(130);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().usuPapellido);

        TableColumn<CliUsuarioDto, String> tbcTipoUser = new TableColumn<>(resourceBundle.getString("key.usertype"));
        tbcTipoUser.setPrefWidth(130);
        tbcTipoUser.setCellValueFactory(cd -> {
            String tipoUsuario = cd.getValue().usuTipousuario.get();
            String tipoUsuarioTexto = "";

            switch (tipoUsuario) {
                case "M" ->
                    tipoUsuarioTexto = "Médico";
                case "A" ->
                    tipoUsuarioTexto = "Administrador";
                case "R" ->
                    tipoUsuarioTexto = "Recepcionista";
            }

            return new SimpleStringProperty(tipoUsuarioTexto);
        });

        tbvResultados.getColumns().addAll(/*tbcId,*/tbcCedula, tbcNombre, tbcApellido, tbcTipoUser);
        tbvResultados.refresh();
    }

    public void fillCbox() {
        cboxTipoUsuario.getItems().clear();
        resourceBundle = FlowController.getInstance().getIdioma();

        String admin = resourceBundle.getString("key.admin");
        String doctor = resourceBundle.getString("key.doctor");
        String receptionist = resourceBundle.getString("key.receptionist");

        ObservableList<String> tiposUsuarios = FXCollections.observableArrayList();
        tiposUsuarios.addAll(admin, doctor, receptionist);
        cboxTipoUsuario.setItems(tiposUsuarios);

    }

    public void cleanNodes() {
        txfCedula.clear();
        txfNombre.clear();
        txfApellido.clear();
        fillCbox();
    }

    public Object getSeleccionado() {
        return resultado;
    }

}
