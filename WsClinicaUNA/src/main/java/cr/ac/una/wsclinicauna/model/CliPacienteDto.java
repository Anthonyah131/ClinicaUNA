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
public class CliPacienteDto {

    private Long pacId;
    private String pacNombre;
    private String pacPapellido;
    private String pacSapellido;
    private String pacCedula;
    private Long pacTelefono;
    private String pacCorreo;
    private String pacGenero;
    private LocalDate pacFnacimiento;
    private Long pacVersion;
    private List<CliExpedienteDto> cliExpedienteList;
    private List<CliCitaDto> cliCitaList;
    private List<CliExpedienteDto> cliExpedienteListEliminados;
    private List<CliCitaDto> cliCitaListEliminados;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliPacienteDto() {
        this.cliExpedienteList = new ArrayList<>();
        this.cliCitaList = new ArrayList<>();
        this.cliExpedienteListEliminados = new ArrayList<>();
        this.cliCitaListEliminados = new ArrayList<>();
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliPacienteDto(CliPaciente cliPaciente) {
        this();
        this.pacId = cliPaciente.getPacId();
        this.pacNombre = cliPaciente.getPacNombre();
        this.pacPapellido = cliPaciente.getPacPapellido();
        this.pacSapellido = cliPaciente.getPacSapellido();
        this.pacCedula = cliPaciente.getPacCedula();
        this.pacTelefono = cliPaciente.getPacTelefono();
        this.pacCorreo = cliPaciente.getPacCorreo();
        this.pacGenero = cliPaciente.getPacGenero();
        this.pacFnacimiento = cliPaciente.getPacFnacimiento();
        this.pacVersion = cliPaciente.getPacVersion();
        this.fecha = LocalDateTime.now();
    }

    public Long getPacId() {
        return pacId;
    }

    public void setPacId(Long pacId) {
        this.pacId = pacId;
    }

    public String getPacNombre() {
        return pacNombre;
    }

    public void setPacNombre(String pacNombre) {
        this.pacNombre = pacNombre;
    }

    public String getPacPapellido() {
        return pacPapellido;
    }

    public void setPacPapellido(String pacPapellido) {
        this.pacPapellido = pacPapellido;
    }

    public String getPacSapellido() {
        return pacSapellido;
    }

    public void setPacSapellido(String pacSapellido) {
        this.pacSapellido = pacSapellido;
    }

    public String getPacCedula() {
        return pacCedula;
    }

    public void setPacCedula(String pacCedula) {
        this.pacCedula = pacCedula;
    }

    public Long getPacTelefono() {
        return pacTelefono;
    }

    public void setPacTelefono(Long pacTelefono) {
        this.pacTelefono = pacTelefono;
    }

    public String getPacCorreo() {
        return pacCorreo;
    }

    public void setPacCorreo(String pacCorreo) {
        this.pacCorreo = pacCorreo;
    }

    public String getPacGenero() {
        return pacGenero;
    }

    public void setPacGenero(String pacGenero) {
        this.pacGenero = pacGenero;
    }

    public LocalDate getPacFnacimiento() {
        return pacFnacimiento;
    }

    public void setPacFnacimiento(LocalDate pacFnacimiento) {
        this.pacFnacimiento = pacFnacimiento;
    }

    public Long getPacVersion() {
        return pacVersion;
    }

    public void setPacVersion(Long pacVersion) {
        this.pacVersion = pacVersion;
    }

    public List<CliExpedienteDto> getCliExpedienteList() {
        return cliExpedienteList;
    }

    public void setCliExpedienteList(List<CliExpedienteDto> cliExpedienteList) {
        this.cliExpedienteList = cliExpedienteList;
    }

    public List<CliCitaDto> getCliCitaList() {
        return cliCitaList;
    }

    public void setCliCitaList(List<CliCitaDto> cliCitaList) {
        this.cliCitaList = cliCitaList;
    }

    public List<CliExpedienteDto> getCliExpedienteListEliminados() {
        return cliExpedienteListEliminados;
    }

    public void setCliExpedienteListEliminados(List<CliExpedienteDto> cliExpedienteListEliminados) {
        this.cliExpedienteListEliminados = cliExpedienteListEliminados;
    }

    public List<CliCitaDto> getCliCitaListEliminados() {
        return cliCitaListEliminados;
    }

    public void setCliCitaListEliminados(List<CliCitaDto> cliCitaListEliminados) {
        this.cliCitaListEliminados = cliCitaListEliminados;
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
