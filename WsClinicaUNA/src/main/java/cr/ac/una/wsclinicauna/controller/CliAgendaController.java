/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.service.CliAgendaService;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArauzKJ
 */
@Path("/CliAgendaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Agenda", description = "Operaciones sobre agenda")
public class CliAgendaController {

    @EJB
    CliAgendaService cliAgendaService;

    @GET
    @Path("/agenda/{id}")
    public Response getAgenda(@PathParam("id") Long id) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAgendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el agenda").build();//TODO
        }
    }

    @GET
    @Path("/agendas")
    public Response getAgendas() {
        try {

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliAgendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los agenda").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/agenda")
    public Response guardarAgenda() {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAgendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el agenda").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarAgenda/{id}")
    public Response eliminarAgenda(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAgendaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el agenda").build();//TODO
        }
    }
}
