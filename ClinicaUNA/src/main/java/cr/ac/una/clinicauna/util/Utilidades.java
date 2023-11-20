package cr.ac.una.clinicauna.util;

import java.time.Duration;
import java.time.LocalTime;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Luvara
 */
public class Utilidades {

    public static int[] calcularJornada(LocalTime horaEntrada, LocalTime horaSalida) {

        LocalTime hora11pm = LocalTime.of(23, 0);
        Duration duration;
        Duration duration1;
        Duration duration2;

        int horas;
        int minutos;

        if (horaSalida.isBefore(horaEntrada)) {
            // La salida es al día siguiente, calculamos la diferencia sumando las duraciones
            duration2 = Duration.between(horaEntrada, hora11pm).abs();
            duration1 = Duration.between(LocalTime.MIDNIGHT, horaSalida).abs();
            duration = duration1.plus(duration2);
            horas = duration.toHoursPart() + 1;
        } else {
            // La salida es en el mismo día
            duration = Duration.between(horaEntrada, horaSalida);
            horas = duration.toHoursPart();
        }

        minutos = duration.toMinutesPart();
        int[] resultados = new int[]{horas, minutos};

        return resultados;
    }

    public static int calcularCitasJornada(int cantHoras, int cantMinutos, int citasHora) {

        int cantCitas = cantHoras * citasHora;
        int minutos = cantMinutos;

        int duraCita = 1;
        switch (citasHora) {
            case 2 ->
                duraCita = 30;
            case 3 ->
                duraCita = 20;
            case 4 ->
                duraCita = 15;
        }

        if (minutos != 0 && minutos >= duraCita) {
            minutos /= duraCita;
            cantCitas += minutos;
        }

        return cantCitas;
    }

    public static void ajustarAnchorVentana(AnchorPane root) {
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
    }
}
