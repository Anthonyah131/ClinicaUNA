/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;

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
    
    
}
