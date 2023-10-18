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
public class CliAtencionDto {

    private SimpleStringProperty ateId;
    private ObjectProperty<LocalDate> ateFechaHora;
    private SimpleStringProperty atePresion;
    private SimpleStringProperty ateFrecuenciaCard;
    private SimpleStringProperty atePeso;
    private SimpleStringProperty ateTalla;
    private SimpleStringProperty ateTemperatura;
    private SimpleStringProperty ateIMC;
    private SimpleStringProperty ateAnotacionEnfermera;
    private SimpleStringProperty ateRazonConsulta;
    private SimpleStringProperty atePlanAtencion;
    private SimpleStringProperty ateObservaciones;
    private SimpleStringProperty ateTratamiento;
    private Long ateVersion;
    private Boolean modificado;

    public CliAtencionDto() {
        this.ateId = new SimpleStringProperty();
        this.ateFechaHora = new SimpleObjectProperty();
        this.atePresion = new SimpleStringProperty();
        this.ateFrecuenciaCard = new SimpleStringProperty();
        this.atePeso = new SimpleStringProperty();
        this.ateTalla = new SimpleStringProperty();
        this.ateTemperatura = new SimpleStringProperty();
        this.ateIMC = new SimpleStringProperty();
        this.ateAnotacionEnfermera = new SimpleStringProperty();
        this.ateRazonConsulta = new SimpleStringProperty();
        this.atePlanAtencion = new SimpleStringProperty();
        this.ateObservaciones = new SimpleStringProperty();
        this.ateTratamiento = new SimpleStringProperty();
        this.modificado = false;
    }

    public Long getAteId() {
        if (this.ateId.get() != null && !this.ateId.get().isEmpty()) {
            return Long.valueOf(this.ateId.get());
        } else {
            return null;
        }
    }

    public void setAteId(Long ateId) {
        this.ateId.set(ateId.toString());
    }

    public LocalDate getAteFechaHora() {
        return ateFechaHora.get();
    }

    public void setAteFechaHora(LocalDate ateFechaHora) {
        this.ateFechaHora.set(ateFechaHora);
    }

    public String getAtePresion() {
        return atePresion.get();
    }

    public void setAtePresion(String atePresion) {
        this.atePresion.set(atePresion);
    }

    public String getAteFrecuenciaCard() {
        return ateFrecuenciaCard.get();
    }

    public void setAteFrecuenciaCard(String ateFrecuenciaCard) {
        this.ateFrecuenciaCard.set(ateFrecuenciaCard);
    }

    public String getAtePeso() {
        return atePeso.get();
    }

    public void setAtePeso(String atePeso) {
        this.atePeso.set(atePeso);
    }

    public String getAteTalla() {
        return ateTalla.get();
    }

    public void setAteTalla(String ateTalla) {
        this.ateTalla.set(ateTalla);
    }

    public String getAteTemperatura() {
        return ateTemperatura.get();
    }

    public void setAteTemperatura(String ateTemperatura) {
        this.ateTemperatura.set(ateTemperatura);
    }

    public String getAteIMC() {
        return ateIMC.get();
    }

    public void setAteIMC(String ateIMC) {
        this.ateIMC.set(ateIMC);
    }

    public String getAteAnotacionEnfermera() {
        return ateAnotacionEnfermera.get();
    }

    public void setAteAnotacionEnfermera(String ateAnotacionEnfermera) {
        this.ateAnotacionEnfermera.set(ateAnotacionEnfermera);
    }

    public String getAteRazonConsulta() {
        return ateRazonConsulta.get();
    }

    public void setAteRazonConsulta(String ateRazonConsulta) {
        this.ateRazonConsulta.set(ateRazonConsulta);
    }

    public String getAtePlanAtencion() {
        return atePlanAtencion.get();
    }

    public void setAtePlanAtencion(String atePlanAtencion) {
        this.atePlanAtencion.set(atePlanAtencion);
    }

    public String getAteObservaciones() {
        return ateObservaciones.get();
    }

    public void setAteObservaciones(String ateObservaciones) {
        this.ateObservaciones.set(ateObservaciones);
    }

    public String getAteTratamiento() {
        return ateTratamiento.get();
    }

    public void setAteTratamiento(String ateTratamiento) {
        this.ateTratamiento.set(ateTratamiento);
    }

    public Long getAteVersion() {
        return ateVersion;
    }

    public void setAteVersion(Long ateVersion) {
        this.ateVersion = ateVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
