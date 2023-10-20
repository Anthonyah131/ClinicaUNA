/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliReporteusuariosDto;
import cr.ac.una.wsclinicauna.model.CliUsuarioDto;
import cr.ac.una.wsclinicauna.service.CliReporteusuariosService;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
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
@Path("/CliReporteusuariosController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Reporteusuarios", description = "Operaciones sobre Reporteusuarios")
public class CliReporteusuariosController {

    @EJB
    CliReporteusuariosService cliReporteusuariosService;

    @GET
    @Path("/ReporteUsuarios/{id}")
    public Response getReporteusuario(@PathParam("id") Long id) {
        try {
            Respuesta res = cliReporteusuariosService.getReporteusuario(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliReporteusuariosDto cliReporteusuariosDto = (CliReporteusuariosDto) res.getResultado("Reporteusuario");
            return Response.ok(cliReporteusuariosDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el parametro").build();//TODO
        }
    }

    @GET
    @Path("/ReporteUsuarios")
    public Response getParametros() {
        try {
            Respuesta res = cliReporteusuariosService.getReporteusuarios();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliUsuarioDto>>((List<CliUsuarioDto>) res.getResultado("Reporteusuarios")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los parametro").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/ReporteUsuarios")
    public Response guardarParametros(CliReporteusuariosDto cliReporteusuariosDto) {
        try {
            Respuesta res = cliReporteusuariosService.guardarReporteusuario(cliReporteusuariosDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            return Response.ok((CliReporteusuariosDto) res.getResultado("Reporteusuario")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el parametro").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarReporteUsuarios/{id}")
    public Response eliminarParametro(@PathParam("id") Long id) {
        try {

            Respuesta res = cliReporteusuariosService.eliminarReporteusuario(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteusuariosController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el parametro").build();//TODO
        }
    }
}
