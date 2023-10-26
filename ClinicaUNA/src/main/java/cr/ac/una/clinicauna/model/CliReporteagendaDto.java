package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliReporteagendaDto {

    public SimpleStringProperty repageId;
    public ObjectProperty<LocalDate> repageFechainicio;
    public ObjectProperty<LocalDate> repageFechafinal;
    public ObjectProperty<LocalDate> repageFechaemision;
    public CliMedicoDto cliMedicoDto;
    private Long repageVersion;
    private Boolean modificado;

    public CliReporteagendaDto() {
        this.repageId = new SimpleStringProperty();
        this.repageFechainicio = new SimpleObjectProperty();
        this.repageFechafinal = new SimpleObjectProperty();
        this.repageFechaemision = new SimpleObjectProperty();
        this.modificado = false;
    }

    public Long getRepageId() {
        if (this.repageId.get() != null && !this.repageId.get().isEmpty()) {
            return Long.valueOf(this.repageId.get());
        } else {
            return null;
        }
    }

    public void setRepageId(Long repageId) {
        this.repageId.set(repageId.toString());
    }

    public LocalDate getRepageFechainicio() {
        return repageFechainicio.get();
    }

    public void setRepageFechainicio(LocalDate repageFechainicio) {
        this.repageFechainicio.set(repageFechainicio);
    }

    public LocalDate getRepageFechafinal() {
        return repageFechafinal.get();
    }

    public void setRepageFechafinal(LocalDate repageFechafinal) {
        this.repageFechafinal.set(repageFechafinal);
    }

    public LocalDate getRepageFechaemision() {
        return repageFechaemision.get();
    }

    public void setRepageFechaemision(LocalDate repageFechaemision) {
        this.repageFechaemision.set(repageFechaemision);
    }

    public CliMedicoDto getCliMedicoDto() {
        return cliMedicoDto;
    }

    public void setCliMedicoDto(CliMedicoDto cliMedicoDto) {
        this.cliMedicoDto = cliMedicoDto;
    }

    public Long getRepageVersion() {
        return repageVersion;
    }

    public void setRepageVersion(Long repageVersion) {
        this.repageVersion = repageVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
