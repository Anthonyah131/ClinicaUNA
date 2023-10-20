/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliAgenda;
import cr.ac.una.wsclinicauna.model.CliAgendaDto;
import cr.ac.una.wsclinicauna.model.CliCita;
import cr.ac.una.wsclinicauna.model.CliCitaDto;
import cr.ac.una.wsclinicauna.model.CliMedico;
import cr.ac.una.wsclinicauna.model.CliMedicoDto;
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
public class CliAgendaService {

    private static final Logger LOG = Logger.getLogger(CliAgendaService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getAgenda(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliAgenda.findByAgeId", CliAgenda.class);
            qryUsuario.setParameter("id", id);
            CliAgenda cliAgenda = (CliAgenda) qryUsuario.getSingleResult();

            CliAgendaDto cliAgendaDto = new CliAgendaDto(cliAgenda);
            cliAgendaDto.setCliMedicoDto(new CliMedicoDto(cliAgenda.getMedId()));

            for (CliCita cliCita : cliAgenda.getCliCitaList()) {
                cliAgendaDto.getCliCitaList().add(new CliCitaDto(cliCita));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Agenda", cliAgendaDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un agenda con el código ingresado.", "getAgenda NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el agenda.", "getAgenda NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el agenda.", "getAgenda " + ex.getMessage());
        }
    }

    public Respuesta getAgendas() {
        try {
            Query qryUsuario = em.createNamedQuery("CliAgenda.findAll", CliAgenda.class);
            List<CliAgenda> cliAgendas = qryUsuario.getResultList();
            List<CliAgendaDto> cliAgendaDtos = new ArrayList<>();
            for (CliAgenda cliAgenda : cliAgendas) {
                CliAgendaDto cliAgendaDto = new CliAgendaDto(cliAgenda);

                cliAgendaDto.setCliMedicoDto(new CliMedicoDto(cliAgenda.getMedId()));

                for (CliCita cliCita : cliAgenda.getCliCitaList()) {
                    cliAgendaDto.getCliCitaList().add(new CliCitaDto(cliCita));
                }
                cliAgendaDtos.add(cliAgendaDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Agendas", cliAgendaDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen agenda con los criterios ingresados.", "getAgendas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el agenda.", "getAgendas " + ex.getMessage());
        }
    }

    public Respuesta guardarAgenda(CliAgendaDto cliAgendaDto) {
        try {
            CliAgenda cliAgenda;
            if (cliAgendaDto.getAgeId() != null && cliAgendaDto.getAgeId() > 0) {
                cliAgenda = em.find(CliAgenda.class, cliAgendaDto.getAgeId());
                if (cliAgenda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el agenda a modificar.", "guardarAgenda NoResultException");
                }
                cliAgenda.actualizar(cliAgendaDto);
                CliMedico cliMedico = em.find(CliMedico.class, cliAgendaDto.getCliMedicoDto().getMedId());
                cliAgenda.setMedId(cliMedico);
                
                for (CliCitaDto cliCitaDto : cliAgendaDto.getCliCitaList()) {
                    if (cliCitaDto.getModificado()) {
                        CliCita cliCita = em.find(CliCita.class, cliCitaDto.getCitId());
                        cliAgenda.getCliCitaList().add(cliCita);
                    }
                }

                for (CliCitaDto cliCitaDto : cliAgendaDto.getCliCitaListEliminados()) {
                    cliAgenda.getCliCitaList().remove(new CliCita(cliCitaDto.getCitId()));
                }

                cliAgenda = em.merge(cliAgenda);
            } else {
                cliAgenda = new CliAgenda(cliAgendaDto);
                em.persist(cliAgenda);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Agenda", new CliAgendaDto(cliAgenda));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el agenda.", "guardarAgenda " + ex.getMessage());
        }
    }

    public Respuesta eliminarAgenda(Long id) {
        try {
            CliAgenda cliAgenda;
            if (id != null && id > 0) {
                cliAgenda = em.find(CliAgenda.class, id);
                if (cliAgenda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el agenda a eliminar.", "eliminarAgenda NoResultException");
                }
                em.remove(cliAgenda);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el agenda a eliminar.", "eliminarAgenda NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el agenda porque tiene relaciones con otros registros.", "eliminarAgenda " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el agenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el agenda.", "eliminarAgenda " + ex.getMessage());
        }
    }
}
