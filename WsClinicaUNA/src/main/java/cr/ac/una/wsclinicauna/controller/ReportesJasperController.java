/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.service.ReportesJasperService;
import cr.ac.una.wsclinicauna.util.Secure;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
        reportesJasperService.getAngendaReport(1L);
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
