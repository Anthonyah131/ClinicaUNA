/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliAgenda;
import cr.ac.una.wsclinicauna.model.CliAgendaDto;
import cr.ac.una.wsclinicauna.model.CliCita;
import cr.ac.una.wsclinicauna.model.CliCitaDto;
import cr.ac.una.wsclinicauna.model.CliPaciente;
import cr.ac.una.wsclinicauna.model.CliPacienteDto;
import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
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
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
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
public class CliCitaService {

    private static final Logger LOG = Logger.getLogger(CliCitaService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getCita(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliCita.findByCitId", CliCita.class);
            qryUsuario.setParameter("citId", id);
            CliCita cliCita = (CliCita) qryUsuario.getSingleResult();

            CliCitaDto cliCitaDto = new CliCitaDto(cliCita);
            cliCitaDto.setCliAgendaDto(new CliAgendaDto(cliCita.getAgeId()));
            cliCitaDto.setCliPacienteDto(new CliPacienteDto(cliCita.getPacId()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Cita", cliCitaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un cita con el código ingresado.", "getCita NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el cita.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el cita.", "getCita NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el cita.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el cita.", "getCita " + ex.getMessage());
        }
    }

    public Respuesta getCitas() {
        try {
            Query qryUsuario = em.createNamedQuery("CliCita.findAll", CliCita.class);
            List<CliCita> cliCitas = qryUsuario.getResultList();
            List<CliCitaDto> cliCitaDtos = new ArrayList<>();
            for (CliCita cliCita : cliCitas) {
                CliCitaDto cliCitaDto = new CliCitaDto(cliCita);

                cliCitaDto.setCliAgendaDto(new CliAgendaDto(cliCita.getAgeId()));
                cliCitaDto.setCliPacienteDto(new CliPacienteDto(cliCita.getPacId()));

                cliCitaDtos.add(cliCitaDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Citas", cliCitaDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen citas con los criterios ingresados.", "getCitas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el citas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el citas.", "getCitas " + ex.getMessage());
        }
    }

    public Respuesta guardarCita(CliCitaDto cliCitaDto) {
        try {
            CliCita cliCita;
            if (cliCitaDto.getCitId() != null && cliCitaDto.getCitId() > 0) {
                cliCita = em.find(CliCita.class, cliCitaDto.getCitId());
                if (cliCita == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el citas a modificar.", "guardarCita NoResultException");
                }
                cliCita.actualizar(cliCitaDto);
                CliAgenda cliAgenda = em.find(CliAgenda.class, cliCitaDto.getCliAgendaDto().getAgeId());
                cliCita.setAgeId(cliAgenda);

                CliPaciente cliPaciente = em.find(CliPaciente.class, cliCitaDto.getCliPacienteDto().getPacId());
                cliCita.setPacId(cliPaciente);
                cliCita = em.merge(cliCita);
            } else {
                cliCita = new CliCita(cliCitaDto);
                em.persist(cliCita);
            }
            em.flush();
            CliCitaDto citaDto = new CliCitaDto(cliCita);
            if (cliCita.getAgeId() != null) {
                citaDto.setCliAgendaDto(new CliAgendaDto(cliCita.getAgeId()));
            }
            if (cliCita.getPacId() != null) {
                citaDto.setCliPacienteDto(new CliPacienteDto(cliCita.getPacId()));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Citas", citaDto);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el citas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el citas.", "guardarCita " + ex.getMessage());
        }
    }

    public Respuesta eliminarCita(Long id) {
        try {
            CliCita cliCita;
            if (id != null && id > 0) {
                cliCita = em.find(CliCita.class, id);
                if (cliCita == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el citas a eliminar.", "eliminarCita NoResultException");
                }
                em.remove(cliCita);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el citas a eliminar.", "eliminarCita NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el citas porque tiene relaciones con otros registros.", "eliminarCita " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el citas.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el citas.", "eliminarCita " + ex.getMessage());
        }
    }

    public Respuesta memorandoEmailAgeCita(CliPacienteDto cliPacienteDto, CliParametrosDto cliParametrosDto, CliCitaDto citaDto) throws AddressException, MessagingException, IOException {
        //setea las propiedades del smtp para poder enviar los emails
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            //proporciona el correo y contrasena del correo con el que va a ser enviado
            String correoRemitente = cliParametrosDto.getParEmail();//"CineUna123@outlook.com";
            String passwordRemitente = cliParametrosDto.getParClave();//"cine1234";
            String correoReceptor = cliPacienteDto.getPacCorreo();
            String asunto = "ClinicaUNA";

            //Mensaje que va a ser enviado
            String mensaje = mensajeEmail(cliPacienteDto, cliParametrosDto.getParHtml(), cliParametrosDto.getParLogo(), cliParametrosDto.getParNombre(), citaDto.getFecha().toLocalDate());

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
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el cita.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.saveClient", "memorandoEmailAgeCita " + ex.getMessage());
        }

    }

    private String mensajeEmail(CliPacienteDto cliPacienteDto, byte[] html, byte[] logo, String nombre, LocalDate fechaCita) throws UnknownHostException, IOException {
        String base64Image = convertirABase64(logo);
        String mensaje = convertirBytesAHTML(html);
        String activacionMensaje = "Hola por parte de" + nombre + "su cita fue agendada para el dia: " + fechaCita + "Gracias de antemano " + cliPacienteDto.getPacNombre();
        mensaje = mensaje.replace("{Insertar nombre de la empresa}", nombre);
        mensaje = mensaje.replace("{Contenido que se le vaya a enviar}", activacionMensaje);
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

}
