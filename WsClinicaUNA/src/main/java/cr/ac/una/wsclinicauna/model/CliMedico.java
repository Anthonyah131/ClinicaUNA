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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_MEDICO")
@NamedQueries({
    @NamedQuery(name = "CliMedico.findAll", query = "SELECT c FROM CliMedico c"),
    @NamedQuery(name = "CliMedico.findByMedId", query = "SELECT c FROM CliMedico c WHERE c.medId = :medId"),
    @NamedQuery(name = "CliMedico.findByMedCodigo", query = "SELECT c FROM CliMedico c WHERE c.medCodigo = :medCodigo"),
    @NamedQuery(name = "CliMedico.findByMedFolio", query = "SELECT c FROM CliMedico c WHERE c.medFolio = :medFolio"),
    @NamedQuery(name = "CliMedico.findByMedCarne", query = "SELECT c FROM CliMedico c WHERE c.medCarne = :medCarne"),
    @NamedQuery(name = "CliMedico.findByMedEstado", query = "SELECT c FROM CliMedico c WHERE c.medEstado = :medEstado"),
    @NamedQuery(name = "CliMedico.findByMedFini", query = "SELECT c FROM CliMedico c WHERE c.medFini = :medFini"),
    @NamedQuery(name = "CliMedico.findByMedFfin", query = "SELECT c FROM CliMedico c WHERE c.medFfin = :medFfin"),
    @NamedQuery(name = "CliMedico.findByMedEspaciosxhora", query = "SELECT c FROM CliMedico c WHERE c.medEspaciosxhora = :medEspaciosxhora"),
    @NamedQuery(name = "CliMedico.findByMedVersion", query = "SELECT c FROM CliMedico c WHERE c.medVersion = :medVersion")})
public class CliMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MED_ID")
    private Long medId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "MED_CODIGO")
    private String medCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MED_FOLIO")
    private String medFolio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "MED_CARNE")
    private String medCarne;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "MED_ESTADO")
    private String medEstado;
    @Column(name = "MED_FINI")
    private LocalDate medFini;
    @Column(name = "MED_FFIN")
    private LocalDate medFfin;
    @Column(name = "MED_ESPACIOSXHORA")
    private Long medEspaciosxhora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MED_VERSION")
    private Long medVersion;
    @OneToMany(mappedBy = "medId", fetch = FetchType.LAZY)
    private List<CliAgenda> cliAgendaList;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliUsuario usuId;
    @OneToMany(mappedBy = "medId", fetch = FetchType.LAZY)
    private List<CliReporteagenda> cliReporteagendaList;

    public CliMedico() {
    }

    public CliMedico(Long medId) {
        this.medId = medId;
    }

    public CliMedico(Long medId, String medCodigo, String medFolio, String medCarne, String medEstado, Long medVersion) {
        this.medId = medId;
        this.medCodigo = medCodigo;
        this.medFolio = medFolio;
        this.medCarne = medCarne;
        this.medEstado = medEstado;
        this.medVersion = medVersion;
    }

    public Long getMedId() {
        return medId;
    }

    public void setMedId(Long medId) {
        this.medId = medId;
    }

    public String getMedCodigo() {
        return medCodigo;
    }

    public void setMedCodigo(String medCodigo) {
        this.medCodigo = medCodigo;
    }

    public String getMedFolio() {
        return medFolio;
    }

    public void setMedFolio(String medFolio) {
        this.medFolio = medFolio;
    }

    public String getMedCarne() {
        return medCarne;
    }

    public void setMedCarne(String medCarne) {
        this.medCarne = medCarne;
    }

    public String getMedEstado() {
        return medEstado;
    }

    public void setMedEstado(String medEstado) {
        this.medEstado = medEstado;
    }

    public LocalDate getMedFini() {
        return medFini;
    }

    public void setMedFini(LocalDate medFini) {
        this.medFini = medFini;
    }

    public LocalDate getMedFfin() {
        return medFfin;
    }

    public void setMedFfin(LocalDate medFfin) {
        this.medFfin = medFfin;
    }

    public Long getMedEspaciosxhora() {
        return medEspaciosxhora;
    }

    public void setMedEspaciosxhora(Long medEspaciosxhora) {
        this.medEspaciosxhora = medEspaciosxhora;
    }

    public Long getMedVersion() {
        return medVersion;
    }

    public void setMedVersion(Long medVersion) {
        this.medVersion = medVersion;
    }

    public List<CliAgenda> getCliAgendaList() {
        return cliAgendaList;
    }

    public void setCliAgendaList(List<CliAgenda> cliAgendaList) {
        this.cliAgendaList = cliAgendaList;
    }

    public CliUsuario getUsuId() {
        return usuId;
    }

    public void setUsuId(CliUsuario usuId) {
        this.usuId = usuId;
    }

    public List<CliReporteagenda> getCliReporteagendaList() {
        return cliReporteagendaList;
    }

    public void setCliReporteagendaList(List<CliReporteagenda> cliReporteagendaList) {
        this.cliReporteagendaList = cliReporteagendaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medId != null ? medId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliMedico)) {
            return false;
        }
        CliMedico other = (CliMedico) object;
        if ((this.medId == null && other.medId != null) || (this.medId != null && !this.medId.equals(other.medId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliMedico[ medId=" + medId + " ]";
    }
    
}
