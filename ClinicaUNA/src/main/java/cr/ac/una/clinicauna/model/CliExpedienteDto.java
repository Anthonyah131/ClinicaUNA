package cr.ac.una.clinicauna.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANTHONY
 */
public class CliExpedienteDto {

    public SimpleStringProperty expId;
    public SimpleStringProperty expHospitalizaciones;
    public SimpleStringProperty expOperaciones;
    public SimpleStringProperty expAlergias;
    public SimpleStringProperty expPatologicos;
    public SimpleStringProperty expTipoalergias;
    public SimpleStringProperty expTratamientos;
    public CliPacienteDto cliPacienteDto;
    public ObservableList<CliExamenDto> cliExamenList;
    public ObservableList<CliAtencionDto> cliAtencionList;
    public ObservableList<CliAntecedenteDto> cliAntecedenteList;
    public List<CliExamenDto> cliExamenListEliminados;
    public List<CliAtencionDto> cliAtencionListEliminados;
    public List<CliAntecedenteDto> cliAntecedenteListEliminados;
    private Long expVersion;
    private Boolean modificado;

    public CliExpedienteDto() {
        this.expId = new SimpleStringProperty();
        this.expHospitalizaciones = new SimpleStringProperty();
        this.expOperaciones = new SimpleStringProperty();
        this.expAlergias = new SimpleStringProperty();
        this.expPatologicos = new SimpleStringProperty();
        this.expTipoalergias = new SimpleStringProperty();
        this.expTratamientos = new SimpleStringProperty();
        this.cliExamenList = FXCollections.observableArrayList();
        this.cliAtencionList = FXCollections.observableArrayList();
        this.cliAntecedenteList = FXCollections.observableArrayList();
        this.cliExamenListEliminados = new ArrayList<>();
        this.cliAtencionListEliminados = new ArrayList<>();
        this.cliAntecedenteListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public Long getExpId() {
        if (this.expId.get() != null && !this.expId.get().isEmpty()) {
            return Long.valueOf(this.expId.get());
        } else {
            return null;
        }
    }

    public void setExpId(Long expId) {
        this.expId.set(expId.toString());
    }

    public Long getExpHospitalizaciones() {
        if (this.expHospitalizaciones.get() != null && !this.expHospitalizaciones.get().isEmpty()) {
            return Long.valueOf(this.expHospitalizaciones.get());
        } else {
            return null;
        }
    }

    public void setExpHospitalizaciones(Long expHospitalizaciones) {
        this.expHospitalizaciones.set(expHospitalizaciones.toString());;
    }

    public Long getExpOperaciones() {
        if (this.expOperaciones.get() != null && !this.expOperaciones.get().isEmpty()) {
            return Long.valueOf(this.expOperaciones.get());
        } else {
            return null;
        }
    }

    public void setExpOperaciones(Long expOperaciones) {
        this.expOperaciones.set(expOperaciones.toString());
    }

    public Long getExpAlergias() {
        if (this.expAlergias.get() != null && !this.expAlergias.get().isEmpty()) {
            return Long.valueOf(this.expAlergias.get());
        } else {
            return null;
        }
    }

    public void setExpAlergias(Long expAlergias) {
        this.expAlergias.set(expAlergias.toString());
    }

    public String getExpPatologicos() {
        return this.expPatologicos.get();
    }

    public void setExpPatologicos(String expPatologicos) {
        this.expPatologicos.set(expPatologicos);
    }

    public String getExpTipoAlergias() {
        return expTipoalergias.get();
    }

    public void setExpTipoAlergias(String expTipoalergias) {
        this.expTipoalergias.set(expTipoalergias);
    }

    public String getExpTratamientos() {
        return expTratamientos.get();
    }

    public void setExpTratamientos(String expTratamientos) {
        this.expTratamientos.set(expTratamientos);
    }

    public CliPacienteDto getCliPacienteDto() {
        return cliPacienteDto;
    }

    public void setCliPacienteDto(CliPacienteDto cliPacienteDto) {
        this.cliPacienteDto = cliPacienteDto;
    }

    public ObservableList<CliExamenDto> getCliExamenList() {
        return cliExamenList;
    }

    public void setCliExamenList(List<CliExamenDto> cliExamenList) {
        this.cliExamenList = FXCollections.observableArrayList(cliExamenList);
    }

    public ObservableList<CliAtencionDto> getCliAtencionList() {
        return cliAtencionList;
    }

    public void setCliAtencionList(List<CliAtencionDto> cliAtencionList) {
        this.cliAtencionList = FXCollections.observableArrayList(cliAtencionList);
    }

    public ObservableList<CliAntecedenteDto> getCliAntecedenteList() {
        return cliAntecedenteList;
    }

    public void setCliAntecedenteList(List<CliAntecedenteDto> cliAntecedenteList) {
        this.cliAntecedenteList = FXCollections.observableArrayList(cliAntecedenteList);
    }

    public List<CliExamenDto> getCliExamenListEliminados() {
        return cliExamenListEliminados;
    }

    public void setCliExamenListEliminados(List<CliExamenDto> cliExamenListEliminados) {
        this.cliExamenListEliminados = cliExamenListEliminados;
    }

    public List<CliAtencionDto> getCliAtencionListEliminados() {
        return cliAtencionListEliminados;
    }

    public void setCliAtencionListEliminados(List<CliAtencionDto> cliAtencionListEliminados) {
        this.cliAtencionListEliminados = cliAtencionListEliminados;
    }

    public List<CliAntecedenteDto> getCliAntecedenteListEliminados() {
        return cliAntecedenteListEliminados;
    }

    public void setCliAntecedenteListEliminados(List<CliAntecedenteDto> cliAntecedenteListEliminados) {
        this.cliAntecedenteListEliminados = cliAntecedenteListEliminados;
    }

    public Long getExpVersion() {
        return expVersion;
    }

    public void setExpVersion(Long expVersion) {
        this.expVersion = expVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
