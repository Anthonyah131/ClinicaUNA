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
public class CliCorreodestinoDto {

    private SimpleStringProperty cdId;
    private SimpleStringProperty cdCorreo;
    private CliReporteDto cliReporteDto;
    private Long cdVersion;
    private Boolean modificado;

    public CliCorreodestinoDto() {
        this.cdId = new SimpleStringProperty();
        this.cdCorreo = new SimpleStringProperty();
        this.modificado = false;
    }

    public Long getCdId() {
        if (this.cdId.get() != null && !this.cdId.get().isEmpty()) {
            return Long.valueOf(this.cdId.get());
        } else {
            return null;
        }
    }

    public void setCdId(Long cdId) {
        this.cdId.set(cdId.toString());
    }

    public String getCdCorreo() {
        return cdCorreo.get();
    }

    public void setCdCorreo(String cdCorreo) {
        this.cdCorreo.set(cdCorreo);
    }
    
    public CliReporteDto getCliReporteDto() {
        return cliReporteDto;
    }

    public void setCliReporteDto(CliReporteDto cliReporteDto) {
        this.cliReporteDto = cliReporteDto;
    }

    public Long getCdVersion() {
        return cdVersion;
    }

    public void setCdVersion(Long cdVersion) {
        this.cdVersion = cdVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
