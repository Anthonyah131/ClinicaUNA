/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliCorreodestinoDto;
import cr.ac.una.wsclinicauna.service.CliCorreodestinoService;
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

@Path("/CliCorreodestinoController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Correo destinatarios", description = "Operaciones sobre Correo destinatarios")
@Secure
public class CliCorreodestinoController {
    @EJB
    CliCorreodestinoService cliCorreodestinoService;
    
    @GET
    @Path("/correodestino/{id}")
    public Response getCorreodestino(@PathParam("id") Long id) {
        try {
            Respuesta res = cliCorreodestinoService.getCorreodestino(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliCorreodestinoDto cliPacienteDto = (CliCorreodestinoDto) res.getResultado("Correodestino");
            return Response.ok(cliPacienteDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Correodestino").build();//TODO
        }
    }

    @GET
    @Path("/correodestinos")
    public Response getCorreodestinos() {
        try {
            Respuesta res = cliCorreodestinoService.getCorreodestinos();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliCorreodestinoDto>>((List<CliCorreodestinoDto>) res.getResultado("Correodestinos")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los Correodestino").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/correodestino")
    public Response guardarCorreodestino(CliCorreodestinoDto cliCorreodestinoDto) {
        try {
            Respuesta res = cliCorreodestinoService.guardarCorreodestino(cliCorreodestinoDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CliCorreodestinoDto) res.getResultado("Correodestino")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Correodestino").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarCorreodestino/{id}")
    public Response eliminarCorreodestino(@PathParam("id") Long id) {
        try {
            Respuesta res = cliCorreodestinoService.eliminarCorreodestino(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliCorreodestinoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el Correodestino").build();//TODO
        }
    }
}
