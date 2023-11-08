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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_REPORTE", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliReporte.findAll", query = "SELECT c FROM CliReporte c"),
    @NamedQuery(name = "CliReporte.findByRepId", query = "SELECT c FROM CliReporte c WHERE c.repId = :repId"),
    @NamedQuery(name = "CliReporte.findByRepNombre", query = "SELECT c FROM CliReporte c WHERE c.repNombre = :repNombre"),
    @NamedQuery(name = "CliReporte.findByRepDescripcion", query = "SELECT c FROM CliReporte c WHERE c.repDescripcion = :repDescripcion"),
    @NamedQuery(name = "CliReporte.findByRepConsulta", query = "SELECT c FROM CliReporte c WHERE c.repConsulta = :repConsulta"),
    @NamedQuery(name = "CliReporte.findByRepTitulo", query = "SELECT c FROM CliReporte c WHERE c.repTitulo = :repTitulo"),
    @NamedQuery(name = "CliReporte.findByRepPeriodicidad", query = "SELECT c FROM CliReporte c WHERE c.repPeriodicidad = :repPeriodicidad"),
    @NamedQuery(name = "CliReporte.findByRepFfin", query = "SELECT c FROM CliReporte c WHERE c.repFfin = :repFfin"),
    @NamedQuery(name = "CliReporte.findByRepVersion", query = "SELECT c FROM CliReporte c WHERE c.repVersion = :repVersion")})
public class CliReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_REPORTE_REP_ID_GENERATOR", sequenceName = "CLI_REPORTE_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_REPORTE_REP_ID_GENERATOR")
    @Column(name = "REP_ID")
    private Long repId;
    @Column(name = "REP_NOMBRE")
    private String repNombre;
    @Column(name = "REP_DESCRIPCION")
    private String repDescripcion;
    @Column(name = "REP_CONSULTA")
    private String repConsulta;
    @Column(name = "REP_TITULO")
    private String repTitulo;
    @Column(name = "REP_PERIODICIDAD")
    private String repPeriodicidad;
    @Column(name = "REP_FFIN")
    private LocalDate repFfin;
    @Basic(optional = false)
    @Column(name = "REP_VERSION")
    private Long repVersion;
    @OneToMany(mappedBy = "repId", fetch = FetchType.LAZY)
    private List<CliParametroconsulta> cliParametroconsultaList;
    @OneToMany(mappedBy = "repId", fetch = FetchType.LAZY)
    private List<CliCorreodestino> cliCorreodestinoList;

    public CliReporte() {
    }

    public CliReporte(Long repId) {
        this.repId = repId;
    }

    public CliReporte(CliReporteDto cliReporteDto) {
        this.repId = cliReporteDto.getRepId();
        actualizar(cliReporteDto);
    }

    public void actualizar(CliReporteDto cliReporteDto) {
        this.repNombre = cliReporteDto.getRepNombre();
        this.repDescripcion = cliReporteDto.getRepDescripcion();
        this.repConsulta = cliReporteDto.getRepConsulta();
        this.repTitulo = cliReporteDto.getRepTitulo();
        this.repPeriodicidad = cliReporteDto.getRepPeriodicidad();
        this.repFfin = cliReporteDto.getRepFfin();
        this.repVersion = cliReporteDto.getRepVersion();
    }

    public Long getRepId() {
        return repId;
    }

    public void setRepId(Long repId) {
        this.repId = repId;
    }

    public String getRepNombre() {
        return repNombre;
    }

    public void setRepNombre(String repNombre) {
        this.repNombre = repNombre;
    }

    public String getRepDescripcion() {
        return repDescripcion;
    }

    public void setRepDescripcion(String repDescripcion) {
        this.repDescripcion = repDescripcion;
    }

    public String getRepConsulta() {
        return repConsulta;
    }

    public void setRepConsulta(String repConsulta) {
        this.repConsulta = repConsulta;
    }

    public String getRepTitulo() {
        return repTitulo;
    }

    public void setRepTitulo(String repTitulo) {
        this.repTitulo = repTitulo;
    }

    public String getRepPeriodicidad() {
        return repPeriodicidad;
    }

    public void setRepPeriodicidad(String repPeriodicidad) {
        this.repPeriodicidad = repPeriodicidad;
    }

    public LocalDate getRepFfin() {
        return repFfin;
    }

    public void setRepFfin(LocalDate repFfin) {
        this.repFfin = repFfin;
    }

    public Long getRepVersion() {
        return repVersion;
    }

    public void setRepVersion(Long repVersion) {
        this.repVersion = repVersion;
    }

    public List<CliParametroconsulta> getCliParametroconsultaList() {
        return cliParametroconsultaList;
    }

    public void setCliParametroconsultaList(List<CliParametroconsulta> cliParametroconsultaList) {
        this.cliParametroconsultaList = cliParametroconsultaList;
    }

    public List<CliCorreodestino> getCliCorreodestinoList() {
        return cliCorreodestinoList;
    }

    public void setCliCorreodestinoList(List<CliCorreodestino> cliCorreodestinoList) {
        this.cliCorreodestinoList = cliCorreodestinoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (repId != null ? repId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliReporte)) {
            return false;
        }
        CliReporte other = (CliReporte) object;
        if ((this.repId == null && other.repId != null) || (this.repId != null && !this.repId.equals(other.repId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.CliReporte[ repId=" + repId + " ]";
    }

}
