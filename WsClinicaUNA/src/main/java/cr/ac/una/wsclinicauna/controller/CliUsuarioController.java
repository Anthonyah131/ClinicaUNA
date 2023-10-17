package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliUsuarioDto;
import cr.ac.una.wsclinicauna.service.CliUsuarioService;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.JwTokenHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 
 */
@Path("/CliUsuarioController")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Usuarios", description = "Operaciones sobre usuarios")
public class CliUsuarioController {
    @EJB
    CliUsuarioService cliUsuarioService;
    @Context
    SecurityContext securityContext;
    
    
    @GET
    @Path("/usuario/{id}")
    public Response getUsuario(@PathParam("id") Long id) {
        try {
            
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();//TODO
        }
    }

    @GET
    @Path("/usuarios")
    public Response getUsuarios() {
        try {
            
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo los usuario").build();//TODO
        }
    }

    //TODO
    @POST
    @Path("/usuario")
    public Response guardarUsuario(CliUsuarioDto cliUsuarioDto) {
        try {
            
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el usuario").build();//TODO
        }
    }

    @DELETE
    @Path("/eliminarUsuario/{id}")
    public Response eliminarUsuario(@PathParam("id") Long id) {
        try {
            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el usuario").build();//TODO
        }
    }

    @GET
    @Path("/usuario/{usuario}/{clave}")
    public Response getUsuario(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        try {
            

            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }
    
    @GET
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
    
    
    @GET
    @Path("/renovar")
    public Response renovarToken() {
        try {
            String usuarioRequest = securityContext.getUserPrincipal().getName();
            if (usuarioRequest != null && !usuarioRequest.isEmpty()) {
                return Response.ok(JwTokenHelper.getInstance().generatePrivateKey(usuarioRequest)).build();
            } else {
                return Response.status(CodigoRespuesta.ERROR_PERMISOS.getValue()).entity("No se pudo renovar el token").build();

            }
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el token").build();
        }
    }
}
