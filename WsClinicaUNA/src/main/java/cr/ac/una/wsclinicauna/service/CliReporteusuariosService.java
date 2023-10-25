/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import cr.ac.una.wsclinicauna.model.CliReporteusuarios;
import cr.ac.una.wsclinicauna.model.CliReporteusuariosDto;
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
public class CliReporteusuariosService {
    
    private static final Logger LOG = Logger.getLogger(CliReporteusuariosService.class.getName());
    @PersistenceContext(unitName = "WsClinicaUNAPU")
    private EntityManager em;

    public Respuesta getReporteusuario(Long id) {
        try {
            Query qryUsuario = em.createNamedQuery("CliReporteusuarios.findByRepusuId", CliReporteusuarios.class);
            qryUsuario.setParameter("repusuId", id);
            CliReporteusuarios cliReporteusuarios = (CliReporteusuarios) qryUsuario.getSingleResult();

            CliReporteusuariosDto cliReporteusuariosDto = new CliReporteusuariosDto(cliReporteusuarios);
            for (CliUsuario cliUsuario : cliReporteusuarios.getCliUsuarioList()) {
                cliReporteusuariosDto.getCliUsuarioList().add(new CliUsuarioDto(cliUsuario));
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteusuario", cliReporteusuariosDto);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existe un reporteusuario con el código ingresado.", "getReporteusuario NoResultException");
        } catch (NonUniqueResultException ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteusuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteusuario.", "getReporteusuario NonUniqueResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteusuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteusuario.", "getReporteusuario " + ex.getMessage());
        }
    }

    public Respuesta getReporteusuarios() {
        try {
            Query qryUsuario = em.createNamedQuery("CliUsuario.findAll", CliUsuario.class);
            List<CliReporteusuarios> cliReporteusuarioses = qryUsuario.getResultList();
            List<CliReporteusuariosDto> cliReporteusuariosDtos = new ArrayList<>();
            for (CliReporteusuarios cliReporteusuarios : cliReporteusuarioses) {
                CliReporteusuariosDto cliUsuarioDto = new CliReporteusuariosDto(cliReporteusuarios);

                for (CliUsuario cliUsuario : cliReporteusuarios.getCliUsuarioList()) {
                    cliUsuarioDto.getCliUsuarioList().add(new CliUsuarioDto(cliUsuario));
                }


                cliReporteusuariosDtos.add(cliUsuarioDto);
            }

            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteusuarios", cliReporteusuariosDtos);

        } catch (NoResultException ex) {
            return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No existen reporteusuario con los criterios ingresados.", "getReporteusuarios NoResultException");
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al consultar el reporteusuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al consultar el reporteusuario.", "getReporteusuarios " + ex.getMessage());
        }
    }

    public Respuesta guardarReporteusuario(CliReporteusuariosDto cliReporteusuariosDto) {
        try {
            CliReporteusuarios cliReporteusuarios;
            if (cliReporteusuariosDto.getRepusuId()!= null && cliReporteusuariosDto.getRepusuId() > 0) {
                cliReporteusuarios = em.find(CliReporteusuarios.class, cliReporteusuariosDto.getRepusuId());
                if (cliReporteusuarios == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el reporteusuario a modificar.", "guardarReporteusuario NoResultException");
                }
                cliReporteusuarios.actualizar(cliReporteusuariosDto);

                for (CliUsuarioDto cliUsuarioDto : cliReporteusuariosDto.getCliUsuarioListEliminados()) {
                    cliReporteusuarios.getCliUsuarioList().remove(new CliUsuario(cliUsuarioDto.getUsuId()));
                }

                if (!cliReporteusuariosDto.getCliUsuarioList().isEmpty()) {
                    for (CliUsuarioDto cliUsuarioDto : cliReporteusuariosDto.getCliUsuarioList()) {
                        if (cliUsuarioDto.getModificado()) {
                            CliUsuario cliUsuario = em.find(CliUsuario.class, cliUsuarioDto.getUsuId());
                            cliUsuario.getCliReporteusuariosList().add(cliReporteusuarios);
                            cliReporteusuarios.getCliUsuarioList().add(cliUsuario);
                        }

                    }
                }
                cliReporteusuarios = em.merge(cliReporteusuarios);
            } else {
                cliReporteusuarios = new CliReporteusuarios(cliReporteusuariosDto);
                em.persist(cliReporteusuarios);
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "", "Reporteusuario", new CliReporteusuariosDto(cliReporteusuarios));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el reporteusuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al guardar el reporteusuario.", "guardarReporteusuario " + ex.getMessage());
        }
    }

    public Respuesta eliminarReporteusuario(Long id) {
        try {
            CliReporteusuarios cliReporteusuarios;
            if (id != null && id > 0) {
                cliReporteusuarios = em.find(CliReporteusuarios.class, id);
                if (cliReporteusuarios == null) {
                    return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "No se encrontró el reporteusuario a eliminar.", "eliminarReporteusuario NoResultException");
                }
                em.remove(cliReporteusuarios);
            } else {
                return new Respuesta(false, CodigoRespuesta.ERROR_NOENCONTRADO, "Debe cargar el reporteusuario a eliminar.", "eliminarReporteusuario NoResultException");
            }
            em.flush();
            return new Respuesta(true, CodigoRespuesta.CORRECTO, "", "");
        } catch (Exception ex) {
            if (ex.getCause() != null && ex.getCause().getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "No se puede eliminar el reporteusuario porque tiene relaciones con otros registros.", "eliminarReporteusuario " + ex.getMessage());
            }
            LOG.log(Level.SEVERE, "Ocurrio un error al guardar el reporteusuario.", ex);
            return new Respuesta(false, CodigoRespuesta.ERROR_INTERNO, "Ocurrio un error al eliminar el reporteusuario.", "eliminarReporteusuario " + ex.getMessage());
        }
    }
}
