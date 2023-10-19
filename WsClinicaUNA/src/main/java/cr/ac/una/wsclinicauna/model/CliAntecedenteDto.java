/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

/**
 *
 * @author ArauzKJ
 */
public class CliAntecedenteDto {
    private Long antId;
    private String antDescripcion;
    private String antTipo;
    private String antParentesco;
    private Long antVersion;
    private CliExpedienteDto cliExpedienteDto;
    private Boolean modificado;

    public Long getAntId() {
        return antId;
    }

    public void setAntId(Long antId) {
        this.antId = antId;
    }

    public String getAntDescripcion() {
        return antDescripcion;
    }

    public void setAntDescripcion(String antDescripcion) {
        this.antDescripcion = antDescripcion;
    }

    public String getAntTipo() {
        return antTipo;
    }

    public void setAntTipo(String antTipo) {
        this.antTipo = antTipo;
    }

    public String getAntParentesco() {
        return antParentesco;
    }

    public void setAntParentesco(String antParentesco) {
        this.antParentesco = antParentesco;
    }

    public Long getAntVersion() {
        return antVersion;
    }

    public void setAntVersion(Long antVersion) {
        this.antVersion = antVersion;
    }

    public CliExpedienteDto getCliExpedienteDto() {
        return cliExpedienteDto;
    }

    public void setCliExpedienteDto(CliExpedienteDto cliExpedienteDto) {
        this.cliExpedienteDto = cliExpedienteDto;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
    
    
}
