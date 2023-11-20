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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_AGENDA", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliAgenda.findAll", query = "SELECT c FROM CliAgenda c"),
    @NamedQuery(name = "CliAgenda.findByAgeId", query = "SELECT c FROM CliAgenda c WHERE c.ageId = :ageId"),
    @NamedQuery(name = "CliAgenda.findByAgeFecha", query = "SELECT c FROM CliAgenda c WHERE c.ageFecha = :ageFecha"),
    @NamedQuery(name = "CliAgenda.findByAgeTiempo", query = "SELECT c FROM CliAgenda c WHERE c.ageTiempo = :ageTiempo"),
    @NamedQuery(name = "CliAgenda.findByAgeEntrada", query = "SELECT c FROM CliAgenda c WHERE c.ageEntrada = :ageEntrada"),
    @NamedQuery(name = "CliAgenda.findByAgeSalida", query = "SELECT c FROM CliAgenda c WHERE c.ageSalida = :ageSalida"),
    @NamedQuery(name = "CliAgenda.findByAgeEspacios", query = "SELECT c FROM CliAgenda c WHERE c.ageEspacios = :ageEspacios"),
    @NamedQuery(name = "CliAgenda.findByAgeVersion", query = "SELECT c FROM CliAgenda c WHERE c.ageVersion = :ageVersion")})
public class CliAgenda implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_AGENDA_AGE_ID_GENERATOR", sequenceName = "CLI_AGENDA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_AGENDA_AGE_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "AGE_ID")
    private Long ageId;
    @Column(name = "AGE_FECHA")
    private LocalDate ageFecha;
    @Column(name = "AGE_TIEMPO")
    private String ageTiempo;
    @Column(name = "AGE_ENTRADA")
    private LocalDateTime ageEntrada;
    @Column(name = "AGE_SALIDA")
    private LocalDateTime ageSalida;
    @Column(name = "AGE_ESPACIOS")
    private Long ageEspacios;
    @Version
    @Basic(optional = false)
    @Column(name = "AGE_VERSION")
    private Long ageVersion;
    @OneToMany(mappedBy = "ageId", fetch = FetchType.LAZY)
    private List<CliCita> cliCitaList;
    @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliMedico medId;

    public CliAgenda() {
    }

    public CliAgenda(Long ageId) {
        this.ageId = ageId;
    }

    public CliAgenda(CliAgendaDto cliAgendaDto) {
        this.ageId = cliAgendaDto.getAgeId();
        actualizar(cliAgendaDto);
    }

    public void actualizar(CliAgendaDto cliAgendaDto) {
        this.ageFecha = cliAgendaDto.getAgeFecha();
        this.ageTiempo = cliAgendaDto.getAgeTiempo();
        this.ageEntrada = cliAgendaDto.getAgeEntrada();
        this.ageSalida = cliAgendaDto.getAgeSalida();
        this.ageEspacios = cliAgendaDto.getAgeEspacios();
        this.ageVersion = cliAgendaDto.getAgeVersion();
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }

    public LocalDate getAgeFecha() {
        return ageFecha;
    }

    public void setAgeFecha(LocalDate ageFecha) {
        this.ageFecha = ageFecha;
    }

    public String getAgeTiempo() {
        return ageTiempo;
    }

    public void setAgeTiempo(String ageTiempo) {
        this.ageTiempo = ageTiempo;
    }
    
    public LocalDateTime getAgeEntrada() {
        return ageEntrada;
    }

    public void setAgeEntrada(LocalDateTime ageEntrada) {
        this.ageEntrada = ageEntrada;
    }
    
    public LocalDateTime getAgeSalida() {
        return ageSalida;
    }

    public void setAgeSalida(LocalDateTime ageSalida) {
        this.ageSalida = ageSalida;
    }

    public Long getAgeEspacios() {
        return ageEspacios;
    }

    public void setAgeEspacios(Long ageEspacios) {
        this.ageEspacios = ageEspacios;
    }

    public Long getAgeVersion() {
        return ageVersion;
    }

    public void setAgeVersion(Long ageVersion) {
        this.ageVersion = ageVersion;
    }

    public List<CliCita> getCliCitaList() {
        return cliCitaList;
    }

    public void setCliCitaList(List<CliCita> cliCitaList) {
        this.cliCitaList = cliCitaList;
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
        hash += (ageId != null ? ageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliAgenda)) {
            return false;
        }
        CliAgenda other = (CliAgenda) object;
        if ((this.ageId == null && other.ageId != null) || (this.ageId != null && !this.ageId.equals(other.ageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliAgenda[ ageId=" + ageId + " ]";
    }
    
}
