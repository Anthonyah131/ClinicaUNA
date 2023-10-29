/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliParametrosDto;
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
public class CliParametrosService {
    public Respuesta getParametro(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliParametrosController/parametro", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliParametrosDto parametrosDto = (CliParametrosDto) request.readEntity(CliParametrosDto.class);
            return new Respuesta(true, "", "", "Parametro", parametrosDto);
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosService.class.getName()).log(Level.SEVERE, "Error obteniendo el parametros [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el parametros.", "getParametros " + ex.getMessage());
        }
    }

    public Respuesta getParametros() {
        try {
            Request request = new Request("CliParametrosController/parametros");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliParametrosDto> parametros = (List<CliParametrosDto>) request.readEntity(new GenericType<List<CliParametrosDto>>() {
            });
            return new Respuesta(true, "", "", "Parametros", parametros);
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosService.class.getName()).log(Level.SEVERE, "Error obteniendo parametros.", ex);
            return new Respuesta(false, "Error obteniendo parametros.", "getParametros " + ex.getMessage());
        }
    }

    public Respuesta guardarParametro(CliParametrosDto parametros) {
        try {
            Request request = new Request("CliParametrosController/parametro");
            request.post(parametros);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliParametrosDto parametrosDto = (CliParametrosDto) request.readEntity(CliParametrosDto.class);
            return new Respuesta(true, "", "", "Parametro", parametrosDto);
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosService.class.getName()).log(Level.SEVERE, "Error guardando el parametros.", ex);
            return new Respuesta(false, "Error guardando el parametros.", "guardarParametros " + ex.getMessage());
        }
    }

    public Respuesta eliminarParametro(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliParametrosController/eliminarParametro", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosService.class.getName()).log(Level.SEVERE, "Error eliminando el parametros.", ex);
            return new Respuesta(false, "Error eliminando el parametros.", "eliminarParametros " + ex.getMessage());
        }
    }
}
