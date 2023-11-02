package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private MFXButton btnBuscarMedico;
    @FXML
    private GridPane grdCitas;
    @FXML
    private MFXButton btnSalir;
    @FXML
    private MFXButton btnCargarAgenda;
    
    CliUsuarioDto usuarioDto;
    CliMedicoDto medicoDto;
    CliCitaDto citaDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // llenarGridPane();
        usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnBuscarMedico(ActionEvent event) {
        AppContext.getInstance().set("PadreMedicos", "P10_AgendaView");
        FlowController.getInstance().delete("P08_MantenimientoMedicosView");
        FlowController.getInstance().goViewInWindowModal("P08_MantenimientoMedicosView", stage, false);
        validarCargarAgenda();
    }

    @FXML
    private void onActionBtnCargarAgenda(ActionEvent event) {
        //validarCargarAgenda();
        System.out.println(citaDto.toString());
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private void validarCargarAgenda() {
        if (dtpFechasCitas.getValue() != null && medicoDto != null) {
            llenarGridPane();
        }
    }

    private void llenarGridPane() {
        int iniJornada = medicoDto.getMedFiniTime().getHour();
        int finJornada = medicoDto.getMedFfinTime().getHour();
        int jornada = finJornada - iniJornada;
        Long citasHoras = medicoDto.getMedEspaciosxhora();

        int numRows = jornada;
        int numCols = Math.toIntExact(citasHoras) + 1;
        int horaInicio = iniJornada;

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
//                        int rowIndex = GridPane.getRowIndex(btnAddPac);
//                        int colIndex = GridPane.getColumnIndex(btnAddPac);
                        ///crearCita(vBox);
                        FlowController.getInstance().goViewInWindowModal("P11_NuevaCitaView", stage, Boolean.FALSE);
                    });
                    vBox.getChildren().add(btnAddPac);
                    vBox.setAlignment(Pos.BOTTOM_RIGHT);
//                    Label label = new Label();
//                    label.setPrefSize(100, 50);
//                    label.setOnMouseClicked(event -> {
//                        int rowIndex = GridPane.getRowIndex(label);
//                        int colIndex = GridPane.getColumnIndex(label);
//                        System.out.println("Fila: " + rowIndex + " columna " + colIndex);
//                    });
                    grdCitas.add(vBox, j, i);
                }
            }
        }
        grdCitas.setGridLinesVisible(true);
    }

    private void crearCita(VBox vBox) {
        vBox.getChildren().clear();

        Label lblEstado = new Label("Estado: ");
        lblEstado.getStyleClass().add("labels-text-minus");

        Label lblEstado2 = new Label();
        lblEstado.getStyleClass().add("labels-text-minus");

        HBox hBox1 = new HBox(lblEstado, lblEstado2);
        hBox1.setAlignment(Pos.CENTER_LEFT);
        hBox1.setSpacing(5);

        Label lblPaciente = new Label("Paciente: ");
        lblPaciente.getStyleClass().add("labels-text-minus");

        Label lblNombrePac = new Label();
        lblNombrePac.getStyleClass().add("labels-text-minus");

        MFXButton btnAddPac = new MFXButton("Agregar");
        btnAddPac.getStyleClass().add("mfx-button-menu-minus");

        HBox hBox2 = new HBox(lblPaciente, lblNombrePac, btnAddPac);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.setSpacing(5);

        Label lblUsuario = new Label("Usuario que registra: ");
        lblUsuario.getStyleClass().add("labels-text-minus");

        Label lblNombreUsu = new Label();
        lblNombreUsu.getStyleClass().add("labels-text-minus");

        HBox hBox3 = new HBox(lblUsuario, lblNombreUsu);
        hBox3.setAlignment(Pos.CENTER_LEFT);
        hBox3.setSpacing(5);

        Label lblMotivo = new Label("Motivo: ");
        lblMotivo.getStyleClass().add("labels-text-minus");

        JFXTextField txfMotivo = new JFXTextField();
        txfMotivo.getStyleClass().add("jfx-text-field-edit");

        HBox hBox4 = new HBox(lblMotivo, txfMotivo);
        hBox4.setAlignment(Pos.CENTER_LEFT);
        hBox4.setSpacing(5);

        Label lblTelefono = new Label("Telefono: ");
        lblTelefono.getStyleClass().add("labels-text-minus");

        Label lblTelefonoUsu = new Label();
        lblTelefonoUsu.getStyleClass().add("labels-text-minus");

        HBox hBox5 = new HBox(lblTelefono, lblTelefonoUsu);
        hBox5.setAlignment(Pos.CENTER_LEFT);
        hBox5.setSpacing(5);

        Label lblCorreo = new Label("Correo: ");
        lblCorreo.getStyleClass().add("labels-text-minus");

        Label lblCorreoUsu = new Label();
        lblCorreoUsu.getStyleClass().add("labels-text-minus");

        HBox hBox6 = new HBox(lblCorreo, lblCorreoUsu);
        hBox6.setAlignment(Pos.CENTER_LEFT);
        hBox6.setSpacing(5);

        MFXButton btnGuardar = new MFXButton("Guardar");
        btnGuardar.getStyleClass().add("mfx-button-menu-minus");

        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        vBox.setPrefWidth(300);
        vBox.getChildren().addAll(hBox1, hBox2, hBox3, hBox4, hBox5, hBox6, btnGuardar);

    }

    public void bindBuscar() {
        P08_MantenimientoMedicosViewController buscadorRegistroController = (P08_MantenimientoMedicosViewController) FlowController.getInstance().getController("P08_MantenimientoMedicosView");
//        unbindUsuario();
        medicoDto = (CliMedicoDto) buscadorRegistroController.getSeleccionado();
        if (medicoDto != null) {
            btnBuscarMedico.setText(medicoDto.getCliUsuarioDto().getUsuNombre() + " " + medicoDto.getCliUsuarioDto().getUsuPapellido() + " " + medicoDto.getCliUsuarioDto().getUsuSapellido());
        }
//        bindUsuario();
    }
    
    public void cargarCita(CliCitaDto cita){
        citaDto = cita;
    }

}
