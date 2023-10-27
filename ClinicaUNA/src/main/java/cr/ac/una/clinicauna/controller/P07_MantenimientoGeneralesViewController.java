package cr.ac.una.clinicauna.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import cr.ac.una.clinicauna.model.CliParametrosDto;
import cr.ac.una.clinicauna.service.CliParametrosService;
import cr.ac.una.clinicauna.util.FlowController;
import cr.ac.una.clinicauna.util.Formato;
import cr.ac.una.clinicauna.util.Respuesta;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.compress.utils.IOUtils;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P07_MantenimientoGeneralesViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnSalir;
    @FXML
    private ImageView imvFotoEmpresa;
    @FXML
    private JFXTextField txfNombre;
    @FXML
    private JFXTextArea txaInformacion;
    @FXML
    private JFXTextField txfCorreo;
    @FXML
    private JFXTextField txfClave;
    @FXML
    private JFXTextField txfPlantilla;
    @FXML
    private MFXButton btnAgregarPlantilla;
    @FXML
    private MFXButton btnGuardar;
    
    CliParametrosDto parametrosDto;
    File file;
    List<Node> requeridos = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txfNombre.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txaInformacion.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txfCorreo.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        txfClave.setTextFormatter(Formato.getInstance().maxLengthFormat(30));
        this.parametrosDto = new CliParametrosDto();
        cargarParametros();
        onActionsBotones();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionBtnSalir(ActionEvent event) {
        FlowController.getInstance().goView("P06_MenuPrincipalView");
    }

    @FXML
    private void onActionBtnAgregarPlantilla(ActionEvent event) {
        //Inicializa el FileChooser y le da un titulo a la nueva ventana
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar archivo");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            txfPlantilla.setText(file.getAbsolutePath());
        }
        try {
            if (file != null) {
                parametrosDto.setParHtml(fileToByte(file));
            }
        } catch (IOException ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionBtnGuardar(ActionEvent event) {
        /*try {
            String invalidos = validarRequeridos();
            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar empleado", getStage(), invalidos);
            } else {
            }
            CliParametrosService parametrosService = new CliParametrosService();
            Respuesta respuesta = parametrosService.guardarParametro(parametrosDto);
            if (!respuesta.getEstado()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Parametros", getStage(), respuesta.getMensaje());
            } else {
                unbindParametro();
                this.parametrosDto = (CliParametrosDto) respuesta.getResultado("Parametro");
                bindParametro(false);
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Parametros", getStage(), "Parametros actualizados correctamente.");
                initialize(null, null);
            }

        } catch (Exception ex) {
            Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, "Error guardando los Parametros.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Parametros", getStage(), "Ocurrio un error guardando los Parametros.");
        }*/
    }

    private void cargarParametros() {
        /*CliParametrosService service = new CliParametrosService();
        Respuesta respuesta = service.getParametros();

        List<CliParametrosDto> tarParametrosDtosList = new ArrayList<>();
        tarParametrosDtosList = (List<CliParametrosDto>) respuesta.getResultado("Parametros");
        if (!tarParametrosDtosList.isEmpty()) {
            if (respuesta.getEstado()) {
                unbindParametro();
                this.parametrosDto = tarParametrosDtosList.get(0);
                imvFotoEmpresa.setImage(byteToImage(this.parametrosDto.getParLogo()));
                bindParametro(false);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar Parametros", getStage(), respuesta.getMensaje());
            }
        } else {
            nuevoParametro();
        }*/
    }

    private void nuevoParametro() {
        unbindParametro();
        this.parametrosDto = new CliParametrosDto();
        bindParametro(true);
    }

    private void bindParametro(Boolean nuevo) {
        txfNombre.textProperty().bindBidirectional(parametrosDto.parNombre);
        txaInformacion.textProperty().bindBidirectional(parametrosDto.parDescripcion);
        txfCorreo.textProperty().bindBidirectional(parametrosDto.parEmail);
        txfClave.textProperty().bindBidirectional(parametrosDto.parClave);
    }

    private void unbindParametro() {
        txfNombre.textProperty().unbindBidirectional(parametrosDto.parNombre);
        txaInformacion.textProperty().unbindBidirectional(parametrosDto.parDescripcion);
        txfCorreo.textProperty().unbindBidirectional(parametrosDto.parEmail);
        txfClave.textProperty().unbindBidirectional(parametrosDto.parClave);
    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && (((JFXTextField) node).getText() == null || ((JFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && (((JFXPasswordField) node).getText() == null || ((JFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    private void onActionsBotones() {
        imvFotoEmpresa.setOnMouseClicked(event -> {

            //Inicializa el FileChooser y le da un titulo a la nueva ventana
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg", "PNG", "*.png", "GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("All Images", "*.*")
            );

            File file = fileChooser.showOpenDialog(null);
            try {
                if (file != null) {
                    this.parametrosDto.setParLogo(fileToByte(file));
                }
            } catch (IOException ex) {
                Logger.getLogger(P03_RegistroViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

            loadImages(imvFotoEmpresa, file);
        });
    }

    //Metodo para cargar imagenes al icono de empresa
    public void loadImages(ImageView imgview, File file_) {
        /*if (file_ != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file_);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                imgview.setImage(image);
            } catch (IOException ex) {
                new Mensaje().show(Alert.AlertType.ERROR, "Imagen", "Error cargando imagen");
            }
        }*/
    }

    private byte[] fileToByte(File file) throws IOException {
        FileInputStream fiStream = new FileInputStream(file.getAbsolutePath());
        byte[] imageInBytes = IOUtils.toByteArray(fiStream);
        return imageInBytes;
    }

    private Image byteToImage(byte[] bytes) {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return new Image(bis);
    }
}