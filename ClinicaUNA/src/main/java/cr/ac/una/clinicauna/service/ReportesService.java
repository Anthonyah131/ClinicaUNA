/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ArauzKJ
 */
public class ReportesService {

    public Respuesta getAngendaReport(Long id, String fechainicial, String fechafin) {
        try {

            Map<String, Object> variables = new HashMap<>();
            variables.put("id", id);
            variables.put("fechainicial", fechainicial);
            variables.put("fechafin", fechafin);

            Request request = new Request("ReportesJasperController/agendaMedico", "/{id}/{fechainicial}/{fechafin}", variables);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            byte[] doc = (byte[]) request.readEntity(byte[].class);
            if (doc == null) {
                return new Respuesta(false, "Error al traer el reporte.", "getAngendaReport ");
            }
            JasperPrint jp = toJasperPrint(doc);
            return new Respuesta(true, "", "", "Reporte", jp);

        } catch (Exception ex) {
            Logger.getLogger(ReportesService.class.getName()).log(Level.SEVERE, "Error al generar el Reporte.", ex);
            return new Respuesta(false, "Error generando el Reporte", "getAngendaReport " + ex.getMessage());
        }
    }

    public Respuesta getExpedienteReport(Long pacId) {
        try {

            Map<String, Object> variables = new HashMap<>();
            variables.put("id", pacId);

            Request request = new Request("ReportesJasperController/expedientePaciente", "/{id}", variables);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            byte[] doc = (byte[]) request.readEntity(byte[].class);
            if (doc == null) {
                return new Respuesta(false, "Error al traer el reporte.", "getExpedienteReport ");
            }
            JasperPrint jp = toJasperPrint(doc);
            return new Respuesta(true, "", "", "Reporte", jp);
        } catch (Exception ex) {
            Logger.getLogger(ReportesService.class.getName()).log(Level.SEVERE, "Error al generar el Reporte.", ex);
            return new Respuesta(false, "Error generando el Reporte", "getAngendaReport " + ex.getMessage());
        }
    }

    public Respuesta getRendimientoMedicos(String fechaInicial, String fechaFin) {
        try {

            Map<String, Object> variables = new HashMap<>();
            variables.put("fechaInicial", fechaInicial);
            variables.put("fechaFin", fechaFin);

            Request request = new Request("ReportesJasperController/rendimientoMedicos", "/{fechaInicial}/{fechaFin}", variables);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            byte[] doc = (byte[]) request.readEntity(byte[].class);
            if (doc == null) {
                return new Respuesta(false, "Error al traer el reporte.", "getAngendaReport ");
            }
            JasperPrint jp = toJasperPrint(doc);
            return new Respuesta(true, "", "", "Reporte", jp);
        } catch (Exception ex) {
            Logger.getLogger(ReportesService.class.getName()).log(Level.SEVERE, "Error al generar el Reporte.", ex);
            return new Respuesta(false, "Error generando el Reporte", "getAngendaReport " + ex.getMessage());
        }
    }

    private JasperPrint toJasperPrint(byte[] b) {
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(b);
            ObjectInputStream ob = new ObjectInputStream(bis);
            return (JasperPrint) ob.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ReportesService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
