/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

/**
 *
 * @author ArauzKJ
 */
public class CliExpedienteDto {

    private Long expId;
    private Long expHospitalizaciones;
    private Long expOperaciones;
    private Long expAlergias;
    private String expTipoAlergias;
    private String expTratamientos;
    private Long expVersion;

    public Long getExpId() {
        return expId;
    }

    public void setExpId(Long expId) {
        this.expId = expId;
    }

    public Long getExpHospitalizaciones() {
        return expHospitalizaciones;
    }

    public void setExpHospitalizaciones(Long expHospitalizaciones) {
        this.expHospitalizaciones = expHospitalizaciones;
    }

    public Long getExpOperaciones() {
        return expOperaciones;
    }

    public void setExpOperaciones(Long expOperaciones) {
        this.expOperaciones = expOperaciones;
    }

    public Long getExpAlergias() {
        return expAlergias;
    }

    public void setExpAlergias(Long expAlergias) {
        this.expAlergias = expAlergias;
    }

    public String getExpTipoAlergias() {
        return expTipoAlergias;
    }

    public void setExpTipoAlergias(String expTipoAlergias) {
        this.expTipoAlergias = expTipoAlergias;
    }

    public String getExpTratamientos() {
        return expTratamientos;
    }

    public void setExpTratamientos(String expTratamientos) {
        this.expTratamientos = expTratamientos;
    }

    public Long getExpVersion() {
        return expVersion;
    }

    public void setExpVersion(Long expVersion) {
        this.expVersion = expVersion;
    }
    
    
}
