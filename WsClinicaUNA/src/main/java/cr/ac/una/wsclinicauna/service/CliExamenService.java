package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliAtencion;
import cr.ac.una.wsclinicauna.model.CliAtencionDto;
import cr.ac.una.wsclinicauna.model.CliExamen;
import cr.ac.una.wsclinicauna.model.CliExamenDto;
import cr.ac.una.wsclinicauna.model.CliExpediente;
import cr.ac.una.wsclinicauna.model.CliExpedienteDto;
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
public class CliExamenService {

    private static final Logger LOG = Logger.getLogger(CliExamenService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getExamen(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliExamen.findByExaId", CliExamen.class);
            qryUsuario.setParameter("exaId", id);
            CliExamen cliExamen = (CliExamen) qryUsuario.getSingleResult();

            CliExamenDto cliExamenDto = new CliExamenDto(cliExamen);
            cliExamenDto.setCliAtencionDto(new CliAtencionDto(cliExamen.getAteId()));
            cliExamenDto.setCliExpedienteDto(new CliExpedienteDto(cliExamen.getExpId()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Examen", cliExamenDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorCitaExamen", "getExamen NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColExamen", "getExamen NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColExamen", "getExamen " + ex.getMessage());
        }
    }

    public Respuesta getExamenes() {
        try {
            Query qryUsuario = em.createNamedQuery("CliExamen.findAll", CliExamen.class);
            List<CliExamen> cliExamens = qryUsuario.getResultList();
            List<CliExamenDto> cliExamenDtos = new ArrayList<>();
            for (CliExamen cliExamen : cliExamens) {
                CliExamenDto cliExamenDto = new CliExamenDto(cliExamen);

                cliExamenDto.setCliAtencionDto(new CliAtencionDto(cliExamen.getAteId()));
                cliExamenDto.setCliExpedienteDto(new CliExpedienteDto(cliExamen.getExpId()));
                cliExamenDtos.add(cliExamenDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Examenes", cliExamenDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorDontExistExamen", "getExamenes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColExamen", "getExamenes " + ex.getMessage());
        }
    }

    public Respuesta guardarExamen(CliExamenDto cliExamenDto) {
        try {
            CliExamen cliExamen;
            if (cliExamenDto.getExaId() != null && cliExamenDto.getExaId() > 0) {
                cliExamen = em.find(CliExamen.class, cliExamenDto.getExaId());
                if (cliExamen == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundExamen", "guardarExamen NoResultException");
                }
                cliExamen.actualizar(cliExamenDto);
                CliAtencion atencion = em.find(CliAtencion.class, cliExamenDto.getCliAtencionDto().getAteId());
                cliExamen.setAteId(atencion);

                CliExpediente cliExpediente = em.find(CliExpediente.class, cliExamenDto.getCliExpedienteDto().getExpId());
                cliExamen.setExpId(cliExpediente);
                cliExamen = em.merge(cliExamen);
            } else {
                cliExamen = new CliExamen(cliExamenDto);
                em.persist(cliExamen);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Examen", new CliExamenDto(cliExamen));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorSaveExamen", "guardarExamen " + ex.getMessage());
        }
    }

    public Respuesta eliminarExamen(Long id) {
        try {
            CliExamen cliExamen;
            if (id != null && id > 0) {
                cliExamen = em.find(CliExamen.class, id);
                if (cliExamen == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundExamenDel", "eliminarExamen NoResultException");
                }
                em.remove(cliExamen);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.loadExamenDel", "eliminarExamen NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.noExamenDelRela", "eliminarExamen " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el examen.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorDelExamen", "eliminarExamen " + ex.getMessage());
        }
    }
}
