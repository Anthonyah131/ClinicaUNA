/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

/**
 *
 * @author ArauzKJ
 */
public class CliUsuarioDto {
    private Long usuId;
    private String usuNombre;
    private String usuPapellido;
    private String usuSapellido;
    private String usuCedula;
    private String usuCorreo;
    private String usuTipousuario;
    private String usuUsuario;
    private String usuClave;
    private String usuTempClave;
    private String usuIdioma;
    private String usuActivo;
    private Long usuVersion;
    private Boolean modificado;
    private String token;

    public CliUsuarioDto() {
        this.modificado = false;
    }

    public CliUsuarioDto(Long usuId, String usuNombre, String usuPapellido, String usuSapellido, String usuCedula, String usuCorreo, String usuTipousuario, String usuUsuario, String usuClave, String usuTempClave, String usuIdioma, String usuActivo, Long usuVersion) {
        this.usuId = usuId;
        this.usuNombre = usuNombre;
        this.usuPapellido = usuPapellido;
        this.usuSapellido = usuSapellido;
        this.usuCedula = usuCedula;
        this.usuCorreo = usuCorreo;
        this.usuTipousuario = usuTipousuario;
        this.usuUsuario = usuUsuario;
        this.usuClave = usuClave;
        this.usuTempClave = usuTempClave;
        this.usuIdioma = usuIdioma;
        this.usuActivo = usuActivo;
        this.usuVersion = usuVersion;
    }

    
    
    public Long getUsuId() {
        return usuId;
    }

    public void setUsuId(Long usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuPapellido() {
        return usuPapellido;
    }

    public void setUsuPapellido(String usuPapellido) {
        this.usuPapellido = usuPapellido;
    }

    public String getUsuSapellido() {
        return usuSapellido;
    }

    public void setUsuSapellido(String usuSapellido) {
        this.usuSapellido = usuSapellido;
    }

    public String getUsuCedula() {
        return usuCedula;
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula = usuCedula;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuTipousuario() {
        return usuTipousuario;
    }

    public void setUsuTipousuario(String usuTipousuario) {
        this.usuTipousuario = usuTipousuario;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuTempClave() {
        return usuTempClave;
    }

    public void setUsuTempClave(String usuTempClave) {
        this.usuTempClave = usuTempClave;
    }

    public String getUsuIdioma() {
        return usuIdioma;
    }

    public void setUsuIdioma(String usuIdioma) {
        this.usuIdioma = usuIdioma;
    }

    public String getUsuActivo() {
        return usuActivo;
    }

    public void setUsuActivo(String usuActivo) {
        this.usuActivo = usuActivo;
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
