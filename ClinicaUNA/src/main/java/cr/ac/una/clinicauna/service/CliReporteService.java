/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliReporteDto;
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
public class CliReporteService {

    public Respuesta generarInformeExcelDesdeConsultaSQL(String consulta) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("consulta", consulta);
            Request request = new Request("CliReporteController/generarReporte", "{consulta}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliReporteService.class.getName()).log(Level.SEVERE, "Error obteniendo el Reporte", ex);
            return new Respuesta(false, "Error obteniendo el Reporte.", "getReporte " + ex.getMessage());
        }
    }
    
    public Respuesta getReporte(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteController/reporte", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteDto reporteDto = (CliReporteDto) request.readEntity(CliReporteDto.class);
            return new Respuesta(true, "", "", "Reporte", reporteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteService.class.getName()).log(Level.SEVERE, "Error obteniendo el Reporte [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el Reporte.", "getReporte " + ex.getMessage());
        }
    }

    public Respuesta getReportes(String nombre) {
        try {
            Request request = new Request("CliReporteController/reportes");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliReporteDto> reportes = (List<CliReporteDto>) request.readEntity(new GenericType<List<CliReporteDto>>() {
            });
            return new Respuesta(true, "", "", "Reportes", reportes);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteService.class.getName()).log(Level.SEVERE, "Error obteniendo Reportes.", ex);
            return new Respuesta(false, "Error obteniendo Reportes.", "getReportes " + ex.getMessage());
        }
    }

    public Respuesta guardarReporte(CliReporteDto reporte) {
        try {
            Request request = new Request("CliReporteController/reporte");
            request.post(reporte);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteDto reporteDto = (CliReporteDto) request.readEntity(CliReporteDto.class);
            return new Respuesta(true, "", "", "Reporte", reporteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteService.class.getName()).log(Level.SEVERE, "Error guardando el Reporte.", ex);
            return new Respuesta(false, "Error guardando el Reporte.", "guardarReporte " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporte(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteController/eliminarReporte", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliReporteService.class.getName()).log(Level.SEVERE, "Error eliminando el Reporte.", ex);
            return new Respuesta(false, "Error eliminando el Reporte.", "eliminarReporte " + ex.getMessage());
        }
    }
}
