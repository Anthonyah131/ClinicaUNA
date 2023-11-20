package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliAgenda;
import cr.ac.una.wsclinicauna.model.CliAgendaDto;
import cr.ac.una.wsclinicauna.model.CliMedico;
import cr.ac.una.wsclinicauna.model.CliMedicoDto;
import cr.ac.una.wsclinicauna.model.CliUsuario;
import cr.ac.una.wsclinicauna.model.CliUsuarioDto;
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
public class CliMedicoService {

    private static final Logger LOG = Logger.getLogger(CliMedicoService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getMedico(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliMedico.findByMedId", CliMedico.class);
            qryUsuario.setParameter("medId", id);
            CliMedico cliMedico = (CliMedico) qryUsuario.getSingleResult();

            CliMedicoDto cliMedicoDto = new CliMedicoDto(cliMedico);
            cliMedicoDto.setCliUsuarioDto(new CliUsuarioDto(cliMedico.getUsuId()));

            for (CliAgenda cliAgenda : cliMedico.getCliAgendaList()) {
                cliMedicoDto.getCliAgendaList().add(new CliAgendaDto(cliAgenda));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Medico", cliMedicoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorCitaMedico", "getMedico NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColMedico", "getMedico NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColMedico", "getMedico " + ex.getMessage());
        }
    }

    public Respuesta getMedicos() {
        try {
            Query qryUsuario = em.createNamedQuery("CliMedico.findAll", CliMedico.class);
            List<CliMedico> cliMedicos = qryUsuario.getResultList();
            List<CliMedicoDto> cliMedicoDtos = new ArrayList<>();
            for (CliMedico cliMedico : cliMedicos) {
                CliMedicoDto cliMedicoDto = new CliMedicoDto(cliMedico);

                cliMedicoDto.setCliUsuarioDto(new CliUsuarioDto(cliMedico.getUsuId()));
                for (CliAgenda cliAgenda : cliMedico.getCliAgendaList()) {
                    cliMedicoDto.getCliAgendaList().add(new CliAgendaDto(cliAgenda));
                }

                cliMedicoDtos.add(cliMedicoDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Medicos", cliMedicoDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.errorDontExistMedico", "getMedicos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorColMedico", "getMedicos " + ex.getMessage());
        }
    }

    public Respuesta guardarMedico(CliMedicoDto cliMedicoDto) {
        try {
            CliMedico cliMedico;
            if (cliMedicoDto.getMedId() != null && cliMedicoDto.getMedId() > 0) {
                cliMedico = em.find(CliMedico.class, cliMedicoDto.getMedId());
                if (cliMedico == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundMedico", "guardarMedico NoResultException");
                }
                cliMedico.actualizar(cliMedicoDto);

                CliUsuario cliUsuario = em.find(CliUsuario.class, cliMedicoDto.getCliUsuarioDto().getUsuId());
                cliMedico.setUsuId(cliUsuario);

                for (CliAgendaDto cliAgendaDto : cliMedicoDto.getCliAgendaList()) {
                    if (cliAgendaDto.getModificado()) {
                        CliAgenda cliAgenda = em.find(CliAgenda.class, cliAgendaDto.getAgeId());
                        cliAgenda.setMedId(cliMedico);
                        cliMedico.getCliAgendaList().add(cliAgenda);
                    }
                }

                for (CliAgendaDto cliAgendaDto : cliMedicoDto.getCliAgendaListEliminados()) {
                    cliMedico.getCliAgendaList().remove(new CliAgenda(cliAgendaDto.getAgeId()));
                }
                cliMedico = em.merge(cliMedico);
            } else {
                cliMedico = new CliMedico(cliMedicoDto);
                em.persist(cliMedico);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Medico", new CliMedicoDto(cliMedico));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorSaveMedico", "guardarMedico " + ex.getMessage());
        }
    }

    public Respuesta eliminarMedico(Long id) {
        try {
            CliMedico cliMedico;
            if (id != null && id > 0) {
                cliMedico = em.find(CliMedico.class, id);
                if (cliMedico == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.noFoundMedicoDel", "eliminarMedico NoResultException");
                }
                em.remove(cliMedico);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "key.loadMedicoDel", "eliminarMedico NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.noMedicoDelRela", "eliminarMedico " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "key.errorDelMedico", "eliminarMedico " + ex.getMessage());
        }
    }
}
