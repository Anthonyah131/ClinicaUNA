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
@Table(name = "CLI_CORREODESTINO", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliCorreodestino.findAll", query = "SELECT c FROM CliCorreodestino c"),
    @NamedQuery(name = "CliCorreodestino.findByCdId", query = "SELECT c FROM CliCorreodestino c WHERE c.cdId = :cdId"),
    @NamedQuery(name = "CliCorreodestino.findByCdCorreo", query = "SELECT c FROM CliCorreodestino c WHERE c.cdCorreo = :cdCorreo"),
    @NamedQuery(name = "CliCorreodestino.findByCdVersion", query = "SELECT c FROM CliCorreodestino c WHERE c.cdVersion = :cdVersion")})
public class CliCorreodestino implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_CORREODESTINO_CD_ID_GENERATOR", sequenceName = "CLI_CORREOSDESTINO_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_CORREODESTINO_CD_ID_GENERATOR")
    @Column(name = "CD_ID")
    private Long cdId;
    @Column(name = "CD_CORREO")
    private String cdCorreo;
    @Version
    @Column(name = "CD_VERSION")
    private Long cdVersion;
    @JoinColumn(name = "REP_ID", referencedColumnName = "REP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliReporte repId;

    public CliCorreodestino() {
    }

    public CliCorreodestino(Long cdId) {
        this.cdId = cdId;
    }

    public CliCorreodestino(CliCorreodestinoDto cliCorreodestinoDto) {
        this.cdId = cliCorreodestinoDto.getCdId();
        actualizar(cliCorreodestinoDto);
    }
    
    public void actualizar(CliCorreodestinoDto cliCorreodestinoDto){
       this.cdCorreo = cliCorreodestinoDto.getCdCorreo();
        this.cdVersion = cliCorreodestinoDto.getCdVersion(); 
    }

    public Long getCdId() {
        return cdId;
    }

    public void setCdId(Long cdId) {
        this.cdId = cdId;
    }

    public String getCdCorreo() {
        return cdCorreo;
    }

    public void setCdCorreo(String cdCorreo) {
        this.cdCorreo = cdCorreo;
    }

    public Long getCdVersion() {
        return cdVersion;
    }

    public void setCdVersion(Long cdVersion) {
        this.cdVersion = cdVersion;
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
        hash += (cdId != null ? cdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliCorreodestino)) {
            return false;
        }
        CliCorreodestino other = (CliCorreodestino) object;
        if ((this.cdId == null && other.cdId != null) || (this.cdId != null && !this.cdId.equals(other.cdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.CliCorreodestino[ cdId=" + cdId + " ]";
    }
    
}
