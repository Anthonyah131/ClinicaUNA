package cr.ac.una.wsclinicauna.controller;

import cr.ac.una.wsclinicauna.model.CliCitaDto;
import cr.ac.una.wsclinicauna.model.CliCorreodestinoDto;
import cr.ac.una.wsclinicauna.model.CliPacienteDto;
import cr.ac.una.wsclinicauna.model.CliParametrosDto;
import cr.ac.una.wsclinicauna.model.CliReporteDto;
import cr.ac.una.wsclinicauna.service.CliCitaService;
import cr.ac.una.wsclinicauna.service.CliParametrosService;
import cr.ac.una.wsclinicauna.service.CliReporteService;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.activation.DataHandler;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author ArauzKJ
 */
@Singleton
public class EnvioCorreo {
    //TODO MANDAR CORREO DE AGENDACION DE CITA

    @EJB
    CliCitaService cliCitaService;
    @EJB
    CliParametrosService cliParametrosService;

    @EJB
    CliReporteService cliReporteService;

    @Schedule(hour = "0", minute = "54", persistent = false)
    private void envioReporte() {
        try {
            Respuesta res = cliReporteService.getReportes();
            List<CliReporteDto> cliReporteDtos = (List<CliReporteDto>) res.getResultado("Reportes");
            Respuesta re2 = cliParametrosService.getParametros();
            List<CliParametrosDto> cliParametrosDtoList = (List<CliParametrosDto>) re2.getResultado("Parametros");
            CliParametrosDto cliParametrosDto = cliParametrosDtoList.get(0);

            for (CliReporteDto reporteDto : cliReporteDtos) {
                // Obtener el día actual
                LocalDate fechaActual = LocalDate.now();

                // Convertir el día devuelto a mayúsculas para asegurar la comparación
                String diaReporte = reporteDto.getRepPeriodicidad().toLowerCase(); // Suponiendo que reporteDto.getRepPeriodicidad() devuelve el día en mayúsculas

                // Mapear el nombre del día a un valor de DayOfWeek
                String diaActual = getSpanishDayOfWeek(fechaActual);

                // Comparar si el día actual es igual al día devuelto
                if (diaActual.toLowerCase().equalsIgnoreCase(diaReporte)) {
                    reporteEmail(reporteDto, cliParametrosDto);
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getSpanishDayOfWeek(LocalDate fecha) {
        DayOfWeek diaActual = fecha.getDayOfWeek();
        Locale spanishLocale = new Locale("es", "ES"); // Establecer el idioma español

        // Obtener el nombre del día en español
        String nombreDia = diaActual.getDisplayName(TextStyle.FULL, spanishLocale);
        return nombreDia;
    }

    private void reporteEmail(CliReporteDto cliReporteDto, CliParametrosDto cliParametrosDto) throws AddressException, MessagingException, IOException {
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
        String asunto = "ClinicaUNA";

        Respuesta re = cliReporteService.generarInformeExcelDesdeConsultaSQL(cliReporteDto);
        byte[] excelData = (byte[]) re.getResultado("ReporteExcel");

        //Mensaje que va a ser enviado
        Multipart multipart = mensajeEmailReporte(cliReporteDto, cliParametrosDto.getParHtml(), cliParametrosDto.getParLogo(), cliParametrosDto.getParNombre(), excelData);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(correoRemitente));

        for (CliCorreodestinoDto correoDto : cliReporteDto.getCliCorreodestinoList()) {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDto.getCdCorreo()));
        }
        message.setSubject(asunto);
//        message.setText(mensaje, "ISO-8859-1", "html");
        // Establecer el contenido del mensaje
        message.setContent(multipart);

        Transport t = session.getTransport("smtp");
        t.connect(correoRemitente, passwordRemitente);
        t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        t.close();

    }

    private Multipart mensajeEmailReporte(CliReporteDto cliReporteDto, byte[] html, byte[] logo, String nombre, byte[] excelData) throws UnknownHostException, IOException {
        try {
            String base64Image = convertirABase64(logo);
            String mensaje = convertirBytesAHTML(html);
            String activacionMensaje = "Hola por parte de" + nombre + " le adjuntamos el informe generado por excel";
            mensaje = mensaje.replace("{Insertar nombre de la empresa}", nombre);
            mensaje = mensaje.replace("{Contenido que se le vaya a enviar}", activacionMensaje);
            mensaje = mensaje.replace("{imagen}", "data:image/png;base64," + base64Image);

            // Crear la parte del mensaje que contendrá el archivo adjunto
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(mensaje);

            // Crear un multipart para el mensaje
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Adjuntar el archivo generado
            messageBodyPart = new MimeBodyPart();
            ByteArrayDataSource fuenteDatos = new ByteArrayDataSource(excelData, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            messageBodyPart.setDataHandler(new DataHandler(fuenteDatos));
            messageBodyPart.setFileName("informe.xlsx");
            multipart.addBodyPart(messageBodyPart);

            return multipart;
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, e);
        } catch (MessagingException e) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(EnvioCorreo.class.getName()).log(Level.SEVERE, null, e);
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
