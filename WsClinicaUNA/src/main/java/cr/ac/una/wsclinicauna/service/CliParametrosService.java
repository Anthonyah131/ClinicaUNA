/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliParametros;
import cr.ac.una.wsclinicauna.model.CliParametrosDto;
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
public class CliParametrosService {
    private static final Logger LOG = Logger.getLogger(CliParametrosService.class.getName());
    @PersistenceContext(unitName="WsClinicaUNAPU")
    private EntityManager em;
    
    public Respuesta getParametro(Long id) {
        try {
            Query qryEmpleado = em.createNamedQuery("CliParametros.findByParId", CliParametros.class);
            qryEmpleado.setParameter("id", id);

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametro", new CliParametrosDto((CliParametros) qryEmpleado.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un parametro con el código ingresado.", "getParametro NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el parametro.", "getParametro NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el parametro.", "getParametro " + ex.getMessage());
        }
    }

    public Respuesta getParametros() {
        try {
            Query qryParametro = em.createNamedQuery("CliParametros.findAll", CliParametros.class);
            List<CliParametros> cliParametroses = qryParametro.getResultList();
            List<CliParametrosDto> cliParametrosDtos = new ArrayList<>();
            for (CliParametros cliParametros : cliParametroses) {
                cliParametrosDtos.add(new CliParametrosDto(cliParametros));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametros", cliParametrosDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen empleados con los criterios ingresados.", "getParametros NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el parametro.", "getParametros " + ex.getMessage());
        }
    }

    public Respuesta guardarParametro(CliParametrosDto cliParametrosDto) {
        try {
            CliParametros cliParametros;
            if (cliParametrosDto.getParId()!= null && cliParametrosDto.getParId()> 0) {
                cliParametros = em.find(CliParametros.class, cliParametrosDto.getParId());
                if (cliParametros == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el parametro a modificar.", "guardarParametro NoResultException");
                }
                cliParametros.actualizar(cliParametrosDto);
                cliParametros = em.merge(cliParametros);
            } else {
                cliParametros = new CliParametros(cliParametrosDto);
                em.persist(cliParametros);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Parametro", new CliParametrosDto(cliParametros));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el parametro.", "guardarParametro " + ex.getMessage());
        }
    }

    public Respuesta eliminarParametro(Long id) {
        try {
            CliParametros cliParametros;
            if (id != null && id > 0) {
                cliParametros = em.find(CliParametros.class, id);
                if (cliParametros == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el parametro a eliminar.", "eliminarParametro NoResultException");
                }
                em.remove(cliParametros);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el parametro a eliminar.", "eliminarParametro NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el parametro porque tiene relaciones con otros registros.", "eliminarParametro " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el parametro.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el parametro.", "eliminarParametro " + ex.getMessage());
        }
    }
}
