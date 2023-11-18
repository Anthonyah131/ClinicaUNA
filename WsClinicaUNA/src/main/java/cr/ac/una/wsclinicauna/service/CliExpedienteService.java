/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliAntecedente;
import cr.ac.una.wsclinicauna.model.CliAntecedenteDto;
import cr.ac.una.wsclinicauna.model.CliAtencion;
import cr.ac.una.wsclinicauna.model.CliAtencionDto;
import cr.ac.una.wsclinicauna.model.CliExamen;
import cr.ac.una.wsclinicauna.model.CliExamenDto;
import cr.ac.una.wsclinicauna.model.CliExpediente;
import cr.ac.una.wsclinicauna.model.CliExpedienteDto;
import cr.ac.una.wsclinicauna.model.CliPaciente;
import cr.ac.una.wsclinicauna.model.CliPacienteDto;
import cr.ac.una.wsclinicauna.model.CliParametroconsulta;
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
public class CliExpedienteService {

    private static final Logger LOG = Logger.getLogger(CliExpedienteService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getExpediente(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliExpediente.findByExpId", CliExpediente.class);
            qryUsuario.setParameter("expId", id);
            CliExpediente cliExpediente = (CliExpediente) qryUsuario.getSingleResult();

            CliExpedienteDto cliExpedienteDto = new CliExpedienteDto(cliExpediente);
            cliExpedienteDto.setCliPacienteDto(new CliPacienteDto(cliExpediente.getPacId()));

            for (CliExamen cliExamen : cliExpediente.getCliExamenList()) {
                cliExpedienteDto.getCliExamenList().add(new CliExamenDto(cliExamen));
            }
            for (CliAtencion cliAtencion : cliExpediente.getCliAtencionList()) {
                cliExpedienteDto.getCliAtencionList().add(new CliAtencionDto(cliAtencion));
            }
            for (CliAntecedente cliAntecedente : cliExpediente.getCliAntecedenteList()) {
                cliExpedienteDto.getCliAntecedenteList().add(new CliAntecedenteDto(cliAntecedente));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Expediente", cliExpedienteDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un expediente con el código ingresado.", "getExpediente NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el expediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el expediente.", "getExpediente NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el expediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el expediente.", "getExpediente " + ex.getMessage());
        }
    }

