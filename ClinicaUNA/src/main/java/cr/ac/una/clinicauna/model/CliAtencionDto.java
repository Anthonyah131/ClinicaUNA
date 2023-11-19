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
public class CliAtencionDto {

    public SimpleStringProperty ateId;
    public ObjectProperty<LocalDateTime> ateFechahora;
    public SimpleStringProperty atePresion;
    public SimpleStringProperty ateFrecuenciacard;
    public SimpleStringProperty atePeso;
    public SimpleStringProperty ateTalla;
    public SimpleStringProperty ateTemperatura;
    public SimpleStringProperty ateImc;
    public SimpleStringProperty ateAnotacionenfe;
    public SimpleStringProperty ateRazonconsulta;
    public SimpleStringProperty atePlanatencion;
    public SimpleStringProperty ateObservaciones;
    public SimpleStringProperty ateTratamiento;
    public CliExpedienteDto cliExpedienteDto;
    public ObservableList<CliExamenDto> cliExamenList;
    public List<CliExamenDto> cliExamenListEliminados;
    private Long ateVersion;
    private Boolean modificado;

    public CliAtencionDto() {
        this.ateId = new SimpleStringProperty();
        this.ateFechahora = new SimpleObjectProperty();
        this.atePresion = new SimpleStringProperty();
        this.ateFrecuenciacard = new SimpleStringProperty();
        this.atePeso = new SimpleStringProperty();
        this.ateTalla = new SimpleStringProperty();
        this.ateTemperatura = new SimpleStringProperty();
        this.ateImc = new SimpleStringProperty();
        this.ateAnotacionenfe = new SimpleStringProperty();
        this.ateRazonconsulta = new SimpleStringProperty();
        this.atePlanatencion = new SimpleStringProperty();
        this.ateObservaciones = new SimpleStringProperty();
        this.ateTratamiento = new SimpleStringProperty();
        this.cliExamenList = FXCollections.observableArrayList();
        this.cliExamenListEliminados = new ArrayList<>();
        this.modificado = false;
    }

    public Long getAteId() {
        if (this.ateId.get() != null && !this.ateId.get().isEmpty()) {
            return Long.valueOf(this.ateId.get());
        } else {
            return null;
        }
    }

    public void setAteId(Long ateId) {
        this.ateId.set(ateId.toString());
    }

    public LocalDateTime getAteFechahora() {
        return ateFechahora.get();
    }

    public void setAteFechahora(LocalDateTime ateFechahora) {
        this.ateFechahora.set(ateFechahora);
    }

    public String getAtePresion() {
        return atePresion.get();
    }

    public void setAtePresion(String atePresion) {
        this.atePresion.set(atePresion);
    }

    public String getAteFrecuenciacard() {
        return ateFrecuenciacard.get();
    }

    public void setAteFrecuenciacard(String ateFrecuenciacard) {
        this.ateFrecuenciacard.set(ateFrecuenciacard);
    }

    public String getAtePeso() {
        return atePeso.get();
    }

    public void setAtePeso(String atePeso) {
        this.atePeso.set(atePeso);
    }

    public String getAteTalla() {
        return ateTalla.get();
    }

    public void setAteTalla(String ateTalla) {
        this.ateTalla.set(ateTalla);
    }

    public String getAteTemperatura() {
        return ateTemperatura.get();
    }

    public void setAteTemperatura(String ateTemperatura) {
        this.ateTemperatura.set(ateTemperatura);
    }

    public String getAteImc() {
        return ateImc.get();
    }

    public void setAteImc(String ateImc) {
        this.ateImc.set(ateImc);
    }

    public String getAteAnotacionenfe() {
        return ateAnotacionenfe.get();
    }

    public void setAteAnotacionenfe(String ateAnotacionenfe) {
        this.ateAnotacionenfe.set(ateAnotacionenfe);
    }

    public String getAteRazonconsulta() {
        return ateRazonconsulta.get();
    }

    public void setAteRazonconsulta(String ateRazonconsulta) {
        this.ateRazonconsulta.set(ateRazonconsulta);
    }

    public String getAtePlanatencion() {
        return atePlanatencion.get();
    }

    public void setAtePlanatencion(String atePlanatencion) {
        this.atePlanatencion.set(atePlanatencion);
    }

    public String getAteObservaciones() {
        return ateObservaciones.get();
    }

    public void setAteObservaciones(String ateObservaciones) {
        this.ateObservaciones.set(ateObservaciones);
    }

    public String getAteTratamiento() {
        return ateTratamiento.get();
    }

    public void setAteTratamiento(String ateTratamiento) {
        this.ateTratamiento.set(ateTratamiento);
    }

    public CliExpedienteDto getCliExpedienteDto() {
        return cliExpedienteDto;
    }

    public void setCliExpedienteDto(CliExpedienteDto cliExpedienteDto) {
        this.cliExpedienteDto = cliExpedienteDto;
    }

    public ObservableList<CliExamenDto> getCliExamenList() {
        return cliExamenList;
    }

    public void setCliExamenList(List<CliExamenDto> cliExamenList) {
        this.cliExamenList = FXCollections.observableArrayList(cliExamenList);
    }

    public List<CliExamenDto> getCliExamenListEliminados() {
        return cliExamenListEliminados;
    }

    public void setCliExamenListEliminados(List<CliExamenDto> cliExamenListEliminados) {
        this.cliExamenListEliminados = cliExamenListEliminados;
    }

    public Long getAteVersion() {
        return ateVersion;
    }

    public void setAteVersion(Long ateVersion) {
        this.ateVersion = ateVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}
