/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ArauzKJ
 */
public class CliMedicoDto {

    private Long medId;
    private String medCodigo;
    private String medFolio;
    private String medCarne;
    private String medEstado;
    private LocalDate medFini;
    private LocalDate medFfin;
    private Long medEspaciosxhora;
    private Long medVersion;
    private List<CliAgendaDto> cliAgendaList;
    private List<CliAgendaDto> cliAgendaListEliminados;
    private CliUsuarioDto cliUsuarioDto;
    private List<CliReporteagendaDto> cliReporteagendaList;
    private List<CliReporteagendaDto> cliReporteagendaListEliminados;
    private Boolean modificado;

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }

    public String getMedCodigo() {
        return medCodigo;
    }

    public void setMedCodigo(String medCodigo) {
        this.medCodigo = medCodigo;
    }

    public String getMedFolio() {
        return medFolio;
    }

    public void setMedFolio(String medFolio) {
        this.medFolio = medFolio;
    }

    public String getMedCarne() {
        return medCarne;
    }

    public void setMedCarne(String medCarne) {
        this.medCarne = medCarne;
    }

    public String getMedEstado() {
        return medEstado;
    }

    public void setMedEstado(String medEstado) {
        this.medEstado = medEstado;
    }

    public LocalDate getMedFini() {
        return medFini;
    }

    public void setMedFini(LocalDate medFini) {
        this.medFini = medFini;
    }

    public LocalDate getMedFfin() {
        return medFfin;
    }

    public void setMedFfin(LocalDate medFfin) {
        this.medFfin = medFfin;
    }

    public Long getMedVersion() {
        return medVersion;
    }

    public void setMedVersion(Long medVersion) {
        this.medVersion = medVersion;
    }

    public Long getMedEspaciosxhora() {
        return medEspaciosxhora;
    }

    public void setMedEspaciosxhora(Long medEspaciosxhora) {
        this.medEspaciosxhora = medEspaciosxhora;
    }

    public List<CliAgendaDto> getCliAgendaList() {
        return cliAgendaList;
    }

    public void setCliAgendaList(List<CliAgendaDto> cliAgendaList) {
        this.cliAgendaList = cliAgendaList;
    }

    public List<CliAgendaDto> getCliAgendaListEliminados() {
        return cliAgendaListEliminados;
    }

    public void setCliAgendaListEliminados(List<CliAgendaDto> cliAgendaListEliminados) {
        this.cliAgendaListEliminados = cliAgendaListEliminados;
    }

    public CliUsuarioDto getCliUsuarioDto() {
        return cliUsuarioDto;
    }

    public void setCliUsuarioDto(CliUsuarioDto cliUsuarioDto) {
        this.cliUsuarioDto = cliUsuarioDto;
    }

    public List<CliReporteagendaDto> getCliReporteagendaList() {
        return cliReporteagendaList;
    }

    public void setCliReporteagendaList(List<CliReporteagendaDto> cliReporteagendaList) {
        this.cliReporteagendaList = cliReporteagendaList;
    }

    public List<CliReporteagendaDto> getCliReporteagendaListEliminados() {
        return cliReporteagendaListEliminados;
    }

    public void setCliReporteagendaListEliminados(List<CliReporteagendaDto> cliReporteagendaListEliminados) {
        this.cliReporteagendaListEliminados = cliReporteagendaListEliminados;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
