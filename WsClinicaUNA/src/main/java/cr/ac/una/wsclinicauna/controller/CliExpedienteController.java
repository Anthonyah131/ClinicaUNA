/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliExpedienteDto;
import cr.ac.una.wsclinicauna.service.CliExpedienteService;
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
@Path("/CliExpedienteController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Expediente", description = "Operaciones sobre expediente")
public class CliExpedienteController {

    @EJB
    CliExpedienteService cliExpedienteService;

    @GET
    @Path("/expediente/{id}")
    public Response getExpediente(@PathParam("id") Long id) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el expediente").build();//TODO
        }
    }

    @GET
    @Path("/expedientes")
    public Response getExpedientes() {
        try {

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los expediente").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/expediente")
    public Response guardarExpediente(CliExpedienteDto cliExpedienteDto) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el expediente").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarExpediente/{id}")
    public Response eliminarExpediente(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliExpedienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el usuario").build();//TODO
        }
    }
}
