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
public class CliReporteDto {

    public SimpleStringProperty repId;
    public SimpleStringProperty repNombre;
    public SimpleStringProperty repDescripcion;
    public SimpleStringProperty repConsulta;
    public SimpleStringProperty repTitulo;
    public SimpleStringProperty repPeriodicidad;
    public ObjectProperty<LocalDate> repFfin;
    ObservableList<CliParametroconsultaDto> cliParametroconsultaList;
    List<CliParametroconsultaDto> cliParametroconsultaListEliminados;
    ObservableList<CliCorreodestinoDto> cliCorreodestinoList;
    List<CliCorreodestinoDto> cliCorreodestinoListEliminados;
    private Long repVersion;
    private Boolean modificado;

    public CliReporteDto() {
        this.repId = new SimpleStringProperty();
        this.repNombre = new SimpleStringProperty();
        this.repDescripcion = new SimpleStringProperty();
        this.repConsulta = new SimpleStringProperty();
        this.repTitulo = new SimpleStringProperty();
        this.repPeriodicidad = new SimpleStringProperty();
        this.repFfin = new SimpleObjectProperty();
        this.cliParametroconsultaList = FXCollections.observableArrayList();
        this.cliParametroconsultaListEliminados = new ArrayList<>();
        this.cliCorreodestinoList = FXCollections.observableArrayList();
        this.cliCorreodestinoListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public Long getRepId() {
        if (this.repId.get() != null && !this.repId.get().isEmpty()) {
            return Long.valueOf(this.repId.get());
        } else {
            return null;
        }
    }

    public void setRepId(Long repId) {
        this.repId.set(repId.toString());
    }

    public String getRepNombre() {
        return repNombre.get();
    }

    public void setRepNombre(String repNombre) {
        this.repNombre.set(repNombre);
    }

    public String getRepDescripcion() {
        return repDescripcion.get();
    }

    public void setRepDescripcion(String repDescripcion) {
        this.repDescripcion.set(repDescripcion);
    }

    public String getRepConsulta() {
        return repConsulta.get();
    }

    public void setRepConsulta(String repConsulta) {
        this.repConsulta.set(repConsulta);
    }

    public String getRepTitulo() {
        return repTitulo.get();
    }

    public void setRepTitulo(String repTitulo) {
        this.repTitulo.set(repTitulo);
    }

    public String getRepPeriodicidad() {
        return repPeriodicidad.get();
    }

    public void setRepPeriodicidad(String repPeriodicidad) {
        this.repPeriodicidad.set(repPeriodicidad);
    }

    public LocalDate getRepFfin() {
        return repFfin.get();
    }

    public void setRepFfin(LocalDate repFfin) {
        this.repFfin.set(repFfin);
    }

    public ObservableList<CliParametroconsultaDto> getCliParametroconsultaList() {
        return cliParametroconsultaList;
    }

    public void setCliParametroconsultaList(List<CliParametroconsultaDto> cliParametroconsultaList) {
        this.cliParametroconsultaList = FXCollections.observableArrayList(cliParametroconsultaList);
    }

    public List<CliParametroconsultaDto> getCliParametroconsultaListEliminados() {
        return cliParametroconsultaListEliminados;
    }

    public void setCliParametroconsultaListEliminados(List<CliParametroconsultaDto> cliParametroconsultaListEliminados) {
        this.cliParametroconsultaListEliminados = cliParametroconsultaListEliminados;
    }

    public ObservableList<CliCorreodestinoDto> getCliCorreodestinoList() {
        return cliCorreodestinoList;
    }

    public void setCliCorreodestinoList(List<CliCorreodestinoDto> cliCorreodestinoList) {
        this.cliCorreodestinoList = FXCollections.observableArrayList(cliCorreodestinoList);
    }

    public List<CliCorreodestinoDto> getCliCorreodestinoListEliminados() {
        return cliCorreodestinoListEliminados;
    }

    public void setCliCorreodestinoListEliminados(List<CliCorreodestinoDto> cliCorreodestinoListEliminados) {
        this.cliCorreodestinoListEliminados = cliCorreodestinoListEliminados;
    }

    public Long getRepVersion() {
        return repVersion;
    }

    public void setRepVersion(Long repVersion) {
        this.repVersion = repVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
