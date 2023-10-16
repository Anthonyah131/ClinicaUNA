package cr.ac.una.clinicauna.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P05_CambioClaveViewController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAceptar;
    @FXML
    private MFXTextField txfClave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
     }

    @FXML
    private void onActionBtnAceptar(ActionEvent event) {
    }
    
}
