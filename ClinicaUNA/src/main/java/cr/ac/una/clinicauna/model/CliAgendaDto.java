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
public class CliAgendaDto {
    private SimpleStringProperty ageId;
    private ObjectProperty<LocalDate> ageFecha;
    private SimpleStringProperty ageTiempo;
    private SimpleStringProperty ageEspacios;
    private Long ageVersion;
    private Boolean modificado;

    public CliAgendaDto() {
        this.ageId = new SimpleStringProperty();
        this.ageFecha = new SimpleObjectProperty();
        this.ageTiempo = new SimpleStringProperty();
        this.ageEspacios = new SimpleStringProperty();
        this.modificado = false;
    }

    public Long getAgeId() {
        if (this.ageId.get() != null && !this.ageId.get().isEmpty()) {
            return Long.valueOf(this.ageId.get());
        } else {
            return null;
        }
    }

    public void setAgeId(Long ageId) {
        this.ageId.set(ageId.toString());
    }

    public LocalDate getAgeFecha() {
        return ageFecha.get();
    }

    public void setAgeFecha(LocalDate ageFecha) {
        this.ageFecha.set(ageFecha);
    }

    public String getAgeTiempo() {
        return ageTiempo.get();
    }

    public void setAgeTiempo(String ageTiempo) {
        this.ageTiempo.set(ageTiempo);
    }

    public Long getAgeEspacios() {
        if (this.ageEspacios.get() != null && !this.ageEspacios.get().isEmpty()) {
            return Long.valueOf(this.ageEspacios.get());
        } else {
            return null;
        }
    }

    public void setAgeEspacios(Long ageEspacios) {
        this.ageEspacios.set(ageEspacios.toString());
    }

    public Long getAgeVersion() {
        return ageVersion;
    }

    public void setAgeVersion(Long ageVersion) {
        this.ageVersion = ageVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}