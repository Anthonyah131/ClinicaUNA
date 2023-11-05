package cr.ac.una.clinicauna.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public SimpleStringProperty usuActivo;
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
        this.usuActivo = new SimpleStringProperty();
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

    public String getUsuTempclave() {
        return this.usuTempclave.get();
    }

    public void setUsuTempclave(String usuTempclave) {
        this.usuTempclave.set(usuTempclave);
    }

    public String getUsuIdioma() {
        return this.usuIdioma.get();
    }

    public void setUsuIdioma(String usuIdioma) {
        this.usuIdioma.set(usuIdioma);
    }

    public String getUsuActivo() {
        return this.usuActivo.get();
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo.set(usuActivo);
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.usuId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CliUsuarioDto other = (CliUsuarioDto) obj;
        return Objects.equals(this.usuId, other.usuId);
    }

    @Override
    public String toString() {
        return "CliUsuarioDto{" + "usuId=" + usuId + ", usuNombre=" + usuNombre + ", usuPapellido=" + usuPapellido + ", usuSapellido=" + usuSapellido + ", usuCedula=" + usuCedula + ", usuCorreo=" + usuCorreo + ", usuTipousuario=" + usuTipousuario + ", usuUsuario=" + usuUsuario + ", usuClave=" + usuClave + ", usuTempclave=" + usuTempclave + ", usuIdioma=" + usuIdioma + ", usuActivo=" + usuActivo + ", cliReporteusuariosList=" + cliReporteusuariosList + ", cliReporteusuariosListEliminados=" + cliReporteusuariosListEliminados + ", cliMedicoList=" + cliMedicoList + ", cliMedicoListEliminados=" + cliMedicoListEliminados + ", usuVersion=" + usuVersion + ", modificado=" + modificado + ", token=" + token + '}';
    }

    public String getNombreApellido() {
        return usuNombre.get() + " " + usuPapellido.get();
    }

    public String getNombreApellidos() {
        return usuNombre.get() + " " + usuPapellido.get() + " " + usuSapellido.get();
    }

}
