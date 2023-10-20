/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.model.CliReporteagendaDto;
import cr.ac.una.wsclinicauna.service.CliReporteagendaService;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArauzKJ
 */
@Path("/CliReporteagendaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Reporteagenda", description = "Operaciones sobre Reporteagenda")
public class CliReporteagendaController {

    @EJB
    CliReporteagendaService cliReporteagendaService;

    @GET
    @Path("/parametro/{id}")
    public Response getReporteagenda(@PathParam("id") Long id) {
        try {
            Respuesta res = cliReporteagendaService.getReporteagenda(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliReporteagendaDto cliReporteexpedienteDto = (CliReporteagendaDto) res.getResultado("Reporteagenda");
            return Response.ok(cliReporteexpedienteDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteagendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el parametro").build();//TODO
        }
    }

    @GET
    @Path("/parametros")
    public Response getReporteagendas() {
        try {
            Respuesta res = cliReporteagendaService.getReporteagendas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliReporteagendaDto>>((List<CliReporteagendaDto>) res.getResultado("Reporteagendas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(CliReporteagendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los parametro").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/parametro")
    public Response guardarReporteagenda(CliReporteagendaDto cliReporteagendaDto) {
        try {
            Respuesta res = cliReporteagendaService.guardarReporteagenda(cliReporteagendaDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CliReporteagendaDto) res.getResultado("Reporteagendas")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteagendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el parametro").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarParametro/{id}")
    public Response eliminarReporteagenda(@PathParam("id") Long id) {
        try {
            Respuesta res = cliReporteagendaService.eliminarReporteagenda(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteagendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el parametro").build();//TODO
        }
    }
}
