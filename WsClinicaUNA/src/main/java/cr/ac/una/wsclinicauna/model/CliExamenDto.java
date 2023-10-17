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
public class CliExamenDto {
   private Long exaId;
    private String exaNombre;
    private LocalDate exaFecha;
    private String exaAnotacionesMed;
    private Long exaVersion;

    public Long getExaId() {
        return exaId;
    }

    public void setExaId(Long exaId) {
        this.exaId = exaId;
    }

    public String getExaNombre() {
        return exaNombre;
    }

    public void setExaNombre(String exaNombre) {
        this.exaNombre = exaNombre;
    }

    public LocalDate getExaFecha() {
        return exaFecha;
    }

    public void setExaFecha(LocalDate exaFecha) {
        this.exaFecha = exaFecha;
    }

    public String getExaAnotacionesMed() {
        return exaAnotacionesMed;
    }

    public void setExaAnotacionesMed(String exaAnotacionesMed) {
        this.exaAnotacionesMed = exaAnotacionesMed;
    }

    public Long getExaVersion() {
        return exaVersion;
    }

    public void setExaVersion(Long exaVersion) {
        this.exaVersion = exaVersion;
    }
    
    
}
