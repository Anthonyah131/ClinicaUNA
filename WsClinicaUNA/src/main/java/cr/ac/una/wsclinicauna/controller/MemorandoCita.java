package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliCitaDto;
import cr.ac.una.wsclinicauna.model.CliPacienteDto;
import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.service.CliCitaService;
import cr.ac.una.wsclinicauna.service.CliParametrosService;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
@Singleton
public class MemorandoCita {
    //TODO MANDAR CORREO DE AGENDACION DE CITA

    @EJB
    CliCitaService cliCitaService;
    @EJB
    CliParametrosService cliParametrosService;

    @Schedule(hour = "10", minute = "38", persistent = false)
    private void memorandoCita() {
        try {
            Respuesta res = cliCitaService.getCitas();
            List<CliCitaDto> cliCitaDtos = (List<CliCitaDto>) res.getResultado("Citas");
            Respuesta re2 = cliParametrosService.getParametros();
            List<CliParametrosDto> cliParametrosDtoList = (List<CliParametrosDto>) re2.getResultado("Parametros");
            CliParametrosDto cliParametrosDto = cliParametrosDtoList.get(0);

            for (CliCitaDto citaDto : cliCitaDtos) {
                Long diferencia = ChronoUnit.DAYS.between(citaDto.getFecha().toLocalDate(), LocalDate.now());
                if (diferencia == 1) {
                    memorandoEmail(citaDto.getCliPacienteDto(), cliParametrosDto, citaDto);
                }
            }
        } catch (AddressException e) {
            Logger.getLogger(MemorandoCita.class.getName()).log(Level.SEVERE, null, e);
        } catch (MessagingException e) {
            Logger.getLogger(MemorandoCita.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(MemorandoCita.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void memorandoEmail(CliPacienteDto cliPacienteDto, CliParametrosDto cliParametrosDto, CliCitaDto citaDto) throws AddressException, MessagingException, IOException {
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

    }

    private String mensajeEmail(CliPacienteDto cliPacienteDto, byte[] html, byte[] logo, String nombre, LocalDate fechaCita) throws UnknownHostException, IOException {
        String base64Image = convertirABase64(logo);
        String mensaje = convertirBytesAHTML(html);
        String activacionMensaje = "Hola por parte de" + nombre + " le recordamos que debe asistir a su cita el dia: " + fechaCita + "Gracias de antemano";
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
