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
public class CliPacienteDto {

    public SimpleStringProperty pacId;
    public SimpleStringProperty pacNombre;
    public SimpleStringProperty pacPapellido;
    public SimpleStringProperty pacSapellido;
    public SimpleStringProperty pacCedula;
    public SimpleStringProperty pacTelefono;
    public SimpleStringProperty pacCorreo;
    public ObjectProperty<String> pacGenero;
    public ObjectProperty<LocalDate> pacFnacimiento;
    public ObservableList<CliExpedienteDto> cliExpedienteList;
    public ObservableList<CliReporteexpedienteDto> cliReporteexpedienteList;
    public ObservableList<CliCitaDto> cliCitaList;
    public List<CliExpedienteDto> cliExpedienteListEliminados;
    public List<CliReporteexpedienteDto> cliReporteexpedienteListEliminados;
    public List<CliCitaDto> cliCitaListEliminados;
    private Long pacVersion;
    private Boolean modificado;

    public CliPacienteDto() {
        this.pacId = new SimpleStringProperty();
        this.pacNombre = new SimpleStringProperty();
        this.pacPapellido = new SimpleStringProperty();
        this.pacSapellido = new SimpleStringProperty();
        this.pacCedula = new SimpleStringProperty();
        this.pacTelefono = new SimpleStringProperty();
        this.pacCorreo = new SimpleStringProperty();
        this.pacGenero = new SimpleObjectProperty("M");
        this.pacFnacimiento = new SimpleObjectProperty();
        this.cliExpedienteList = FXCollections.observableArrayList();
        this.cliReporteexpedienteList = FXCollections.observableArrayList();
        this.cliCitaList = FXCollections.observableArrayList();
        this.cliExpedienteListEliminados = new ArrayList<>();
        this.cliReporteexpedienteListEliminados = new ArrayList<>();
        this.cliCitaListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public Long getPacId() {
        if (this.pacId.get() != null && !this.pacId.get().isEmpty()) {
            return Long.valueOf(this.pacId.get());
        } else {
            return null;
        }
    }

    public void setPacId(Long pacId) {
        this.pacId.set(pacId.toString());
    }

    public String getPacNombre() {
        return pacNombre.get();
    }

    public void setPacNombre(String pacNombre) {
        this.pacNombre.set(pacNombre);
    }

    public String getPacPapellido() {
        return pacPapellido.get();
    }

    public void setPacPapellido(String pacPapellido) {
        this.pacPapellido.set(pacPapellido);
    }

    public String getPacSapellido() {
        return pacSapellido.get();
    }

    public void setPacSapellido(String pacSapellido) {
        this.pacSapellido.set(pacSapellido);
    }

    public String getPacCedula() {
        return pacCedula.get();
    }

    public void setPacCedula(String pacCedula) {
        this.pacCedula.set(pacCedula);
    }

    public String getPacTelefono() {
        return pacTelefono.get();
    }

    public void setPacTelefono(String pacTelefono) {
        this.pacTelefono.set(pacTelefono);
    }

    public String getPacCorreo() {
        return pacCorreo.get();
    }

    public void setPacCorreo(String pacCorreo) {
        this.pacCorreo.set(pacCorreo);
    }

    public String getPacGenero() {
        return pacGenero.get();
    }

    public void setPacGenero(String pacGenero) {
        this.pacGenero.set(pacGenero);
    }

    public LocalDate getPacFnacimiento() {
        return pacFnacimiento.get();
    }

    public void setPacFnacimiento(LocalDate pacFnacimiento) {
        this.pacFnacimiento.set(pacFnacimiento);
    }

    public ObservableList<CliExpedienteDto> getCliExpedienteList() {
        return cliExpedienteList;
    }

    public void setCliExpedienteList(List<CliExpedienteDto> cliExpedienteList) {
        this.cliExpedienteList = FXCollections.observableArrayList(cliExpedienteList);
    }

    public ObservableList<CliReporteexpedienteDto> getCliReporteexpedienteList() {
        return cliReporteexpedienteList;
    }

    public void setCliReporteexpedienteList(List<CliReporteexpedienteDto> cliReporteexpedienteList) {
        this.cliReporteexpedienteList = FXCollections.observableArrayList(cliReporteexpedienteList);
    }

    public ObservableList<CliCitaDto> getCliCitaList() {
        return cliCitaList;
    }

    public void setCliCitaList(List<CliCitaDto> cliCitaList) {
        this.cliCitaList = FXCollections.observableArrayList(cliCitaList);
    }

    public List<CliExpedienteDto> getCliExpedienteListEliminados() {
        return cliExpedienteListEliminados;
    }

    public void setCliExpedienteListEliminados(List<CliExpedienteDto> cliExpedienteListEliminados) {
        this.cliExpedienteListEliminados = cliExpedienteListEliminados;
    }

    public List<CliReporteexpedienteDto> getCliReporteexpedienteListEliminados() {
        return cliReporteexpedienteListEliminados;
    }

    public void setCliReporteexpedienteListEliminados(List<CliReporteexpedienteDto> cliReporteexpedienteListEliminados) {
        this.cliReporteexpedienteListEliminados = cliReporteexpedienteListEliminados;
    }

    public List<CliCitaDto> getCliCitaListEliminados() {
        return cliCitaListEliminados;
    }

    public void setCliCitaListEliminados(List<CliCitaDto> cliCitaListEliminados) {
        this.cliCitaListEliminados = cliCitaListEliminados;
    }

    public Long getPacVersion() {
        return pacVersion;
    }

    public void setPacVersion(Long pacVersion) {
        this.pacVersion = pacVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }
    
    public String getNombreString() {
        return getPacNombre() + " " + getPacPapellido() + " " + getPacSapellido();
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
