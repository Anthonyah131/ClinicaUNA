/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliMedico;
import cr.ac.una.wsclinicauna.model.CliMedicoDto;
import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.model.CliReporteusuarios;
import cr.ac.una.wsclinicauna.model.CliReporteusuariosDto;
import cr.ac.una.wsclinicauna.model.CliUsuario;
import cr.ac.una.wsclinicauna.model.CliUsuarioDto;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author ArauzKJ
 */
@Stateless
@LocalBean
public class CliUsuarioService {

    private static final Logger LOG = Logger.getLogger(CliUsuarioService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta validarUsuario(String usuario, String clave) {
        try {
            Query qryActividad = em.createNamedQuery("CliUsuario.findByUsuarioClave", CliUsuario.class);
            qryActividad.setParameter("usuario", usuario);
            qryActividad.setParameter("clave", clave);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new CliUsuarioDto((CliUsuario) qryActividad.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un usuario con las credenciales ingresadas.", "validarUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "validarUsuario " + ex.getMessage());
        }
    }

    public Respuesta getUsuario(Long id) {
        try {
            Query qryEmpleado = em.createNamedQuery("CliUsuario.findByUsuId", CliUsuario.class);
            qryEmpleado.setParameter("id", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new CliUsuarioDto((CliUsuario) qryEmpleado.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un empleado con el código ingresado.", "getUsuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getUsuario " + ex.getMessage());
        }
    }

    public Respuesta getUsuarios() {
        try {
            Query qryEmpleado = em.createNamedQuery("CliUsuario.findAll", CliUsuario.class);
            List<CliUsuario> cliUsuarios = qryEmpleado.getResultList();
            List<CliUsuarioDto> cliUsuarioDtos = new ArrayList<>();
            for (CliUsuario cliUsuario : cliUsuarios) {
                cliUsuarioDtos.add(new CliUsuarioDto(cliUsuario));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuarios", cliUsuarioDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen empleados con los criterios ingresados.", "getUsuarios NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el usuario.", "getEmpleado " + ex.getMessage());
        }
    }

    public Respuesta guardarUsuario(CliUsuarioDto cliUsuarioDto) {
        try {
            CliUsuario cliUsuario;
            if (cliUsuarioDto.getUsuId() != null && cliUsuarioDto.getUsuId() > 0) {
                cliUsuario = em.find(CliUsuario.class, cliUsuarioDto.getUsuId());
                if (cliUsuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "guardarUsuario NoResultException");
                }
                cliUsuario.actualizar(cliUsuarioDto);
                
                for (CliMedicoDto cliMedicoDto : cliUsuarioDto.getCliMedicoListEliminados()) {
                    cliUsuario.getCliMedicoList().remove(new CliMedico(cliMedicoDto.getMedId()));
                }

                if (!cliUsuarioDto.getCliMedicoList().isEmpty()) {
                    for (CliMedicoDto cliMedicoDto : cliUsuarioDto.getCliMedicoList()) {
                        CliMedico cliMedico = em.find(CliMedico.class, cliMedicoDto.getMedId());
                        cliUsuario.getCliMedicoList().add(cliMedico);
                    }
                }

                for (CliReporteusuariosDto cliReporteusuariosDto : cliUsuarioDto.getCliReporteusuariosListEliminados()) {
                    cliUsuario.getCliReporteusuariosList().remove(new CliReporteusuarios(cliReporteusuariosDto.getRepusuId()));
                }

                if (!cliUsuarioDto.getCliReporteusuariosList().isEmpty()) {
                    for (CliReporteusuariosDto cliReporteusuariosDto : cliUsuarioDto.getCliReporteusuariosList()) {
                        CliReporteusuarios cliReporteusuarios = em.find(CliReporteusuarios.class, cliReporteusuariosDto.getRepusuId());
                        cliUsuario.getCliReporteusuariosList().add(cliReporteusuarios);
                    }
                }
                cliUsuario = em.merge(cliUsuario);
            } else {
                cliUsuario = new CliUsuario(cliUsuarioDto);
                em.persist(cliUsuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Usuario", new CliUsuarioDto(cliUsuario));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "guardarUsuario " + ex.getMessage());
        }
    }

    public Respuesta eliminarUsuario(Long id) {
        try {
            CliUsuario cliUsuario;
            if (id != null && id > 0) {
                cliUsuario = em.find(CliUsuario.class, id);
                if (cliUsuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a eliminar.", "eliminarUsuario NoResultException");
                }
                em.remove(cliUsuario);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el usuario a eliminar.", "eliminarUsuario NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el usuario porque tiene relaciones con otros registros.", "eliminarUsuario " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el usuario.", "eliminarUsuario " + ex.getMessage());
        }
    }

    public Respuesta activacionCuenta(Long usuId) {
        try {
            Query qryActividad = em.createNamedQuery("CliUsuario.findByUsuId", CliUsuario.class);
            qryActividad.setParameter("usuId", usuId);
            CliUsuarioDto cliUsuarioDto = new CliUsuarioDto((CliUsuario) qryActividad.getSingleResult());
            cliUsuarioDto.setUsuActivo("A");
            CliUsuario cliUsuario;
            if (cliUsuarioDto.getUsuId() != null && cliUsuarioDto.getUsuId() > 0) {
                cliUsuario = em.find(CliUsuario.class, cliUsuarioDto.getUsuId());
                if (cliUsuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "activacionCuenta NoResultException");
                }
                cliUsuario.actualizar(cliUsuarioDto);
                cliUsuario = em.merge(cliUsuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "activacionCuenta " + ex.getMessage());
        }
    }

    public Respuesta recuperarClave(String usuCorreo, CliParametrosDto parametrosDto) {
        try {
            Query qryActividad = em.createNamedQuery("CliUsuario.findByUsuCorreo", CliUsuario.class);
            qryActividad.setParameter("usuCorreo", usuCorreo);
            CliUsuarioDto cliUsuarioDto = new CliUsuarioDto((CliUsuario) qryActividad.getSingleResult());
            recuClave(cliUsuarioDto, parametrosDto);
            CliUsuario cliUsuario;
            if (cliUsuarioDto.getUsuId() != null && cliUsuarioDto.getUsuId() > 0) {
                cliUsuario = em.find(CliUsuario.class, cliUsuarioDto.getUsuId());
                if (cliUsuario == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el usuario a modificar.", "recuperarClave NoResultException");
                }
                cliUsuario.actualizar(cliUsuarioDto);
                cliUsuario = em.merge(cliUsuario);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el usuario.", "recuperarClave " + ex.getMessage());
        }
    }

    public Respuesta correoActivacion(CliUsuarioDto cliUsuarioDto, CliParametrosDto cliParametrosDto) {
        try {
            //setea las propiedades del smtp para poder enviar los emails
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            //proporciona el correo y contrasena del correo con el que va a ser enviado
            String correoRemitente = cliParametrosDto.getParEmail();//"CineUna123@outlook.com";
            String passwordRemitente = cliParametrosDto.getParClave();//"cine1234";
            String correoReceptor = cliUsuarioDto.getUsuCorreo();
            String asunto = "ClinicaUNA";

            //Mensaje que va a ser enviado
            String mensaje = mensajeEmail(cliUsuarioDto, cliParametrosDto.getParHtml(), cliParametrosDto.getParLogo(), cliParametrosDto.getParNombre());

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "guardarCliente " + ex.getMessage());
        }
    }

    private static String generateRandomPassword(int len) {
        // Rango ASCII – alfanumérico (0-9, a-z, A-Z)
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        // cada iteración del bucle elige aleatoriamente un carácter del dado
        // rango ASCII y lo agrega a la instancia `StringBuilder`
        for (int i = 0; i < len; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }

    private Respuesta recuClave(CliUsuarioDto cliUsuarioDto, CliParametrosDto cliParametrosDto) {
        int len = 8;
        //System.out.println(generateRandomPassword(len));

        try {
            //setea las propiedades del smtp para poder enviar los emails
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            //Llamada a los parametros
            //proporciona el correo y contrasena del correo con el que va a ser enviado
            String correoRemitente = cliParametrosDto.getParEmail();//"CineUna123@outlook.com";
            String passwordRemitente = cliParametrosDto.getParClave();//"cine1234";
            //Optine el correo al cual va a ser enviado el mensaje
            String correoReceptor = cliUsuarioDto.getUsuCorreo();
            String asunto = "EvaComUNA";

            //Llama al metodo de creacion de contrasena y se la manda a la persona y luego se la setea para que la cambie
            String claveRestaurada = generateRandomPassword(len);

            String mensaje = mensajeClave(cliParametrosDto.getParHtml(), cliParametrosDto.getParLogo(), cliParametrosDto.getParNombre(), claveRestaurada);

            //Envio del mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje, "ISO-8859-1", "html");

            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            t.close();

            cliUsuarioDto.setUsuClave(claveRestaurada);
            cliUsuarioDto.setUsuTempclave(claveRestaurada);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "");

        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cliente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el cliente.", "recuClave " + ex.getMessage());
        }
    }

    private String obtenerIp() throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

    private String mensajeClave(byte[] html, byte[] logo, String nombre, String claveRestaurada) throws IOException {

        String base64Image = convertirABase64(logo);
        String mensaje = convertirBytesAHTML(html);
        String recuperacionMensaje = "Hola por parte de " + nombre + " se generar una nueva clave! La clave nueva es: " + claveRestaurada + "  Atte: " + nombre;

        mensaje = mensaje.replace("{Insertar nombre de la empresa}", nombre);

        mensaje = mensaje.replace("{Contenido que se le vaya a enviar}", recuperacionMensaje);

        mensaje = mensaje.replace("{imagen}", "data:image/png;base64," + base64Image);

        return mensaje;
    }

    private static String convertirBytesAHTML(byte[] bytes) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(bytes);
        String html = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        return html;
    }

    private static String convertirABase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    private String mensajeEmail(CliUsuarioDto cliUsuarioDto, byte[] html, byte[] logo, String nombre) throws UnknownHostException, IOException {

        String base64Image = convertirABase64(logo);
        String mensaje = convertirBytesAHTML(html);
        String activacionMensaje = "<p style=\"font-size: 14px; line-height: 180%;\"><span style=\"font-size: 18px; line-height: 32.4px; color: #000000;\">"
                + "<span style=\"font-size: 18px; line-height: 32.4px; color: #000000;\">"
                + "<span style=\"line-height: 32.4px; font-family: Montserrat, sans-serif; font-size: 18px;\">"
                + "Presione el link para activar su cuenta: "
                + "<a href=\"http://" + obtenerIp() + ":8080/ws/CliUsuarioController/todo" + cliUsuarioDto.getUsuId() + "\">"
                + "http://" + obtenerIp() + ":8080/EvaComUNAWs/activacion.html?id=" + cliUsuarioDto.getUsuId()
                + "</a>"
                + "</span>"
                + "</span>"
                + "</span>"
                + "</p>";
        mensaje = mensaje.replace("{Insertar nombre de la empresa}", nombre);
        mensaje = mensaje.replace("{Contenido que se le vaya a enviar}", activacionMensaje);
        mensaje = mensaje.replace("{imagen}", "data:image/png;base64," + base64Image);

        return mensaje;
    }
}
