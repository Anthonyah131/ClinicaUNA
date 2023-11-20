package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliCorreodestinoDto;
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
public class CliCorreodestinoService {
    public Respuesta getCorreodestino(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliCorreodestinoController/correodestino", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliCorreodestinoDto correodestinoDto = (CliCorreodestinoDto) request.readEntity(CliCorreodestinoDto.class);
            return new Respuesta(true, "", "", "Correodestino", correodestinoDto);
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoService.class.getName()).log(Level.SEVERE, "Error obteniendo el Correodestino [" + id + "]", ex);
            return new Respuesta(false, "key.errorObCorreo", "getCorreodestino " + ex.getMessage());
        }
    }

    public Respuesta getCorreodestinos() {
        try {
            Request request = new Request("CliCorreodestinoController/correodestinos");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliCorreodestinoDto> correodestinos = (List<CliCorreodestinoDto>) request.readEntity(new GenericType<List<CliCorreodestinoDto>>() {
            });
            return new Respuesta(true, "", "", "Correodestinos", correodestinos);
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoService.class.getName()).log(Level.SEVERE, "Error obteniendo Correodestinos.", ex);
            return new Respuesta(false, "key.errorObCorreo", "getCorreodestinos " + ex.getMessage());
        }
    }

    public Respuesta guardarCorreodestino(CliCorreodestinoDto correodestino) {
        try {
            Request request = new Request("CliCorreodestinoController/correodestino");
            request.post(correodestino);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliCorreodestinoDto correodestinoDto = (CliCorreodestinoDto) request.readEntity(CliCorreodestinoDto.class);
            return new Respuesta(true, "", "", "Correodestino", correodestinoDto);
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoService.class.getName()).log(Level.SEVERE, "Error guardando el Correodestino.", ex);
            return new Respuesta(false, "key.errorSaveCorreo", "guardarCorreodestino " + ex.getMessage());
        }
    }

    public Respuesta eliminarCorreodestino(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliCorreodestinoController/eliminarCorreodestino", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoService.class.getName()).log(Level.SEVERE, "Error eliminando el Correodestino.", ex);
            return new Respuesta(false, "key.errorDelCorreo", "eliminarCorreodestino " + ex.getMessage());
        }
    }
}
