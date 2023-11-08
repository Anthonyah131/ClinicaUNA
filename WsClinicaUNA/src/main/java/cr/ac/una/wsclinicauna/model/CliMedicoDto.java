/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private LocalDateTime medFini;
    private LocalDateTime medFfin;
    private Long medEspaciosxhora;
    private Long medVersion;
    private List<CliAgendaDto> cliAgendaList;
    private List<CliAgendaDto> cliAgendaListEliminados;
    private CliUsuarioDto cliUsuarioDto;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliMedicoDto() {
        this.cliAgendaList = new ArrayList<>();
        this.cliAgendaListEliminados = new ArrayList<>();
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliMedicoDto(CliMedico cliMedico) {
        this();
        this.medId = cliMedico.getMedId();
        this.medCodigo = cliMedico.getMedCodigo();
        this.medFolio = cliMedico.getMedFolio();
        this.medCarne = cliMedico.getMedCarne();
        this.medEstado = cliMedico.getMedEstado();
        this.medFini = cliMedico.getMedFini();
        this.medFfin = cliMedico.getMedFfin();
        this.medEspaciosxhora = cliMedico.getMedEspaciosxhora();
        this.medVersion = cliMedico.getMedVersion();
        this.fecha = LocalDateTime.now();
    }

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

    public LocalDateTime getMedFini() {
        return medFini;
    }

    public void setMedFini(LocalDateTime medFini) {
        this.medFini = medFini;
    }

    public LocalDateTime getMedFfin() {
        return medFfin;
    }

    public void setMedFfin(LocalDateTime medFfin) {
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
