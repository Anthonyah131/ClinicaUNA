/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliReporteexpedienteDto {

    private SimpleStringProperty repexpId;
    private ObjectProperty<LocalDate> repexpFechaemision;
    private CliPacienteDto cliPacienteDto;
    private Long repexpVersion;
    private Boolean modificado;

    public CliReporteexpedienteDto() {
        this.repexpId = new SimpleStringProperty();
        this.repexpFechaemision = new SimpleObjectProperty();
        this.modificado = false;
    }

    public Long getRepexpId() {
        if (this.repexpId.get() != null && !this.repexpId.get().isEmpty()) {
            return Long.valueOf(this.repexpId.get());
        } else {
            return null;
        }
    }

    public void setRepexpId(Long repexpId) {
        this.repexpId.set(repexpId.toString());
    }

    public LocalDate getRepexpFechaemision() {
        return repexpFechaemision.get();
    }

    public void setRepexpFechaemision(LocalDate repexpFechaemision) {
        this.repexpFechaemision.set(repexpFechaemision);
    }

    public CliPacienteDto getCliPacienteDto() {
        return cliPacienteDto;
    }

    public void setCliPacienteDto(CliPacienteDto cliPacienteDto) {
        this.cliPacienteDto = cliPacienteDto;
    }

    public Long getRepexpVersion() {
        return repexpVersion;
    }

    public void setRepexpVersion(Long repexpVersion) {
        this.repexpVersion = repexpVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
