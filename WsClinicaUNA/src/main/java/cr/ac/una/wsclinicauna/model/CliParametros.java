/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author ArauzKJ
 */
@Entity
@Table(name = "CLI_PARAMETROS")
@NamedQueries({
    @NamedQuery(name = "CliParametros.findAll", query = "SELECT c FROM CliParametros c"),
    @NamedQuery(name = "CliParametros.findByParId", query = "SELECT c FROM CliParametros c WHERE c.parId = :parId"),
    @NamedQuery(name = "CliParametros.findByParNombre", query = "SELECT c FROM CliParametros c WHERE c.parNombre = :parNombre"),
    @NamedQuery(name = "CliParametros.findByParDescripcion", query = "SELECT c FROM CliParametros c WHERE c.parDescripcion = :parDescripcion"),
    @NamedQuery(name = "CliParametros.findByParEmail", query = "SELECT c FROM CliParametros c WHERE c.parEmail = :parEmail"),
    @NamedQuery(name = "CliParametros.findByParClave", query = "SELECT c FROM CliParametros c WHERE c.parClave = :parClave"),
    @NamedQuery(name = "CliParametros.findByParVersion", query = "SELECT c FROM CliParametros c WHERE c.parVersion = :parVersion")})
public class CliParametros implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_ID")
    private Long parId;
    @Size(max = 30)
    @Column(name = "PAR_NOMBRE")
    private String parNombre;
    @Size(max = 30)
    @Column(name = "PAR_DESCRIPCION")
    private String parDescripcion;
    @Size(max = 30)
    @Column(name = "PAR_EMAIL")
    private String parEmail;
    @Size(max = 30)
    @Column(name = "PAR_CLAVE")
    private String parClave;
    @Lob
    @Column(name = "PAR_FOTO")
    private Serializable parFoto;
    @Lob
    @Column(name = "PAR_HTML")
    private Serializable parHtml;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAR_VERSION")
    private Long parVersion;

    public CliParametros() {
    }

    public CliParametros(Long parId) {
        this.parId = parId;
    }

    public CliParametros(Long parId, Long parVersion) {
        this.parId = parId;
        this.parVersion = parVersion;
    }

    public Long getParId() {
        return parId;
    }

    public void setParId(Long parId) {
        this.parId = parId;
    }

    public String getParNombre() {
        return parNombre;
    }

    public void setParNombre(String parNombre) {
        this.parNombre = parNombre;
    }

    public String getParDescripcion() {
        return parDescripcion;
    }

    public void setParDescripcion(String parDescripcion) {
        this.parDescripcion = parDescripcion;
    }

    public String getParEmail() {
        return parEmail;
    }

    public void setParEmail(String parEmail) {
        this.parEmail = parEmail;
    }

    public String getParClave() {
        return parClave;
    }

    public void setParClave(String parClave) {
        this.parClave = parClave;
    }

    public Serializable getParFoto() {
        return parFoto;
    }

    public void setParFoto(Serializable parFoto) {
        this.parFoto = parFoto;
    }

    public Serializable getParHtml() {
        return parHtml;
    }

    public void setParHtml(Serializable parHtml) {
        this.parHtml = parHtml;
    }

    public Long getParVersion() {
        return parVersion;
    }

    public void setParVersion(Long parVersion) {
        this.parVersion = parVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parId != null ? parId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CliParametros)) {
            return false;
        }
        CliParametros other = (CliParametros) object;
        if ((this.parId == null && other.parId != null) || (this.parId != null && !this.parId.equals(other.parId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cr.ac.una.wsclinicauna.model.CliParametros[ parId=" + parId + " ]";
    }
    
}
