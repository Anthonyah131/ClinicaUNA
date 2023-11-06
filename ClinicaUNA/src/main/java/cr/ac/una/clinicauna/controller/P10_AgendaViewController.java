package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.model.CliAgendaDto;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliAgendaService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.SoundUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.input.DataFormat;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    CliUsuarioDto usuarioDto;
    CliMedicoDto medicoDto;
    CliAgendaDto agendaDto;
    CliCitaDto citaDto;
    CliCitaDto citasMatriz[][];
    List<CliCitaDto> listaCitas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        iniciarVariables();
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
    private void onActionBtnSalir(ActionEvent event) {
        SoundUtil.mouseEnterSound();
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    private void iniciarVariables() {
        usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
        citaDto = new CliCitaDto();
        agendaDto = new CliAgendaDto();
        listaCitas = new ArrayList<>();

        dtpFechasCitas.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                validarCargarAgenda();
            }
        });
    }

    private void validarCargarAgenda() {
        if (dtpFechasCitas.getValue() != null && medicoDto != null) {
            cargarAgenda();

            int iniJornada = medicoDto.getMedFiniTime().getHour();
            int finJornada = medicoDto.getMedFfinTime().getHour();
            int jornada = finJornada - iniJornada;
            int citasHoras = Math.toIntExact(medicoDto.getMedEspaciosxhora());

            citasMatriz = new CliCitaDto[jornada + 1][citasHoras + 1];
            if (!listaCitas.isEmpty()) {
                for (int i = 0; i < listaCitas.size(); i++) {
                    citaAMatriz(listaCitas.get(i), iniJornada, citasHoras);
                }
            }
            llenarGridPane();
        }
    }

    private void cargarAgenda() {
        CliAgendaService service = new CliAgendaService();
        Respuesta respuesta = service.getAgenda(medicoDto, dtpFechasCitas.getValue());

        if (respuesta.getEstado()) {
            agendaDto = (CliAgendaDto) respuesta.getResultado("Agenda"); // agendaDto tiene la agenda seleccionad, dentro tiene las citas y el paciente de cada cita
            if (agendaDto == null) {
                agendaDto = new CliAgendaDto();
                agendaDto.setAgeFecha(dtpFechasCitas.getValue());
            }

            listaCitas.clear();
            listaCitas = agendaDto.getCliCitaList();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Agenda", getStage(), respuesta.getMensaje());
        }
    }

    private void citaAMatriz(CliCitaDto cita, int iniJornada, int citasHoras) {
        LocalDateTime fechaHora = cita.getCitFechaHora();
        int hora = fechaHora.getHour();
        int minuto = fechaHora.getMinute();

        int col = 1;

        switch (citasHoras) {
            case 2 -> {
                col = switch (minuto) {
                    case 0 ->
                        1;
                    default ->
                        2;
                };
            }
            case 3 -> {
                col = switch (minuto) {
                    case 0 ->
                        1;
                    case 20 ->
                        2;
                    default ->
                        3;
                };
            }
            case 4 -> {
                col = switch (minuto) {
                    case 0 ->
                        1;
                    case 15 ->
                        2;
                    case 30 ->
                        3;
                    default ->
                        4;
                };
            }
        }
        int fila = hora - iniJornada + 1;

        citasMatriz[fila][col] = cita;
    }

    private void llenarGridPane() {
        int iniJornada = medicoDto.getMedFiniTime().getHour();
        int finJornada = medicoDto.getMedFfinTime().getHour();
        int jornada = finJornada - iniJornada;
        int citasHoras = Math.toIntExact(medicoDto.getMedEspaciosxhora());

        int numRows = jornada + 1;
        int numCols = citasHoras + 1;
        int horaInicio = iniJornada;

        int citasHorasEncabezado = 60 / citasHoras;

        grdCitas.getChildren().clear();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Label label = new Label();
                if (i == 0 && j == 0) {
                    label = new Label("Horas");
                    label.getStyleClass().add("label-agenda-horas");
//                    label.setPrefWidth(90);
                    grdCitas.add(label, j, i);
                }
                if (i == 0 && j > 0) {
                    int minutos = citasHorasEncabezado * (j - 1);
                    String aux = (minutos == 0) ? "00:0" : "00:";
                    label.setText(aux + minutos);
                    label.getStyleClass().add("label-agenda-horas");
//                    label.setPrefSize(150, 30);
//                    label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                    grdCitas.add(label, j, i);
                    continue;
                }
                if (j == 0 && i > 0) {
                    String aux = (horaInicio < 12) ? ":00 am." : ":00 pm.";
                    int hora12 = (horaInicio <= 12) ? horaInicio : horaInicio - 12;

                    label.setText(hora12 + aux);
                    label.getStyleClass().add("label-agenda-horas");
//                    label.setPrefWidth(90);
                    grdCitas.add(label, j, i);
                    horaInicio++;
                    continue;
                }
                if (i > 0 && j > 0) {
                   Label labelCit = new Label("Agregar cita");
                    if (citasMatriz[i][j] != null) {
                        citaDto = citasMatriz[i][j];
                        crearCita(labelCit);
                    } else {
                        labelCit.setPrefSize(150, 70);
                        Image image = new Image("cr/ac/una/ClinicaUNA/resources/media/icons/addIcon.png");
                        ImageView imageView = new ImageView(image);
                        imageView.setFitHeight(30);
                        imageView.setFitWidth(30);
                        labelCit.setGraphic(imageView);
                        labelCit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        labelCit.contentDisplayProperty().set(ContentDisplay.TOP);
                        labelCit.setAlignment(Pos.CENTER);
                    }

                    labelCit.setOnMouseClicked(event -> {
                        FlowController.getInstance().delete("P11_NuevaCitaView");
                        int rowIndex = GridPane.getRowIndex(labelCit);
                        int colIndex = GridPane.getColumnIndex(labelCit);

                        if (citasMatriz[rowIndex][colIndex] != null) {
                            citaDto = citasMatriz[rowIndex][colIndex];
                        } else {
                            citaDto = new CliCitaDto();
                        }

                        P11_NuevaCitaViewController citaController = (P11_NuevaCitaViewController) FlowController.getInstance().getController("P11_NuevaCitaView");
                        citaController.cargarDefecto(citaDto, usuarioDto, agendaDto, medicoDto, calcularHora(rowIndex, colIndex));

                        FlowController.getInstance().goViewInWindowModal("P11_NuevaCitaView", stage, Boolean.FALSE);

                        if (citaDto.getCitId() != null) {
                            citasMatriz[rowIndex][colIndex] = citaDto;
                            crearCita(labelCit);
                        }

                        System.out.println("Fila: " + rowIndex + " columna " + colIndex);

                    });
                    grdCitas.add(labelCit, j, i);
                }
            }
        }
        grdCitas.setGridLinesVisible(true);
    }

    private static final DataFormat DATA_FORMAT = new DataFormat("label");

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
            btnBuscarMedico.setText(medicoDto.getCliUsuarioDto().getNombreApellidos());
        }
//        bindUsuario();
    }

    public void cargarCita(CliCitaDto cita, CliAgendaDto agenda, CliMedicoDto medico) {
        citaDto = cita;
        agendaDto = agenda;
        medicoDto = medico;
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

    private void guardarAgenda() {

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
