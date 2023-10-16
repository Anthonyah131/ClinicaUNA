package cr.ac.una.clinicauna.controller;

import cr.ac.una.clinicauna.util.FlowController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Luvara
 */
public class P02_PrincipalViewController extends Controller implements Initializable {

    @FXML
    private BorderPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Stage stage1 = FlowController.getInstance().getMainStage();
        stage1.setOnShown(event -> {
            // Luego de que la escena se muestre, llamar la siguiente pantalla
           FlowController.getInstance().goView("P06_MenuPrincipalView");
        });
    }

    @Override
    public void initialize() {
    }
}