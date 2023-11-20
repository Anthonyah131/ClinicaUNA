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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.LocalDate;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_PACIENTE",schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliPaciente.findAll", query = "SELECT c FROM CliPaciente c"),
    @NamedQuery(name = "CliPaciente.findByPacId", query = "SELECT c FROM CliPaciente c WHERE c.pacId = :pacId"),
    @NamedQuery(name = "CliPaciente.findByPacNombre", query = "SELECT c FROM CliPaciente c WHERE c.pacNombre = :pacNombre"),
    @NamedQuery(name = "CliPaciente.findByPacPapellido", query = "SELECT c FROM CliPaciente c WHERE c.pacPapellido = :pacPapellido"),
    @NamedQuery(name = "CliPaciente.findByPacSapellido", query = "SELECT c FROM CliPaciente c WHERE c.pacSapellido = :pacSapellido"),
    @NamedQuery(name = "CliPaciente.findByPacCedula", query = "SELECT c FROM CliPaciente c WHERE c.pacCedula = :pacCedula"),
    @NamedQuery(name = "CliPaciente.findByPacTelefono", query = "SELECT c FROM CliPaciente c WHERE c.pacTelefono = :pacTelefono"),
    @NamedQuery(name = "CliPaciente.findByPacCorreo", query = "SELECT c FROM CliPaciente c WHERE c.pacCorreo = :pacCorreo"),
    @NamedQuery(name = "CliPaciente.findByPacGenero", query = "SELECT c FROM CliPaciente c WHERE c.pacGenero = :pacGenero"),
    @NamedQuery(name = "CliPaciente.findByPacFnacimiento", query = "SELECT c FROM CliPaciente c WHERE c.pacFnacimiento = :pacFnacimiento"),
    @NamedQuery(name = "CliPaciente.findByPacVersion", query = "SELECT c FROM CliPaciente c WHERE c.pacVersion = :pacVersion")})
public class CliPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_PACIENTE_PAC_ID_GENERATOR", sequenceName = "CLI_PACIENTE_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_PACIENTE_PAC_ID_GENERATOR")
    @Basic(optional = false)
    @Column(name = "PAC_ID")
    private Long pacId;
    @Basic(optional = false)
    @Column(name = "PAC_NOMBRE")
    private String pacNombre;
    @Basic(optional = false)
    @Column(name = "PAC_PAPELLIDO")
    private String pacPapellido;
    @Column(name = "PAC_SAPELLIDO")
    private String pacSapellido;
    @Basic(optional = false)
    @Column(name = "PAC_CEDULA")
    private String pacCedula;
    @Column(name = "PAC_TELEFONO")
    private Long pacTelefono;
    @Basic(optional = false)
    @Column(name = "PAC_CORREO")
    private String pacCorreo;
    @Basic(optional = false)
    @Column(name = "PAC_GENERO")
    private String pacGenero;
    @Basic(optional = false)
    @Column(name = "PAC_FNACIMIENTO")
    private LocalDate pacFnacimiento;
    @Version
    @Basic(optional = false)
    @Column(name = "PAC_VERSION")
    private Long pacVersion;
    @OneToMany(mappedBy = "pacId", fetch = FetchType.LAZY)
    private List<CliExpediente> cliExpedienteList;
    @OneToMany(mappedBy = "pacId", fetch = FetchType.LAZY)
    private List<CliCita> cliCitaList;

    public CliPaciente() {
    }

    public CliPaciente(Long pacId) {
        this.pacId = pacId;
    }

    public CliPaciente(CliPacienteDto cliPacienteDto) {
        this.pacId = cliPacienteDto.getPacId();
        actualizar(cliPacienteDto);
    }

    public void actualizar(CliPacienteDto cliPacienteDto) {
        this.pacNombre = cliPacienteDto.getPacNombre();
        this.pacPapellido = cliPacienteDto.getPacPapellido();
        this.pacSapellido = cliPacienteDto.getPacSapellido();
        this.pacCedula = cliPacienteDto.getPacCedula();
        this.pacTelefono = cliPacienteDto.getPacTelefono();
        this.pacCorreo = cliPacienteDto.getPacCorreo();
        this.pacGenero = cliPacienteDto.getPacGenero();
        this.pacFnacimiento = cliPacienteDto.getPacFnacimiento();
        this.pacVersion = cliPacienteDto.getPacVersion();
    }

    public Long getPacId() {
        return pacId;
    }

    public void setPacId(Long pacId) {
        this.pacId = pacId;
    }

    public String getPacNombre() {
        return pacNombre;
    }

    public void setPacNombre(String pacNombre) {
        this.pacNombre = pacNombre;
    }

    public String getPacPapellido() {
        return pacPapellido;
    }

    public void setPacPapellido(String pacPapellido) {
        this.pacPapellido = pacPapellido;
    }

    public String getPacSapellido() {
        return pacSapellido;
    }

    public void setPacSapellido(String pacSapellido) {
        this.pacSapellido = pacSapellido;
    }

    public String getPacCedula() {
        return pacCedula;
    }

    public void setPacCedula(String pacCedula) {
        this.pacCedula = pacCedula;
    }

    public Long getPacTelefono() {
        return pacTelefono;
    }

    public void setPacTelefono(Long pacTelefono) {
        this.pacTelefono = pacTelefono;
    }

    public String getPacCorreo() {
        return pacCorreo;
    }

    public void setPacCorreo(String pacCorreo) {
        this.pacCorreo = pacCorreo;
    }

    public String getPacGenero() {
        return pacGenero;
    }

    public void setPacGenero(String pacGenero) {
        this.pacGenero = pacGenero;
    }

    public LocalDate getPacFnacimiento() {
        return pacFnacimiento;
    }

    public void setPacFnacimiento(LocalDate pacFnacimiento) {
        this.pacFnacimiento = pacFnacimiento;
    }

    public Long getPacVersion() {
        return pacVersion;
    }

    public void setPacVersion(Long pacVersion) {
        this.pacVersion = pacVersion;
    }

    public List<CliExpediente> getCliExpedienteList() {
        return cliExpedienteList;
    }

    public void setCliExpedienteList(List<CliExpediente> cliExpedienteList) {
        this.cliExpedienteList = cliExpedienteList;
    }


    public List<CliCita> getCliCitaList() {
        return cliCitaList;
    }

    public void setCliCitaList(List<CliCita> cliCitaList) {
        this.cliCitaList = cliCitaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacId != null ? pacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliPaciente)) {
            return false;
        }
        CliPaciente other = (CliPaciente) object;
        if ((this.pacId == null && other.pacId != null) || (this.pacId != null && !this.pacId.equals(other.pacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliPaciente[ pacId=" + pacId + " ]";
    }
    
}
