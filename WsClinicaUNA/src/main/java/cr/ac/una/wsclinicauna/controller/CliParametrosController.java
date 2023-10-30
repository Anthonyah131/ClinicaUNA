/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.model.CliUsuarioDto;
import cr.ac.una.wsclinicauna.service.CliParametrosService;
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
@Path("/CliParametrosController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Parametros", description = "Operaciones sobre parametros")
@Secure
public class CliParametrosController {

    @EJB
    CliParametrosService cliParametrosService;

    @GET
    @Path("/parametro/{id}")
    public Response getParametro(@PathParam("id") Long id) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el parametro").build();//TODO
        }
    }

    @GET
    @Path("/parametros")
    public Response getParametros() {
        try {
            Respuesta res = cliParametrosService.getParametros();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliParametrosDto>>((List<CliParametrosDto>) res.getResultado("Parametros")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los parametro").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/parametro")
    public Response guardarParametros(CliParametrosDto cliParametrosDto) {
        try {
            Respuesta res = cliParametrosService.guardarParametro(cliParametrosDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            cliParametrosDto = (CliParametrosDto) res.getResultado("Parametro");
            return Response.ok(cliParametrosDto).build();
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el parametro").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarParametro/{id}")
    public Response eliminarParametro(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliParametrosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el parametro").build();//TODO
        }
    }
}
