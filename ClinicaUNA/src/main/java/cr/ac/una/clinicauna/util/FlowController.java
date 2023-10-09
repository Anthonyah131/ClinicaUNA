/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.clinicauna.util;

import cr.ac.una.clinicauna.App;
import cr.ac.una.clinicauna.controller.Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.AnchorPane;

public class FlowController {

    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static ResourceBundle idioma;
    private static HashMap<String, FXMLLoader> loaders = new HashMap<>();

    private FlowController() {
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FlowController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FlowController();
                }
            }
        }
    }

    public static FlowController getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void InitializeFlow(Stage stage, ResourceBundle idioma) {
        getInstance();
        this.mainStage = stage;
        this.idioma = idioma;
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = loaders.get(name);
        if (loader == null) {
            synchronized (FlowController.class) {
                if (loader == null) {
                    try {
                        loader = new FXMLLoader(App.class.getResource("view/" + name + ".fxml"), this.idioma);
                        loader.load();
                        loaders.put(name, loader);
                    } catch (Exception ex) {
                        loader = null;
                        java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Creando loader [" + name + "].", ex);
                    }
                }
            }
        }
        return loader;
    }

    public void goMain() {
        try {
            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P01_PrincipalView.fxml"), FlowController.idioma)));
//            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P02_LogInView.fxml"), FlowController.idioma)));
//            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P03_RegistroView.fxml"), FlowController.idioma)));
//            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P07_MantenimientoGeneralesView.fxml"), FlowController.idioma)));
//            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P08_MantenimientoCompetencias.fxml"), FlowController.idioma)));
//            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P09_MantenimientoPuestosView.fxml"), FlowController.idioma)));
//            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P11_RegistroEvaluacionesView.fxml"), FlowController.idioma)));
//             FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("view/P12_AplicarEvaluacionView.fxml"), FlowController.idioma)));

            FlowController.mainStage.show();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }

    public void goMainCliente() {
        try {
            FlowController.mainStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("/cr/ac/una/evaconuna/view/.fxml"), FlowController.idioma)));
            FlowController.mainStage.show();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }

    public void goView(String viewName) {
        goView(viewName, "Center", null);
    }

    public void goView(String viewName, String accion) {
        goView(viewName, "Center", accion);
    }

    public void goView(String viewName, String location, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = FlowController.mainStage;
            controller.setStage(stage);
        }
        switch (location) {
            case "Center" -> {
                AnchorPane anchorP = ((AnchorPane) ((BorderPane) stage.getScene().getRoot()).getCenter());
                anchorP.getChildren().clear();
                anchorP.getChildren().add(loader.getRoot());
            }
            case "Top" -> {
            }
            case "Bottom" -> {
            }
            case "Right" -> {
            }
            case "Left" -> {
            }
            default -> {
            }
        }
    }

    public void goViewInStage(String viewName, Stage stage) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setStage(stage);
        stage.getScene().setRoot(loader.getRoot());
    }

    public void goViewInWindow(String viewName) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/clinicauna/resources/LogoUNArojo.png")));
        stage.setTitle("ClinicaUNA");
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void goViewInWindowModal(String viewName, Stage parentStage, Boolean resizable) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/cr/ac/una/clinicauna/resources/LogoUNArojo.png")));
        stage.setTitle("ClinicaUNA");
        stage.setResizable(resizable);
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentStage);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    public Controller getController(String viewName) {
        return getLoader(viewName).getController();
    }

    public void limpiarLoader(String view) {
        this.loaders.remove(view);
    }

    public ResourceBundle getIdioma() {
        return idioma;
    }

    public static void setIdioma(ResourceBundle idioma) {
        FlowController.idioma = idioma;
    }

    public void initialize() {
        this.loaders.clear();
    }

    public void salir() {
        this.mainStage.close();
    }

    public void delete(String parameter) {
        loaders.remove(parameter);
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public Scene getMainScene() {
        return mainStage.getScene();
    }

}
