package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliAtencionDto;
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
public class CliAtencionService {
    public Respuesta getAtencion(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliAtencionController/atencion", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliAtencionDto atencionDto = (CliAtencionDto) request.readEntity(CliAtencionDto.class);
            return new Respuesta(true, "", "", "Atencion", atencionDto);
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionService.class.getName()).log(Level.SEVERE, "Error obteniendo el atencion [" + id + "]", ex);
            return new Respuesta(false, "key.errorObAtencion", "getAtencion " + ex.getMessage());
        }
    }

    public Respuesta getAtencions() {
        try {
            Request request = new Request("CliAtencionController/atencions");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliAtencionDto> atencions = (List<CliAtencionDto>) request.readEntity(new GenericType<List<CliAtencionDto>>() {
            });
            return new Respuesta(true, "", "", "Atencions", atencions);
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionService.class.getName()).log(Level.SEVERE, "Error obteniendo atencions.", ex);
            return new Respuesta(false, "key.errorObAtencion", "getAtencions " + ex.getMessage());
        }
    }

    public Respuesta guardarAtencion(CliAtencionDto atencion) {
        try {
            Request request = new Request("CliAtencionController/atencion");
            request.post(atencion);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliAtencionDto atencionDto = (CliAtencionDto) request.readEntity(CliAtencionDto.class);
            return new Respuesta(true, "", "", "Atencion", atencionDto);
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionService.class.getName()).log(Level.SEVERE, "Error guardando el atencion.", ex);
            return new Respuesta(false, "key.errorSaveAtencion", "guardarAtencion " + ex.getMessage());
        }
    }

    public Respuesta eliminarAtencion(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliAtencionController/eliminarAtencion", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionService.class.getName()).log(Level.SEVERE, "Error eliminando el atencion.", ex);
            return new Respuesta(false, "key.errorDelAtencion", "eliminarAtencion " + ex.getMessage());
        }
    }
}
