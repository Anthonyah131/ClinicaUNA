/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    private SimpleStringProperty pacId;
    private SimpleStringProperty pacNombre;
    private SimpleStringProperty pacPapellido;
    private SimpleStringProperty pacSapellido;
    private SimpleStringProperty pacCedula;
    private SimpleStringProperty pacTelefono;
    private SimpleStringProperty pacCorreo;
    private ObjectProperty<String> pacGenero;
    private ObjectProperty<LocalDate> pacFnacimiento;
    private ObservableList<CliExpedienteDto> cliExpedienteList;
    private ObservableList<CliReporteexpedienteDto> cliReporteexpedienteList;
    private ObservableList<CliCitaDto> cliCitaList;
    private List<CliExpedienteDto> cliExpedienteListEliminados;
    private List<CliReporteexpedienteDto> cliReporteexpedienteListEliminados;
    private List<CliCitaDto> cliCitaListEliminados;
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

    public String getPacPrimerApellido() {
        return pacPapellido.get();
    }

    public void setPacPrimerApellido(String pacPapellido) {
        this.pacPapellido.set(pacPapellido);
    }

    public String getPacSegundoApellido() {
        return pacSapellido.get();
    }

    public void setPacSegundoApellido(String pacSapellido) {
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

    public LocalDate getPacFechaNacimiento() {
        return pacFnacimiento.get();
    }

    public void setPacFechaNacimiento(LocalDate pacFnacimiento) {
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

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
