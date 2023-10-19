/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArauzKJ
 */
public class CliExpedienteDto {

    private Long expId;
    private Long expHospitalizaciones;
    private Long expOperaciones;
    private Long expAlergias;
    private String expPatologicos;
    private String expTiposalergias;
    private String expTratamientos;
    private Long expVersion;
    private CliPacienteDto cliPacienteDto;
    private List<CliExamenDto> cliExamenList;
    private List<CliAtencionDto> cliAtencionList;
    private List<CliAntecedenteDto> cliAntecedenteList;
    private List<CliExamenDto> cliExamenListEliminados;
    private List<CliAtencionDto> cliAtencionListEliminados;
    private List<CliAntecedenteDto> cliAntecedenteListEliminados;
    private Boolean modificado;

    public CliExpedienteDto() {
        this.cliExamenList = new ArrayList<>();
        this.cliAtencionList = new ArrayList<>();
        this.cliAntecedenteList = new ArrayList<>();
        this.cliExamenListEliminados = new ArrayList<>();
        this.cliAtencionListEliminados = new ArrayList<>();
        this.cliAntecedenteListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public CliExpedienteDto(CliExpediente cliExpediente) {
        this();
        this.expId = cliExpediente.getExpId();
        this.expHospitalizaciones = cliExpediente.getExpHospitalizaciones();
        this.expOperaciones = cliExpediente.getExpOperaciones();
        this.expAlergias = cliExpediente.getExpAlergias();
        this.expPatologicos = cliExpediente.getExpPatologicos();
        this.expTratamientos = cliExpediente.getExpTratamientos();
        this.expVersion = cliExpediente.getExpVersion();
    }

    
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

    public CliPacienteDto getCliPacienteDto() {
        return cliPacienteDto;
    }

    public void setCliPacienteDto(CliPacienteDto cliPacienteDto) {
        this.cliPacienteDto = cliPacienteDto;
    }

    public List<CliExamenDto> getCliExamenList() {
        return cliExamenList;
    }

    public void setCliExamenList(List<CliExamenDto> cliExamenList) {
        this.cliExamenList = cliExamenList;
    }

    public List<CliAtencionDto> getCliAtencionList() {
        return cliAtencionList;
    }

    public void setCliAtencionList(List<CliAtencionDto> cliAtencionList) {
        this.cliAtencionList = cliAtencionList;
    }

    public List<CliAntecedenteDto> getCliAntecedenteList() {
        return cliAntecedenteList;
    }

    public void setCliAntecedenteList(List<CliAntecedenteDto> cliAntecedenteList) {
        this.cliAntecedenteList = cliAntecedenteList;
    }

    public List<CliExamenDto> getCliExamenListEliminados() {
        return cliExamenListEliminados;
    }

    public void setCliExamenListEliminados(List<CliExamenDto> cliExamenListEliminados) {
        this.cliExamenListEliminados = cliExamenListEliminados;
    }

    public List<CliAtencionDto> getCliAtencionListEliminados() {
        return cliAtencionListEliminados;
    }

    public void setCliAtencionListEliminados(List<CliAtencionDto> cliAtencionListEliminados) {
        this.cliAtencionListEliminados = cliAtencionListEliminados;
    }

    public List<CliAntecedenteDto> getCliAntecedenteListEliminados() {
        return cliAntecedenteListEliminados;
    }

    public void setCliAntecedenteListEliminados(List<CliAntecedenteDto> cliAntecedenteListEliminados) {
        this.cliAntecedenteListEliminados = cliAntecedenteListEliminados;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public String getExpPatologicos() {
        return expPatologicos;
    }

    public void setExpPatologicos(String expPatologicos) {
        this.expPatologicos = expPatologicos;
    }

    public String getExpTiposalergias() {
        return expTiposalergias;
    }

    public void setExpTiposalergias(String expTiposalergias) {
        this.expTiposalergias = expTiposalergias;
    }

}
