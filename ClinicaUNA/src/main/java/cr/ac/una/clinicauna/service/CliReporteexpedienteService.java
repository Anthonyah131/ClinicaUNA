/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliReporteexpedienteDto;
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
public class CliReporteexpedienteService {
    public Respuesta getReporteExpediente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteexpedienteController/ReporteExpediente", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteexpedienteDto reporteExpedienteDto = (CliReporteexpedienteDto) request.readEntity(CliReporteexpedienteDto.class);
            return new Respuesta(true, "", "", "ReporteExpediente", reporteExpedienteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error obteniendo el ReporteExpediente [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el ReporteExpediente.", "getReporteExpediente " + ex.getMessage());
        }
    }

    public Respuesta getReportesExpediente() {
        try {
            Request request = new Request("CliReporteexpedienteController/ReportesExpediente");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliReporteexpedienteDto> reportesExpediente = (List<CliReporteexpedienteDto>) request.readEntity(new GenericType<List<CliReporteexpedienteDto>>() {
            });
            return new Respuesta(true, "", "", "ReportesExpediente", reportesExpediente);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error obteniendo ReportesExpediente.", ex);
            return new Respuesta(false, "Error obteniendo ReportesExpediente.", "getReportesExpediente " + ex.getMessage());
        }
    }

    public Respuesta guardarReporteExpediente(CliReporteexpedienteDto reporte) {
        try {
            Request request = new Request("CliReporteexpedienteController/ReporteExpediente");
            request.post(reporte);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteexpedienteDto reporteExpedienteDto = (CliReporteexpedienteDto) request.readEntity(CliReporteexpedienteDto.class);
            return new Respuesta(true, "", "", "ReporteExpediente", reporteExpedienteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error guardando el ReporteExpediente.", ex);
            return new Respuesta(false, "Error guardando el ReporteExpediente.", "guardarReporteExpediente " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporteExpediente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteexpedienteController/eliminarReporteExpediente", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error eliminando el ReporteExpediente.", ex);
            return new Respuesta(false, "Error eliminando el ReporteExpediente.", "eliminarReporteExpediente " + ex.getMessage());
        }
    }
}
