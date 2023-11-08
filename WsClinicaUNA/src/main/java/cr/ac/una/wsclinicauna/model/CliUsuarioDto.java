/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.util.ArrayList;
import java.util.List;

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
    private String usuTempclave;
    private String usuIdioma;
    private String usuActivo;
    private Long usuVersion;
    List<CliMedicoDto> cliMedicoList;
    List<CliMedicoDto> cliMedicoListEliminados;
    private Boolean modificado;
    private String token;

    public CliUsuarioDto() {
        this.modificado = false;
        this.cliMedicoList = new ArrayList<>();
        this.cliMedicoListEliminados = new ArrayList<>();
    }

    public CliUsuarioDto(CliUsuario cliUsuario) {
        this();
        this.usuId = cliUsuario.getUsuId();
        this.usuNombre = cliUsuario.getUsuNombre();
        this.usuPapellido = cliUsuario.getUsuPapellido();
        this.usuSapellido = cliUsuario.getUsuSapellido();
        this.usuCedula = cliUsuario.getUsuCedula();
        this.usuCorreo = cliUsuario.getUsuCorreo();
        this.usuTipousuario = cliUsuario.getUsuTipousuario();
        this.usuUsuario = cliUsuario.getUsuUsuario();
        this.usuClave = cliUsuario.getUsuClave();
        this.usuTempclave = cliUsuario.getUsuTempclave();
        this.usuIdioma = cliUsuario.getUsuIdioma();
        this.usuActivo = cliUsuario.getUsuActivo();
        this.usuVersion = cliUsuario.getUsuVersion();
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

    public String getUsuTempclave() {
        return usuTempclave;
    }

    public void setUsuTempclave(String usuTempclave) {
        this.usuTempclave = usuTempclave;
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

    public List<CliMedicoDto> getCliMedicoList() {
        return cliMedicoList;
    }

    public void setCliMedicoList(List<CliMedicoDto> cliMedicoList) {
        this.cliMedicoList = cliMedicoList;
    }

    public List<CliMedicoDto> getCliMedicoListEliminados() {
        return cliMedicoListEliminados;
    }

    public void setCliMedicoListEliminados(List<CliMedicoDto> cliMedicoListEliminados) {
        this.cliMedicoListEliminados = cliMedicoListEliminados;
    }
    
    
    
}
