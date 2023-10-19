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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;



/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_ATENCION", schema = "ClinicaUNA")
@NamedQueries({
    @NamedQuery(name = "CliAtencion.findAll", query = "SELECT c FROM CliAtencion c"),
    @NamedQuery(name = "CliAtencion.findByAteId", query = "SELECT c FROM CliAtencion c WHERE c.ateId = :ateId"),
    @NamedQuery(name = "CliAtencion.findByAteFechahora", query = "SELECT c FROM CliAtencion c WHERE c.ateFechahora = :ateFechahora"),
    @NamedQuery(name = "CliAtencion.findByAtePresion", query = "SELECT c FROM CliAtencion c WHERE c.atePresion = :atePresion"),
    @NamedQuery(name = "CliAtencion.findByAteFrecuenciacard", query = "SELECT c FROM CliAtencion c WHERE c.ateFrecuenciacard = :ateFrecuenciacard"),
    @NamedQuery(name = "CliAtencion.findByAtePeso", query = "SELECT c FROM CliAtencion c WHERE c.atePeso = :atePeso"),
    @NamedQuery(name = "CliAtencion.findByAteTalla", query = "SELECT c FROM CliAtencion c WHERE c.ateTalla = :ateTalla"),
    @NamedQuery(name = "CliAtencion.findByAteTemperatura", query = "SELECT c FROM CliAtencion c WHERE c.ateTemperatura = :ateTemperatura"),
    @NamedQuery(name = "CliAtencion.findByAteImc", query = "SELECT c FROM CliAtencion c WHERE c.ateImc = :ateImc"),
    @NamedQuery(name = "CliAtencion.findByAteAnotacionenfe", query = "SELECT c FROM CliAtencion c WHERE c.ateAnotacionenfe = :ateAnotacionenfe"),
    @NamedQuery(name = "CliAtencion.findByAteRazonconsulta", query = "SELECT c FROM CliAtencion c WHERE c.ateRazonconsulta = :ateRazonconsulta"),
    @NamedQuery(name = "CliAtencion.findByAtePlanatencion", query = "SELECT c FROM CliAtencion c WHERE c.atePlanatencion = :atePlanatencion"),
    @NamedQuery(name = "CliAtencion.findByAteObservaciones", query = "SELECT c FROM CliAtencion c WHERE c.ateObservaciones = :ateObservaciones"),
    @NamedQuery(name = "CliAtencion.findByAteTratamiento", query = "SELECT c FROM CliAtencion c WHERE c.ateTratamiento = :ateTratamiento"),
    @NamedQuery(name = "CliAtencion.findByAteVersion", query = "SELECT c FROM CliAtencion c WHERE c.ateVersion = :ateVersion")})
