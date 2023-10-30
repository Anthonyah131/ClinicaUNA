/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliMedicoDto;
import cr.ac.una.wsclinicauna.service.CliMedicoService;
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
@Path("/CliMedicoController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Medico", description = "Operaciones sobre medico")
@Secure
public class CliMedicoController {
    @EJB
    CliMedicoService cliMedicoService;
    @GET
    @Path("/medico/{id}")
    public Response getMedico(@PathParam("id") Long id) {
        try {
            Respuesta res = cliMedicoService.getMedico(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliMedicoDto cliPacienteDto = (CliMedicoDto) res.getResultado("Medico");
            return Response.ok(cliPacienteDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el medico").build();//TODO
        }
    }

    @GET
    @Path("/medicos")
    public Response getMedicos() {
        try {
            Respuesta res = cliMedicoService.getMedicos();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliMedicoDto>>((List<CliMedicoDto>) res.getResultado("Medicos")) {
            }).build();
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
            
            Respuesta res = cliMedicoService.guardarMedico(cliMedicoDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CliMedicoDto) res.getResultado("Medico")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el medico").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarMedico/{id}")
    public Response eliminarMedico(@PathParam("id") Long id) {
        try {
            Respuesta res = cliMedicoService.eliminarMedico(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliMedicoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el medico").build();//TODO
        }
    }
}
