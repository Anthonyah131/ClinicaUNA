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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_MEDICO",schema = "ClinicaUNA")
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
    @SequenceGenerator(name = "CLI_MEDICO_MED_ID_GENERATOR", sequenceName = "CLI_MEDICO_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_MEDICO_MED_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "MED_ID")
    private Long medId;
    @Basic(optional = false)
    @Column(name = "MED_CODIGO")
    private String medCodigo;
    @Basic(optional = false)
    @Column(name = "MED_FOLIO")
    private String medFolio;
    @Basic(optional = false)
    @Column(name = "MED_CARNE")
    private String medCarne;
    @Basic(optional = false)
    @Column(name = "MED_ESTADO")
    private String medEstado;
    @Column(name = "MED_FINI")
    private LocalDateTime medFini;
    @Column(name = "MED_FFIN")
    private LocalDateTime medFfin;
    @Column(name = "MED_ESPACIOSXHORA")
    private Long medEspaciosxhora;
    @Version
    @Basic(optional = false)
    @Column(name = "MED_VERSION")
    private Long medVersion;
    @OneToMany(mappedBy = "medId", fetch = FetchType.LAZY)
    private List<CliAgenda> cliAgendaList;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliUsuario usuId;

    public CliMedico() {
    }

    public CliMedico(Long medId) {
        this.medId = medId;
    }

    public CliMedico(CliMedicoDto cliMedicoDto) {
        this.medId = cliMedicoDto.getMedId();
        actualizar(cliMedicoDto);
    }

    public void actualizar(CliMedicoDto cliMedicoDto) {
        this.medCodigo = cliMedicoDto.getMedCodigo();
        this.medFolio = cliMedicoDto.getMedFolio();
        this.medCarne = cliMedicoDto.getMedCarne();
        this.medEstado = cliMedicoDto.getMedEstado();
        this.medFini = cliMedicoDto.getMedFini();
        this.medFfin = cliMedicoDto.getMedFfin();
        this.medEspaciosxhora = cliMedicoDto.getMedEspaciosxhora();
        this.medVersion = cliMedicoDto.getMedVersion();
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

    public LocalDateTime getMedFini() {
        return medFini;
    }

    public void setMedFini(LocalDateTime medFini) {
        this.medFini = medFini;
    }

    public LocalDateTime getMedFfin() {
        return medFfin;
    }

    public void setMedFfin(LocalDateTime medFfin) {
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
