package cr.ac.una.clinicauna.util;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.util.List;
import javafx.scene.Node;

/**
 *
 * @author Luvara
 */
public class ValidarRequeridos {
        
    public static String validarRequeridos(List<Node> requeridos) {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && (((JFXTextField) node).getText() == null || ((JFXTextField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += ", " + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && (((JFXPasswordField) node).getText() == null || ((JFXPasswordField) node).getText().isBlank())) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += ", " + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += ", " + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXTimePicker && ((JFXTimePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXTimePicker) node).getAccessibleText();
                } else {
                    invalidos += ", " + ((JFXTimePicker) node).getAccessibleText();
                }
                validos = false;
            }else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += ", " + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            String message = invalidos + "].";
            return message;
        }
    }
}
