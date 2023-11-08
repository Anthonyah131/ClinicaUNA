/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un antecedente con el código ingresado.", "getAntecedente NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el antecedente.", "getAntecedente NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el antecedente.", "getAntecedente " + ex.getMessage());
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
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen antecedente con los criterios ingresados.", "getAntecedentes NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el antecedente.", "getAntecedentes " + ex.getMessage());
        }
    }

    public Respuesta guardarAntecedente(CliAntecedenteDto cliAntecedenteDto) {
        try {
            CliAntecedente cliAntecedente;
            if (cliAntecedenteDto.getAntId()!= null && cliAntecedenteDto.getAntId()> 0) {
                cliAntecedente = em.find(CliAntecedente.class, cliAntecedenteDto.getAntId());
                if (cliAntecedente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el antecedente a modificar.", "guardarAntecedente NoResultException");
                }
                cliAntecedente.actualizar(cliAntecedenteDto);
                CliExpediente cliExpediente = em.find(CliExpediente.class, cliAntecedenteDto.getCliExpedienteDto().getExpId());
                cliAntecedente.setExpId(cliExpediente);
                cliAntecedente = em.merge(cliAntecedente);
            } else {
                cliAntecedente = new CliAntecedente(cliAntecedenteDto);
                em.persist(cliAntecedente);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Antecedente", new CliAntecedenteDto(cliAntecedente));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el antecedente.", "guardarAntecedente " + ex.getMessage());
        }
    }

    public Respuesta eliminarAntecedente(Long id) {
        try {
            CliAntecedente cliAntecedente;
            if (id != null && id > 0) {
                cliAntecedente = em.find(CliAntecedente.class, id);
                if (cliAntecedente == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el antecedente a eliminar.", "eliminarAntecedente NoResultException");
                }
                em.remove(cliAntecedente);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el antecedente a eliminar.", "eliminarAntecedente NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el antecedente porque tiene relaciones con otros registros.", "eliminarAntecedente " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el antecedente.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el antecedente.", "eliminarAntecedente " + ex.getMessage());
        }
    }
}
