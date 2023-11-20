package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliCorreodestino;
import cr.ac.una.wsclinicauna.model.CliCorreodestinoDto;
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
public class CliCorreodestinoService {

    private static final Logger LOG = Logger.getLogger(CliCorreodestinoService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getCorreodestino(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliCorreodestino.findByCdId", CliCorreodestino.class);
            qryUsuario.setParameter("cdId", id);
            CliCorreodestino cliCorreodestino = (CliCorreodestino) qryUsuario.getSingleResult();

            CliCorreodestinoDto cliCorreodestinoDto = new CliCorreodestinoDto(cliCorreodestino);
            cliCorreodestinoDto.setCliReporteDto(new CliReporteDto(cliCorreodestino.getRepId()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correodestino", cliCorreodestinoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorCitaCorreo", "getCorreodestino NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Correodestino.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorObCorreo", "getCorreodestino NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Correodestino.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorObCorreo", "getCorreodestino " + ex.getMessage());
        }
    }

    public Respuesta getCorreodestinos() {
        try {
            Query qryUsuario = em.createNamedQuery("CliCorreodestino.findAll", CliCorreodestino.class);
            List<CliCorreodestino> cliCorreodestinos = qryUsuario.getResultList();
            List<CliCorreodestinoDto> cliCorreodestinoDtos = new ArrayList<>();
            for (CliCorreodestino cliCorreodestino : cliCorreodestinos) {
                CliCorreodestinoDto cliCorreodestinoDto = new CliCorreodestinoDto(cliCorreodestino);

                cliCorreodestinoDto.setCliReporteDto(new CliReporteDto(cliCorreodestino.getRepId()));

                cliCorreodestinoDtos.add(cliCorreodestinoDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correodestinos", cliCorreodestinoDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorDontExistCorreo", "getCorreodestinos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el Correodestino.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColCorreo", "getCorreodestinos " + ex.getMessage());
        }
    }

    public Respuesta guardarCorreodestino(CliCorreodestinoDto cliCorreodestinoDto) {
        try {
            CliCorreodestino cliCorreodestino;
            if (cliCorreodestinoDto.getCdId() != null && cliCorreodestinoDto.getCdId() > 0) {
                cliCorreodestino = em.find(CliCorreodestino.class, cliCorreodestinoDto.getCdId());
                if (cliCorreodestino == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundCorreo", "guardarCorreodestino NoResultException");
                }
                cliCorreodestino.actualizar(cliCorreodestinoDto);

                CliReporte cliReporte = em.find(CliReporte.class, cliCorreodestinoDto.getCliReporteDto().getRepId());
                cliCorreodestino.setRepId(cliReporte);

                cliCorreodestino = em.merge(cliCorreodestino);
            } else {
                cliCorreodestino = new CliCorreodestino(cliCorreodestinoDto);
                em.persist(cliCorreodestino);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Correodestino", new CliCorreodestinoDto(cliCorreodestino));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Correodestino.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorSaveCorreo", "guardarCorreodestino " + ex.getMessage());
        }
    }

    public Respuesta eliminarCorreodestino(Long id) {
        try {
            CliCorreodestino cliCorreodestino;
            if (id != null && id > 0) {
                cliCorreodestino = em.find(CliCorreodestino.class, id);
                if (cliCorreodestino == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundCorreoDel", "eliminarCorreodestino NoResultException");
                }
                em.remove(cliCorreodestino);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.loadCorreoDel", "eliminarCorreodestino NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.noCorreoDelRela", "eliminarExpediente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el Correodestino.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorDelCorreo", "eliminarCorreodestino " + ex.getMessage());
        }
    }
}
