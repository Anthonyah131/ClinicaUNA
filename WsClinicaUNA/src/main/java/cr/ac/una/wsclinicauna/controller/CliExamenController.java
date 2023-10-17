/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliExamenDto;
import cr.ac.una.wsclinicauna.service.CliExamenService;
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
@Path("/CliExamenController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Examen", description = "Operaciones sobre examen")
public class CliExamenController {
    @EJB
    CliExamenService cliExamenService;
    @GET
    @Path("/examen/{id}")
    public Response getExamen(@PathParam("id") Long id) {
        try {
            
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliExamenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el examen").build();//TODO
        }
    }

    @GET
    @Path("/examenes")
    public Response getExamens() {
        try {
            
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliExamenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los examen").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/examen")
    public Response guardarExamen(CliExamenDto cliExamenDto) {
        try {
            
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliExamenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el examen").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarExamen/{id}")
    public Response eliminarExamen(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliExamenController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el examen").build();//TODO
        }
    }
}
