/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

/**
 *
 * @author ArauzKJ
 */
public class CliCorreodestinoDto {

    private Long cdId;
    private String cdCorreo;
    private Long cdVersion;
    private CliReporteDto cliReporteDto;
    private Boolean modificado;

    public CliCorreodestinoDto() {
        this.modificado = false;
    }

    public CliCorreodestinoDto(CliCorreodestino cliCorreodestino) {
        this.cdId = cliCorreodestino.getCdId();
        this.cdCorreo = cliCorreodestino.getCdCorreo();
        this.cdVersion = cliCorreodestino.getCdVersion();
    }

    public Long getCdId() {
        return cdId;
    }

    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    public String getCdCorreo() {
        return cdCorreo;
    }

    public void setCdCorreo(String cdCorreo) {
        this.cdCorreo = cdCorreo;
    }

    public Long getCdVersion() {
        return cdVersion;
    }

    public void setCdVersion(Long cdVersion) {
        this.cdVersion = cdVersion;
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
