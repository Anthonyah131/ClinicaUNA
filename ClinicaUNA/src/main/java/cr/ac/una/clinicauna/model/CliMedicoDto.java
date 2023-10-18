/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliMedicoDto {
    private SimpleStringProperty medId;
    private SimpleStringProperty medCodigo;
    private SimpleStringProperty medFolio;
    private SimpleStringProperty medCarne;
    private SimpleBooleanProperty medEstado;
    private ObjectProperty<LocalDate> medFini;
    private ObjectProperty<LocalDate> medFfin;
    private Long medVersion;
    private Boolean modificado;

    public CliMedicoDto() {
        this.medId = new SimpleStringProperty();
        this.medCodigo = new SimpleStringProperty();
        this.medFolio = new SimpleStringProperty();
        this.medCarne = new SimpleStringProperty();
        this.medEstado = new SimpleBooleanProperty(false);
        this.medFini = new SimpleObjectProperty();
        this.medFfin = new SimpleObjectProperty();
        this.modificado = false;
    }

    public Long getMedId() {
        if (this.medId.get() != null && !this.medId.get().isEmpty()) {
            return Long.valueOf(this.medId.get());
        } else {
            return null;
        }
    }

    public void setMedId(Long medId) {
        this.medId.set(medId.toString());
    }

    public String getMedCodigo() {
        return medCodigo.get();
    }

    public void setMedCodigo(String medCodigo) {
        this.medCodigo.set(medCodigo);
    }

    public String getMedFolio() {
        return medFolio.get();
    }

    public void setMedFolio(String medFolio) {
        this.medFolio.set(medFolio);
    }

    public String getMedCarne() {
        return medCarne.get();
    }

    public void setMedCarne(String medCarne) {
        this.medCarne.set(medCarne);
    }

    public String getMedEstado() {
        return medEstado.get() ? "A" : "I";
    }

    public void setMedEstado(String medEstado) {
        this.medEstado.set(medEstado.equals("A"));
    }

    public LocalDate getMedFini() {
        return medFini.get();
    }

    public void setMedFini(LocalDate medFini) {
        this.medFini.set(medFini);
    }

    public LocalDate getMedFfin() {
        return medFfin.get();
    }

    public void setMedFfin(LocalDate medFfin) {
        this.medFfin.set(medFfin);
    }

    public Long getMedVersion() {
        return medVersion;
    }

    public void setMedVersion(Long medVersion) {
        this.medVersion = medVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
