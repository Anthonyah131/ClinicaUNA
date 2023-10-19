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
public class CliReporteagendaDto {

    private Long repageId;
    private LocalDate repageFechainicio;
    private LocalDate repageFechafinal;
    private LocalDate repageFechaemision;
    private Long repageVersion;
    private CliMedicoDto cliMedicoDto;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliReporteagendaDto() {
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliReporteagendaDto(CliReporteagenda cliReporteagenda) {
        this();
        this.repageId = cliReporteagenda.getRepageId();
        this.repageFechainicio = cliReporteagenda.getRepageFechainicio();
        this.repageFechafinal = cliReporteagenda.getRepageFechafinal();
        this.repageFechaemision = cliReporteagenda.getRepageFechaemision();
        this.repageVersion = cliReporteagenda.getRepageVersion();
        this.fecha = LocalDateTime.now();
    }

    
    
    public Long getRepageId() {
        return repageId;
    }

    public void setRepageId(Long repageId) {
        this.repageId = repageId;
    }

    public LocalDate getRepageFechainicio() {
        return repageFechainicio;
    }

    public void setRepageFechainicio(LocalDate repageFechainicio) {
        this.repageFechainicio = repageFechainicio;
    }

    public LocalDate getRepageFechafinal() {
        return repageFechafinal;
    }

    public void setRepageFechafinal(LocalDate repageFechafinal) {
        this.repageFechafinal = repageFechafinal;
    }

    public LocalDate getRepageFechaemision() {
        return repageFechaemision;
    }

    public void setRepageFechaemision(LocalDate repageFechaemision) {
        this.repageFechaemision = repageFechaemision;
    }

    public Long getRepageVersion() {
        return repageVersion;
    }

    public void setRepageVersion(Long repageVersion) {
        this.repageVersion = repageVersion;
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
