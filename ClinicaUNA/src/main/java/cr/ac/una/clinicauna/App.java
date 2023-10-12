package cr.ac.una.clinicauna;

import cr.ac.una.clinicauna.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle idioma = ResourceBundle.getBundle("/cr/ac/una/clinicauna/resources/i18n/traduccion_es");
        FlowController.getInstance().InitializeFlow(stage, idioma);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/clinicauna/resources/LogoUNArojo.png")));
        stage.setTitle("ClinicaUNA");
        
        FlowController.getInstance().goViewInWindow("P01_LogInView", false);
    }

    public static void main(String[] args) {
        launch();
    }

}
