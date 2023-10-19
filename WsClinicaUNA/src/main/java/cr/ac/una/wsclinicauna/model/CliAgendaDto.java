/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArauzKJ
 */
public class CliAgendaDto {

    private Long ageId;
    private LocalDate ageFecha;
    private String ageTiempo;
    private Long ageEspacios;
    private Long ageVersion;
    private List<CliCitaDto> cliCitaList;
    private List<CliCitaDto> cliCitaListEliminados;
    private CliMedicoDto cliMedicoDto;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliAgendaDto() {
        this.cliCitaList = new ArrayList<>();
        this.cliCitaListEliminados = new ArrayList<>();
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliAgendaDto(CliAgenda cliAgenda) {
        this();
        this.ageId = cliAgenda.getAgeId();
        this.ageFecha = cliAgenda.getAgeFecha();
        this.ageTiempo = cliAgenda.getAgeTiempo();
        this.ageEspacios = cliAgenda.getAgeEspacios();
        this.ageVersion = cliAgenda.getAgeVersion();
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }

    public LocalDate getAgeFecha() {
        return ageFecha;
    }

    public void setAgeFecha(LocalDate ageFecha) {
        this.ageFecha = ageFecha;
    }

    public String getAgeTiempo() {
        return ageTiempo;
    }

    public void setAgeTiempo(String ageTiempo) {
        this.ageTiempo = ageTiempo;
    }

    public Long getAgeEspacios() {
        return ageEspacios;
    }

    public void setAgeEspacios(Long ageEspacios) {
        this.ageEspacios = ageEspacios;
    }

    public Long getAgeVersion() {
        return ageVersion;
    }

    public void setAgeVersion(Long ageVersion) {
        this.ageVersion = ageVersion;
    }

    public List<CliCitaDto> getCliCitaList() {
        return cliCitaList;
    }

    public void setCliCitaList(List<CliCitaDto> cliCitaList) {
        this.cliCitaList = cliCitaList;
    }

    public List<CliCitaDto> getCliCitaListEliminados() {
        return cliCitaListEliminados;
    }

    public void setCliCitaListEliminados(List<CliCitaDto> cliCitaListEliminados) {
        this.cliCitaListEliminados = cliCitaListEliminados;
    }

    public CliMedicoDto getCliMedicoDto() {
        return cliMedicoDto;
    }

    public void setCliMedicoDto(CliMedicoDto cliMedicoDto) {
        this.cliMedicoDto = cliMedicoDto;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
