package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.model.CliUsuarioDto;
import cr.ac.una.wsclinicauna.service.CliParametrosService;
import cr.ac.una.wsclinicauna.service.CliUsuarioService;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.JwTokenHelper;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import java.util.List;
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
@Secure
public class CliUsuarioController {

    @EJB
    CliUsuarioService cliUsuarioService;
    @EJB
    CliParametrosService cliParametrosService;
    @Context
    SecurityContext securityContext;

    @GET
    @Path("/usuario/{id}")
    public Response getUsuario(@PathParam("id") Long id) {
        try {
            Respuesta res = cliUsuarioService.getUsuario(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            CliUsuarioDto cliUsuarioDto = (CliUsuarioDto) res.getResultado("Usuario");
            return Response.ok(cliUsuarioDto).build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();//TODO
        }
    }

    @GET
    @Path("/usuarios")
    public Response getUsuarios() {
        try {
            Respuesta res = cliUsuarioService.getUsuarios();
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok(new GenericEntity<List<CliUsuarioDto>>((List<CliUsuarioDto>) res.getResultado("Usuarios")) {
            }).build();
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
            Respuesta res = cliUsuarioService.guardarUsuario(cliUsuarioDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }

            cliUsuarioDto = (CliUsuarioDto) res.getResultado("Usuario");

            Respuesta re2 = cliParametrosService.getParametros();
            List<CliParametrosDto> cliParametrosDtoList = (List<CliParametrosDto>) re2.getResultado("Parametros");
            CliParametrosDto cliParametrosDto = cliParametrosDtoList.get(0);

            if (cliUsuarioDto.getUsuId() == null) {
                cliUsuarioService.correoActivacion(cliUsuarioDto, cliParametrosDto);
            }
            return Response.ok(cliUsuarioDto).build();
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando el usuario").build();
        }
    }

    @DELETE
    @Path("/eliminarUsuario/{id}")
    public Response eliminarUsuario(@PathParam("id") Long id) {
        try {
            Respuesta res = cliUsuarioService.eliminarUsuario(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando el usuario").build();
        }
    }

    @GET
    @Path("/usuario/{usuario}/{clave}")
    public Response getUsuario(@PathParam("usuario") String usuario, @PathParam("clave") String clave) {
        try {
            Respuesta res = cliUsuarioService.validarUsuario(usuario, clave);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            CliUsuarioDto cliUsuarioDto = (CliUsuarioDto) res.getResultado("Usuario");
            cliUsuarioDto.setToken(JwTokenHelper.getInstance().generatePrivateKey(usuario));
            return Response.ok(cliUsuarioDto).build();
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();
        }
    }

    @GET
    @Path("/activacion/{id}")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.TEXT_HTML)
    public Response activacionUsuario(@PathParam("id") Long id) {
        try {
            Respuesta res = cliUsuarioService.activacionCuenta(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }
            return Response.ok().entity("<html lang=\"es\">\n"
                    + "    <head>\n"
                    + "        <meta charset=\"UTF-8\">\n"
                    + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "        <title>Activación de Usuario - Clínica de Salud</title>\n"
                    + "        <link rel=\"stylesheet\" href=\"styles.css\">\n"
                    + "        <meta name=\"theme-color\" content=\"#397dcc\">\n"
                    + "        <style>\n"
                    + "            body {\n"
                    + "                font-family: Arial, sans-serif;\n"
                    + "                margin: 0;\n"
                    + "                padding: 0;\n"
                    + "                background-color: #f8f8f8;\n"
                    + "            }\n"
                    + "\n"
                    + "            header {\n"
                    + "                background-color: #3498db;\n"
                    + "                color: #fff;\n"
                    + "                text-align: center;\n"
                    + "                padding: 20px;\n"
                    + "            }\n"
                    + "\n"
                    + "            main {\n"
                    + "                max-width: 800px;\n"
                    + "                margin: 20px auto;\n"
                    + "                padding: 20px;\n"
                    + "                background-color: #fff;\n"
                    + "                border-radius: 8px;\n"
                    + "                box-shadow: 0 0 10px rgba(0,0,0,0.1);\n"
                    + "            }\n"
                    + "\n"
                    + "            img {\n"
                    + "                max-width: 100%;\n"
                    + "                height: auto;\n"
                    + "                margin-bottom: 20px;\n"
                    + "            }\n"
                    + "\n"
                    + "            footer {\n"
                    + "                background-color: #333;\n"
                    + "                color: #fff;\n"
                    + "                text-align: center;\n"
                    + "                padding: 10px;\n"
                    + "                position: fixed;\n"
                    + "                width: 100%;\n"
                    + "                bottom: 0;\n"
                    + "            }\n"
                    + "            @keyframes fade-in {\n"
                    + "                from {\n"
                    + "                    opacity: 0;\n"
                    + "                    transform: translateY(-20px);\n"
                    + "                }\n"
                    + "                to   {\n"
                    + "                    opacity: 1;\n"
                    + "                    transform: translateY(0);\n"
                    + "                }\n"
                    + "            }\n"
                    + "\n"
                    + "            main {\n"
                    + "                animation: fade-in 1.5s ease;\n"
                    + "            }\n"
                    + "        </style>\n"
                    + "    </head>\n"
                    + "    <body>\n"
                    + "        <header>\n"
                    + "            <h1>Bienvenido a la Clínica de Salud</h1>\n"
                    + "        </header>\n"
                    + "        <main>\n"
                    + "            <p>Gracias por activar tu cuenta. Estamos encantados de tenerte como parte de nuestra comunidad.</p>\n"
                    + "            <img src=\"https://images.unsplash.com/photo-1550831106-2747f0d6a81c?auto=format&fit=crop&q=80&w=1974&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\" alt=\"Consulta Médica\">\n"
                    + "            <img src=\"https://images.unsplash.com/photo-1631217868264-e5b90bb7e133?auto=format&fit=crop&q=80&w=2091&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D\" alt=\"Personal Médico\">\n"
                    + "        </main>\n"
                    + "        <footer>\n"
                    + "            <p>&copy; 2023 Clínica de Salud</p>\n"
                    + "        </footer>\n"
                    + "    </body>\n"
                    + "</html>").build();
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();//TODO
        }
    }

    @GET
    @Path("/recuperarClave/{correo}")
    public Response recuperarClave(@PathParam("correo") String correo) {
        try {
            Respuesta re2 = cliParametrosService.getParametros();
            List<CliParametrosDto> cliParametrosDtoList = (List<CliParametrosDto>) re2.getResultado("Parametros");
            CliParametrosDto cliParametrosDto = cliParametrosDtoList.get(0);

            Respuesta res = cliUsuarioService.recuperarClave(correo, cliParametrosDto);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();//TODO
            }

            return Response.ok().build();//TODO
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo el usuario").build();//TODO
        }
    }

    @GET
    public Response ping() {
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
