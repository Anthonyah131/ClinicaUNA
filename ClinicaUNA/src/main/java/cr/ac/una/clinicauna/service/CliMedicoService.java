/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliMedicoDto;
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
public class CliMedicoService {
    public Respuesta getMedico(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliMedicoController/medico", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliMedicoDto medicoDto = (CliMedicoDto) request.readEntity(CliMedicoDto.class);
            return new Respuesta(true, "", "", "Medico", medicoDto);
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoService.class.getName()).log(Level.SEVERE, "Error obteniendo el medico [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el medico.", "getMedico " + ex.getMessage());
        }
    }

    public Respuesta getMedicos() {
        try {
            Request request = new Request("CliMedicoController/medicos");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliMedicoDto> medicos = (List<CliMedicoDto>) request.readEntity(new GenericType<List<CliMedicoDto>>() {
            });
            return new Respuesta(true, "", "", "Medicos", medicos);
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoService.class.getName()).log(Level.SEVERE, "Error obteniendo medicos.", ex);
            return new Respuesta(false, "Error obteniendo medicos.", "getMedicos " + ex.getMessage());
        }
    }

    public Respuesta guardarMedico(CliMedicoDto medico) {
        try {
            Request request = new Request("CliMedicoController/medico");
            request.post(medico);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliMedicoDto medicoDto = (CliMedicoDto) request.readEntity(CliMedicoDto.class);
            return new Respuesta(true, "", "", "Medico", medicoDto);
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoService.class.getName()).log(Level.SEVERE, "Error guardando el medico.", ex);
            return new Respuesta(false, "Error guardando el medico.", "guardarMedico " + ex.getMessage());
        }
    }

    public Respuesta eliminarMedico(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliMedicoController/eliminarMedico", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoService.class.getName()).log(Level.SEVERE, "Error eliminando el medico.", ex);
            return new Respuesta(false, "Error eliminando el medico.", "eliminarMedico " + ex.getMessage());
        }
    }
}
