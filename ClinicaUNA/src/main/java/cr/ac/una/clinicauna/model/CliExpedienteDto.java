/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliExpedienteDto {

    private SimpleStringProperty expId;
    private SimpleStringProperty expHospitalizaciones;
    private SimpleStringProperty expOperaciones;
    private SimpleStringProperty expAlergias;
    private SimpleStringProperty expTipoAlergias;
    private SimpleStringProperty expTratamientos;
    private Long expVersion;
    private Boolean modificado;

    public CliExpedienteDto() {
        this.expId = new SimpleStringProperty();
        this.expHospitalizaciones = new SimpleStringProperty();
        this.expOperaciones = new SimpleStringProperty();
        this.expAlergias = new SimpleStringProperty();
        this.expTipoAlergias = new SimpleStringProperty();
        this.expTratamientos = new SimpleStringProperty();
        this.modificado = false;
    }

    public Long getExpId() {
        if (this.expId.get() != null && !this.expId.get().isEmpty()) {
            return Long.valueOf(this.expId.get());
        } else {
            return null;
        }
    }

    public void setExpId(Long expId) {
        this.expId.set(expId.toString());
    }

    public Long getExpHospitalizaciones() {
        if (this.expHospitalizaciones.get() != null && !this.expHospitalizaciones.get().isEmpty()) {
            return Long.valueOf(this.expHospitalizaciones.get());
        } else {
            return null;
        }
    }

    public void setExpHospitalizaciones(Long expHospitalizaciones) {
        this.expHospitalizaciones.set(expHospitalizaciones.toString());;
    }

    public Long getExpOperaciones() {
        if (this.expOperaciones.get() != null && !this.expOperaciones.get().isEmpty()) {
            return Long.valueOf(this.expOperaciones.get());
        } else {
            return null;
        }
    }

    public void setExpOperaciones(Long expOperaciones) {
        this.expOperaciones.set(expOperaciones.toString());
    }

    public Long getExpAlergias() {
        if (this.expAlergias.get() != null && !this.expAlergias.get().isEmpty()) {
            return Long.valueOf(this.expAlergias.get());
        } else {
            return null;
        }
    }

    public void setExpAlergias(Long expAlergias) {
        this.expAlergias.set(expAlergias.toString());
    }

    public String getExpTipoAlergias() {
        return expTipoAlergias.get();
    }

    public void setExpTipoAlergias(String expTipoAlergias) {
        this.expTipoAlergias.set(expTipoAlergias);
    }

    public String getExpTratamientos() {
        return expTratamientos.get();
    }

    public void setExpTratamientos(String expTratamientos) {
        this.expTratamientos.set(expTratamientos);
    }

    public Long getExpVersion() {
        return expVersion;
    }

    public void setExpVersion(Long expVersion) {
        this.expVersion = expVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}