package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliCitaDto {

    public SimpleStringProperty citId;
    public SimpleStringProperty citUsuarioRegistra;
    public SimpleStringProperty citMotivo;
    public ObjectProperty<LocalDate> citFechaHora;
    public CliAgendaDto cliAgendaDto;
    public CliPacienteDto cliPacienteDto;
    private Long citVersion;
    private Boolean modificado;

    public CliCitaDto() {
        this.citId = new SimpleStringProperty();
        this.citUsuarioRegistra = new SimpleStringProperty();
        this.citMotivo = new SimpleStringProperty();
        this.citFechaHora = new SimpleObjectProperty();
        this.modificado = false;
    }

    public Long getCitId() {
        if (this.citId.get() != null && !this.citId.get().isEmpty()) {
            return Long.valueOf(this.citId.get());
        } else {
            return null;
        }
    }

    public void setCitId(Long citId) {
        this.citId.set(citId.toString());
    }

    public String getCitUsuarioRegistra() {
        return citUsuarioRegistra.get();
    }

    public void setCitUsuarioRegistra(String citUsuarioRegistra) {
        this.citUsuarioRegistra.set(citUsuarioRegistra);
    }

    public String getCitMotivo() {
        return citMotivo.get();
    }

    public void setCitMotivo(String citMotivo) {
        this.citMotivo.set(citMotivo);
    }

    public LocalDate getCitFechaHora() {
        return citFechaHora.get();
    }

    public void setCitFechaHora(LocalDate citFechaHora) {
        this.citFechaHora.set(citFechaHora);
    }

    public CliAgendaDto getCliAgendaDto() {
        return cliAgendaDto;
    }

    public void setCliAgendaDto(CliAgendaDto cliAgendaDto) {
        this.cliAgendaDto = cliAgendaDto;
    }

    public CliPacienteDto getCliPacienteDto() {
        return cliPacienteDto;
    }

    public void setCliPacienteDto(CliPacienteDto cliPacienteDto) {
        this.cliPacienteDto = cliPacienteDto;
    }

    public Long getCitVersion() {
        return citVersion;
    }

    public void setCitVersion(Long citVersion) {
        this.citVersion = citVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
