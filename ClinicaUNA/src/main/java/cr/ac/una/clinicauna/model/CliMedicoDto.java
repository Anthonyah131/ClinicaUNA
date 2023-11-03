package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANTHONY
 */
public class CliMedicoDto {

    public SimpleStringProperty medId;
    public SimpleStringProperty medCodigo;
    public SimpleStringProperty medFolio;
    public SimpleStringProperty medCarne;
    public SimpleBooleanProperty medEstado;
    public ObjectProperty<LocalDateTime> medFini;
    public ObjectProperty<LocalDateTime> medFfin;
    public SimpleStringProperty medEspaciosxhora;
    public CliUsuarioDto cliUsuarioDto;
    public ObservableList<CliReporteagendaDto> cliReporteagendaList;
    public ObservableList<CliAgendaDto> cliAgendaList;
    public List<CliReporteagendaDto> cliReporteagendaListEliminados;
    public List<CliAgendaDto> cliAgendaListEliminados;
    private Long medVersion;
    private Boolean modificado;

    public CliMedicoDto() {
        this.medId = new SimpleStringProperty();
        this.medCodigo = new SimpleStringProperty();
        this.medFolio = new SimpleStringProperty();
        this.medCarne = new SimpleStringProperty();
        this.medEstado = new SimpleBooleanProperty(false);
        this.medFini = new SimpleObjectProperty();
        this.medFfin = new SimpleObjectProperty();
        this.medEspaciosxhora = new SimpleStringProperty();
        this.cliAgendaList = FXCollections.observableArrayList();
        this.cliReporteagendaList = FXCollections.observableArrayList();
        this.cliAgendaListEliminados = new ArrayList<>();
        this.cliReporteagendaListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public Long getMedId() {
        if (this.medId.get() != null && !this.medId.get().isEmpty()) {
            return Long.valueOf(this.medId.get());
        } else {
            return null;
        }
    }

    public void setMedId(Long medId) {
        this.medId.set(medId.toString());
    }

    public String getMedCodigo() {
        return medCodigo.get();
    }

    public void setMedCodigo(String medCodigo) {
        this.medCodigo.set(medCodigo);
    }

    public String getMedFolio() {
        return medFolio.get();
    }

    public void setMedFolio(String medFolio) {
        this.medFolio.set(medFolio);
    }

    public String getMedCarne() {
        return medCarne.get();
    }

    public void setMedCarne(String medCarne) {
        this.medCarne.set(medCarne);
    }

    public String getMedEstado() {
        return medEstado.get() ? "A" : "I";
    }

    public void setMedEstado(String medEstado) {
        this.medEstado.set(medEstado.equals("A"));
    }

    public LocalDateTime getMedFini() {
        return medFini.get();
    }

    public void setMedFini(LocalDateTime medFini) {
        this.medFini.set(medFini);
    }

    public LocalTime getMedFiniTime() {
        if (medFini.get() != null) {
            return medFini.get().toLocalTime();
        } else {
            return null;
        }
    }

    public void setMedFiniTime(LocalTime time) {
        LocalDateTime currentDateTime = medFini.get();
        LocalDateTime newDateTime;
        if (currentDateTime != null) {
            newDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), time);
        } else {
            newDateTime = LocalDateTime.of(LocalDate.now(), time);
        }
        medFini.set(newDateTime);
    }

    public LocalDateTime getMedFfin() {
        return medFfin.get();
    }

    public void setMedFfin(LocalDateTime medFfin) {
        this.medFfin.set(medFfin);
    }

    public LocalTime getMedFfinTime() {
        if (medFfin.get() != null) {
            return medFfin.get().toLocalTime();
        } else {
            return null;
        }
    }

    public void setMedFfinTime(LocalTime time) {
        LocalDateTime currentDateTime = medFfin.get();
        LocalDateTime newDateTime;
        if (currentDateTime != null) {
            newDateTime = LocalDateTime.of(currentDateTime.toLocalDate(), time);
        } else {
            newDateTime = LocalDateTime.of(LocalDate.now(), time);
        }
        medFfin.set(newDateTime);
    }

    public Long getMedEspaciosxhora() {
        if (this.medEspaciosxhora.get() != null && !this.medEspaciosxhora.get().isEmpty()) {
            return Long.valueOf(this.medEspaciosxhora.get());
        } else {
            return null;
        }
    }
    
    public String getNombreString() {
        return cliUsuarioDto.getUsuNombre() + " " + cliUsuarioDto.getUsuPapellido() + " " + cliUsuarioDto.getUsuSapellido();
    }

    public void setMedEspaciosxhora(Long medEspaciosxhora) {
        this.medEspaciosxhora.set(medEspaciosxhora.toString());
    }

    public CliUsuarioDto getCliUsuarioDto() {
        return cliUsuarioDto;
    }

    public void setCliUsuarioDto(CliUsuarioDto cliUsuarioDto) {
        this.cliUsuarioDto = cliUsuarioDto;
    }

    public ObservableList<CliAgendaDto> getCliAgendaList() {
        return cliAgendaList;
    }

    public void setCliAgendaList(List<CliAgendaDto> cliAgendaList) {
        this.cliAgendaList = FXCollections.observableArrayList(cliAgendaList);
    }

    public ObservableList<CliReporteagendaDto> getCliReporteagendaList() {
        return cliReporteagendaList;
    }

    public void setCliReporteagendaList(List<CliReporteagendaDto> cliReporteagendaList) {
        this.cliReporteagendaList = FXCollections.observableArrayList(cliReporteagendaList);
    }

    public List<CliAgendaDto> getCliAgendaListEliminados() {
        return cliAgendaListEliminados;
    }

    public void setCliAgendaListEliminados(List<CliAgendaDto> cliAgendaListEliminados) {
        this.cliAgendaListEliminados = cliAgendaListEliminados;
    }

    public List<CliReporteagendaDto> getCliReporteagendaListEliminados() {
        return cliReporteagendaListEliminados;
    }

    public void setCliReporteagendaListEliminados(List<CliReporteagendaDto> cliReporteagendaListEliminados) {
        this.cliReporteagendaListEliminados = cliReporteagendaListEliminados;
    }

    public Long getMedVersion() {
        return medVersion;
    }

    public void setMedVersion(Long medVersion) {
        this.medVersion = medVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
