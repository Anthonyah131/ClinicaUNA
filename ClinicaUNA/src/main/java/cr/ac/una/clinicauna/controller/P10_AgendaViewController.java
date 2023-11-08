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
    //CliCitaDto citasMatriz[][];
    CliCitaDto citasVector[];
    List<CliCitaDto> listaCitas;
    Label labelVector[];

    int iniJornada;
    int finJornada;
    int jornada;
    int citasHoras;

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
        //Inicializar entidades
        usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
        citaDto = new CliCitaDto();
        agendaDto = new CliAgendaDto();
        listaCitas = new ArrayList<>();

        //Listener para el datepicker
        dtpFechasCitas.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                validarCargarAgenda();
            }
        });
    }

    private void validarCargarAgenda() {
        if (dtpFechasCitas.getValue() != null && medicoDto != null) {
            //Recupera la agenda si la hay desde base y la lista de citas
            cargarAgenda();
            //Variables varias para calculos de espacios en el grid
            iniJornada = medicoDto.getMedFiniTime().getHour();
            finJornada = medicoDto.getMedFfinTime().getHour();
            jornada = finJornada - iniJornada;
            citasHoras = Math.toIntExact(medicoDto.getMedEspaciosxhora());
            //Establecer tamano vector de citas
            labelVector = new Label[jornada * citasHoras];

            //Recorre la lista de citas y las pasa a la matriz
            pasarListaCitasAVector();
            // llena el gridpane con labeles
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
            listaCitas.addAll(agendaDto.getCliCitaList());
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Agenda", getStage(), respuesta.getMensaje());
        }
    }

    private void pasarListaCitasAVector() {
        listaCitas.clear();
        listaCitas.addAll(agendaDto.getCliCitaList());
        citasVector = new CliCitaDto[jornada * citasHoras];
        if (!listaCitas.isEmpty()) {
            for (int i = 0; i < listaCitas.size(); i++) {
                LocalDateTime fechaHora = listaCitas.get(i).getCitFechaHora();
                citasVector[citaAPosVector(fechaHora)] = listaCitas.get(i);
            }
        }
    }

    private int citaAPosVector(LocalDateTime fechaHora) {

        int hora = fechaHora.getHour();
        int minuto = fechaHora.getMinute();

        int col = 0;
        //comprobar minutos para 2 citas
        if (citasHoras == 2 && minuto == 30) {
            col = 1;
        }
        //comprobar minutos para 3 citas
        if (citasHoras == 3) {
            if (minuto == 20) {
                col = 1;
            } else if (minuto == 40) {
                col = 2;
            }
        }
        //comprobar minutos para 4 citas
        if (citasHoras == 4) {
            switch (minuto) {
                case 15 ->
                    col = 1;
                case 30 ->
                    col = 2;
                case 45 ->
                    col = 3;
            }
        }

        int fila = hora - iniJornada;
        int posVector = fila * citasHoras + col;
        // Pasar de pos de matriz a pos de vector
        return posVector;
    }

    private void comprobarEspaciosCitas() { // arreglar
        for (int i = 0; i < citasVector.length; i++) {
            if (citasVector[i] != null) {
                CliCitaDto cita = citasVector[i];
                int espaciosCitas = Math.toIntExact(cita.getCliCantespacios());
                if (espaciosCitas > 1) {
                    for (int j = 0; j < espaciosCitas; j++) {
                        citasVector[i] = cita;
                        i++;
                    }
                }
            }
        }
    }

    private void llenarGridPane() {
        int numRows = jornada + 1;
        int numCols = citasHoras + 1;
        int horaInicio = iniJornada;

        int citasHorasEncabezado = 60 / citasHoras;
        int contadorLabel = 0;

        grdCitas.getChildren().clear();

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 0 && j == 0) {
                    Label label = crearLabelBordes("Horas");
                    grdCitas.add(label, j, i);
                }
                if (i == 0 && j > 0) {
                    int minutos = citasHorasEncabezado * (j - 1);
                    String aux = (minutos == 0) ? "00:0" : "00:";
                    Label label = crearLabelBordes(aux + minutos);
                    grdCitas.add(label, j, i);
                    continue;
                }
                if (j == 0 && i > 0) {
                    String aux = (horaInicio < 12) ? ":00 am." : ":00 pm.";
                    int hora12 = (horaInicio <= 12) ? horaInicio : horaInicio - 12;
                    Label label = crearLabelBordes(hora12 + aux);
                    grdCitas.add(label, j, i);
                    horaInicio++;
                    continue;
                }
                if (i > 0 && j > 0) {
                    Label label = new Label();

                    label.setOnMouseClicked(event -> {
                        FlowController.getInstance().delete("P11_NuevaCitaView");
                        int rowIndex = GridPane.getRowIndex(label);
                        int colIndex = GridPane.getColumnIndex(label);

                        int posVector = (rowIndex - 1) * citasHoras + colIndex - 1;
                        LocalDateTime hora;
                        if (citasVector[posVector] != null) {
                            citaDto = citasVector[posVector];
                            hora = citaDto.getCitFechaHora();
                            posVector = citaAPosVector(hora);
                        } else {
                            citaDto = new CliCitaDto();
                            hora = calcularHora(rowIndex, colIndex);
                        }
                        System.out.println(hora);
                        System.out.println(posVector);

                        P11_NuevaCitaViewController citaController = (P11_NuevaCitaViewController) FlowController.getInstance().getController("P11_NuevaCitaView");
                        citaController.cargarDefecto(citaDto, usuarioDto, agendaDto, medicoDto,
                                hora, citasVector, posVector);
                        FlowController.getInstance().goViewInWindowModal("P11_NuevaCitaView", stage, Boolean.FALSE);

                        if (citaDto.getCitId() != null) {
                            pasarListaCitasAVector();
                            comprobarEspaciosCitas();
                            crearCita();
                            //crearCita(label);
                        }

                        System.out.println("Fila: " + rowIndex + " columna " + colIndex);

                    });
                    labelVector[contadorLabel] = label;
                    contadorLabel++;
                    grdCitas.add(label, j, i);
                }
            }
        }
        comprobarEspaciosCitas();
        crearCita();
        grdCitas.setGridLinesVisible(true);
    }

    private void crearCita() {
        for (int i = 0; i < citasVector.length; i++) {
            if (citasVector[i] != null) {
                crearLabelCita(labelVector[i], citasVector[i].citaLabel(), citasVector[i].getCitEstado());
            } else {
                crearLabelAddCita(labelVector[i]);
            }
        }
    }

    private Label crearLabelBordes(String texto) {
        Label label = new Label();
        label.setText(texto);
        label.getStyleClass().add("label-agenda-horas");
        return label;
    }

    private void crearLabelAddCita(Label label) {
        label.getStyleClass().clear();
        label.setText("");
        label.setText("Agregar cita");
        Image image = new Image("cr/ac/una/ClinicaUNA/resources/media/icons/addIcon.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        label.setGraphic(imageView);
        label.getStyleClass().add("label-agregar-cita");

    }

    private void crearLabelCita(Label label, String texto, String estadoCita) {
        //Label label = new Label();
        label.setText("");
        label.setGraphic(null);
        label.setText(texto);
        label.setPrefSize(290, 130);
        label.setPadding(new Insets(5));
        estadoCita(label, estadoCita);
    }

//    private void crearCita(Label label) {
//
//        if (citaDto != null) {
//            label.setText("");
//            label.setGraphic(null);
////            String estadoCita = "Estado: " + estadoCita() + "\n";
////            String nombrePac = "Paciente: " + citaDto.getNombreString() + "\n";
////            String usuarioRegistra = "Usuario que registra: " + citaDto.getCitUsuarioRegistra() + "\n";
////            String motivo = (citaDto.getCitMotivo() != null) ? "Motivo: " + citaDto.getCitMotivo() + "\n" : "Motivo: No indica\n";
////            String telefono = "Telefono: " + citaDto.getCliPacienteDto().getPacTelefono() + "\n";
////            String correo = "Correo: " + citaDto.getCliPacienteDto().getPacCorreo() + "\n";
//
//            label.setPrefSize(290, 130);
//            label.setPadding(new Insets(5));
////            estadoCita(label,);
////            label.setText(estadoCita + nombrePac + usuarioRegistra + motivo + telefono + correo);
//            label.setText(citaDto.citaLabel());
//        }
//    }
    private void estadoCita(Label label, String estadoCita) {
        label.getStyleClass().clear();
        switch (estadoCita) {
            case "P" ->
                label.getStyleClass().add("label-cita-programada");
            case "A" ->
                label.getStyleClass().add("label-cita-atendida");
            case "U" ->
                label.getStyleClass().add("label-cita-ausente");
        }
    }

    private LocalDateTime calcularHora(int fila, int columna) {
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
        //System.out.println(fechaHora);

        return fechaHora;
    }

    public void bindBuscar() {
        P08_MantenimientoMedicosViewController buscadorRegistroController = (P08_MantenimientoMedicosViewController) FlowController.getInstance().getController("P08_MantenimientoMedicosView");
        medicoDto = (CliMedicoDto) buscadorRegistroController.getSeleccionado();
        if (medicoDto != null) {
            btnBuscarMedico.setText(medicoDto.getCliUsuarioDto().nombreDosApellidos());
        }
    }

    //verificar si se ocupa
    public void cargarCita(CliCitaDto cita, CliAgendaDto agenda, CliMedicoDto medico) {
        citaDto = cita;
        agendaDto = agenda;
        medicoDto = medico;
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
