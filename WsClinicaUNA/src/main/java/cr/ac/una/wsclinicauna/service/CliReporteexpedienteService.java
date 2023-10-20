/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliPaciente;
import cr.ac.una.wsclinicauna.model.CliPacienteDto;
import cr.ac.una.wsclinicauna.model.CliReporteexpediente;
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
public class CliReporteexpedienteService {

    private static final Logger LOG = Logger.getLogger(CliReporteexpedienteService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getReporteexpediente(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliReporteexpediente.findByRepexpId", CliReporteexpediente.class);
            qryUsuario.setParameter("id", id);
            CliReporteexpediente cliReporteusuarios = (CliReporteexpediente) qryUsuario.getSingleResult();

            CliReporteexpedienteDto cliReporteusuariosDto = new CliReporteexpedienteDto(cliReporteusuarios);
            cliReporteusuariosDto.setCliPacienteDto(new CliPacienteDto(cliReporteusuarios.getPacId()));

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteexpediente", cliReporteusuariosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un reporteexpediente con el código ingresado.", "getReporteexpediente NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteexpediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteexpediente.", "getReporteexpediente NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteexpediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteexpediente.", "getReporteexpediente " + ex.getMessage());
        }
    }

    public Respuesta getReporteexpedientes() {
        try {
            Query qryUsuario = em.createNamedQuery("CliReporteexpediente.findAll", CliReporteexpediente.class);
            List<CliReporteexpediente> cliReporteexpedientes = qryUsuario.getResultList();
            List<CliReporteexpedienteDto> cliReporteexpedienteDtos = new ArrayList<>();
            for (CliReporteexpediente cliReporteexpediente : cliReporteexpedientes) {
                CliReporteexpedienteDto cliReporteexpedienteDto = new CliReporteexpedienteDto(cliReporteexpediente);

                cliReporteexpedienteDto.setCliPacienteDto(new CliPacienteDto(cliReporteexpediente.getPacId()));

                cliReporteexpedienteDtos.add(cliReporteexpedienteDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteexpedientes", cliReporteexpedienteDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen reporteexpediente con los criterios ingresados.", "getReporteexpedientes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteexpediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteexpediente.", "getReporteexpedientes " + ex.getMessage());
        }
    }

    public Respuesta guardarReporteexpediente(CliReporteexpedienteDto cliReporteexpedienteDto) {
        try {
            CliReporteexpediente cliReporteexpediente;
            if (cliReporteexpedienteDto.getRepexpId()!= null && cliReporteexpedienteDto.getRepexpId() > 0) {
                cliReporteexpediente = em.find(CliReporteexpediente.class, cliReporteexpedienteDto.getRepexpId());
                if (cliReporteexpediente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el reporteexpediente a modificar.", "guardarReporteexpediente NoResultException");
                }
                cliReporteexpediente.actualizar(cliReporteexpedienteDto);
                CliPaciente cliPaciente= em.find(CliPaciente.class, cliReporteexpedienteDto.getCliPacienteDto().getPacId());
                cliReporteexpediente.setPacId(cliPaciente);
                cliReporteexpediente = em.merge(cliReporteexpediente);
            } else {
                cliReporteexpediente = new CliReporteexpediente(cliReporteexpedienteDto);
                em.persist(cliReporteexpediente);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteexpediente", new CliReporteexpedienteDto(cliReporteexpediente));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el reporteexpediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el reporteexpediente.", "guardarReporteexpediente " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporteexpediente(Long id) {
        try {
            CliReporteexpediente cliReporteexpediente;
            if (id != null && id > 0) {
                cliReporteexpediente = em.find(CliReporteexpediente.class, id);
                if (cliReporteexpediente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el reporteexpediente a eliminar.", "eliminarReporteexpediente NoResultException");
                }
                em.remove(cliReporteexpediente);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el reporteexpediente a eliminar.", "eliminarReporteexpediente NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el reporteexpediente porque tiene relaciones con otros registros.", "eliminarReporteexpediente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el reporteexpediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el reporteexpediente.", "eliminarReporteexpediente " + ex.getMessage());
        }
    }
}
