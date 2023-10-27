package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
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
    private JFXTimePicker tpkHoraLlegada;
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
    private TableView<CliUsuarioDto> tbvResultados;

    CliMedicoDto CliMedicoDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfBuscarCodigo.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfBuscarFolio.setTextFormatter(Formato.getInstance().letrasFormat(10));
        txfBuscarNombre.setTextFormatter(Formato.getInstance().letrasFormat(25));
        txfBuscarPapellido.setTextFormatter(Formato.getInstance().letrasFormat(25));
        
        txfCodigoMedico.setTextFormatter(Formato.getInstance().letrasFormat(15));
        txfFolioMedico.setTextFormatter(Formato.getInstance().letrasFormat(10));
        txfLicencia.setTextFormatter(Formato.getInstance().letrasFormat(15));
        CliMedicoDto = new CliMedicoDto();
        fillCbox();
        fillTableView();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBtnLimpiarBusquedaMedico(ActionEvent event) {
    }

    @FXML
    private void onActionBtnLimpiarCampos(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }
    
    public void fillCbox() {
        cboxCantidadCitas.getItems().clear();
//
//        String admin = resourceBundle.getString("key.admin");
//        String doctor = resourceBundle.getString("key.doctor");
//        String receptionist = resourceBundle.getString("key.receptionist");

        ObservableList<Integer> citasHora = FXCollections.observableArrayList();
        citasHora.addAll(1, 2, 3,4);
        cboxCantidadCitas.setItems(citasHora);
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

        TableColumn<CliUsuarioDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(100);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<CliUsuarioDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<CliUsuarioDto, Boolean> p) -> new ButtonCell());

        tbvResultados.getColumns().addAll(tbcId, tbcCedula, tbcNombre, tbcApellido, tbcEliminar);

        tbvResultados.refresh();
    }

    private class ButtonCell extends TableCell<CliUsuarioDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.setText("X");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                CliUsuarioDto car = (CliUsuarioDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                if (!car.getModificado()) {
                    //  CliPacienteDto.getTarCaracteristicaEliminados().add(car);
                }
                tbvResultados.getItems().remove(car);
                tbvResultados.refresh();
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
