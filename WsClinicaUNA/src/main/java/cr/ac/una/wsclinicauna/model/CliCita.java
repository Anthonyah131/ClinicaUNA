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
@Table(name = "CLI_CITA", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliCita.findAll", query = "SELECT c FROM CliCita c"),
    @NamedQuery(name = "CliCita.findByCitId", query = "SELECT c FROM CliCita c WHERE c.citId = :citId"),
    @NamedQuery(name = "CliCita.findByCitUsuarioregistra", query = "SELECT c FROM CliCita c WHERE c.citUsuarioregistra = :citUsuarioregistra"),
    @NamedQuery(name = "CliCita.findByCitMotivo", query = "SELECT c FROM CliCita c WHERE c.citMotivo = :citMotivo"),
    @NamedQuery(name = "CliCita.findByCitFechahora", query = "SELECT c FROM CliCita c WHERE c.citFechahora = :citFechahora"),
    @NamedQuery(name = "CliCita.findByCitVersion", query = "SELECT c FROM CliCita c WHERE c.citVersion = :citVersion")})
public class CliCita implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_CITA_CIT_ID_GENERATOR", sequenceName = "CLI_CITA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_CITA_CIT_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIT_ID")
    private Long citId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CIT_USUARIOREGISTRA")
    private String citUsuarioregistra;
    @Size(max = 80)
    @Column(name = "CIT_MOTIVO")
    private String citMotivo;
    @Column(name = "CIT_FECHAHORA")
    private LocalDate citFechahora;
    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIT_VERSION")
    private Long citVersion;
    @JoinColumn(name = "AGE_ID", referencedColumnName = "AGE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliAgenda ageId;
    @JoinColumn(name = "PAC_ID", referencedColumnName = "PAC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliPaciente pacId;

    public CliCita() {
    }

    public CliCita(Long citId) {
        this.citId = citId;
    }

    public CliCita(Long citId, String citUsuarioregistra, Long citVersion) {
        this.citId = citId;
        this.citUsuarioregistra = citUsuarioregistra;
        this.citVersion = citVersion;
    }

    public Long getCitId() {
        return citId;
    }

    public void setCitId(Long citId) {
        this.citId = citId;
    }

    public String getCitUsuarioregistra() {
        return citUsuarioregistra;
    }

    public void setCitUsuarioregistra(String citUsuarioregistra) {
        this.citUsuarioregistra = citUsuarioregistra;
    }

    public String getCitMotivo() {
        return citMotivo;
    }

    public void setCitMotivo(String citMotivo) {
        this.citMotivo = citMotivo;
    }

    public LocalDate getCitFechahora() {
        return citFechahora;
    }

    public void setCitFechahora(LocalDate citFechahora) {
        this.citFechahora = citFechahora;
    }

    public Long getCitVersion() {
        return citVersion;
    }

    public void setCitVersion(Long citVersion) {
        this.citVersion = citVersion;
    }

    public CliAgenda getAgeId() {
        return ageId;
    }

    public void setAgeId(CliAgenda ageId) {
        this.ageId = ageId;
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
        hash += (citId != null ? citId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliCita)) {
            return false;
        }
        CliCita other = (CliCita) object;
        if ((this.citId == null && other.citId != null) || (this.citId != null && !this.citId.equals(other.citId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliCita[ citId=" + citId + " ]";
    }
    
}
