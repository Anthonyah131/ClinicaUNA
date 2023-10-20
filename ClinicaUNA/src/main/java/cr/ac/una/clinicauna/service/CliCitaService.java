/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliCitaDto;
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
public class CliCitaService {
    public Respuesta getCita(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliCitaController/cita", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliCitaDto citaDto = (CliCitaDto) request.readEntity(CliCitaDto.class);
            return new Respuesta(true, "", "", "Cita", citaDto);
        } catch (Exception ex) {
            Logger.getLogger(CliCitaService.class.getName()).log(Level.SEVERE, "Error obteniendo el cita [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el cita.", "getCita " + ex.getMessage());
        }
    }

    public Respuesta getCitas() {
        try {
            Request request = new Request("CliCitaController/citas");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliCitaDto> citas = (List<CliCitaDto>) request.readEntity(new GenericType<List<CliCitaDto>>() {
            });
            return new Respuesta(true, "", "", "Citas", citas);
        } catch (Exception ex) {
            Logger.getLogger(CliCitaService.class.getName()).log(Level.SEVERE, "Error obteniendo citas.", ex);
            return new Respuesta(false, "Error obteniendo citas.", "getCitas " + ex.getMessage());
        }
    }

    public Respuesta guardarCita(CliCitaDto cita) {
        try {
            Request request = new Request("CliCitaController/cita");
            request.post(cita);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliCitaDto citaDto = (CliCitaDto) request.readEntity(CliCitaDto.class);
            return new Respuesta(true, "", "", "Cita", citaDto);
        } catch (Exception ex) {
            Logger.getLogger(CliCitaService.class.getName()).log(Level.SEVERE, "Error guardando el cita.", ex);
            return new Respuesta(false, "Error guardando el cita.", "guardarCita " + ex.getMessage());
        }
    }

    public Respuesta eliminarCita(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliCitaController/eliminarCita", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliCitaService.class.getName()).log(Level.SEVERE, "Error eliminando el cita.", ex);
            return new Respuesta(false, "Error eliminando el cita.", "eliminarCita " + ex.getMessage());
        }
    }
}
