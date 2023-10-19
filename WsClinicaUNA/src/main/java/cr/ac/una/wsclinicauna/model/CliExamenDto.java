/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ArauzKJ
 */
public class CliExamenDto {

    private Long exaId;
    private String exaNombre;
    private LocalDate exaFecha;
    private String exaAnotacionesmed;
    private Long exaVersion;
    private CliAtencionDto cliAtencionDto;
    private CliExpedienteDto cliExpedienteDto;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliExamenDto() {
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliExamenDto(CliExamen cliExamen) {
        this();
        this.exaId = cliExamen.getExaId();
        this.exaNombre = cliExamen.getExaNombre();
        this.exaFecha = cliExamen.getExaFecha();
        this.exaAnotacionesmed = cliExamen.getExaAnotacionesmed();
        this.exaVersion = cliExamen.getExaVersion();
        this.fecha = LocalDateTime.now();
    }

    
    
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

    public String getExaAnotacionesmed() {
        return exaAnotacionesmed;
    }

    public void setExaAnotacionesmed(String exaAnotacionesmed) {
        this.exaAnotacionesmed = exaAnotacionesmed;
    }

    public Long getExaVersion() {
        return exaVersion;
    }

    public void setExaVersion(Long exaVersion) {
        this.exaVersion = exaVersion;
    }

    public CliAtencionDto getCliAtencionDto() {
        return cliAtencionDto;
    }

    public void setCliAtencionDto(CliAtencionDto cliAtencionDto) {
        this.cliAtencionDto = cliAtencionDto;
    }

    public CliExpedienteDto getCliExpedienteDto() {
        return cliExpedienteDto;
    }

    public void setCliExpedienteDto(CliExpedienteDto cliExpedienteDto) {
        this.cliExpedienteDto = cliExpedienteDto;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
