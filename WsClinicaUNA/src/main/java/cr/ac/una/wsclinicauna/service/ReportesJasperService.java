package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.controller.ReportesJasperController;
import cr.ac.una.wsclinicauna.model.CliMedico;
import cr.ac.una.wsclinicauna.model.CliPaciente;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author ArauzKJ
 */
@Stateless
@LocalBean
public class ReportesJasperService {

    private static final Logger LOG = Logger.getLogger(ReportesJasperService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getAngendaReport(Long id, Date fechainicial,Date fechafin) {
        try {
            Connection co = em.unwrap(Connection.class);
            CliMedico cliMedico = em.find(CliMedico.class, id);

            Map<String, Object> variables = new HashMap<>();
            variables.put("usuid", id);
            variables.put("fechainicial", fechainicial);
            variables.put("fechafin", fechafin);

            JasperReport jr = (JasperReport) JRLoader.loadObject(ReportesJasperController.class.getResource("/cr/ac/una/wsclinicauna/reportes/Report1.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jr, variables, co);
            byte[] byteReport = toByteArray(jp);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", byteReport);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorObReporte", "getAngendaReport " + ex.getMessage());
        }
    }
    
    public Respuesta getExpedienteReport(Long pacId) {
        try {
            Connection co = em.unwrap(Connection.class);
            CliPaciente cliPaciente = em.find(CliPaciente.class, pacId);

            Map<String, Object> variables = new HashMap<>();
            variables.put("pacId", pacId);

            JasperReport jr = (JasperReport) JRLoader.loadObject(ReportesJasperController.class.getResource("/cr/ac/una/wsclinicauna/reportes/Report2.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jr, variables, co);
            byte[] byteReport = toByteArray(jp);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", byteReport);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorObReporte", "getExpedienteReport " + ex.getMessage());
        }
    }
    
    public Respuesta getRendimientoMedicos(Date fechaInicial,Date fechaFin) {
        try {
            Connection co = em.unwrap(Connection.class);

            Map<String, Object> variables = new HashMap<>();
            variables.put("fechaInicial", fechaInicial);
            variables.put("fechaFin", fechaFin);

            JasperReport jr = (JasperReport) JRLoader.loadObject(ReportesJasperController.class.getResource("/cr/ac/una/wsclinicauna/reportes/Report3.jasper"));

            JasperPrint jp = JasperFillManager.fillReport(jr, variables, co);
            byte[] byteReport = toByteArray(jp);
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporte", byteReport);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el usuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorObReporte", "getAngendaReport " + ex.getMessage());
        }
    }

    private byte[] toByteArray(Object ob) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(bos);
            obs.writeObject(ob);
            return bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(ReportesJasperService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
