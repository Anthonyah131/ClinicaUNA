/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliReporteexpedienteDto;
import cr.ac.una.wsclinicauna.service.CliReporteexpedienteService;
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
@Path("/CliReporteexpedienteController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Reporteexpediente", description = "Operaciones sobre Reporteexpediente")
public class CliReporteexpedienteController {

    @EJB
    CliReporteexpedienteService cliReporteexpedienteService;

    @GET
    @Path("/ReporteExpediente/{id}")
    public Response getReporteexpediente(@PathParam("id") Long id) {
        try {
            Respuesta res = cliReporteexpedienteService.getReporteexpediente(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliReporteexpedienteDto cliReporteexpedienteDto = (CliReporteexpedienteDto) res.getResultado("Reporteexpediente");
            return Response.ok(cliReporteexpedienteDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteexpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el parametro").build();//TODO
        }
    }

    @GET
    @Path("/ReporteExpediente")
    public Response getReporteexpedientes() {
        try {
            Respuesta res = cliReporteexpedienteService.getReporteexpedientes();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliReporteexpedienteDto>>((List<CliReporteexpedienteDto>) res.getResultado("Reporteexpedientes")) {
            }).build();

        } catch (Exception ex) {
            Logger.getLogger(CliReporteexpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los parametro").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/ReporteExpediente")
    public Response guardarReporteexpediente(CliReporteexpedienteDto cliReporteexpedienteDto) {
        try {
            Respuesta res = cliReporteexpedienteService.guardarReporteexpediente(cliReporteexpedienteDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok((CliReporteexpedienteDto) res.getResultado("Reporteexpediente")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteexpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el parametro").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarReporteExpediente/{id}")
    public Response eliminarReporteexpediente(@PathParam("id") Long id) {
        try {

            Respuesta res = cliReporteexpedienteService.eliminarReporteexpediente(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteexpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el parametro").build();//TODO
        }
    }
}
