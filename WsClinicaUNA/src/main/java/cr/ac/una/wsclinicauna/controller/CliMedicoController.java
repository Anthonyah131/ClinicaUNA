/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliMedicoDto;
import cr.ac.una.wsclinicauna.service.CliMedicoService;
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
@Path("/CliMedicoController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Medico", description = "Operaciones sobre medico")
public class CliMedicoController {
    @EJB
    CliMedicoService cliMedicoService;
    @GET
    @Path("/medico/{id}")
    public Response getMedico(@PathParam("id") Long id) {
        try {
            
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el medico").build();//TODO
        }
    }

    @GET
    @Path("/medicos")
    public Response getMedicos() {
        try {
            
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los medico").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/medico")
    public Response guardarMedico(CliMedicoDto cliMedicoDto) {
        try {
            
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el medico").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarMedico/{id}")
    public Response eliminarMedico(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el medico").build();//TODO
        }
    }
}
