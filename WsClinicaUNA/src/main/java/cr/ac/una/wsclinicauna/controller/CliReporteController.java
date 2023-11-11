/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliReporteDto;
import cr.ac.una.wsclinicauna.service.CliReporteService;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArauzKJ
 */
@Path("/CliReporteController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Reporte", description = "Operaciones sobre Reporte")
@Secure
public class CliReporteController {

    @EJB
    CliReporteService cliReporteService;

    @GET
    @Path("/generarReporte/{consulta}")
    public Response generarInformeExcelDesdeConsultaSQL(@PathParam("consulta") String consulta) {
        try {
            Respuesta res1 = cliReporteService.getReportes();
            List<CliReporteDto> cliReporteDtos = (List<CliReporteDto>) res1.getResultado("Reportes");
            Respuesta res = cliReporteService.generarInformeExcelDesdeConsultaSQL(cliReporteDtos.get(0));
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            byte[] excelData = (byte[]) res.getResultado("ReporteExcel");
            String currentDirectory = System.getProperty("user.dir");
            String outputPath = currentDirectory + "/informe.xlsx";
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                fos.write(excelData);
            } catch (IOException e) {
                e.printStackTrace();
                // Trata cualquier error de escritura del archivo
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliReporteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Reporte").build();//TODO
        }
    }

    @GET
    @Path("/reporte/{id}")
    public Response getReporte(@PathParam("id") Long id) {
        try {
            Respuesta res = cliReporteService.getReporte(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliReporteDto cliPacienteDto = (CliReporteDto) res.getResultado("Reporte");
            return Response.ok(cliPacienteDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el Reporte").build();//TODO
        }
    }

    @GET
    @Path("/reportes")
    public Response getReportes() {
        try {
            Respuesta res = cliReporteService.getReportes();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliReporteDto>>((List<CliReporteDto>) res.getResultado("Reportes")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(CliReporteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los Reporte").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/reporte")
    public Response guardarReporte(CliReporteDto cliReporteDto) {
        try {
            Respuesta res = cliReporteService.guardarReporte(cliReporteDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((CliReporteDto) res.getResultado("Reporte")).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el Reporte").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarReporte/{id}")
    public Response eliminarReporte(@PathParam("id") Long id) {
        try {
            Respuesta res = cliReporteService.eliminarReporte(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliReporteController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el Reporte").build();//TODO
        }
    }

}
