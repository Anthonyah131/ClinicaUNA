/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

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
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_EXAMEN", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliExamen.findAll", query = "SELECT c FROM CliExamen c"),
    @NamedQuery(name = "CliExamen.findByExaId", query = "SELECT c FROM CliExamen c WHERE c.exaId = :exaId"),
    @NamedQuery(name = "CliExamen.findByExaNombre", query = "SELECT c FROM CliExamen c WHERE c.exaNombre = :exaNombre"),
    @NamedQuery(name = "CliExamen.findByExaFecha", query = "SELECT c FROM CliExamen c WHERE c.exaFecha = :exaFecha"),
    @NamedQuery(name = "CliExamen.findByExaAnotacionesmed", query = "SELECT c FROM CliExamen c WHERE c.exaAnotacionesmed = :exaAnotacionesmed"),
    @NamedQuery(name = "CliExamen.findByExaVersion", query = "SELECT c FROM CliExamen c WHERE c.exaVersion = :exaVersion")})
public class CliExamen implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_EXAMEN_EXA_ID_GENERATOR", sequenceName = "CLI_EXAMEN_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_EXAMEN_EXA_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "EXA_ID")
    private Long exaId;
    @Basic(optional = false)
    @Column(name = "EXA_NOMBRE")
    private String exaNombre;
    @Column(name = "EXA_FECHA")
    private LocalDate exaFecha;
    @Column(name = "EXA_ANOTACIONESMED")
    private String exaAnotacionesmed;
    @Version
    @Basic(optional = false)
    @Column(name = "EXA_VERSION")
    private Long exaVersion;
    @JoinColumn(name = "ATE_ID", referencedColumnName = "ATE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliAtencion ateId;
    @JoinColumn(name = "EXP_ID", referencedColumnName = "EXP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliExpediente expId;

    public CliExamen() {
    }

    public CliExamen(Long exaId) {
        this.exaId = exaId;
    }

    public CliExamen(CliExamenDto cliExamenDto) {
        this.exaId = exaId;
        actualizar(cliExamenDto);
    }

    public void actualizar(CliExamenDto cliExamenDto) {
        this.exaNombre = cliExamenDto.getExaNombre();
        this.exaFecha = cliExamenDto.getExaFecha();
        this.exaAnotacionesmed = cliExamenDto.getExaAnotacionesmed();
        this.exaVersion = cliExamenDto.getExaVersion();
    }

    public Long getExaId() {
        return exaId;
    }

    public void setExaId(Long exaId) {
        this.exaId = exaId;
    }

    public String getExaNombre() {
        return exaNombre;
    }

    public void setExaNombre(String exaNombre) {
        this.exaNombre = exaNombre;
    }

    public LocalDate getExaFecha() {
        return exaFecha;
    }

    public void setExaFecha(LocalDate exaFecha) {
        this.exaFecha = exaFecha;
    }

    public String getExaAnotacionesmed() {
        return exaAnotacionesmed;
    }

    public void setExaAnotacionesmed(String exaAnotacionesmed) {
        this.exaAnotacionesmed = exaAnotacionesmed;
    }

    public Long getExaVersion() {
        return exaVersion;
    }

    public void setExaVersion(Long exaVersion) {
        this.exaVersion = exaVersion;
    }

    public CliAtencion getAteId() {
        return ateId;
    }

    public void setAteId(CliAtencion ateId) {
        this.ateId = ateId;
    }

    public CliExpediente getExpId() {
        return expId;
    }

    public void setExpId(CliExpediente expId) {
        this.expId = expId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exaId != null ? exaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliExamen)) {
            return false;
        }
        CliExamen other = (CliExamen) object;
        if ((this.exaId == null && other.exaId != null) || (this.exaId != null && !this.exaId.equals(other.exaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliExamen[ exaId=" + exaId + " ]";
    }

}
