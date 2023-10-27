package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliUsuarioDto;
import jakarta.ws.rs.core.GenericType;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;

/**
 *
 * @author ANTHONY
 */
public class CliUsuarioService {

    public Respuesta renovarToken() {
        try {
            Request request = new Request("CliUsuarioController/renovar");
            request.getRenewal();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            String token = (String) request.readEntity(String.class);
            return new Respuesta(true, "", "", "Token", token);
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el token", ex);
            return new Respuesta(false, "Error renovando el token.", "renovarToken " + ex.getMessage());
        }
    }

    public Respuesta getUsuario(String usuario, String clave) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("usuario", usuario);
            parametros.put("clave", clave);
            Request request = new Request("CliUsuarioController/usuario", "/{usuario}/{clave}", parametros);
            request.getToken();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliUsuarioDto usuarioDto = (CliUsuarioDto) request.readEntity(CliUsuarioDto.class);
            return new Respuesta(true, "", "", "Usuario", usuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + usuario + "]", ex);
            return new Respuesta(false, "key.errorQuerying", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getUsuario(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliUsuarioController/usuario", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliUsuarioDto usuarioDto = (CliUsuarioDto) request.readEntity(CliUsuarioDto.class);
            return new Respuesta(true, "", "", "Usuario", usuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo el usuario [" + id + "]", ex);
            return new Respuesta(false, "key.errorQuerying", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getUsuarios(String cedula, String nombre, String pApellido, String tipoUsuario) {
        try {
            Request request = new Request("CliUsuarioController/usuarios");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliUsuarioDto> usuarios = (List<CliUsuarioDto>) request.readEntity(new GenericType<List<CliUsuarioDto>>() {
            });

            if (cedula != null && !cedula.isBlank()) {
                String cedulaBuscado = cedula.toLowerCase();
                usuarios = usuarios.stream()
                        .filter(p -> p.getUsuCedula().toLowerCase().contains(cedulaBuscado))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }
            if (nombre != null && !nombre.isBlank()) {
                String nombreBuscado = nombre.toLowerCase();
                usuarios = usuarios.stream()
                        .filter(p -> p.getUsuNombre().toLowerCase().contains(nombreBuscado))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }
            if (pApellido != null && !pApellido.isBlank()) {
                String apellidoBuscada = pApellido.toLowerCase();
                usuarios = usuarios.stream()
                        .filter(p -> p.getUsuPapellido().toLowerCase().contains(apellidoBuscada))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }
            if (tipoUsuario != null && !tipoUsuario.isBlank()) {
                System.out.println(tipoUsuario);
                switch (tipoUsuario) {
                    case "Médico", "Doctor" ->
                        usuarios = usuarios.stream()
                                .filter(p -> p.getUsuTipousuario().contains("M"))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                    case "Recepcionista", "Receptionist" ->
                        usuarios = usuarios.stream()
                                .filter(p -> p.getUsuTipousuario().contains("R"))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                    case "Administrador", "Administrator" ->
                        usuarios = usuarios.stream()
                                .filter(p -> p.getUsuTipousuario().contains("A"))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList));
                    default -> {
                    }
                }
            }
            return new Respuesta(true, "", "", "Usuarios", usuarios);
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioService.class.getName()).log(Level.SEVERE, "Error obteniendo usuarios.", ex);
            return new Respuesta(false, "key.errorQuerying", "getUsuarios " + ex.getMessage());
        }
    }

    public Respuesta guardarUsuario(CliUsuarioDto usuario) {
        try {
            Request request = new Request("CliUsuarioController/usuario");
            request.post(usuario);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliUsuarioDto usuarioDto = (CliUsuarioDto) request.readEntity(CliUsuarioDto.class);
            return new Respuesta(true, "", "", "Usuario", usuarioDto);
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioService.class.getName()).log(Level.SEVERE, "Error guardando el usuario.", ex);
            return new Respuesta(false, "key.errorSavingUser", "guardarUsuario " + ex.getMessage());
        }
    }

    public Respuesta eliminarUsuario(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliUsuarioController/eliminarUsuario", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioService.class.getName()).log(Level.SEVERE, "Error eliminando el usuario.", ex);
            return new Respuesta(false, "key.deleteUserError", "eliminarUsuario " + ex.getMessage());
        }
    }

    public Respuesta recuperarClave(String correo) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("correo", correo);
            Request request = new Request("CliUsuarioController/recuperarClave", "/{correo}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliUsuarioService.class.getName()).log(Level.SEVERE, "Error recuperando la clave", ex);
            return new Respuesta(false, "key.errorQuerying", "RecuperarClave " + ex.getMessage());
        }
    }
}
