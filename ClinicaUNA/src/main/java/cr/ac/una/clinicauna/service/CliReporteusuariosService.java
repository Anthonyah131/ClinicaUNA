/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliReporteusuariosDto;
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
public class CliReporteusuariosService {
    public Respuesta getReporteUsuarios(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteusuariosController/ReporteUsuarios", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteusuariosDto reporteUsuariosDto = (CliReporteusuariosDto) request.readEntity(CliReporteusuariosDto.class);
            return new Respuesta(true, "", "", "ReporteUsuarios", reporteUsuariosDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error obteniendo el ReporteUsuarios [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el ReporteUsuarios.", "getReporteUsuarios " + ex.getMessage());
        }
    }

    public Respuesta getReportesUsuarios() {
        try {
            Request request = new Request("CliReporteusuariosController/ReportesUsuarios");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliReporteusuariosDto> reportesUsuarios = (List<CliReporteusuariosDto>) request.readEntity(new GenericType<List<CliReporteusuariosDto>>() {
            });
            return new Respuesta(true, "", "", "ReportesUsuarios", reportesUsuarios);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error obteniendo ReportesUsuarios.", ex);
            return new Respuesta(false, "Error obteniendo ReportesUsuarios.", "getReportesUsuarios " + ex.getMessage());
        }
    }

    public Respuesta guardarReporteUsuarios(CliReporteusuariosDto usuario) {
        try {
            Request request = new Request("CliReporteusuariosController/ReporteUsuarios");
            request.post(usuario);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliReporteusuariosDto reporteUsuariosDto = (CliReporteusuariosDto) request.readEntity(CliReporteusuariosDto.class);
            return new Respuesta(true, "", "", "ReporteUsuarios", reporteUsuariosDto);
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error guardando el ReporteUsuarios.", ex);
            return new Respuesta(false, "Error guardando el ReporteUsuarios.", "guardarReporteUsuarios " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporteUsuarios(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliReporteusuariosController/eliminarReporteUsuarios", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosService.class.getName()).log(Level.SEVERE, "Error eliminando el ReporteUsuarios.", ex);
            return new Respuesta(false, "Error eliminando el ReporteUsuarios.", "eliminarReporteUsuarios " + ex.getMessage());
        }
    }
}
