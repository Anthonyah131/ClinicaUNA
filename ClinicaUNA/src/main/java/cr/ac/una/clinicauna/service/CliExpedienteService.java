/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliExpedienteDto;
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
public class CliExpedienteService {
    public Respuesta getExpediente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliExpedienteController/expediente", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliExpedienteDto expedienteDto = (CliExpedienteDto) request.readEntity(CliExpedienteDto.class);
            return new Respuesta(true, "", "", "Expediente", expedienteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteService.class.getName()).log(Level.SEVERE, "Error obteniendo el expediente [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el expediente.", "getExpediente " + ex.getMessage());
        }
    }

    public Respuesta getExpedientes() {
        try {
            Request request = new Request("CliExpedienteController/expedientes");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliExpedienteDto> expedientes = (List<CliExpedienteDto>) request.readEntity(new GenericType<List<CliExpedienteDto>>() {
            });
            return new Respuesta(true, "", "", "Expedientes", expedientes);
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteService.class.getName()).log(Level.SEVERE, "Error obteniendo expedientes.", ex);
            return new Respuesta(false, "Error obteniendo expedientes.", "getExpedientes " + ex.getMessage());
        }
    }

    public Respuesta guardarExpediente(CliExpedienteDto expediente) {
        try {
            Request request = new Request("CliExpedienteController/expediente");
            request.post(expediente);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliExpedienteDto expedienteDto = (CliExpedienteDto) request.readEntity(CliExpedienteDto.class);
            return new Respuesta(true, "", "", "Expediente", expedienteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteService.class.getName()).log(Level.SEVERE, "Error guardando el expediente.", ex);
            return new Respuesta(false, "Error guardando el expediente.", "guardarExpediente " + ex.getMessage());
        }
    }

    public Respuesta eliminarExpediente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliExpedienteController/eliminarExpediente", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteService.class.getName()).log(Level.SEVERE, "Error eliminando el expediente.", ex);
            return new Respuesta(false, "Error eliminando el expediente.", "eliminarExpediente " + ex.getMessage());
        }
    }
}