    public Respuesta getExpedientes() {
        try {
            Query qryUsuario = em.createNamedQuery("CliExpediente.findAll", CliExpediente.class);
            List<CliExpediente> cliExpedientes = qryUsuario.getResultList();
            List<CliExpedienteDto> cliExpedienteDtos = new ArrayList<>();
            for (CliExpediente cliExpediente : cliExpedientes) {
                CliExpedienteDto cliExpedienteDto = new CliExpedienteDto(cliExpediente);

                cliExpedienteDto.setCliPacienteDto(new CliPacienteDto(cliExpediente.getPacId()));

                for (CliExamen cliExamen : cliExpediente.getCliExamenList()) {
                    cliExpedienteDto.getCliExamenList().add(new CliExamenDto(cliExamen));
                }
                for (CliAtencion cliAtencion : cliExpediente.getCliAtencionList()) {
                    cliExpedienteDto.getCliAtencionList().add(new CliAtencionDto(cliAtencion));
                }
                for (CliAntecedente cliAntecedente : cliExpediente.getCliAntecedenteList()) {
                    cliExpedienteDto.getCliAntecedenteList().add(new CliAntecedenteDto(cliAntecedente));
                }

                cliExpedienteDtos.add(cliExpedienteDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Expedientes", cliExpedienteDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen expediente con los criterios ingresados.", "getExpedientes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el expediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el expediente.", "getExpedientes " + ex.getMessage());
        }
    }

    public Respuesta guardarExpediente(CliExpedienteDto cliExpedienteDto) {
        try {
            CliExpediente cliExpediente;
            if (cliExpedienteDto.getExpId() != null && cliExpedienteDto.getExpId() > 0) {
                cliExpediente = em.find(CliExpediente.class, cliExpedienteDto.getExpId());
                if (cliExpediente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el expediente a modificar.", "guardarExpediente NoResultException");
                }
                cliExpediente.actualizar(cliExpedienteDto);
                CliPaciente cliPaciente = em.find(CliPaciente.class, cliExpedienteDto.getCliPacienteDto().getPacId());
                cliExpediente.setPacId(cliPaciente);

                for (CliExamenDto cliExamenDto : cliExpedienteDto.getCliExamenList()) {
                    if (cliExamenDto.getModificado()) {
                        CliExamen cliExamen = em.find(CliExamen.class, cliExamenDto.getExaId());
                        cliExamen.setExpId(cliExpediente);
                        cliExpediente.getCliExamenList().add(cliExamen);
                    }
                }

                for (CliExamenDto cliExamenDto : cliExpedienteDto.getCliExamenListEliminados()) {
                    cliExpediente.getCliExamenList().remove(new CliExamen(cliExamenDto.getExaId()));
                }

                for (CliAtencionDto cliAtencionDto : cliExpedienteDto.getCliAtencionList()) {
                    if (cliAtencionDto.getModificado()) {
                        CliAtencion cliAtencion = em.find(CliAtencion.class, cliAtencionDto.getAteId());
                        cliAtencion.setExpId(cliExpediente);
                        cliExpediente.getCliAtencionList().add(cliAtencion);
                    }
                }

                for (CliAtencionDto cliAtencionDto : cliExpedienteDto.getCliAtencionListEliminados()) {
                    cliExpediente.getCliAtencionList().remove(new CliAtencion(cliAtencionDto.getAteId()));
                }

                for (CliAntecedenteDto cliAntecedenteDto : cliExpedienteDto.getCliAntecedenteList()) {
                    if (cliAntecedenteDto.getModificado()) {
                        CliAntecedente cliAntecedente = em.find(CliAntecedente.class, cliAntecedenteDto.getAntId());
                        cliAntecedente.setExpId(cliExpediente);
                        cliExpediente.getCliAntecedenteList().add(cliAntecedente);
                    }
                }

                for (CliAntecedenteDto cliAntecedenteDto : cliExpedienteDto.getCliAntecedenteListEliminados()) {
                    CliAntecedente cliAntecedente = em.find(CliAntecedente.class, cliAntecedenteDto.getAntId());
                    cliExpediente.getCliAntecedenteList().remove(new CliAntecedente(cliAntecedenteDto.getAntId()));
                    em.remove(cliAntecedente);
                }

                cliExpediente = em.merge(cliExpediente);
            } else {
                cliExpediente = new CliExpediente(cliExpedienteDto);
                em.persist(cliExpediente);
            }
            em.flush();
            CliExpedienteDto expedienteDto = new CliExpedienteDto(cliExpediente);
            expedienteDto.setCliPacienteDto(new CliPacienteDto(cliExpediente.getPacId()));
            for (CliExamen cliExamen : cliExpediente.getCliExamenList()) {
                expedienteDto.getCliExamenList().add(new CliExamenDto(cliExamen));
            }
            for (CliAtencion cliAtencion : cliExpediente.getCliAtencionList()) {
                expedienteDto.getCliAtencionList().add(new CliAtencionDto(cliAtencion));
            }
            for (CliAntecedente cliAntecedente : cliExpediente.getCliAntecedenteList()) {
                expedienteDto.getCliAntecedenteList().add(new CliAntecedenteDto(cliAntecedente));
            }
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Expediente", expedienteDto);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el expediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el expediente.", "guardarExpediente " + ex.getMessage());
        }
    }

    public Respuesta eliminarExpediente(Long id) {
        try {
            CliExpediente cliExpediente;
            if (id != null && id > 0) {
                cliExpediente = em.find(CliExpediente.class, id);
                if (cliExpediente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el expediente a eliminar.", "eliminarExpediente NoResultException");
                }
                em.remove(cliExpediente);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el expediente a eliminar.", "eliminarExpediente NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el expediente porque tiene relaciones con otros registros.", "eliminarExpediente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el expediente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el expediente.", "eliminarExpediente " + ex.getMessage());
        }
    }
}
