/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

/**
 *
 * @author ArauzKJ
 */
public class CliParametroconsultaDto {
    private Long parcId;
    private String parcParametro;
    private String parcValor;
    private Long parcVersion;
    private Boolean modificado;
    private CliReporteDto cliReporteDto;

    public CliParametroconsultaDto() {
        this.modificado = false;
    }

    public CliParametroconsultaDto(CliParametroconsulta cliParametroconsulta) {
        this.parcId = cliParametroconsulta.getParcId();
        this.parcParametro = cliParametroconsulta.getParcParametro();
        this.parcValor = cliParametroconsulta.getParcValor();
        this.parcVersion = cliParametroconsulta.getParcVersion();
    }

    public Long getParcId() {
        return parcId;
    }

    public void setParcId(Long parcId) {
        this.parcId = parcId;
    }

    public String getParcParametro() {
        return parcParametro;
    }

    public void setParcParametro(String parcParametro) {
        this.parcParametro = parcParametro;
    }

    public String getParcValor() {
        return parcValor;
    }

    public void setParcValor(String parcValor) {
        this.parcValor = parcValor;
    }

    public Long getParcVersion() {
        return parcVersion;
    }

    public void setParcVersion(Long parcVersion) {
        this.parcVersion = parcVersion;
    }

    public CliReporteDto getCliReporteDto() {
        return cliReporteDto;
    }

    public void setCliReporteDto(CliReporteDto cliReporteDto) {
        this.cliReporteDto = cliReporteDto;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
    
}
