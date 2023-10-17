/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliPacienteDto;
import cr.ac.una.wsclinicauna.service.CliPacienteService;
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
@Path("/CliPacienteController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Paciente", description = "Operaciones sobre paciente")
public class CliPacienteController {

    @EJB
    CliPacienteService cliPacienteService;

    @GET
    @Path("/paciente/{id}")
    public Response getPaciente(@PathParam("id") Long id) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el paciente").build();//TODO
        }
    }

    @GET
    @Path("/pacientes")
    public Response getPacientes() {
        try {

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los paciente").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/paciente")
    public Response guardarPaciente(CliPacienteDto cliPacienteDto) {
        try {

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el paciente").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarPaciente/{id}")
    public Response eliminarPaciente(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el paciente").build();//TODO
        }
    }
}
