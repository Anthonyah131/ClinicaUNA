/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliReporteagendaDto;
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
public class CliReporteagendaService {

    public Respuesta getReporteAgenda(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteagendaController/ReporteAgenda", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteagendaDto reporteAgendaDto = (CliReporteagendaDto) request.readEntity(CliReporteagendaDto.class);
            return new Respuesta(true, "", "", "ReporteAgenda", reporteAgendaDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error obteniendo el ReporteAgenda [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el ReporteAgenda.", "getReporteAgenda " + ex.getMessage());
        }
    }

    public Respuesta getReportesAgenda() {
        try {
            Request request = new Request("CliReporteagendaController/ReportesAgenda");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliReporteagendaDto> reportesAgenda = (List<CliReporteagendaDto>) request.readEntity(new GenericType<List<CliReporteagendaDto>>() {
            });
            return new Respuesta(true, "", "", "ReportesAgenda", reportesAgenda);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error obteniendo ReportesAgenda.", ex);
            return new Respuesta(false, "Error obteniendo ReportesAgenda.", "getReportesAgenda " + ex.getMessage());
        }
    }

    public Respuesta guardarReporteAgenda(CliReporteagendaDto reporte) {
        try {
            Request request = new Request("CliReporteagendaController/ReporteAgenda");
            request.post(reporte);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteagendaDto reporteAgendaDto = (CliReporteagendaDto) request.readEntity(CliReporteagendaDto.class);
            return new Respuesta(true, "", "", "ReporteAgenda", reporteAgendaDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error guardando el ReporteAgenda.", ex);
            return new Respuesta(false, "Error guardando el ReporteAgenda.", "guardarReporteAgenda " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporteAgenda(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteagendaController/eliminarReporteAgenda", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error eliminando el ReporteAgenda.", ex);
            return new Respuesta(false, "Error eliminando el ReporteAgenda.", "eliminarReporteAgenda " + ex.getMessage());
        }
    }
}
