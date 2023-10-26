/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P09_MantenimientoPacientesViewController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private JFXTextField txfCedula;
    @FXML
    private JFXRadioButton rdbHombre;
    @FXML
    private ToggleGroup tggGenero;
    @FXML
    private JFXRadioButton rdbMujer;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextField txfPapellido;
    @FXML
    private JFXTextField txfSapellido;
    @FXML
    private JFXTextField txfCorreo;
    @FXML
    private JFXTextField txfTelefono;
    @FXML
    private MFXButton btnLimpiarCampos;
    @FXML
    private MFXButton btnBuscar;
    @FXML
    private MFXButton btnGuardar;
    @FXML
    private JFXTextField txfBuscarCedula;
    @FXML
    private JFXTextField txfBuscarNombre;
    @FXML
    private JFXTextField txfBuscarPapellido;
    @FXML
    private MFXButton btnFiltrar;
    @FXML
    private TableView<CliPacienteDto> tbvResultados;
    
    CliPacienteDto CliPacienteDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CliPacienteDto = new CliPacienteDto();
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
    private void onActionBtnLimpiarCampos(ActionEvent event) {
    }

    @FXML
    private void onActionBtnBuscar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
    }

    @FXML
    private void onActionBtnFiltrar(ActionEvent event) {
    }

    public void fillTableView() {
        ResourceBundle resourceBundle = FlowController.getInstance().getIdioma();
        
        tbvResultados.getItems().clear();

        TableColumn<CliPacienteDto, String> tbcId = new TableColumn<>("Id");
        tbcId.setPrefWidth(50);
        tbcId.setCellValueFactory(cd -> cd.getValue().pacId);

        TableColumn<CliPacienteDto, String> tbcCedula = new TableColumn<>(resourceBundle.getString("key.identification"));
        tbcCedula.setPrefWidth(100);
        tbcCedula.setCellValueFactory(cd -> cd.getValue().pacCedula);

        TableColumn<CliPacienteDto, String> tbcNombre = new TableColumn<>(resourceBundle.getString("key.name"));
        tbcNombre.setPrefWidth(100);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().pacNombre);

        TableColumn<CliPacienteDto, String> tbcApellido = new TableColumn<>(resourceBundle.getString("key.papellido"));
        tbcApellido.setPrefWidth(150);
        tbcApellido.setCellValueFactory(cd -> cd.getValue().pacPapellido);
        
        TableColumn<CliPacienteDto, Boolean> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEliminar.setPrefWidth(100);
        tbcEliminar.setCellValueFactory((TableColumn.CellDataFeatures<CliPacienteDto, Boolean> p) -> new SimpleBooleanProperty(p.getValue() != null));
        tbcEliminar.setCellFactory((TableColumn<CliPacienteDto, Boolean> p) -> new ButtonCell());

        tbvResultados.getColumns().addAll(tbcId, tbcCedula,tbcNombre, tbcApellido, tbcEliminar);
        tbvResultados.refresh();
    }
    
    private class ButtonCell extends TableCell<CliPacienteDto, Boolean> {

        final MFXButton cellButton = new MFXButton();

        ButtonCell() {
            cellButton.setPrefWidth(500);
            cellButton.setText("X");
            cellButton.getStyleClass().add("mfx-button-menuSalir");

            cellButton.setOnAction((ActionEvent t) -> {
                CliPacienteDto car = (CliPacienteDto) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
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