public class CliAtencion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "CLI_ATENCION_ATE_ID_GENERATOR", sequenceName = "CLI_ATENCION_SEQ01", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLI_ATENCION_ATE_ID_GENERATOR")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ATE_ID")
    private Long ateId;
    @Column(name = "ATE_FECHAHORA")
    private LocalDate ateFechahora;
    @Size(max = 5)
    @Column(name = "ATE_PRESION")
    private String atePresion;
    @Size(max = 5)
    @Column(name = "ATE_FRECUENCIACARD")
    private String ateFrecuenciacard;
    @Size(max = 5)
    @Column(name = "ATE_PESO")
    private String atePeso;
    @Size(max = 5)
    @Column(name = "ATE_TALLA")
    private String ateTalla;
    @Size(max = 5)
    @Column(name = "ATE_TEMPERATURA")
    private String ateTemperatura;
    @Size(max = 5)
    @Column(name = "ATE_IMC")
    private String ateImc;
    @Size(max = 80)
    @Column(name = "ATE_ANOTACIONENFE")
    private String ateAnotacionenfe;
    @Size(max = 50)
    @Column(name = "ATE_RAZONCONSULTA")
    private String ateRazonconsulta;
    @Size(max = 1)
    @Column(name = "ATE_PLANATENCION")
    private String atePlanatencion;
    @Size(max = 80)
    @Column(name = "ATE_OBSERVACIONES")
    private String ateObservaciones;
    @Size(max = 50)
    @Column(name = "ATE_TRATAMIENTO")
    private String ateTratamiento;
    @Version
    @Basic(optional = false)
    @NotNull
    @Column(name = "ATE_VERSION")
    private Long ateVersion;
    @OneToMany(mappedBy = "ateId", fetch = FetchType.LAZY)
    private List<CliExamen> cliExamenList;
    @JoinColumn(name = "EXP_ID", referencedColumnName = "EXP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private CliExpediente expId;

    public CliAtencion() {
    }

    public CliAtencion(Long ateId) {
        this.ateId = ateId;
    }

    public CliAtencion(CliAtencionDto cliAtencionDto) {
        this.ateId = ateId;
        actualizar(cliAtencionDto);
    }

    public void actualizar(CliAtencionDto cliAtencionDto) {
        this.ateFechahora = cliAtencionDto.getAteFechahora();
        this.atePresion = cliAtencionDto.getAtePresion();
        this.ateFrecuenciacard = cliAtencionDto.getAteFrecuenciacard();
        this.atePeso = cliAtencionDto.getAtePeso();
        this.ateTalla = cliAtencionDto.getAteTalla();
        this.ateTemperatura = cliAtencionDto.getAteTemperatura();
        this.ateImc = cliAtencionDto.getAteImc();
        this.ateAnotacionenfe = cliAtencionDto.getAteAnotacionenfe();
        this.ateRazonconsulta = cliAtencionDto.getAteRazonconsulta();
        this.atePlanatencion = cliAtencionDto.getAtePlanatencion();
        this.ateObservaciones = cliAtencionDto.getAteObservaciones();
        this.ateTratamiento = cliAtencionDto.getAteTratamiento();
        this.ateVersion = cliAtencionDto.getAteVersion();
    }

    public Long getAteId() {
        return ateId;
    }

    public void setAteId(Long ateId) {
        this.ateId = ateId;
    }

    public LocalDate getAteFechahora() {
        return ateFechahora;
    }

    public void setAteFechahora(LocalDate ateFechahora) {
        this.ateFechahora = ateFechahora;
    }

    public String getAtePresion() {
        return atePresion;
    }

    public void setAtePresion(String atePresion) {
        this.atePresion = atePresion;
    }

    public String getAteFrecuenciacard() {
        return ateFrecuenciacard;
    }

    public void setAteFrecuenciacard(String ateFrecuenciacard) {
        this.ateFrecuenciacard = ateFrecuenciacard;
    }

    public String getAtePeso() {
        return atePeso;
    }

    public void setAtePeso(String atePeso) {
        this.atePeso = atePeso;
    }

    public String getAteTalla() {
        return ateTalla;
    }

    public void setAteTalla(String ateTalla) {
        this.ateTalla = ateTalla;
    }

    public String getAteTemperatura() {
        return ateTemperatura;
    }

    public void setAteTemperatura(String ateTemperatura) {
        this.ateTemperatura = ateTemperatura;
    }

    public String getAteImc() {
        return ateImc;
    }

    public void setAteImc(String ateImc) {
        this.ateImc = ateImc;
    }

    public String getAteAnotacionenfe() {
        return ateAnotacionenfe;
    }

    public void setAteAnotacionenfe(String ateAnotacionenfe) {
        this.ateAnotacionenfe = ateAnotacionenfe;
    }

    public String getAteRazonconsulta() {
        return ateRazonconsulta;
    }

    public void setAteRazonconsulta(String ateRazonconsulta) {
        this.ateRazonconsulta = ateRazonconsulta;
    }

    public String getAtePlanatencion() {
        return atePlanatencion;
    }

    public void setAtePlanatencion(String atePlanatencion) {
        this.atePlanatencion = atePlanatencion;
    }

    public String getAteObservaciones() {
        return ateObservaciones;
    }

    public void setAteObservaciones(String ateObservaciones) {
        this.ateObservaciones = ateObservaciones;
    }

    public String getAteTratamiento() {
        return ateTratamiento;
    }

    public void setAteTratamiento(String ateTratamiento) {
        this.ateTratamiento = ateTratamiento;
    }

    public Long getAteVersion() {
        return ateVersion;
    }

    public void setAteVersion(Long ateVersion) {
        this.ateVersion = ateVersion;
    }

    public List<CliExamen> getCliExamenList() {
        return cliExamenList;
    }

    public void setCliExamenList(List<CliExamen> cliExamenList) {
        this.cliExamenList = cliExamenList;
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
        hash += (ateId != null ? ateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliAtencion)) {
            return false;
        }
        CliAtencion other = (CliAtencion) object;
        if ((this.ateId == null && other.ateId != null) || (this.ateId != null && !this.ateId.equals(other.ateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliAtencion[ ateId=" + ateId + " ]";
    }
    
}
