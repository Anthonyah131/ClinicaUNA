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
public class CliAtencionService {

    private static final Logger LOG = Logger.getLogger(CliAtencionService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getAtencion(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliAtencion.findByAteId", CliAtencion.class);
            qryUsuario.setParameter("ateId", id);
            CliAtencion cliAtencion = (CliAtencion) qryUsuario.getSingleResult();

            CliAtencionDto cliAtencionDto = new CliAtencionDto(cliAtencion);
            cliAtencionDto.setCliExpedienteDto(new CliExpedienteDto(cliAtencion.getExpId()));

            for (CliExamen cliExamen : cliAtencion.getCliExamenList()) {
                cliAtencionDto.getCliExamenList().add(new CliExamenDto(cliExamen));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Atencion", cliAtencionDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorAtencionCod", "getAtencion NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el atencion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColAtencion", "getAtencion NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el atencion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColAtencion", "getAtencion " + ex.getMessage());
        }
    }

    public Respuesta getAtenciones() {
        try {
            Query qryUsuario = em.createNamedQuery("CliAtencion.findAll", CliAtencion.class);
            List<CliAtencion> cliAtencions = qryUsuario.getResultList();
            List<CliAtencionDto> cliAtencionDtos = new ArrayList<>();
            for (CliAtencion cliAtencion : cliAtencions) {
                CliAtencionDto cliAtencionDto = new CliAtencionDto(cliAtencion);

                cliAtencionDto.setCliExpedienteDto(new CliExpedienteDto(cliAtencion.getExpId()));

                for (CliExamen cliExamen : cliAtencion.getCliExamenList()) {
                    cliAtencionDto.getCliExamenList().add(new CliExamenDto(cliExamen));
                }

                cliAtencionDtos.add(cliAtencionDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Atenciones", cliAtencionDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorDontExistAtencio", "getAtenciones NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el atencion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColAtencion", "getAtenciones " + ex.getMessage());
        }
    }

    public Respuesta guardarAtencion(CliAtencionDto cliAtencionDto) {
        try {
            CliAtencion cliAtencion;
            if (cliAtencionDto.getAteId() != null && cliAtencionDto.getAteId() > 0) {
                cliAtencion = em.find(CliAtencion.class, cliAtencionDto.getAteId());
                if (cliAtencion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundAtencion", "guardarAtencion NoResultException");
                }
                cliAtencion.actualizar(cliAtencionDto);
                if (cliAtencionDto.getCliExpedienteDto() != null) {
                    CliExpediente cliExpediente = em.find(CliExpediente.class, cliAtencionDto.getCliExpedienteDto().getExpId());
                    cliAtencion.setExpId(cliExpediente);
                }

                for (CliExamenDto cliExamenDto : cliAtencionDto.getCliExamenList()) {
                    if (cliExamenDto.getModificado()) {
                        CliExamen cliExamen = em.find(CliExamen.class, cliExamenDto.getExaId());
                        cliAtencion.getCliExamenList().add(cliExamen);
                        cliExamen.setAteId(cliAtencion);
                    }
                }

                for (CliExamenDto cliExamenDto : cliAtencionDto.getCliExamenListEliminados()) {
                    cliAtencion.getCliExamenList().remove(new CliExamen(cliExamenDto.getExaId()));
                }

                cliAtencion = em.merge(cliAtencion);
            } else {
                cliAtencion = new CliAtencion(cliAtencionDto);
                em.persist(cliAtencion);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Atencion", new CliAtencionDto(cliAtencion));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el atencion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorSaveAtencion", "guardarAtencion " + ex.getMessage());
        }
    }

    public Respuesta eliminarAtencion(Long id) {
        try {
            CliAtencion cliAtencion;
            if (id != null && id > 0) {
                cliAtencion = em.find(CliAtencion.class, id);
                if (cliAtencion == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundAtencionDel", "eliminarAtencion NoResultException");
                }
                em.remove(cliAtencion);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.loadAtencionDel", "eliminarAtencion NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.noAtencionDelRela", "eliminarAtencion " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el atencion.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorDelAtencion", "eliminarAtencion " + ex.getMessage());
        }
    }
}
