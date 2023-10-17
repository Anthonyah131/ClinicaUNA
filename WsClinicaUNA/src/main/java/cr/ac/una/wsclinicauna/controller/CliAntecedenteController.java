/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliAntecedenteDto;
import cr.ac.una.wsclinicauna.service.CliAntecedenteService;
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
@Path("/CliAntecedenteController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Antecedente", description = "Operaciones sobre antecedente")
public class CliAntecedenteController {

    @EJB
    CliAntecedenteService cliAntecedenteService;

    @GET
    @Path("/antecedente/{id}")
    public Response getAntecedente(@PathParam("id") Long id) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el antecedente").build();//TODO
        }
    }

    @GET
    @Path("/antecedentes")
    public Response getAntecedentes() {
        try {

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los antecedente").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/antecedente")
    public Response guardarAntecedente(CliAntecedenteDto cliAntecedenteDto) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el antecedente").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarAntecedente/{id}")
    public Response eliminarAntecedente(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliAntecedenteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el antecedente").build();//TODO
        }
    }
}
