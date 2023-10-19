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


/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_ANTECEDENTE", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliAntecedente.findAll", query = "SELECT c FROM CliAntecedente c"),
    @NamedQuery(name = "CliAntecedente.findByAntId", query = "SELECT c FROM CliAntecedente c WHERE c.antId = :antId"),
    @NamedQuery(name = "CliAntecedente.findByAntDescripcion", query = "SELECT c FROM CliAntecedente c WHERE c.antDescripcion = :antDescripcion"),
    @NamedQuery(name = "CliAntecedente.findByAntTipo", query = "SELECT c FROM CliAntecedente c WHERE c.antTipo = :antTipo"),
    @NamedQuery(name = "CliAntecedente.findByAntParentesco", query = "SELECT c FROM CliAntecedente c WHERE c.antParentesco = :antParentesco"),
    @NamedQuery(name = "CliAntecedente.findByAntVersion", query = "SELECT c FROM CliAntecedente c WHERE c.antVersion = :antVersion")})
public class CliAntecedente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_ANTECEDENTE_ANT_ID_GENERATOR", sequenceName = "CLI_ANTECEDENTE_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_ANTECEDENTE_ANT_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANT_ID")
    private Long antId;
    @Size(max = 100)
    @Column(name = "ANT_DESCRIPCION")
    private String antDescripcion;
    @Size(max = 10)
    @Column(name = "ANT_TIPO")
    private String antTipo;
    @Size(max = 20)
    @Column(name = "ANT_PARENTESCO")
    private String antParentesco;
    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANT_VERSION")
    private Long antVersion;
    @JoinColumn(name = "EXP_ID", referencedColumnName = "EXP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliExpediente expId;

    public CliAntecedente() {
    }

    public CliAntecedente(Long antId) {
        this.antId = antId;
    }

    public CliAntecedente(Long antId, Long antVersion) {
        this.antId = antId;
        this.antVersion = antVersion;
    }

    public Long getAntId() {
        return antId;
    }

    public void setAntId(Long antId) {
        this.antId = antId;
    }

    public String getAntDescripcion() {
        return antDescripcion;
    }

    public void setAntDescripcion(String antDescripcion) {
        this.antDescripcion = antDescripcion;
    }

    public String getAntTipo() {
        return antTipo;
    }

    public void setAntTipo(String antTipo) {
        this.antTipo = antTipo;
    }

    public String getAntParentesco() {
        return antParentesco;
    }

    public void setAntParentesco(String antParentesco) {
        this.antParentesco = antParentesco;
    }

    public Long getAntVersion() {
        return antVersion;
    }

    public void setAntVersion(Long antVersion) {
        this.antVersion = antVersion;
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
        hash += (antId != null ? antId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliAntecedente)) {
            return false;
        }
        CliAntecedente other = (CliAntecedente) object;
        if ((this.antId == null && other.antId != null) || (this.antId != null && !this.antId.equals(other.antId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliAntecedente[ antId=" + antId + " ]";
    }
    
}
