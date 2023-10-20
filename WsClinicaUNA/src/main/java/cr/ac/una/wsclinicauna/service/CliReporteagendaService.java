/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliMedico;
import cr.ac.una.wsclinicauna.model.CliMedicoDto;
import cr.ac.una.wsclinicauna.model.CliReporteagenda;
import cr.ac.una.wsclinicauna.model.CliReporteagendaDto;
import cr.ac.una.wsclinicauna.model.CliReporteexpedienteDto;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
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
public class CliReporteagendaService {
    private static final Logger LOG = Logger.getLogger(CliReporteagendaService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getReporteagenda(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliReporteagenda.findByRepageId", CliReporteagenda.class);
            qryUsuario.setParameter("id", id);
            CliReporteagenda cliReporteusuarios = (CliReporteagenda) qryUsuario.getSingleResult();

            CliReporteagendaDto cliReporteusuariosDto = new CliReporteagendaDto(cliReporteusuarios);
            cliReporteusuariosDto.setCliMedicoDto(new CliMedicoDto(cliReporteusuarios.getMedId()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteagenda", cliReporteusuariosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un reporteagenda con el código ingresado.", "getReporteagenda NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteagenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteagenda.", "getReporteagenda NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteagenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteagenda.", "getReporteagenda " + ex.getMessage());
        }
    }

    public Respuesta getReporteagendas() {
        try {
            Query qryUsuario = em.createNamedQuery("CliReporteagenda.findAll", CliReporteagenda.class);
            List<CliReporteagenda> cliReporteagendas = qryUsuario.getResultList();
            List<CliReporteagendaDto> cliReporteagendaDtos = new ArrayList<>();
            for (CliReporteagenda cliReporteagenda : cliReporteagendas) {
                CliReporteagendaDto cliReporteagendaDto = new CliReporteagendaDto(cliReporteagenda);

                cliReporteagendaDto.setCliMedicoDto(new CliMedicoDto(cliReporteagenda.getMedId()));

                cliReporteagendaDtos.add(cliReporteagendaDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteagendas", cliReporteagendaDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen reporteagenda con los criterios ingresados.", "getReporteagendas NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteagenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteagenda.", "getReporteagendas " + ex.getMessage());
        }
    }

    public Respuesta guardarReporteagenda(CliReporteagendaDto cliReporteagendaDto) {
        try {
            CliReporteagenda cliReporteagenda;
            if (cliReporteagendaDto.getRepageId()!= null && cliReporteagendaDto.getRepageId()> 0) {
                cliReporteagenda = em.find(CliReporteagenda.class, cliReporteagendaDto.getRepageId());
                if (cliReporteagenda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el reporteagenda a modificar.", "guardarReporteagenda NoResultException");
                }
                cliReporteagenda.actualizar(cliReporteagendaDto);
                CliMedico cliMedico= em.find(CliMedico.class, cliReporteagendaDto.getCliMedicoDto().getMedId());
                cliReporteagenda.setMedId(cliMedico);
                cliReporteagenda = em.merge(cliReporteagenda);
            } else {
                cliReporteagenda = new CliReporteagenda(cliReporteagendaDto);
                em.persist(cliReporteagenda);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteagendas", new CliReporteagendaDto(cliReporteagenda));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el reporteagenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el reporteagenda.", "guardarReporteagenda " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporteagenda(Long id) {
        try {
            CliReporteagenda cliReporteagenda;
            if (id != null && id > 0) {
                cliReporteagenda = em.find(CliReporteagenda.class, id);
                if (cliReporteagenda == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el reporteagenda a eliminar.", "eliminarReporteagenda NoResultException");
                }
                em.remove(cliReporteagenda);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el reporteagenda a eliminar.", "eliminarReporteagenda NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el reporteagenda porque tiene relaciones con otros registros.", "eliminarReporteagenda " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el reporteagenda.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el reporteagenda.", "eliminarReporteagenda " + ex.getMessage());
        }
    }
}
