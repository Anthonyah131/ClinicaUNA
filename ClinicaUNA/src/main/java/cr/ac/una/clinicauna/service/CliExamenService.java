package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliExamenDto;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANTHONY
 */
public class CliExamenService {
    public Respuesta getExamen(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliExamenController/examen", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliExamenDto examenDto = (CliExamenDto) request.readEntity(CliExamenDto.class);
            return new Respuesta(true, "", "", "Examen", examenDto);
        } catch (Exception ex) {
            Logger.getLogger(CliExamenService.class.getName()).log(Level.SEVERE, "Error obteniendo el examen [" + id + "]", ex);
            return new Respuesta(false, "key.errorObExamen", "getExamen " + ex.getMessage());
        }
    }

    public Respuesta getExamens() {
        try {
            Request request = new Request("CliExamenController/examens");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliExamenDto> examens = (List<CliExamenDto>) request.readEntity(new GenericType<List<CliExamenDto>>() {
            });
            return new Respuesta(true, "", "", "Examens", examens);
        } catch (Exception ex) {
            Logger.getLogger(CliExamenService.class.getName()).log(Level.SEVERE, "Error obteniendo examens.", ex);
            return new Respuesta(false, "key.errorObExamen", "getExamens " + ex.getMessage());
        }
    }

    public Respuesta guardarExamen(CliExamenDto examen) {
        try {
            Request request = new Request("CliExamenController/examen");
            request.post(examen);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliExamenDto examenDto = (CliExamenDto) request.readEntity(CliExamenDto.class);
            return new Respuesta(true, "", "", "Examen", examenDto);
        } catch (Exception ex) {
            Logger.getLogger(CliExamenService.class.getName()).log(Level.SEVERE, "Error guardando el examen.", ex);
            return new Respuesta(false, "key.errorSaveExamen", "guardarExamen " + ex.getMessage());
        }
    }

    public Respuesta eliminarExamen(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliExamenController/eliminarExamen", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliExamenService.class.getName()).log(Level.SEVERE, "Error eliminando el examen.", ex);
            return new Respuesta(false, "key.errorDelExamen", "eliminarExamen " + ex.getMessage());
        }
    }
}
