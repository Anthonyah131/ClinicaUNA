package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ArauzKJ
 */
public class CliReporteDto {

    private Long repId;
    private String repNombre;
    private String repDescripcion;
    private String repConsulta;
    private String repTitulo;
    private String repPeriodicidad;
    private LocalDate repFini;
    private LocalDate repFsig;
    private Long repVersion;
    private List<CliParametroconsultaDto> cliParametroconsultaList;
    private List<CliParametroconsultaDto> cliParametroconsultaListEliminados;
    private List<CliCorreodestinoDto> cliCorreodestinoList;
    private List<CliCorreodestinoDto> cliCorreodestinoListEliminados;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliReporteDto() {
        this.cliParametroconsultaList = new ArrayList<>();
        this.cliParametroconsultaListEliminados = new ArrayList<>();
        this.cliCorreodestinoList =  new ArrayList<>();
        this.cliCorreodestinoListEliminados = new ArrayList<>();
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliReporteDto(CliReporte cliReporte) {
        this();
        this.repId = cliReporte.getRepId();
        this.repNombre = cliReporte.getRepNombre();
        this.repDescripcion = cliReporte.getRepDescripcion();
        this.repConsulta = cliReporte.getRepConsulta();
        this.repTitulo = cliReporte.getRepTitulo();
        this.repPeriodicidad = cliReporte.getRepPeriodicidad();
        this.repFini = cliReporte.getRepFini();
        this.repFsig = cliReporte.getRepFsig();
        this.repVersion = cliReporte.getRepVersion();
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

    public LocalDate getRepFini() {
        return repFini;
    }

    public void setRepFini(LocalDate repFini) {
        this.repFini = repFini;
    }
    
    public LocalDate getRepFsig() {
        return repFsig;
    }

    public void setRepFsig(LocalDate repFsig) {
        this.repFsig = repFsig;
    }

    public Long getRepVersion() {
        return repVersion;
    }

    public void setRepVersion(Long repVersion) {
        this.repVersion = repVersion;
    }

    public List<CliParametroconsultaDto> getCliParametroconsultaList() {
        return cliParametroconsultaList;
    }

    public void setCliParametroconsultaList(List<CliParametroconsultaDto> cliParametroconsultaList) {
        this.cliParametroconsultaList = cliParametroconsultaList;
    }

    public List<CliParametroconsultaDto> getCliParametroconsultaListEliminados() {
        return cliParametroconsultaListEliminados;
    }

    public void setCliParametroconsultaListEliminados(List<CliParametroconsultaDto> cliParametroconsultaListEliminados) {
        this.cliParametroconsultaListEliminados = cliParametroconsultaListEliminados;
    }

    public List<CliCorreodestinoDto> getCliCorreodestinoList() {
        return cliCorreodestinoList;
    }

    public void setCliCorreodestinoList(List<CliCorreodestinoDto> cliCorreodestinoList) {
        this.cliCorreodestinoList = cliCorreodestinoList;
    }

    public List<CliCorreodestinoDto> getCliCorreodestinoListEliminados() {
        return cliCorreodestinoListEliminados;
    }

    public void setCliCorreodestinoListEliminados(List<CliCorreodestinoDto> cliCorreodestinoListEliminados) {
        this.cliCorreodestinoListEliminados = cliCorreodestinoListEliminados;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

}
