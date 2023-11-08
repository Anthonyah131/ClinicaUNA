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
public class CliParametroconsultaDto {

    private SimpleStringProperty parcId;
    private SimpleStringProperty parcParametro;
    private SimpleStringProperty parcValor;
    private CliReporteDto cliReporteDto;
    private Long parcVersion;
    private Boolean modificado;

    public CliParametroconsultaDto() {
        this.parcId = new SimpleStringProperty();
        this.parcParametro = new SimpleStringProperty();
        this.parcValor = new SimpleStringProperty();
        this.modificado = false;
    }

    public Long getParcId() {
        if (this.parcId.get() != null && !this.parcId.get().isEmpty()) {
            return Long.valueOf(this.parcId.get());
        } else {
            return null;
        }
    }

    public void setParcId(Long parcId) {
        this.parcId.set(parcId.toString());
    }

    public String getParcParametro() {
        return parcParametro.get();
    }

    public void setParcParametro(String parcParametro) {
        this.parcParametro.set(parcParametro);
    }

    public String getParcValor() {
        return parcValor.get();
    }

    public void setParcValor(String parcValor) {
        this.parcValor.set(parcValor);
    }

    public CliReporteDto getCliReporteDto() {
        return cliReporteDto;
    }

    public void setCliReporteDto(CliReporteDto cliReporteDto) {
        this.cliReporteDto = cliReporteDto;
    }
    
    public Long getParcVersion() {
        return parcVersion;
    }

    public void setParcVersion(Long parcVersion) {
        this.parcVersion = parcVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
