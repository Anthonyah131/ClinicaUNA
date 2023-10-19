/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "CLI_REPORTEEXPEDIENTE",schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliReporteexpediente.findAll", query = "SELECT c FROM CliReporteexpediente c"),
    @NamedQuery(name = "CliReporteexpediente.findByRepexpId", query = "SELECT c FROM CliReporteexpediente c WHERE c.repexpId = :repexpId"),
    @NamedQuery(name = "CliReporteexpediente.findByRepexpFechaemision", query = "SELECT c FROM CliReporteexpediente c WHERE c.repexpFechaemision = :repexpFechaemision"),
    @NamedQuery(name = "CliReporteexpediente.findByRepexpVersion", query = "SELECT c FROM CliReporteexpediente c WHERE c.repexpVersion = :repexpVersion")})
public class CliReporteexpediente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_REPORTEEXPEDIENTE_REPEXP_ID_GENERATOR", sequenceName = "CLI_REPORTEEXPEDIENTE_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_REPORTEEXPEDIENTE_REPEXP_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPEXP_ID")
    private Long repexpId;
    @Column(name = "REPEXP_FECHAEMISION")
    private LocalDate repexpFechaemision;
    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPEXP_VERSION")
    private Long repexpVersion;
    @JoinColumn(name = "PAC_ID", referencedColumnName = "PAC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliPaciente pacId;

    public CliReporteexpediente() {
    }

    public CliReporteexpediente(Long repexpId) {
        this.repexpId = repexpId;
    }

    public CliReporteexpediente(Long repexpId, Long repexpVersion) {
        this.repexpId = repexpId;
        this.repexpVersion = repexpVersion;
    }

    public Long getRepexpId() {
        return repexpId;
    }

    public void setRepexpId(Long repexpId) {
        this.repexpId = repexpId;
    }

    public LocalDate getRepexpFechaemision() {
        return repexpFechaemision;
    }

    public void setRepexpFechaemision(LocalDate repexpFechaemision) {
        this.repexpFechaemision = repexpFechaemision;
    }

    public Long getRepexpVersion() {
        return repexpVersion;
    }

    public void setRepexpVersion(Long repexpVersion) {
        this.repexpVersion = repexpVersion;
    }

    public CliPaciente getPacId() {
        return pacId;
    }

    public void setPacId(CliPaciente pacId) {
        this.pacId = pacId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repexpId != null ? repexpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliReporteexpediente)) {
            return false;
        }
        CliReporteexpediente other = (CliReporteexpediente) object;
        if ((this.repexpId == null && other.repexpId != null) || (this.repexpId != null && !this.repexpId.equals(other.repexpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliReporteexpediente[ repexpId=" + repexpId + " ]";
    }
    
}
