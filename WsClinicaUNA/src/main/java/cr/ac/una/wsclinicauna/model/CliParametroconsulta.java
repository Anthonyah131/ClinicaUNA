package cr.ac.una.wsclinicauna.model;

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
import java.io.Serializable;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_PARAMETROCONSULTA", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliParametroconsulta.findAll", query = "SELECT c FROM CliParametroconsulta c"),
    @NamedQuery(name = "CliParametroconsulta.findByParcId", query = "SELECT c FROM CliParametroconsulta c WHERE c.parcId = :parcId"),
    @NamedQuery(name = "CliParametroconsulta.findByParcParametro", query = "SELECT c FROM CliParametroconsulta c WHERE c.parcParametro = :parcParametro"),
    @NamedQuery(name = "CliParametroconsulta.findByParcValor", query = "SELECT c FROM CliParametroconsulta c WHERE c.parcValor = :parcValor"),
    @NamedQuery(name = "CliParametroconsulta.findByParcVersion", query = "SELECT c FROM CliParametroconsulta c WHERE c.parcVersion = :parcVersion")})
public class CliParametroconsulta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_PARAMETROCONSULTA_PARC_ID_GENERATOR", sequenceName = "CLI_PARAMETROCONSULTA_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_PARAMETROCONSULTA_PARC_ID_GENERATOR")
    @Column(name = "PARC_ID")
    private Long parcId;
    @Column(name = "PARC_PARAMETRO")
    private String parcParametro;
    @Column(name = "PARC_VALOR")
    private String parcValor;
    @Version
    @Column(name = "PARC_VERSION")
    private Long parcVersion;
    @JoinColumn(name = "REP_ID", referencedColumnName = "REP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliReporte repId;

    public CliParametroconsulta() {
    }

    public CliParametroconsulta(Long parcId) {
        this.parcId = parcId;
    }

    public CliParametroconsulta(CliParametroconsultaDto cliParametroconsultaDto) {
        this.parcId = cliParametroconsultaDto.getParcId();
        actualizar(cliParametroconsultaDto);
    }

    public void actualizar(CliParametroconsultaDto cliParametroconsultaDto) {
        this.parcParametro = cliParametroconsultaDto.getParcParametro();
        this.parcValor = cliParametroconsultaDto.getParcValor();
        this.parcVersion = cliParametroconsultaDto.getParcVersion();
    }

    public Long getParcId() {
        return parcId;
    }

    public void setParcId(Long parcId) {
        this.parcId = parcId;
    }

    public String getParcParametro() {
        return parcParametro;
    }

    public void setParcParametro(String parcParametro) {
        this.parcParametro = parcParametro;
    }

    public String getParcValor() {
        return parcValor;
    }

    public void setParcValor(String parcValor) {
        this.parcValor = parcValor;
    }

    public Long getParcVersion() {
        return parcVersion;
    }

    public void setParcVersion(Long parcVersion) {
        this.parcVersion = parcVersion;
    }

    public CliReporte getRepId() {
        return repId;
    }

    public void setRepId(CliReporte repId) {
        this.repId = repId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parcId != null ? parcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliParametroconsulta)) {
            return false;
        }
        CliParametroconsulta other = (CliParametroconsulta) object;
        if ((this.parcId == null && other.parcId != null) || (this.parcId != null && !this.parcId.equals(other.parcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.CliParametroconsulta[ parcId=" + parcId + " ]";
    }

}
