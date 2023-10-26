package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANTHONY
 */
public class CliAgendaDto {

    public SimpleStringProperty ageId;
    public ObjectProperty<LocalDate> ageFecha;
    public SimpleStringProperty ageTiempo;
    public SimpleStringProperty ageEspacios;
    public CliMedicoDto cliMedicoDto;
    public ObservableList<CliCitaDto> cliCitaList;
    public List<CliCitaDto> cliCitaListEliminados;
    private Long ageVersion;
    private Boolean modificado;

    public CliAgendaDto() {
        this.ageId = new SimpleStringProperty();
        this.ageFecha = new SimpleObjectProperty();
        this.ageTiempo = new SimpleStringProperty();
        this.ageEspacios = new SimpleStringProperty();
        this.cliCitaList = FXCollections.observableArrayList();
        this.cliCitaListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public Long getAgeId() {
        if (this.ageId.get() != null && !this.ageId.get().isEmpty()) {
            return Long.valueOf(this.ageId.get());
        } else {
            return null;
        }
    }

    public void setAgeId(Long ageId) {
        this.ageId.set(ageId.toString());
    }

    public LocalDate getAgeFecha() {
        return ageFecha.get();
    }

    public void setAgeFecha(LocalDate ageFecha) {
        this.ageFecha.set(ageFecha);
    }

    public String getAgeTiempo() {
        return ageTiempo.get();
    }

    public void setAgeTiempo(String ageTiempo) {
        this.ageTiempo.set(ageTiempo);
    }

    public Long getAgeEspacios() {
        if (this.ageEspacios.get() != null && !this.ageEspacios.get().isEmpty()) {
            return Long.valueOf(this.ageEspacios.get());
        } else {
            return null;
        }
    }

    public void setAgeEspacios(Long ageEspacios) {
        this.ageEspacios.set(ageEspacios.toString());
    }

    public CliMedicoDto getCliMedicoDto() {
        return cliMedicoDto;
    }

    public void setCliMedicoDto(CliMedicoDto cliMedicoDto) {
        this.cliMedicoDto = cliMedicoDto;
    }

    public ObservableList<CliCitaDto> getCliCitaList() {
        return cliCitaList;
    }

    public void setCliCitaList(List<CliCitaDto> cliCitaList) {
        this.cliCitaList = FXCollections.observableArrayList(cliCitaList);
    }

    public List<CliCitaDto> getCliCitaListEliminados() {
        return cliCitaListEliminados;
    }

    public void setCliCitaListEliminados(List<CliCitaDto> cliCitaListEliminados) {
        this.cliCitaListEliminados = cliCitaListEliminados;
    }

    public Long getAgeVersion() {
        return ageVersion;
    }

    public void setAgeVersion(Long ageVersion) {
        this.ageVersion = ageVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
