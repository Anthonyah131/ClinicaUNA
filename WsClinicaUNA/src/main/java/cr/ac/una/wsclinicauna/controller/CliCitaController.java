/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliCitaDto;
import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.service.CliCitaService;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/CliCitaController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Cita", description = "Operaciones sobre cita")
@Secure
public class CliCitaController {

    @EJB
    CliCitaService cliCitaService;
    @EJB
    CliParametrosService cliParametrosService;

    @GET
    @Path("/cita/{id}")
    public Response getCita(@PathParam("id") Long id) {
        try {
            Respuesta res = cliCitaService.getCita(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliCitaDto cliPacienteDto = (CliCitaDto) res.getResultado("Cita");
            return Response.ok(cliPacienteDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliCitaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el cita").build();//TODO
        }
    }

    @GET
    @Path("/citas")
    public Response getCitas() {
        try {
            Respuesta res = cliCitaService.getCitas();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliCitaDto>>((List<CliCitaDto>) res.getResultado("Citas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(CliCitaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los cita").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/cita")
    public Response guardarCita(CliCitaDto cliCitaDto) {
        try {
            Respuesta res = cliCitaService.guardarCita(cliCitaDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            
            Respuesta re2 = cliParametrosService.getParametros();
            List<CliParametrosDto> cliParametrosDtoList = (List<CliParametrosDto>) re2.getResultado("Parametros");
            CliParametrosDto cliParametrosDto = cliParametrosDtoList.get(0);
            
            cliCitaService.memorandoEmailAgeCita(cliCitaDto.getCliPacienteDto(), cliParametrosDto, cliCitaDto);
            
            return Response.ok((CliCitaDto) res.getResultado("Citas")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliCitaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el cita").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarCita/{id}")
    public Response eliminarCita(@PathParam("id") Long id) {
        try {
            Respuesta res = cliCitaService.eliminarCita(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliCitaController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el cita").build();//TODO
        }
    }

}
