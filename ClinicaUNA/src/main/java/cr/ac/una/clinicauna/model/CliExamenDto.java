/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliExamenDto {
   private SimpleStringProperty exaId;
    private SimpleStringProperty exaNombre;
    private ObjectProperty<LocalDate> exaFecha;
    private SimpleStringProperty exaAnotacionesmed;
    private CliAtencionDto cliAtencionDto;
    private CliExpedienteDto cliExpedienteDto;
    private Long exaVersion;
    private Boolean modificado;

    public CliExamenDto() {
        this.exaId = new SimpleStringProperty();
        this.exaNombre = new SimpleStringProperty();
        this.exaFecha = new SimpleObjectProperty();
        this.exaAnotacionesmed = new SimpleStringProperty();
        this.modificado = false;
    }

    public Long getExaId() {
        if (this.exaId.get() != null && !this.exaId.get().isEmpty()) {
            return Long.valueOf(this.exaId.get());
        } else {
            return null;
        }
    }

    public void setExaId(Long exaId) {
        this.exaId.set(exaId.toString());
    }

    public String getExaNombre() {
        return exaNombre.get();
    }

    public void setExaNombre(String exaNombre) {
        this.exaNombre.set(exaNombre);
    }

    public LocalDate getExaFecha() {
        return exaFecha.get();
    }

    public void setExaFecha(LocalDate exaFecha) {
        this.exaFecha.set(exaFecha);
    }

    public String getExaAnotacionesMed() {
        return exaAnotacionesmed.get();
    }

    public void setExaAnotacionesMed(String exaAnotacionesmed) {
        this.exaAnotacionesmed.set(exaAnotacionesmed);
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

    public Long getExaVersion() {
        return exaVersion;
    }

    public void setExaVersion(Long exaVersion) {
        this.exaVersion = exaVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }   
}
