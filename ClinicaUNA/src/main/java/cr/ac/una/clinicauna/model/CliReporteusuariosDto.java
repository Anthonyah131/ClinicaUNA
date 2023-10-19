/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class CliReporteusuariosDto {

    private SimpleStringProperty repusuId;
    private ObjectProperty<LocalDate> repusuFechaemision;
    private ObservableList<CliUsuarioDto> cliUsuarioList;
    private List<CliUsuarioDto> cliUsuarioListEliminados;
    private Long repusuVersion;
    private Boolean modificado;

    public CliReporteusuariosDto() {
        this.repusuId = new SimpleStringProperty();
        this.repusuFechaemision = new SimpleObjectProperty();
        this.cliUsuarioList = FXCollections.observableArrayList();
        this.cliUsuarioListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public Long getRepusuId() {
        if (this.repusuId.get() != null && !this.repusuId.get().isEmpty()) {
            return Long.valueOf(this.repusuId.get());
        } else {
            return null;
        }
    }

    public void setRepusuId(Long repusuId) {
        this.repusuId.set(repusuId.toString());
    }

    public LocalDate getRepusuFechaemision() {
        return repusuFechaemision.get();
    }

    public void setRepusuFechaemision(LocalDate repusuFechaemision) {
        this.repusuFechaemision.set(repusuFechaemision);
    }

    public ObservableList<CliUsuarioDto> getCliUsuarioList() {
        return cliUsuarioList;
    }

    public void setCliUsuarioList(List<CliUsuarioDto> cliUsuarioList) {
        this.cliUsuarioList = FXCollections.observableArrayList(cliUsuarioList);
    }

    public List<CliUsuarioDto> getCliUsuarioListEliminados() {
        return cliUsuarioListEliminados;
    }

    public void setCliUsuarioListEliminados(List<CliUsuarioDto> cliUsuarioListEliminados) {
        this.cliUsuarioListEliminados = cliUsuarioListEliminados;
    }

    public Long getRepusuVersion() {
        return repusuVersion;
    }

    public void setRepusuVersion(Long repusuVersion) {
        this.repusuVersion = repusuVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
