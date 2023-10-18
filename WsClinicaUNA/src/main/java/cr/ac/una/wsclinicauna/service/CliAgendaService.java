/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliUsuarioDto;
import cr.ac.una.wsclinicauna.util.CodigoRespuesta;
import cr.ac.una.wsclinicauna.util.Respuesta;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import java.sql.SQLIntegrityConstraintViolationException;
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
    @PersistenceContext(unitName="WsClinicaUNAPU")
    private EntityManager em;
    
    public Respuesta getAgenda(Long id) {
        try {
//            Query qryEmpleado = em.createNamedQuery("Empleado.findByEmpId", Empleado.class);
//            qryEmpleado.setParameter("id", id);

            return null;//new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleado", new EmpleadoDto((Empleado) qryEmpleado.getSingleResult()));

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un empleado con el código ingresado.", "getEmpleado NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getAgenda " + ex.getMessage());
        }
    }

    public Respuesta getAgendas() {
        try {
//            Query qryEmpleado = em.createNamedQuery("Empleado.findByCedulaNombrePapellido", Empleado.class);
//            List<Empleado> empleados = qryEmpleado.getResultList();
//            List<EmpleadoDto> empleadosDto = new ArrayList<>();
//            for (Empleado empleado : empleados) {
//                empleadosDto.add(new EmpleadoDto(empleado));
//            }

            return null;//new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleados", empleadosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen empleados con los criterios ingresados.", "getEmpleados NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el empleado.", "getEmpleado " + ex.getMessage());
        }
    }

    public Respuesta guardarAgenda(CliUsuarioDto empleadoDto) {
        try {
//            Empleado empleado;
//            if (empleadoDto.getId() != null && empleadoDto.getId() > 0) {
//                empleado = em.find(Empleado.class, empleadoDto.getId());
//                if (empleado == null) {
//                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el empleado a modificar.", "guardarEmpleado NoResultException");
//                }
//                empleado.actualizar(empleadoDto);
//                empleado = em.merge(empleado);
//            } else {
//                empleado = new Empleado(empleadoDto);
//                em.persist(empleado);
//            }
//            em.flush();
            return null;//new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Empleado", new EmpleadoDto(empleado));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el empleado.", "guardarEmpleado " + ex.getMessage());
        }
    }

    public Respuesta eliminarAgenda(Long id) {
        try {
//            Empleado empleado;
//            if (id != null && id > 0) {
//                empleado = em.find(Empleado.class, id);
//                if (empleado == null) {
//                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el empleado a eliminar.", "eliminarEmpleado NoResultException");
//                }
//                em.remove(empleado);
//            } else {
//                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el empleado a eliminar.", "eliminarEmpleado NoResultException");
//            }
//            em.flush();
            return null;//new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el empleado porque tiene relaciones con otros registros.", "eliminarEmpleado " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el empleado.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el empleado.", "eliminarEmpleado " + ex.getMessage());
        }
    }
}
