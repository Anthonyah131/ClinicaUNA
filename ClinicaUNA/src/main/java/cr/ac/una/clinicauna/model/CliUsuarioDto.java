/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliUsuarioDto {
    private SimpleStringProperty usuId;
    private SimpleStringProperty usuNombre;
    private SimpleStringProperty usuPapellido;
    private SimpleStringProperty usuSapellido;
    private SimpleStringProperty usuCedula;
    private SimpleStringProperty usuCorreo;
    private SimpleStringProperty usuTipousuario;
    private SimpleStringProperty usuUsuario;
    private SimpleStringProperty usuClave;
    private SimpleStringProperty usuTempClave;
    private SimpleStringProperty usuIdioma;
    private SimpleBooleanProperty usuActivo;
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
        this.usuTipousuario = new SimpleStringProperty("R");
        this.usuUsuario = new SimpleStringProperty();
        this.usuClave = new SimpleStringProperty();
        this.usuTempClave = new SimpleStringProperty();
        this.usuIdioma = new SimpleStringProperty();
        this.usuActivo = new SimpleBooleanProperty(false);
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
        return usuNombre.get();
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre.set(usuNombre);
    }

    public String getUsuPapellido() {
        return usuPapellido.get();
    }

    public void setUsuPapellido(String usuPapellido) {
        this.usuPapellido.set(usuPapellido);
    }

    public String getUsuSapellido() {
        return usuSapellido.get();
    }

    public void setUsuSapellido(String usuSapellido) {
        this.usuSapellido.set(usuSapellido);
    }

    public String getUsuCedula() {
        return usuCedula.get();
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
        return usuTipousuario.get();
    }

    public void setUsuTipousuario(String usuTipousuario) {
        this.usuTipousuario.set(usuTipousuario);
    }

    public String getUsuUsuario() {
        return usuUsuario.get();
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario.set(usuUsuario);
    }

    public String getUsuClave() {
        return usuClave.get();
    }

    public void setUsuClave(String usuClave) {
        this.usuClave.set(usuClave);
    }

    public String getUsuTempClave() {
        return usuTempClave.get();
    }

    public void setUsuTempClave(String usuTempClave) {
        this.usuTempClave.set(usuTempClave);
    }

    public String getUsuIdioma() {
        return usuIdioma.get();
    }

    public void setUsuIdioma(String usuIdioma) {
        this.usuIdioma.set(usuIdioma);
    }

    public String getUsuActivo() {
        return usuActivo.get() ? "A" : "I";
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo.set(usuActivo.equals("A"));
    }

    public Long getUsuVersion() {
        return usuVersion;
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