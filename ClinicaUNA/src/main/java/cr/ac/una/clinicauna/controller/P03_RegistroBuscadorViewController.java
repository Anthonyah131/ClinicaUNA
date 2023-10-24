package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliUsuarioService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
//        CliUsuarioService service = new CliUsuarioService();
//        Respuesta respuesta = service.getUsuarios();
//
//        if (respuesta.getEstado()) {
//            tbvResultados.getItems().clear();
//            usuarios.clear();
//            usuarios.addAll((List<CliUsuarioDto>) respuesta.getResultado("TarUsuario"));
//            tbvResultados.setItems(usuarios);
//            tbvResultados.refresh();
//        } else {
//            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Usuarios", getStage(), respuesta.getMensaje());
//        }
    }

    @FXML
    private void onMousePressenTbvResultados(MouseEvent event) {
    }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
        resultado = tbvResultados.getSelectionModel().getSelectedItem();
        if (resultado != null){
        P03_RegistroViewController registroController = (P03_RegistroViewController) FlowController.getInstance().getController("P03_RegistroView");
        registroController.bindBuscar();
        }
        getStage().close();
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
        cleanNodes();
    }

    public void fillTableView() {
        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();
        
        tbvResultados.getItems().clear();

        TableColumn<CliUsuarioDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(50);
        tbcId.setCellValueFactory(cd -> cd.getValue().usuId);

        TableColumn<CliUsuarioDto, String> tbcCedula = new TableColumn<>(resourceBundle.getString("key.identification"));
        tbcCedula.setPrefWidth(100);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().usuCedula);

        TableColumn<CliUsuarioDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.name"));
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().usuNombre);

        TableColumn<CliUsuarioDto, String> tbcApellido = new TableColumn<>(resourceBundle.getString("key.papellido"));
        tbcApellido.setPrefWidth(150);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().usuPapellido);

        tbvResultados.getColumns().add(tbcId);
        tbvResultados.getColumns().add(tbcCedula);
        tbvResultados.getColumns().add(tbcNombre);
        tbvResultados.getColumns().add(tbcApellido);
        tbvResultados.refresh();
    }

    public void fillCbox() {
        cboxTipoUsuario.getItems().clear();

        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();
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
