/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliAntecedenteDto;
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
public class CliAntecedenteService {
    public Respuesta getAntecedente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliAntecedenteController/antecedente", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliAntecedenteDto antecedenteDto = (CliAntecedenteDto) request.readEntity(CliAntecedenteDto.class);
            return new Respuesta(true, "", "", "Antecedente", antecedenteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteService.class.getName()).log(Level.SEVERE, "Error obteniendo el antecedente [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el antecedente.", "getAntecedente " + ex.getMessage());
        }
    }

    public Respuesta getAntecedentes() {
        try {
            Request request = new Request("CliAntecedenteController/antecedentes");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliAntecedenteDto> antecedentes = (List<CliAntecedenteDto>) request.readEntity(new GenericType<List<CliAntecedenteDto>>() {
            });
            return new Respuesta(true, "", "", "Antecedentes", antecedentes);
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteService.class.getName()).log(Level.SEVERE, "Error obteniendo antecedentes.", ex);
            return new Respuesta(false, "Error obteniendo antecedentes.", "getAntecedentes " + ex.getMessage());
        }
    }

    public Respuesta guardarAntecedente(CliAntecedenteDto antecedente) {
        try {
            Request request = new Request("CliAntecedenteController/antecedente");
            request.post(antecedente);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliAntecedenteDto antecedenteDto = (CliAntecedenteDto) request.readEntity(CliAntecedenteDto.class);
            return new Respuesta(true, "", "", "Antecedente", antecedenteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteService.class.getName()).log(Level.SEVERE, "Error guardando el antecedente.", ex);
            return new Respuesta(false, "Error guardando el antecedente.", "guardarAntecedente " + ex.getMessage());
        }
    }

    public Respuesta eliminarAntecedente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliAntecedenteController/eliminarAntecedente", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteService.class.getName()).log(Level.SEVERE, "Error eliminando el antecedente.", ex);
            return new Respuesta(false, "Error eliminando el antecedente.", "eliminarAntecedente " + ex.getMessage());
        }
    }
}
