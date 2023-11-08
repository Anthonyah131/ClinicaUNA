/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliParametroconsultaDto;
import cr.ac.una.wsclinicauna.service.CliParametroconsultaService;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import cr.ac.una.wsclinicauna.util.Secure;
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

@Path("/CliParametroconsultaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Parametrosconsulta", description = "Operaciones sobre Parametrosconsulta")
@Secure
public class CliParametroconsultaController {
    @EJB
    CliParametroconsultaService cliParametroconsultaService;
    
    @GET
    @Path("/parametroconsulta/{id}")
    public Response getParametroconsulta(@PathParam("id") Long id) {
        try {
            Respuesta res = cliParametroconsultaService.getParametroconsulta(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliParametroconsultaDto cliPacienteDto = (CliParametroconsultaDto) res.getResultado("Parametroconsulta");
            return Response.ok(cliPacienteDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Parametroconsulta").build();//TODO
        }
    }

    @GET
    @Path("/parametroconsultas")
    public Response getParametroconsultas() {
        try {
            Respuesta res = cliParametroconsultaService.getParametroconsultas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliParametroconsultaDto>>((List<CliParametroconsultaDto>) res.getResultado("Parametroconsultas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los Parametroconsulta").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/parametroconsulta")
    public Response guardarParametroconsulta(CliParametroconsultaDto cliParametroconsultaDto) {
        try {
            Respuesta res = cliParametroconsultaService.guardarParametroconsulta(cliParametroconsultaDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CliParametroconsultaDto) res.getResultado("Parametroconsulta")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Parametroconsulta").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarParametroconsulta/{id}")
    public Response eliminarParametroconsulta(@PathParam("id") Long id) {
        try {
            Respuesta res = cliParametroconsultaService.eliminarParametroconsulta(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliParametroconsultaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el Parametroconsulta").build();//TODO
        }
    }
}
