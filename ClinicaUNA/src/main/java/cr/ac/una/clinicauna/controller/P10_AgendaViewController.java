package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.DataFormat;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;

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
    CliCitaDto citasMatriz[][];

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // llenarGridPane();
        usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
        citaDto = new CliCitaDto();
//        dragAndDrop();
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
//    int rowIndex;
//    int colIndex;
    private static final DataFormat DATA_FORMAT = new DataFormat("label");

    private void llenarGridPane() {
        int iniJornada = medicoDto.getMedFiniTime().getHour();
        int finJornada = medicoDto.getMedFfinTime().getHour();
        int jornada = finJornada - iniJornada;
        int citasHoras = Math.toIntExact(medicoDto.getMedEspaciosxhora());

        int numRows = jornada + 1;
        int numCols = citasHoras + 1;
        int horaInicio = iniJornada;

        int citasHorasEncabezado = 60 / citasHoras;

        citasMatriz = new CliCitaDto[numRows][numCols];

        grdCitas.getChildren().clear();
        // Crear filas

//        Label draggableLabel = new Label("Drag me");
//        draggableLabel.setOnDragDetected(event -> {
//            Dragboard dragboard = draggableLabel.startDragAndDrop(TransferMode.MOVE);
//            ClipboardContent content = new ClipboardContent();
//            content.put(DATA_FORMAT, "label");
//            dragboard.setContent(content);
//            event.consume();
//        });
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                citasMatriz[i][j] = null;
                if (i == 0 && j == 0) {
                    Label label = new Label("Horas");
                    label.getStyleClass().add("labels-text-minus");
                    label.setPrefWidth(90);
                    grdCitas.add(label, j, i);
                }
                if (i == 0 && j > 0) {
                    Label label = new Label();
                    int minutos = citasHorasEncabezado * (j - 1);
                    String aux = (minutos == 0) ? "00:0" : "00:";
                    label.setText(aux + minutos);
                    label.getStyleClass().add("labels-text-minus");
                    label.setPrefSize(150, 30);
                    grdCitas.add(label, j, i);
                    continue;
                }
                if (j == 0 && i > 0) {
                    Label label = new Label();

                    String aux = (horaInicio < 12) ? ":00 am." : ":00 pm.";
                    int hora12 = (horaInicio <= 12) ? horaInicio : horaInicio - 12;

                    label.setText(hora12 + aux);
                    label.getStyleClass().add("labels-text-minus");
                    label.setPrefWidth(90);                    
                    grdCitas.add(label, j, i);
                    horaInicio++;
                    continue;
                }
                if (i > 0 && j > 0) {
                    Label label = new Label("Agregar cita");
                    label.setPrefSize(150, 70);
                    Image image = new Image("cr/ac/una/ClinicaUNA/resources/media/icons/addIcon.png");
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    label.setGraphic(imageView);
                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    label.contentDisplayProperty().set(ContentDisplay.TOP);
                    label.setAlignment(Pos.CENTER);

                    label.setOnMouseClicked(event -> {
                        FlowController.getInstance().delete("P11_NuevaCitaView");
                        int rowIndex = GridPane.getRowIndex(label);
                        int colIndex = GridPane.getColumnIndex(label);
//                        calcularHora(rowIndex, colIndex);

                        if (citasMatriz[rowIndex][colIndex] != null) {
                            citaDto = citasMatriz[rowIndex][colIndex];
                        } else {
                            citaDto = new CliCitaDto();
                        }

                        P11_NuevaCitaViewController citaController = (P11_NuevaCitaViewController) FlowController.getInstance().getController("P11_NuevaCitaView");
                        citaController.cargarDefecto(citaDto, usuarioDto, calcularHora(rowIndex, colIndex));

                        FlowController.getInstance().goViewInWindowModal("P11_NuevaCitaView", stage, Boolean.FALSE);

//                        if (citaDto.getCitId() != null) {
                            citasMatriz[rowIndex][colIndex] = citaDto;
                            crearCita(label);
//                        }

                        System.out.println("Fila: " + rowIndex + " columna " + colIndex);

                    });
                    // Configurar eventos de arrastrar y soltar para las etiquetas
//                    label.setOnDragOver(event -> {
//                        if (event.getDragboard().hasContent(DATA_FORMAT)) {
//                            event.acceptTransferModes(TransferMode.MOVE);
//                        }
//                        event.consume();
//                    });
//                    label.setOnDragDropped(event -> {
//                        Dragboard dragboard = event.getDragboard();
//                        if (dragboard.hasContent(DATA_FORMAT)) {
//                            grdCitas.getChildren().remove(draggableLabel);
//                            Label droppedLabel = new Label("Dropped");
//                            grdCitas.add(droppedLabel, numCols, numRows);
//                            event.setDropCompleted(true);
//                        }
//                        event.consume();
//                    });
                    grdCitas.add(label, j, i);
                }
            }
        }
        grdCitas.setGridLinesVisible(true);
    }

    private LocalDateTime calcularHora(int fila, int columna) {
//        if (citaDto != null) {
        int iniJornada = medicoDto.getMedFiniTime().getHour();
        int finJornada = medicoDto.getMedFfinTime().getHour();
        int jornada = finJornada - iniJornada;
        int citasHoras = Math.toIntExact(medicoDto.getMedEspaciosxhora());

        int hora = iniJornada + fila - 1;

        int minutos = 0;

        if (columna == 2) {
            switch (citasHoras) {
                case 2 ->
                    minutos = 30;
                case 3 ->
                    minutos = 20;
                case 4 ->
                    minutos = 15;
            }
        }
        if (columna == 3) {
            switch (citasHoras) {
                case 3 ->
                    minutos = 40;
                case 4 ->
                    minutos = 30;
            }
        }
        if (columna == 4) {
            minutos = 45;
        }

        LocalDate fecha = dtpFechasCitas.getValue();
        int year = fecha.getYear();
        int month = fecha.getMonthValue();
        int day = fecha.getDayOfMonth();

        LocalDateTime fechaHora = LocalDateTime.of(year, month, day, hora, minutos);
        System.out.println(fechaHora);

        return fechaHora;
    }

    private void crearCita(Label label) {

        if (citaDto != null) {
            label.setText(""); // O label.setText(null)
            label.setGraphic(null); // Eliminar la imagen
//            String estadoCita = "Estado: " + estadoCita() + "\n";
//            String nombrePac = "Paciente: " + citaDto.getNombreString() + "\n";
//            String usuarioRegistra = "Usuario que registra: " + citaDto.getCitUsuarioRegistra() + "\n";
//            String motivo = (citaDto.getCitMotivo() != null) ? "Motivo: " + citaDto.getCitMotivo() + "\n" : "Motivo: No indica\n";
//            String telefono = "Telefono: " + citaDto.getCliPacienteDto().getPacTelefono() + "\n";
//            String correo = "Correo: " + citaDto.getCliPacienteDto().getPacCorreo() + "\n";

            label.setPrefSize(300, 130);
            label.setPadding(new Insets(5));
            estadoCita(label);
//            label.setText(estadoCita + nombrePac + usuarioRegistra + motivo + telefono + correo);
            label.setText(citaDto.citaLabel());
        }
    }

    public void bindBuscar() {
        P08_MantenimientoMedicosViewController buscadorRegistroController = (P08_MantenimientoMedicosViewController) FlowController.getInstance().getController("P08_MantenimientoMedicosView");
//        unbindUsuario();
        medicoDto = (CliMedicoDto) buscadorRegistroController.getSeleccionado();
        if (medicoDto != null) {
            btnBuscarMedico.setText(medicoDto.getNombreString());
        }
//        bindUsuario();
    }

    public void cargarCita(CliCitaDto cita) {
        citaDto = cita;
    }

    private void estadoCita(Label label) {
        switch (citaDto.getCitEstado()) {
            case "P" ->
                label.getStyleClass().add("label-cita-programada");
            case "A" ->
                label.getStyleClass().add("label-cita-atendida");
//            case "C" ->
            //label.getStyleClass().add("label-cita-programada");
            case "U" ->
                label.getStyleClass().add("label-cita-ausente");
        }
    }


//    public void dragAndDrop() {
//
//        grdCitas.setOnDragOver(event -> {
//            if (event.getGestureSource() != grdCitas && event.getDragboard().hasString()) {
//                event.acceptTransferModes(TransferMode.MOVE);
//            }
//            event.consume();
//        });
//
//        grdCitas.setOnDragDropped(event -> {
//            Dragboard db = event.getDragboard();
//            boolean success = false;
//
//            if (db.hasString()) {
//                Label sourceLabel = createLabel(db.getString());
//                grdCitas.add(sourceLabel, colIndex, rowIndex);
//                success = true;
//            }
//
//            event.setDropCompleted(success);
//            event.consume();
//        });
//    }
//
//    private Label createLabel(String text) {
//        Label label = new Label(text);
//        label.setOnDragDetected(event -> {
//            Dragboard db = label.startDragAndDrop(TransferMode.MOVE);
//            ClipboardContent content = new ClipboardContent();
//            content.putString(label.getText());
//            db.setContent(content);
//            event.consume();
//        });
//
//        return label;
//    }
}
