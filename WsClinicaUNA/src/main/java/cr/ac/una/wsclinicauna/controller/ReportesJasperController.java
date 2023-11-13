/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.service.ReportesJasperService;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import cr.ac.una.wsclinicauna.util.Secure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArauzKJ
 */
@Path("/ReportesJasperController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "ReportesJasper", description = "Operaciones sobre ReportesJasper")
@Secure
public class ReportesJasperController {

    @EJB
    ReportesJasperService reportesJasperService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response
                .ok("ping Jakarta EE")
                .build();
    }

    @GET
    @Path("/agendaMedico/{id}/{fechainicial}/{fechafin}")
    public Response agendaMedico(@PathParam("id") Long id, @PathParam("fechainicial") String fechainicial, @PathParam("fechafin") String fechafin) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechainicial2 = LocalDate.parse(fechainicial, formatter);
            LocalDate fechafin2 = LocalDate.parse(fechafin, formatter);
            Respuesta res = reportesJasperService.getAngendaReport(id, fechainicial2, fechafin2);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            //Aun no se que devolver

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(ReportesJasperController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el reporte").build();
        }
    }
    
    
    @GET
    @Path("/expedientePaciente/{id}")
    public Response expedientePaciente(@PathParam("id") Long id) {
        try {
            Respuesta res = reportesJasperService.getExpedienteReport(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            //Aun no se que devolver

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(ReportesJasperController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el reporte").build();
        }
    }

}
