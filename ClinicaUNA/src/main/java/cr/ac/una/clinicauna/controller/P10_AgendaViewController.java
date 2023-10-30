package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P10_AgendaViewController extends Controller implements Initializable {

    @FXML
    private JFXDatePicker dtpFechasCitas;
    @FXML
    private Label lblNombreMedico;
    @FXML
    private MFXButton btnBuscarMedico;
    @FXML
    private GridPane grdCitas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarGridPane();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnBuscarMedico(ActionEvent event) {

    }

    private void llenarGridPane() {
        int numRows = 7;
        int numCols = 5;
        int horaInicio = 8;

        grdCitas.getChildren().clear();
        // Crear filas
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j == 0) {
                    Label label = new Label();
                    label.setText(horaInicio + ":00");
                    label.getStyleClass().add("labels-text-minus");
                    label.setPrefWidth(70);
                    grdCitas.add(label, j, i);
                    horaInicio++;
                } else {
                    VBox vBox = new VBox();
                    vBox.setPrefSize(200, 50);
                    Image image = new Image("cr/ac/una/ClinicaUNA/resources/media/icons/addIcon.png");
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    MFXButton btnAddPac = new MFXButton();
                    btnAddPac.setGraphic(imageView);
                    btnAddPac.getStyleClass().add("mfx-button-Image");
                    btnAddPac.setOnAction(event -> {
                        crearCita(vBox);
                    });
                    vBox.getChildren().add(btnAddPac);
                    vBox.setAlignment(Pos.BOTTOM_RIGHT);
                    grdCitas.add(vBox, j, i);
                }
            }
        }
        grdCitas.setGridLinesVisible(true);
    }

    private void crearCita(VBox vBox) {
        vBox.getChildren().clear();

        Label lblEstado = new Label("Estado: ");
        Label lblEstado2 = new Label();
        HBox hBox1 = new HBox(lblEstado, lblEstado2);
        Label lblPaciente = new Label("Paciente: ");
        Label lblNombrePac = new Label();
        MFXButton btnAddPac = new MFXButton("Agregar");
        HBox hBox2 = new HBox(lblPaciente, lblNombrePac, btnAddPac);
        Label lblUsuario = new Label("Usuario que registra: ");
        Label lblNombreUsu = new Label();
        HBox hBox3 = new HBox(lblUsuario, lblNombreUsu);
        Label lblMotivo = new Label("Motivo: ");
        JFXTextField txfMotivo = new JFXTextField();
        HBox hBox4 = new HBox(lblMotivo, txfMotivo);
        Label lblTelefono = new Label("Telefono: ");
        Label lblTelefonoUsu = new Label();
        HBox hBox5 = new HBox(lblTelefono, lblTelefonoUsu);
        Label lblCorreo = new Label("Telefono: ");
        Label lblCorreoUsu = new Label();
        HBox hBox6 = new HBox(lblCorreo, lblCorreoUsu);
        MFXButton btnGuardar = new MFXButton("Guardar");

        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, btnGuardar);

    }

}
