/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliAtencionDto;
import cr.ac.una.wsclinicauna.service.CliAtencionService;
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
@Path("/CliAtencionController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Atencion", description = "Operaciones sobre atencion")
public class CliAtencionController {

    @EJB
    CliAtencionService cliAtencionService;

    @GET
    @Path("/atencion/{id}")
    public Response getAtencion(@PathParam("id") Long id) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el atencion").build();//TODO
        }
    }

    @GET
    @Path("/atenciones")
    public Response getAtenciones() {
        try {

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los atencion").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/atencion")
    public Response guardarAtencion(CliAtencionDto cliAtencionDto) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el atencion").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarAtencion/{id}")
    public Response eliminarAtencion(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAtencionController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el atencion").build();//TODO
        }
    }
}
