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
@Table(name = "CLI_REPORTEAGENDA",schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliReporteagenda.findAll", query = "SELECT c FROM CliReporteagenda c"),
    @NamedQuery(name = "CliReporteagenda.findByRepageId", query = "SELECT c FROM CliReporteagenda c WHERE c.repageId = :repageId"),
    @NamedQuery(name = "CliReporteagenda.findByRepageFechainicio", query = "SELECT c FROM CliReporteagenda c WHERE c.repageFechainicio = :repageFechainicio"),
    @NamedQuery(name = "CliReporteagenda.findByRepageFechafinal", query = "SELECT c FROM CliReporteagenda c WHERE c.repageFechafinal = :repageFechafinal"),
    @NamedQuery(name = "CliReporteagenda.findByRepageFechaemision", query = "SELECT c FROM CliReporteagenda c WHERE c.repageFechaemision = :repageFechaemision"),
    @NamedQuery(name = "CliReporteagenda.findByRepageVersion", query = "SELECT c FROM CliReporteagenda c WHERE c.repageVersion = :repageVersion")})
public class CliReporteagenda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_REPORTEAGENDA_REPAGE_ID_GENERATOR", sequenceName = "CLI_REPORTEAGENDA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_REPORTEAGENDA_REPAGE_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPAGE_ID")
    private Long repageId;
    @Column(name = "REPAGE_FECHAINICIO")
    private LocalDate repageFechainicio;
    @Column(name = "REPAGE_FECHAFINAL")
    private LocalDate repageFechafinal;
    @Column(name = "REPAGE_FECHAEMISION")
    private LocalDate repageFechaemision;
    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "REPAGE_VERSION")
    private Long repageVersion;
    @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliMedico medId;

    public CliReporteagenda() {
    }

    public CliReporteagenda(Long repageId) {
        this.repageId = repageId;
    }

    public CliReporteagenda(Long repageId, Long repageVersion) {
        this.repageId = repageId;
        this.repageVersion = repageVersion;
    }

    public Long getRepageId() {
        return repageId;
    }

    public void setRepageId(Long repageId) {
        this.repageId = repageId;
    }

    public LocalDate getRepageFechainicio() {
        return repageFechainicio;
    }

    public void setRepageFechainicio(LocalDate repageFechainicio) {
        this.repageFechainicio = repageFechainicio;
    }

    public LocalDate getRepageFechafinal() {
        return repageFechafinal;
    }

    public void setRepageFechafinal(LocalDate repageFechafinal) {
        this.repageFechafinal = repageFechafinal;
    }

    public LocalDate getRepageFechaemision() {
        return repageFechaemision;
    }

    public void setRepageFechaemision(LocalDate repageFechaemision) {
        this.repageFechaemision = repageFechaemision;
    }

    public Long getRepageVersion() {
        return repageVersion;
    }

    public void setRepageVersion(Long repageVersion) {
        this.repageVersion = repageVersion;
    }

    public CliMedico getMedId() {
        return medId;
    }

    public void setMedId(CliMedico medId) {
        this.medId = medId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repageId != null ? repageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliReporteagenda)) {
            return false;
        }
        CliReporteagenda other = (CliReporteagenda) object;
        if ((this.repageId == null && other.repageId != null) || (this.repageId != null && !this.repageId.equals(other.repageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliReporteagenda[ repageId=" + repageId + " ]";
    }
    
}
