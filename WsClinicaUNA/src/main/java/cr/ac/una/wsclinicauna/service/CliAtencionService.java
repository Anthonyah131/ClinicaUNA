/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.service;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 *
 * @author ArauzKJ
 */
@Stateless
@LocalBean
public class CliAtencionService {
    private static final Logger LOG = Logger.getLogger(CliAtencionService.class.getName());
    @PersistenceContext(unitName="WsClinicaUNAPU")
    private EntityManager em;
}
