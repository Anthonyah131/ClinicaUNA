/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

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
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_EXPEDIENTE")
@NamedQueries({
    @NamedQuery(name = "CliExpediente.findAll", query = "SELECT c FROM CliExpediente c"),
    @NamedQuery(name = "CliExpediente.findByExpId", query = "SELECT c FROM CliExpediente c WHERE c.expId = :expId"),
    @NamedQuery(name = "CliExpediente.findByExpHospitalizaciones", query = "SELECT c FROM CliExpediente c WHERE c.expHospitalizaciones = :expHospitalizaciones"),
    @NamedQuery(name = "CliExpediente.findByExpOperaciones", query = "SELECT c FROM CliExpediente c WHERE c.expOperaciones = :expOperaciones"),
    @NamedQuery(name = "CliExpediente.findByExpAlergias", query = "SELECT c FROM CliExpediente c WHERE c.expAlergias = :expAlergias"),
    @NamedQuery(name = "CliExpediente.findByExpPatologicos", query = "SELECT c FROM CliExpediente c WHERE c.expPatologicos = :expPatologicos"),
    @NamedQuery(name = "CliExpediente.findByExpTiposalergias", query = "SELECT c FROM CliExpediente c WHERE c.expTiposalergias = :expTiposalergias"),
    @NamedQuery(name = "CliExpediente.findByExpTratamientos", query = "SELECT c FROM CliExpediente c WHERE c.expTratamientos = :expTratamientos"),
    @NamedQuery(name = "CliExpediente.findByExpVersion", query = "SELECT c FROM CliExpediente c WHERE c.expVersion = :expVersion")})
public class CliExpediente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXP_ID")
    private Long expId;
    @Column(name = "EXP_HOSPITALIZACIONES")
    private Long expHospitalizaciones;
    @Column(name = "EXP_OPERACIONES")
    private Long expOperaciones;
    @Column(name = "EXP_ALERGIAS")
    private Long expAlergias;
    @Size(max = 500)
    @Column(name = "EXP_PATOLOGICOS")
    private String expPatologicos;
    @Size(max = 500)
    @Column(name = "EXP_TIPOSALERGIAS")
    private String expTiposalergias;
    @Size(max = 500)
    @Column(name = "EXP_TRATAMIENTOS")
    private String expTratamientos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXP_VERSION")
    private Long expVersion;
    @JoinColumn(name = "PAC_ID", referencedColumnName = "PAC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliPaciente pacId;
    @OneToMany(mappedBy = "expId", fetch = FetchType.LAZY)
    private List<CliExamen> cliExamenList;
    @OneToMany(mappedBy = "expId", fetch = FetchType.LAZY)
    private List<CliAtencion> cliAtencionList;
    @OneToMany(mappedBy = "expId", fetch = FetchType.LAZY)
    private List<CliAntecedente> cliAntecedenteList;

    public CliExpediente() {
    }

    public CliExpediente(Long expId) {
        this.expId = expId;
    }

    public CliExpediente(Long expId, Long expVersion) {
        this.expId = expId;
        this.expVersion = expVersion;
    }

    public Long getExpId() {
        return expId;
    }

    public void setExpId(Long expId) {
        this.expId = expId;
    }

    public Long getExpHospitalizaciones() {
        return expHospitalizaciones;
    }

    public void setExpHospitalizaciones(Long expHospitalizaciones) {
        this.expHospitalizaciones = expHospitalizaciones;
    }

    public Long getExpOperaciones() {
        return expOperaciones;
    }

    public void setExpOperaciones(Long expOperaciones) {
        this.expOperaciones = expOperaciones;
    }

    public Long getExpAlergias() {
        return expAlergias;
    }

    public void setExpAlergias(Long expAlergias) {
        this.expAlergias = expAlergias;
    }

    public String getExpPatologicos() {
        return expPatologicos;
    }

    public void setExpPatologicos(String expPatologicos) {
        this.expPatologicos = expPatologicos;
    }

    public String getExpTiposalergias() {
        return expTiposalergias;
    }

    public void setExpTiposalergias(String expTiposalergias) {
        this.expTiposalergias = expTiposalergias;
    }

    public String getExpTratamientos() {
        return expTratamientos;
    }

    public void setExpTratamientos(String expTratamientos) {
        this.expTratamientos = expTratamientos;
    }

    public Long getExpVersion() {
        return expVersion;
    }

    public void setExpVersion(Long expVersion) {
        this.expVersion = expVersion;
    }

    public CliPaciente getPacId() {
        return pacId;
    }

    public void setPacId(CliPaciente pacId) {
        this.pacId = pacId;
    }

    public List<CliExamen> getCliExamenList() {
        return cliExamenList;
    }

    public void setCliExamenList(List<CliExamen> cliExamenList) {
        this.cliExamenList = cliExamenList;
    }

    public List<CliAtencion> getCliAtencionList() {
        return cliAtencionList;
    }

    public void setCliAtencionList(List<CliAtencion> cliAtencionList) {
        this.cliAtencionList = cliAtencionList;
    }

    public List<CliAntecedente> getCliAntecedenteList() {
        return cliAntecedenteList;
    }

    public void setCliAntecedenteList(List<CliAntecedente> cliAntecedenteList) {
        this.cliAntecedenteList = cliAntecedenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (expId != null ? expId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliExpediente)) {
            return false;
        }
        CliExpediente other = (CliExpediente) object;
        if ((this.expId == null && other.expId != null) || (this.expId != null && !this.expId.equals(other.expId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliExpediente[ expId=" + expId + " ]";
    }
    
}
