/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;

/**
 *
 * @author ArauzKJ
 */
public class CliAtencionDto {
    private Long ateId;
    private LocalDate ateFechaHora;
    private String atePresion;
    private String ateFrecuenciaCard;
    private String atePeso;
    private String ateTalla;
    private String ateTemperatura;
    private String ateIMC;
    private String ateAnotacionEnfermera;
    private String ateRazonConsulta;
    private String atePlanAtencion;
    private String ateObservaciones;
    private String ateTratamiento;
    private Long ateVersion;

    public Long getAteId() {
        return ateId;
    }

    public void setAteId(Long ateId) {
        this.ateId = ateId;
    }

    public LocalDate getAteFechaHora() {
        return ateFechaHora;
    }

    public void setAteFechaHora(LocalDate ateFechaHora) {
        this.ateFechaHora = ateFechaHora;
    }

    public String getAtePresion() {
        return atePresion;
    }

    public void setAtePresion(String atePresion) {
        this.atePresion = atePresion;
    }

    public String getAteFrecuenciaCard() {
        return ateFrecuenciaCard;
    }

    public void setAteFrecuenciaCard(String ateFrecuenciaCard) {
        this.ateFrecuenciaCard = ateFrecuenciaCard;
    }

    public String getAtePeso() {
        return atePeso;
    }

    public void setAtePeso(String atePeso) {
        this.atePeso = atePeso;
    }

    public String getAteTalla() {
        return ateTalla;
    }

    public void setAteTalla(String ateTalla) {
        this.ateTalla = ateTalla;
    }

    public String getAteTemperatura() {
        return ateTemperatura;
    }

    public void setAteTemperatura(String ateTemperatura) {
        this.ateTemperatura = ateTemperatura;
    }

    public String getAteIMC() {
        return ateIMC;
    }

    public void setAteIMC(String ateIMC) {
        this.ateIMC = ateIMC;
    }

    public String getAteAnotacionEnfermera() {
        return ateAnotacionEnfermera;
    }

    public void setAteAnotacionEnfermera(String ateAnotacionEnfermera) {
        this.ateAnotacionEnfermera = ateAnotacionEnfermera;
    }

    public String getAteRazonConsulta() {
        return ateRazonConsulta;
    }

    public void setAteRazonConsulta(String ateRazonConsulta) {
        this.ateRazonConsulta = ateRazonConsulta;
    }

    public String getAtePlanAtencion() {
        return atePlanAtencion;
    }

    public void setAtePlanAtencion(String atePlanAtencion) {
        this.atePlanAtencion = atePlanAtencion;
    }

    public String getAteObservaciones() {
        return ateObservaciones;
    }

    public void setAteObservaciones(String ateObservaciones) {
        this.ateObservaciones = ateObservaciones;
    }

    public String getAteTratamiento() {
        return ateTratamiento;
    }

    public void setAteTratamiento(String ateTratamiento) {
        this.ateTratamiento = ateTratamiento;
    }

    public Long getAteVersion() {
        return ateVersion;
    }

    public void setAteVersion(Long ateVersion) {
        this.ateVersion = ateVersion;
    }
    
    
}
