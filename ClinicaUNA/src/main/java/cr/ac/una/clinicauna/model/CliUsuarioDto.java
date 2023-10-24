/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ANTHONY
 */
public class CliUsuarioDto {
    public SimpleStringProperty usuId;
    public SimpleStringProperty usuNombre;
    public SimpleStringProperty usuPapellido;
    public SimpleStringProperty usuSapellido;
    public SimpleStringProperty usuCedula;
    public SimpleStringProperty usuCorreo;
    public SimpleStringProperty usuTipousuario;
    public SimpleStringProperty usuUsuario;
    public SimpleStringProperty usuClave;
    public SimpleStringProperty usuTempclave;
    public SimpleStringProperty usuIdioma;
    public SimpleBooleanProperty usuActivo;
    ObservableList<CliReporteusuariosDto> cliReporteusuariosList;
    List<CliReporteusuariosDto> cliReporteusuariosListEliminados;
    ObservableList<CliMedicoDto> cliMedicoList;
    List<CliMedicoDto> cliMedicoListEliminados;
    private Long usuVersion;
    private Boolean modificado;
    private String token;

    public CliUsuarioDto() {
        this.usuId = new SimpleStringProperty();
        this.usuNombre = new SimpleStringProperty();
        this.usuPapellido = new SimpleStringProperty();
        this.usuSapellido = new SimpleStringProperty();
        this.usuCedula = new SimpleStringProperty();
        this.usuCorreo = new SimpleStringProperty();
        this.usuTipousuario = new SimpleStringProperty();
        this.usuUsuario = new SimpleStringProperty();
        this.usuClave = new SimpleStringProperty();
        this.usuTempclave = new SimpleStringProperty();
        this.usuIdioma = new SimpleStringProperty();
        this.usuActivo = new SimpleBooleanProperty(false);
        this.cliReporteusuariosList = FXCollections.observableArrayList();
        this.cliReporteusuariosListEliminados = new ArrayList<>();
        this.cliMedicoList = FXCollections.observableArrayList();
        this.cliMedicoListEliminados = new ArrayList<>();
        this.modificado = false;
    }
    
    public Long getUsuId() {
        if (this.usuId.get() != null && !this.usuId.get().isEmpty()) {
            return Long.valueOf(this.usuId.get());
        } else {
            return null;
        }
    }

    public void setUsuId(Long usuId) {
        this.usuId.set(usuId.toString());
    }

    public String getUsuNombre() {
        return this.usuNombre.get();
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre.set(usuNombre);
    }

    public String getUsuPapellido() {
        return this.usuPapellido.get();
    }

    public void setUsuPapellido(String usuPapellido) {
        this.usuPapellido.set(usuPapellido);
    }

    public String getUsuSapellido() {
        return this.usuSapellido.get();
    }

    public void setUsuSapellido(String usuSapellido) {
        this.usuSapellido.set(usuSapellido);
    }

    public String getUsuCedula() {
        return this.usuCedula.get();
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula.set(usuCedula);
    }

    public String getUsuCorreo() {
        return usuCorreo.get();
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo.set(usuCorreo);
    }

    public String getUsuTipousuario() {
        return this.usuTipousuario.get();
    }

    public void setUsuTipousuario(String usuTipousuario) {
        this.usuTipousuario.set(usuTipousuario);
    }

    public String getUsuUsuario() {
        return this.usuUsuario.get();
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario.set(usuUsuario);
    }

    public String getUsuClave() {
        return this.usuClave.get();
    }

    public void setUsuClave(String usuClave) {
        this.usuClave.set(usuClave);
    }

    public String getUsuTempClave() {
        return this.usuTempclave.get();
    }

    public void setUsuTempClave(String usuTempclave) {
        this.usuTempclave.set(usuTempclave);
    }

    public String getUsuIdioma() {
        return this.usuIdioma.get();
    }

    public void setUsuIdioma(String usuIdioma) {
        this.usuIdioma.set(usuIdioma);
    }

    public String getUsuActivo() {
        return this.usuActivo.get() ? "A" : "I";
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo.set(usuActivo.equals("A"));
    }

    public ObservableList<CliReporteusuariosDto> getCliReporteusuariosList() {
        return this.cliReporteusuariosList;
    }

    public void setCliReporteusuariosList(List<CliReporteusuariosDto> cliReporteusuariosList) {
        this.cliReporteusuariosList = FXCollections.observableArrayList(cliReporteusuariosList);
    }

    public List<CliReporteusuariosDto> getCliReporteusuariosListEliminados() {
        return this.cliReporteusuariosListEliminados;
    }

    public void setCliReporteusuariosListEliminados(List<CliReporteusuariosDto> cliReporteusuariosListEliminados) {
        this.cliReporteusuariosListEliminados = cliReporteusuariosListEliminados;
    }

    public ObservableList<CliMedicoDto> getCliMedicoList() {
        return this.cliMedicoList;
    }

    public void setCliMedicoList(List<CliMedicoDto> cliMedicoList) {
        this.cliMedicoList = FXCollections.observableArrayList(cliMedicoList);
    }

    public List<CliMedicoDto> getCliMedicoListEliminados() {
        return this.cliMedicoListEliminados;
    }

    public void setCliMedicoListEliminados(List<CliMedicoDto> cliMedicoListEliminados) {
        this.cliMedicoListEliminados = cliMedicoListEliminados;
    }

    public Long getUsuVersion() {
        return this.usuVersion;
    }

    public void setUsuVersion(Long usuVersion) {
        this.usuVersion = usuVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}