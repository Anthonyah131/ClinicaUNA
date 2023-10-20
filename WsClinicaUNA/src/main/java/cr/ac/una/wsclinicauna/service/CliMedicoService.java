/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliAgenda;
import cr.ac.una.wsclinicauna.model.CliAgendaDto;
import cr.ac.una.wsclinicauna.model.CliMedico;
import cr.ac.una.wsclinicauna.model.CliMedicoDto;
import cr.ac.una.wsclinicauna.model.CliReporteagenda;
import cr.ac.una.wsclinicauna.model.CliReporteagendaDto;
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
            qryUsuario.setParameter("id", id);
            CliMedico cliMedico = (CliMedico) qryUsuario.getSingleResult();

            CliMedicoDto cliMedicoDto = new CliMedicoDto(cliMedico);
            cliMedicoDto.setCliUsuarioDto(new CliUsuarioDto(cliMedico.getUsuId()));

            for (CliAgenda cliAgenda : cliMedico.getCliAgendaList()) {
                cliMedicoDto.getCliAgendaList().add(new CliAgendaDto(cliAgenda));
            }

            for (CliReporteagenda cliReporteagenda : cliMedico.getCliReporteagendaList()) {
                cliMedicoDto.getCliReporteagendaList().add(new CliReporteagendaDto(cliReporteagenda));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Medico", cliMedicoDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un medico con el código ingresado.", "getMedico NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el medico.", "getMedico NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el medico.", "getMedico " + ex.getMessage());
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

                for (CliReporteagenda cliReporteagenda : cliMedico.getCliReporteagendaList()) {
                    cliMedicoDto.getCliReporteagendaList().add(new CliReporteagendaDto(cliReporteagenda));
                }
                cliMedicoDtos.add(cliMedicoDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Medicos", cliMedicoDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen medico con los criterios ingresados.", "getMedicos NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el medico.", "getMedicos " + ex.getMessage());
        }
    }

    public Respuesta guardarMedico(CliMedicoDto cliMedicoDto) {
        try {
            CliMedico cliMedico;
            if (cliMedicoDto.getMedId() != null && cliMedicoDto.getMedId() > 0) {
                cliMedico = em.find(CliMedico.class, cliMedicoDto.getMedId());
                if (cliMedico == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el medico a modificar.", "guardarMedico NoResultException");
                }
                cliMedico.actualizar(cliMedicoDto);

                CliUsuario cliUsuario = em.find(CliUsuario.class, cliMedicoDto.getCliUsuarioDto().getUsuId());
                cliMedico.setUsuId(cliUsuario);

                for (CliReporteagendaDto cliReporteagendaDto : cliMedicoDto.getCliReporteagendaList()) {
                    if (cliReporteagendaDto.getModificado()) {
                        CliReporteagenda cliReporteagenda = em.find(CliReporteagenda.class, cliReporteagendaDto.getRepageId());
                        cliMedico.getCliReporteagendaList().add(cliReporteagenda);
                    }
                }

                for (CliReporteagendaDto cliReporteagendaDto : cliMedicoDto.getCliReporteagendaListEliminados()) {
                    cliMedico.getCliReporteagendaList().remove(new CliReporteagenda(cliReporteagendaDto.getRepageId()));
                }

                for (CliAgendaDto cliAgendaDto : cliMedicoDto.getCliAgendaList()) {
                    if (cliAgendaDto.getModificado()) {
                        CliAgenda cliAgenda = em.find(CliAgenda.class, cliAgendaDto.getAgeId());
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
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el medico.", "guardarMedico " + ex.getMessage());
        }
    }

    public Respuesta eliminarMedico(Long id) {
        try {
            CliMedico cliMedico;
            if (id != null && id > 0) {
                cliMedico = em.find(CliMedico.class, id);
                if (cliMedico == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el medico a eliminar.", "eliminarMedico NoResultException");
                }
                em.remove(cliMedico);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el medico a eliminar.", "eliminarMedico NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el medico porque tiene relaciones con otros registros.", "eliminarMedico " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el medico.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el medico.", "eliminarMedico " + ex.getMessage());
        }
    }
}
