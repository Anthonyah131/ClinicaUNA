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
    private SimpleStringProperty exaAnotacionesMed;
    private Long exaVersion;
    private Boolean modificado;

    public CliExamenDto() {
        this.exaId = new SimpleStringProperty();
        this.exaNombre = new SimpleStringProperty();
        this.exaFecha = new SimpleObjectProperty();
        this.exaAnotacionesMed = new SimpleStringProperty();
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
        return exaAnotacionesMed.get();
    }

    public void setExaAnotacionesMed(String exaAnotacionesMed) {
        this.exaAnotacionesMed.set(exaAnotacionesMed);
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
