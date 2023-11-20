package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliAntecedente;
import cr.ac.una.wsclinicauna.model.CliAntecedenteDto;
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
public class CliAntecedenteService {

    private static final Logger LOG = Logger.getLogger(CliAntecedenteService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getAntecedente(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliAntecedente.findByAntId", CliAntecedente.class);
            qryUsuario.setParameter("antId", id);
            CliAntecedente cliAntecedente = (CliAntecedente) qryUsuario.getSingleResult();

            CliAntecedenteDto cliAntecedenteDto = new CliAntecedenteDto(cliAntecedente);
            cliAntecedenteDto.setCliExpedienteDto(new CliExpedienteDto(cliAntecedente.getExpId()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Antecedente", cliAntecedenteDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorAntecedenteCod", "getAntecedente NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColAntecedente", "getAntecedente NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColAntecedente", "getAntecedente " + ex.getMessage());
        }
    }

    public Respuesta getAntecedentes() {
        try {
            Query qryUsuario = em.createNamedQuery("CliAntecedente.findAll", CliAntecedente.class);
            List<CliAntecedente> cliAntecedentes = qryUsuario.getResultList();
            List<CliAntecedenteDto> cliAntecedenteDtos = new ArrayList<>();
            for (CliAntecedente cliAntecedente : cliAntecedentes) {
                CliAntecedenteDto cliAntecedenteDto = new CliAntecedenteDto(cliAntecedente);

                cliAntecedenteDto.setCliExpedienteDto(new CliExpedienteDto(cliAntecedente.getExpId()));

                cliAntecedenteDtos.add(cliAntecedenteDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Antecedentes", cliAntecedenteDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorDontExistAnte", "getAntecedentes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColAntecedente", "getAntecedentes " + ex.getMessage());
        }
    }

    public Respuesta guardarAntecedente(CliAntecedenteDto cliAntecedenteDto) {
        try {
            CliAntecedente cliAntecedente;
            if (cliAntecedenteDto.getAntId() != null && cliAntecedenteDto.getAntId() > 0) {
                cliAntecedente = em.find(CliAntecedente.class, cliAntecedenteDto.getAntId());
                if (cliAntecedente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundAntecedent", "guardarAntecedente NoResultException");
                }
                cliAntecedente.actualizar(cliAntecedenteDto);
                if (cliAntecedenteDto.getCliExpedienteDto() != null) {
                    CliExpediente cliExpediente = em.find(CliExpediente.class, cliAntecedenteDto.getCliExpedienteDto().getExpId());
                    cliAntecedente.setExpId(cliExpediente);
                }
                cliAntecedente = em.merge(cliAntecedente);
            } else {
                cliAntecedente = new CliAntecedente(cliAntecedenteDto);
                em.persist(cliAntecedente);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Antecedente", new CliAntecedenteDto(cliAntecedente));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorSaveAntecedente", "guardarAntecedente " + ex.getMessage());
        }
    }

    public Respuesta eliminarAntecedente(Long id) {
        try {
            CliAntecedente cliAntecedente;
            if (id != null && id > 0) {
                cliAntecedente = em.find(CliAntecedente.class, id);
                if (cliAntecedente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundAnteDel", "eliminarAntecedente NoResultException");
                }
                em.remove(cliAntecedente);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.loadAnteDel", "eliminarAntecedente NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.noAnteDelRela", "eliminarAntecedente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorDelAntecedente", "eliminarAntecedente " + ex.getMessage());
        }
    }
}
