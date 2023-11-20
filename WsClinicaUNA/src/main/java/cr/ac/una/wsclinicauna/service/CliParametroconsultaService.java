package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliParametroconsulta;
import cr.ac.una.wsclinicauna.model.CliParametroconsultaDto;
import cr.ac.una.wsclinicauna.model.CliReporte;
import cr.ac.una.wsclinicauna.model.CliReporteDto;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ArauzKJ
 */
@Stateless
@LocalBean
public class CliParametroconsultaService {

    private static final Logger LOG = Logger.getLogger(CliParametroconsultaService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getParametroconsulta(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliParametroconsulta.findByParcId", CliParametroconsulta.class);
            qryUsuario.setParameter("parcId", id);
            CliParametroconsulta cliParametroconsulta = (CliParametroconsulta) qryUsuario.getSingleResult();

            CliParametroconsultaDto cliParametroconsultaDto = new CliParametroconsultaDto(cliParametroconsulta);
            cliParametroconsultaDto.setCliReporteDto(new CliReporteDto(cliParametroconsulta.getRepId()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametroconsulta", cliParametroconsultaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorCitaParConsulta", "getParametroconsulta NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parametroconsulta.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColParConsulta", "getParametroconsulta NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Parametroconsulta.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColParConsulta", "getParametroconsulta " + ex.getMessage());
        }
    }

    public Respuesta getParametroconsultas() {
        try {
            Query qryUsuario = em.createNamedQuery("CliParametroconsulta.findAll", CliParametroconsulta.class);
            List<CliParametroconsulta> cliParametroconsultas = qryUsuario.getResultList();
            List<CliParametroconsultaDto> cliParametroconsultaDtos = new ArrayList<>();
            for (CliParametroconsulta cliParametroconsulta : cliParametroconsultas) {
                CliParametroconsultaDto cliParametroconsultaDto = new CliParametroconsultaDto(cliParametroconsulta);

                cliParametroconsultaDto.setCliReporteDto(new CliReporteDto(cliParametroconsulta.getRepId()));

                cliParametroconsultaDtos.add(cliParametroconsultaDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametroconsultas", cliParametroconsultaDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorCitaParConsulta", "getCliParametroconsultas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el CliParametroconsulta.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColParConsulta", "getCliParametroconsultas " + ex.getMessage());
        }
    }

    public Respuesta guardarParametroconsulta(CliParametroconsultaDto cliParametroconsultaDto) {
        try {
            CliParametroconsulta cliParametroconsulta;
            if (cliParametroconsultaDto.getParcId()!= null && cliParametroconsultaDto.getParcId()> 0) {
                cliParametroconsulta = em.find(CliParametroconsulta.class, cliParametroconsultaDto.getParcId());
                if (cliParametroconsulta == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundParConsulta", "guardarCliParametroconsulta NoResultException");
                }
                cliParametroconsulta.actualizar(cliParametroconsultaDto);
                CliReporte cliPaciente = em.find(CliReporte.class, cliParametroconsultaDto.getCliReporteDto().getRepId());
                cliParametroconsulta.setRepId(cliPaciente);

                cliParametroconsulta = em.merge(cliParametroconsulta);
            } else {
                cliParametroconsulta = new CliParametroconsulta(cliParametroconsultaDto);
                em.persist(cliParametroconsulta);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametroconsulta", new CliParametroconsultaDto(cliParametroconsulta));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parametroconsulta.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorSaveParConsulta", "guardarParametroconsulta " + ex.getMessage());
        }
    }

    public Respuesta eliminarParametroconsulta(Long id) {
        try {
            CliParametroconsulta cliParametroconsulta;
            if (id != null && id > 0) {
                cliParametroconsulta = em.find(CliParametroconsulta.class, id);
                if (cliParametroconsulta == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundParConsulta", "eliminarParametroconsulta NoResultException");
                }
                em.remove(cliParametroconsulta);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.loadParConsultaDel", "eliminarParametroconsulta NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.noParConsultaDelRela", "eliminarParametroconsulta " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Parametroconsulta.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorDelParConsulta", "eliminarParametroconsulta " + ex.getMessage());
        }
    }
}
