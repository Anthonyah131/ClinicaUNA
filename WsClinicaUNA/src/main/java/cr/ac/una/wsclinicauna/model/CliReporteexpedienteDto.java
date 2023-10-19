/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ArauzKJ
 */
public class CliReporteexpedienteDto {
    private Long repexpId;
    private LocalDate repexpFechaemision;
    private Long repexpVersion;
    private CliPacienteDto cliPacienteDto;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliReporteexpedienteDto() {
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliReporteexpedienteDto(CliReporteexpediente cliReporteexpediente) {
        this();
        this.repexpId = cliReporteexpediente.getRepexpId();
        this.repexpFechaemision = cliReporteexpediente.getRepexpFechaemision();
        this.repexpVersion = cliReporteexpediente.getRepexpVersion();
    }
    
    
    
    public Long getRepexpId() {
        return repexpId;
    }

    public void setRepexpId(Long repexpId) {
        this.repexpId = repexpId;
    }

    public LocalDate getRepexpFechaemision() {
        return repexpFechaemision;
    }

    public void setRepexpFechaemision(LocalDate repexpFechaemision) {
        this.repexpFechaemision = repexpFechaemision;
    }

    public Long getRepexpVersion() {
        return repexpVersion;
    }

    public void setRepexpVersion(Long repexpVersion) {
        this.repexpVersion = repexpVersion;
    }

    public CliPacienteDto getCliPacienteDto() {
        return cliPacienteDto;
    }

    public void setCliPacienteDto(CliPacienteDto cliPacienteDto) {
        this.cliPacienteDto = cliPacienteDto;
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
