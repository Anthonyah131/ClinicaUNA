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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_REPORTEUSUARIOS",schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliReporteusuarios.findAll", query = "SELECT c FROM CliReporteusuarios c"),
    @NamedQuery(name = "CliReporteusuarios.findByRepusuId", query = "SELECT c FROM CliReporteusuarios c WHERE c.repusuId = :repusuId"),
    @NamedQuery(name = "CliReporteusuarios.findByRepusuFechaemision", query = "SELECT c FROM CliReporteusuarios c WHERE c.repusuFechaemision = :repusuFechaemision"),
    @NamedQuery(name = "CliReporteusuarios.findByRepusuVersion", query = "SELECT c FROM CliReporteusuarios c WHERE c.repusuVersion = :repusuVersion")})
public class CliReporteusuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_REPORTEUSUARIOS_REPUSU_ID_GENERATOR", sequenceName = "CLI_REPORTEUSUARIOS_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_REPORTEUSUARIOS_REPUSU_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "REPUSU_ID")
    private Long repusuId;
    @Column(name = "REPUSU_FECHAEMISION")
    private LocalDate repusuFechaemision;
    @Basic(optional = false)
    @Column(name = "REPUSU_VERSION")
    private Long repusuVersion;
    @ManyToMany(mappedBy = "cliReporteusuariosList", fetch = FetchType.LAZY)
    private List<CliUsuario> cliUsuarioList;

    public CliReporteusuarios() {
    }

    public CliReporteusuarios(Long repusuId) {
        this.repusuId = repusuId;
    }

    public CliReporteusuarios(CliReporteusuariosDto cliReporteusuariosDto) {
        this.repusuId = cliReporteusuariosDto.getRepusuId();
        actualizar(cliReporteusuariosDto);
    }

    public void actualizar(CliReporteusuariosDto cliReporteusuariosDto) {
        this.repusuFechaemision = cliReporteusuariosDto.getRepusuFechaemision();
        this.repusuVersion = cliReporteusuariosDto.getRepusuVersion();
    }
    
    public Long getRepusuId() {
        return repusuId;
    }

    public void setRepusuId(Long repusuId) {
        this.repusuId = repusuId;
    }

    public LocalDate getRepusuFechaemision() {
        return repusuFechaemision;
    }

    public void setRepusuFechaemision(LocalDate repusuFechaemision) {
        this.repusuFechaemision = repusuFechaemision;
    }

    public Long getRepusuVersion() {
        return repusuVersion;
    }

    public void setRepusuVersion(Long repusuVersion) {
        this.repusuVersion = repusuVersion;
    }

    public List<CliUsuario> getCliUsuarioList() {
        return cliUsuarioList;
    }

    public void setCliUsuarioList(List<CliUsuario> cliUsuarioList) {
        this.cliUsuarioList = cliUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repusuId != null ? repusuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliReporteusuarios)) {
            return false;
        }
        CliReporteusuarios other = (CliReporteusuarios) object;
        if ((this.repusuId == null && other.repusuId != null) || (this.repusuId != null && !this.repusuId.equals(other.repusuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliReporteusuarios[ repusuId=" + repusuId + " ]";
    }
    
}
