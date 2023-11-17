package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.model.CliAgendaDto;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliMedicoDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.service.CliAgendaService;
import cr.ac.una.clinicauna.service.CliCitaService;
import cr.ac.una.clinicauna.util.AppContext;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Mensaje;
import cr.ac.una.clinicauna.util.Respuesta;
import cr.ac.una.clinicauna.util.SoundUtil;
import cr.ac.una.clinicauna.util.Utilidades;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
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
    private Label lblTotalCitas;
    @FXML
    private Label lblJornada;
    @FXML
    private Label lblCitasLibres;
    @FXML
    private Label lblCitasProgramadas;
    @FXML
    private Label lblHoraEntrada;
    @FXML
    private Label lblHoraSalida;
    @FXML
    private AnchorPane root;

    CliUsuarioDto usuarioDto;
    CliMedicoDto medicoDto;
    CliAgendaDto agendaDto;
    CliCitaDto citaDto;
    CliCitaDto citasVector[];
    List<CliCitaDto> listaCitas;
    Label labelVector[];

    int iniJornada;
    int finJornada;
    int jornada;
    int citasHoras;
    int minutosIniJornada;
    int cantCitasTotales;
    int[] jornadaDoctor;
    int casillasVacias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Utilidades.ajustarAnchorVentana(root);
        iniciarVariables();
        grdCitas.setOnDragOver(event -> {
            if (event.getGestureSource() != grdCitas && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
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
            LocalTime horaEntradaTime;
            LocalTime horaSalidaTime;

            if (agendaDto.getAgeId() != null) {
                horaEntradaTime = agendaDto.getAgeEntradaTime();
                horaSalidaTime = agendaDto.getAgeSalidaTime();
            } else {
                horaEntradaTime = medicoDto.getMedFiniTime();
                horaSalidaTime = medicoDto.getMedFfinTime();
            }

            iniJornada = horaEntradaTime.getHour();
            finJornada = horaSalidaTime.getMinute();
            minutosIniJornada = horaEntradaTime.getMinute();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

            String horaEntrada = horaEntradaTime.format(formatter);
            String horaSalida = horaSalidaTime.format(formatter);

            lblHoraEntrada.setText(horaEntrada);
            lblHoraSalida.setText(horaSalida);

            //Obtener la cantidad de horas y minutos que va a trabajar el doctor y asi obtener la jornada
            jornadaDoctor = Utilidades.calcularJornada(horaEntradaTime, horaSalidaTime);
            jornada = jornadaDoctor[0];
            citasHoras = medicoDto.getMedEspaciosxhora().intValue();
            lblJornada.setText(jornadaDoctor[0] + "h " + jornadaDoctor[1] + "m");

            //obtener el total de citas que puede atender el doctor para inicializar vectores
            cantCitasTotales = Utilidades.calcularCitasJornada(jornadaDoctor[0], jornadaDoctor[1], citasHoras);
            lblTotalCitas.setText(cantCitasTotales + ".");

            //Establecer tamano vector de citas
            labelVector = new Label[cantCitasTotales];
            citasVector = new CliCitaDto[cantCitasTotales];

            // llena el gridpane con labeles
            llenarGridPane();
            //Recorre la lista de citas y las pasa a la matriz
            pasarListaCitasAVector();
            comprobarEspaciosCitas();
            crearCita();
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
                agendaDto.setAgeEntradaTime(medicoDto.getMedFiniTime());
                agendaDto.setAgeSalidaTime(medicoDto.getMedFfinTime());
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
        //limpiar el vector de citas
        for (int i = 0; i < citasVector.length; i++) {
            citasVector[i] = null; // O cualquier otro valor adecuado para limpiar los datos existentes
        }
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
        int posVector = fila * citasHoras + col - casillasVacias;
        // Pasar de pos de matriz a pos de vector
        return posVector;
    }

    private void comprobarEspaciosCitas() { // arreglar
        int citasDisponibles = 0;
        int citasProgramadas = 0;
        for (int i = 0; i < citasVector.length; i++) {
            if (citasVector[i] != null) {
                CliCitaDto cita = citasVector[i];
                int espaciosCitas = Math.toIntExact(cita.getCliCantespacios());
                if (espaciosCitas > 1) {
                    for (int j = 0; j < espaciosCitas; j++) {
                        citasVector[i] = cita;
                        i++;
//                        citasProgramadas++;
                    }
//                } else {
//                    citasProgramadas++;
                }
                citasProgramadas++;
            }
            citasDisponibles++;
        }
        lblCitasProgramadas.setText(citasProgramadas + ".");
        lblCitasLibres.setText(citasDisponibles + ".");
    }

    private void llenarGridPane() {
        int numRows = jornada + 1;
        int numCols = citasHoras + 1;
        int horaInicio = iniJornada;

        if (finJornada > 0) {
            numRows++;
        }

        int citasHorasEncabezado = 60 / citasHoras;
        int contadorLabel = 0;
        casillasVacias = 0;

        grdCitas.getChildren().clear();
        //grdCitas = new GridPane();

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
                if (i == 1 && citasHorasEncabezado * (j - 1) < minutosIniJornada) {
                    casillasVacias++;
                    continue;
                }

                if (i > 0 && j > 0 && contadorLabel < cantCitasTotales) {
                    Label label = new Label();

                    label.setOnMouseClicked(event -> {
                        FlowController.getInstance().delete("P11_NuevaCitaView");
                        int rowIndex = GridPane.getRowIndex(label);
                        int colIndex = GridPane.getColumnIndex(label);

                        int posVector = (rowIndex - 1) * citasHoras + colIndex - 1 - casillasVacias;
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
                            cargarAgenda();
                            pasarListaCitasAVector();
                            comprobarEspaciosCitas();
                            crearCita();
                        }

                        System.out.println("Fila: " + rowIndex + " columna " + colIndex);

                    });
                    label.setOnDragDetected(event -> {
                        Dragboard db = label.startDragAndDrop(TransferMode.MOVE);
                        ClipboardContent content = new ClipboardContent();
                        content.putString(label.getText());
                        db.setContent(content);

                        int colIndex = GridPane.getColumnIndex(label);
                        int rowIndex = GridPane.getRowIndex(label);
                        posDragDrop = (rowIndex - 1) * citasHoras + colIndex - 1 - casillasVacias;

                        if (citasVector[posDragDrop] != null) {
                            citaDto = citasVector[posDragDrop];
                            posDragDrop = citaAPosVector(citaDto.getCitFechaHora());
                            citaDto = citasVector[posDragDrop];
                        }

                        System.out.println("Inicio" + rowIndex + " - " + colIndex);
                        event.consume();
                    });
                    label.setOnDragOver(event -> {
                        if (event.getGestureSource() != label && event.getDragboard().hasString()) {
                            event.acceptTransferModes(TransferMode.MOVE);
                        }
                        event.consume();
                    });
                    label.setOnDragDropped(event -> {
                        Dragboard db = event.getDragboard();
                        boolean success = false;

                        if (db.hasString()) {
                            int colIndex = GridPane.getColumnIndex(label);
                            int rowIndex = GridPane.getRowIndex(label);

                            int posAux = posDragDrop;

                            posDragDrop = (rowIndex - 1) * citasHoras + colIndex - 1 - casillasVacias;

                            if (citaDto.getCliCantespacios() > 1) {
                                if (comprobarEspacios(posDragDrop, citaDto.getCliCantespacios().intValue())) {
                                    citasVector[posAux] = null;
                                    citaDto.setCitFechaHora(calcularHora(rowIndex, colIndex));
                                    citasVector[posDragDrop] = citaDto;
                                    actualizarCita(citaDto);
                                } else {
                                    new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "No hay suficientes campos libres");
                                }
                            } else {
                                citasVector[posAux] = null;
                                citaDto.setCitFechaHora(calcularHora(rowIndex, colIndex));
                                citasVector[posDragDrop] = citaDto;
                                actualizarCita(citaDto);
                            }

                            pasarListaCitasAVector();
                            comprobarEspaciosCitas();
                            crearCita();

                            System.out.println("Fin" + rowIndex + " - " + colIndex);

                            success = true;
                        }

                        event.setDropCompleted(success);
                        event.consume();
                    });

                    labelVector[contadorLabel] = label;
                    contadorLabel++;
                    grdCitas.add(label, j, i);
                }
            }
        }
       grdCitas.setGridLinesVisible(true);
    }

    private void actualizarCita(CliCitaDto cita) {
        try {
            CliCitaService citaService = new CliCitaService();
            Respuesta respuesta = citaService.guardarCita(cita);

            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "key.saveUser", getStage(), respuesta.getMensaje());
            }
        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "key.errorSavingUser");
        }
    }

    private Boolean comprobarEspacios(int posV, int tamano) {
        int indiceVector = posV;
        for (int i = 0; i < tamano; i++) {
            if (indiceVector == citasVector.length) {
                return false;
            }
            if (citasVector[indiceVector] != null) {
                return false;
            }
            indiceVector++;
        }
        return true;
    }
    int posDragDrop;

    private void crearCita() {
        try {
            for (int i = 0; i < citasVector.length; i++) {
                if (citasVector[i] != null) {
                    crearLabelCita(labelVector[i], citasVector[i].citaLabel(), citasVector[i].getCitEstado());
                } else {
                    crearLabelAddCita(labelVector[i]);
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Error: El objeto Label es nulo. No se puede invocar getStyleClass().");
            new Mensaje().showModali18n(Alert.AlertType.ERROR, "key.saveUser", getStage(), "Conflicto en la hora de entrada o salida y la duracion de las citas");
            grdCitas.getChildren().clear();
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
        label.setText("");
        label.setGraphic(null);
        label.setText(texto);
        label.setPrefSize(290, 130);
        label.setPadding(new Insets(5));
        estadoCita(label, estadoCita);
    }

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
}