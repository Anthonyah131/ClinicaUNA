package cr.ac.una.clinicauna;

import cr.ac.una.clinicauna.util.FlowController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/clinicauna/resources/LogoUNArojo.png")));
        stage.setTitle("ClinicaUNA");
        FlowController.getInstance().goViewInWindow("P01_LogInView");
    }

    public static void main(String[] args) {
        launch();
    }

}
