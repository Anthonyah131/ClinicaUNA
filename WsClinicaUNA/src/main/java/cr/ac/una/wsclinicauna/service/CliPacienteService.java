package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliCita;
import cr.ac.una.wsclinicauna.model.CliCitaDto;
import cr.ac.una.wsclinicauna.model.CliExpediente;
import cr.ac.una.wsclinicauna.model.CliExpedienteDto;
import cr.ac.una.wsclinicauna.model.CliPaciente;
import cr.ac.una.wsclinicauna.model.CliPacienteDto;
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
public class CliPacienteService {

    private static final Logger LOG = Logger.getLogger(CliPacienteService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getPaciente(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliPaciente.findByPacId", CliPaciente.class);
            qryUsuario.setParameter("pacId", id);
            CliPaciente cliPaciente = (CliPaciente) qryUsuario.getSingleResult();

            CliPacienteDto cliPacienteDto = new CliPacienteDto(cliPaciente);
            for (CliExpediente cliExpediente : cliPaciente.getCliExpedienteList()) {
                cliPacienteDto.getCliExpedienteList().add(new CliExpedienteDto(cliExpediente));
            }
            for (CliCita cliCita : cliPaciente.getCliCitaList()) {
                cliPacienteDto.getCliCitaList().add(new CliCitaDto(cliCita));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Paciente", cliPacienteDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un paciente con el código ingresado.", "getPaciente NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el paciente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el paciente.", "getPaciente NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el paciente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el paciente.", "getPaciente " + ex.getMessage());
        }
    }

    public Respuesta getPacientes() {
        try {
            Query qryUsuario = em.createNamedQuery("CliPaciente.findAll", CliPaciente.class);
            List<CliPaciente> cliPacientes = qryUsuario.getResultList();
            List<CliPacienteDto> cliPacienteDtos = new ArrayList<>();
            for (CliPaciente cliPaciente : cliPacientes) {
                CliPacienteDto cliPacienteDto = new CliPacienteDto(cliPaciente);

                for (CliExpediente cliExpediente : cliPaciente.getCliExpedienteList()) {
                    cliPacienteDto.getCliExpedienteList().add(new CliExpedienteDto(cliExpediente));
                }
                for (CliCita cliCita : cliPaciente.getCliCitaList()) {
                    cliPacienteDto.getCliCitaList().add(new CliCitaDto(cliCita));
                }

                cliPacienteDtos.add(cliPacienteDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Pacientes", cliPacienteDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen paciente con los criterios ingresados.", "getPacientes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el paciente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el paciente.", "getPacientes " + ex.getMessage());
        }
    }

    public Respuesta guardarPaciente(CliPacienteDto cliPacienteDto) {
        try {
            CliPaciente cliPaciente;
            if (cliPacienteDto.getPacId() != null && cliPacienteDto.getPacId() > 0) {
                cliPaciente = em.find(CliPaciente.class, cliPacienteDto.getPacId());
                if (cliPaciente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el paciente a modificar.", "guardarPaciente NoResultException");
                }
                cliPaciente.actualizar(cliPacienteDto);

                for (CliExpedienteDto cliExpedienteDto : cliPacienteDto.getCliExpedienteList()) {
                    if (cliExpedienteDto.getModificado()) {
                        CliExpediente cliExpediente = em.find(CliExpediente.class, cliExpedienteDto.getExpId());
                        cliPaciente.getCliExpedienteList().add(cliExpediente);
                        cliExpediente.setPacId(cliPaciente);
                    }
                }

                for (CliExpedienteDto cliExpedienteDto : cliPacienteDto.getCliExpedienteListEliminados()) {
                    cliPaciente.getCliExpedienteList().remove(new CliExpediente(cliExpedienteDto.getExpId()));
                }

                for (CliCitaDto cliCitaDto : cliPacienteDto.getCliCitaList()) {
                    if (cliCitaDto.getModificado()) {
                        CliCita cliCita = em.find(CliCita.class, cliCitaDto.getCitId());
                        cliCita.setPacId(cliPaciente);
                        cliPaciente.getCliCitaList().add(cliCita);
                        cliCita.setPacId(cliPaciente);
                    }
                }

                for (CliCitaDto cliCitaDto : cliPacienteDto.getCliCitaListEliminados()) {
                    cliPaciente.getCliCitaList().remove(new CliCita(cliCitaDto.getCitId()));
                }

                cliPaciente = em.merge(cliPaciente);
            } else {
                cliPaciente = new CliPaciente(cliPacienteDto);
                em.persist(cliPaciente);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Paciente", new CliPacienteDto(cliPaciente));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el paciente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el paciente.", "guardarPaciente " + ex.getMessage());
        }
    }

    public Respuesta eliminarPaciente(Long id) {
        try {
            CliPaciente cliPaciente;
            if (id != null && id > 0) {
                cliPaciente = em.find(CliPaciente.class, id);
                if (cliPaciente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el paciente a eliminar.", "eliminarPaciente NoResultException");
                }
                em.remove(cliPaciente);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el paciente a eliminar.", "eliminarPaciente NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el paciente porque tiene relaciones con otros registros.", "eliminarPaciente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el paciente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el paciente.", "eliminarPaciente " + ex.getMessage());
        }
    }
}
