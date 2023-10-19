/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.QueryHint;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_USUARIO", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliUsuario.findAll", query = "SELECT c FROM CliUsuario c"),
    @NamedQuery(name = "CliUsuario.findByUsuId", query = "SELECT c FROM CliUsuario c WHERE c.usuId = :usuId"),
    @NamedQuery(name = "CliUsuario.findByUsuNombre", query = "SELECT c FROM CliUsuario c WHERE c.usuNombre = :usuNombre"),
    @NamedQuery(name = "CliUsuario.findByUsuPapellido", query = "SELECT c FROM CliUsuario c WHERE c.usuPapellido = :usuPapellido"),
    @NamedQuery(name = "CliUsuario.findByUsuSapellido", query = "SELECT c FROM CliUsuario c WHERE c.usuSapellido = :usuSapellido"),
    @NamedQuery(name = "CliUsuario.findByUsuCedula", query = "SELECT c FROM CliUsuario c WHERE c.usuCedula = :usuCedula"),
    @NamedQuery(name = "CliUsuario.findByUsuCorreo", query = "SELECT c FROM CliUsuario c WHERE c.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "CliUsuario.findByUsuTipousuario", query = "SELECT c FROM CliUsuario c WHERE c.usuTipousuario = :usuTipousuario"),
    @NamedQuery(name = "CliUsuario.findByUsuUsuario", query = "SELECT c FROM CliUsuario c WHERE c.usuUsuario = :usuUsuario"),
    @NamedQuery(name = "CliUsuario.findByUsuClave", query = "SELECT c FROM CliUsuario c WHERE c.usuUsuario = :usuUsuario and c.usuClave = :usuClave", hints = @QueryHint(name = "eclipselink.refresh", value = "true")),
    @NamedQuery(name = "CliUsuario.findByUsuClave", query = "SELECT c FROM CliUsuario c WHERE c.usuClave = :usuClave"),
    @NamedQuery(name = "CliUsuario.findByUsuTempclave", query = "SELECT c FROM CliUsuario c WHERE c.usuTempclave = :usuTempclave"),
    @NamedQuery(name = "CliUsuario.findByUsuIdioma", query = "SELECT c FROM CliUsuario c WHERE c.usuIdioma = :usuIdioma"),
    @NamedQuery(name = "CliUsuario.findByUsuActivo", query = "SELECT c FROM CliUsuario c WHERE c.usuActivo = :usuActivo"),
    @NamedQuery(name = "CliUsuario.findByUsuVersion", query = "SELECT c FROM CliUsuario c WHERE c.usuVersion = :usuVersion")})
public class CliUsuario implements Serializable {
    
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_USUARIOS_USU_ID_GENERATOR", sequenceName = "CLI_USUARIOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_USUARIOS_USU_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_ID")
    private Long usuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USU_NOMBRE")
    private String usuNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USU_PAPELLIDO")
    private String usuPapellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USU_SAPELLIDO")
    private String usuSapellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "USU_CEDULA")
    private String usuCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "USU_CORREO")
    private String usuCorreo;
    @Size(max = 1)
    @Column(name = "USU_TIPOUSUARIO")
    private String usuTipousuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USU_USUARIO")
    private String usuUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "USU_CLAVE")
    private String usuClave;
    @Size(max = 15)
    @Column(name = "USU_TEMPCLAVE")
    private String usuTempclave;
    @Size(max = 1)
    @Column(name = "USU_IDIOMA")
    private String usuIdioma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "USU_ACTIVO")
    private String usuActivo;
    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_VERSION")
    private Long usuVersion;
    @JoinTable(name = "CLI_USUARIOREPORTEUSUARIOS", joinColumns = {
        @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PARUSU_ID", referencedColumnName = "REPUSU_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CliReporteusuarios> cliReporteusuariosList;
    @OneToMany(mappedBy = "usuId", fetch = FetchType.LAZY)
    private List<CliMedico> cliMedicoList;
    
    public CliUsuario() {
    }
    
    public CliUsuario(CliUsuarioDto cliUsuarioDto) {
        this.usuId = cliUsuarioDto.getUsuId();
        actualizar(cliUsuarioDto);
    }
    
    public void actualizar(CliUsuarioDto cliUsuarioDto) {
        this.usuNombre = cliUsuarioDto.getUsuNombre();
        this.usuPapellido = cliUsuarioDto.getUsuPapellido();
        this.usuSapellido = cliUsuarioDto.getUsuSapellido();
        this.usuCedula = cliUsuarioDto.getUsuCedula();
        this.usuCorreo = cliUsuarioDto.getUsuCorreo();
        this.usuTipousuario = cliUsuarioDto.getUsuTipousuario();
        this.usuUsuario = cliUsuarioDto.getUsuUsuario();
        this.usuClave = cliUsuarioDto.getUsuClave();
        this.usuTempclave = cliUsuarioDto.getUsuTempclave();
        this.usuIdioma = cliUsuarioDto.getUsuIdioma();
        this.usuActivo = cliUsuarioDto.getUsuActivo();
        this.usuVersion = cliUsuarioDto.getUsuVersion();
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
    
    public List<CliReporteusuarios> getCliReporteusuariosList() {
        return cliReporteusuariosList;
    }
    
    public void setCliReporteusuariosList(List<CliReporteusuarios> cliReporteusuariosList) {
        this.cliReporteusuariosList = cliReporteusuariosList;
    }
    
    public List<CliMedico> getCliMedicoList() {
        return cliMedicoList;
    }
    
    public void setCliMedicoList(List<CliMedico> cliMedicoList) {
        this.cliMedicoList = cliMedicoList;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliUsuario)) {
            return false;
        }
        CliUsuario other = (CliUsuario) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliUsuario[ usuId=" + usuId + " ]";
    }
    
}
