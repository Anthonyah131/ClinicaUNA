/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliParametroconsultaDto;
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
public class CliParametroconsultaService {

    public Respuesta getParametroconsulta(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliParametroconsultaController/parametroconsulta", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliParametroconsultaDto parametroconsultaDto = (CliParametroconsultaDto) request.readEntity(CliParametroconsultaDto.class);
            return new Respuesta(true, "", "", "Parametroconsulta", parametroconsultaDto);
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaService.class.getName()).log(Level.SEVERE, "Error obteniendo el Parametroconsulta [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Parametroconsulta.", "getParametroconsulta " + ex.getMessage());
        }
    }

    public Respuesta getParametroconsultas() {
        try {
            Request request = new Request("CliParametroconsultaController/parametroconsultas");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliParametroconsultaDto> parametroconsultas = (List<CliParametroconsultaDto>) request.readEntity(new GenericType<List<CliParametroconsultaDto>>() {
            });
            return new Respuesta(true, "", "", "Parametroconsultas", parametroconsultas);
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaService.class.getName()).log(Level.SEVERE, "Error obteniendo Parametroconsultas.", ex);
            return new Respuesta(false, "Error obteniendo Parametroconsultas.", "getParametroconsultas " + ex.getMessage());
        }
    }

    public Respuesta guardarParametroconsulta(CliParametroconsultaDto parametroconsulta) {
        try {
            Request request = new Request("CliParametroconsultaController/parametroconsulta");
            request.post(parametroconsulta);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliParametroconsultaDto parametroconsultaDto = (CliParametroconsultaDto) request.readEntity(CliParametroconsultaDto.class);
            return new Respuesta(true, "", "", "Parametroconsulta", parametroconsultaDto);
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaService.class.getName()).log(Level.SEVERE, "Error guardando el Parametroconsulta.", ex);
            return new Respuesta(false, "Error guardando el Parametroconsulta.", "guardarParametroconsulta " + ex.getMessage());
        }
    }

    public Respuesta eliminarParametroconsulta(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliParametroconsultaController/eliminarParametroconsulta", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaService.class.getName()).log(Level.SEVERE, "Error eliminando el Parametroconsulta.", ex);
            return new Respuesta(false, "Error eliminando el Parametroconsulta.", "eliminarParametroconsulta " + ex.getMessage());
        }
    }
}
