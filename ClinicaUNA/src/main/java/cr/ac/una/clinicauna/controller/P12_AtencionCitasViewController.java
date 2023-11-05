package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXDatePicker;
import cr.ac.una.clinicauna.model.CliAgendaDto;
import cr.ac.una.clinicauna.model.CliCitaDto;
import cr.ac.una.clinicauna.model.CliUsuarioDto;
import cr.ac.una.clinicauna.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P12_AtencionCitasViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnSalir;

    @FXML
    private JFXDatePicker dtpFecha;
    @FXML
    private TableView<CliCitaDto> tbvCitas;
    @FXML
    private MFXButton btnIrExpediente;
    @FXML
    private Label lblUserName;

    CliUsuarioDto usuarioDto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDto = (CliUsuarioDto) AppContext.getInstance().get("Usuario");
        lblUserName.setText(usuarioDto.getNombreApellidos());
        fillTableView();
        // TODO
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
    }

    @FXML
    private void onActionBtnIrExpediente(ActionEvent event) {
    }

    public void fillTableView() {
        tbvCitas.getItems().clear();

//        TableColumn<CliUsuarioDto, String> tbcId = new TableColumn<>("Id");
//        tbcId.setPrefWidth(30);
//        tbcId.setCellValueFactory(cd -> cd.getValue().usuId);
        TableColumn<CliCitaDto, String> tbcFecha = new TableColumn<>(/*resourceBundle.getString("key.identification")*/"Fecha");
        tbcFecha.setPrefWidth(100);
        tbcFecha.setCellValueFactory(cd -> cd.getValue().citFechaHora.asString());

        TableColumn<CliCitaDto, String> tbcNombre = new TableColumn<>(/*resourceBundle.getString("key.name")*/"Nombre paciente");
        tbcNombre.setPrefWidth(120);
        tbcNombre.setCellValueFactory(cd -> cd.getValue().cliPacienteDto.pacNombreCompleto);

        TableColumn<CliCitaDto, String> tbcMotivo = new TableColumn<>(/*resourceBundle.getString("key.papellido")*/"Motivo");
        tbcMotivo.setPrefWidth(130);
        tbcMotivo.setCellValueFactory(cd -> cd.getValue().citMotivo);

        TableColumn<CliCitaDto, String> tbcHora = new TableColumn<>(/*resourceBundle.getString("key.usertype")*/"Hora");
        tbcHora.setPrefWidth(130);
        tbcHora.setCellValueFactory(cd -> {
            LocalDateTime fecha = cd.getValue().getCitFechaHora();
            int hora = fecha.getHour();
            int minuto = fecha.getMinute();

            String horaFormateada;

            String aux = (hora < 12) ? " am." : " pm.";
            String minutes = (minuto == 0) ? ":0" : ":";
            int hora12 = (hora <= 12) ? hora : hora - 12;

            horaFormateada = (hora12 < 10) ? "0" : "";
            horaFormateada += hora12 + minutes + minuto + aux;

//            try {
//                SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//                Date fechaHora = formatoEntrada.parse(fecha);
//
//                // Formatear la hora en el formato deseado (08:30 am/pm)
//                SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
//                horaFormateada = formatoHora.format(fechaHora);
//
//                // Formatear la fecha en el formato deseado (día mes año)
//                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MMMM yyyy");
//                String fechaFormateada = formatoFecha.format(fechaHora);
//
//                System.out.println("Hora: " + horaFormateada);
//                System.out.println("Fecha: " + fechaFormateada);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            return new SimpleStringProperty(horaFormateada);
        });

        tbvCitas.getColumns().addAll(/*tbcHora,*/tbcFecha, tbcNombre, tbcMotivo/*, tbcTipoUser*/);
        tbvCitas.refresh();
    }

}
