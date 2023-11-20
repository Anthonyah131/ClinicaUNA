package cr.ac.una.wsclinicauna.model;

import java.time.LocalDateTime;

/**
 *
 * @author ArauzKJ
 */
public class CliCitaDto {

    private Long citId;
    private String citUsuarioRegistra;
    private String citMotivo;
    private LocalDateTime citFechaHora;
    private String citEstado;
    private Long cliCantespacios;
    private Long citVersion;
    private CliAgendaDto cliAgendaDto;
    private CliPacienteDto cliPacienteDto;
    private Boolean modificado;
    private LocalDateTime fecha;

    public CliCitaDto() {
        this.modificado = false;
        this.fecha = LocalDateTime.now();
    }

    public CliCitaDto(CliCita cliCita) {
        this();
        this.citId = cliCita.getCitId();
        this.citUsuarioRegistra = cliCita.getCitUsuarioregistra();
        this.citMotivo = cliCita.getCitMotivo();
        this.citFechaHora = cliCita.getCitFechahora();
        this.citEstado = cliCita.getCitEstado();
        this.cliCantespacios = cliCita.getCliCantespacios();
        this.citVersion = cliCita.getCitVersion();
        this.fecha = LocalDateTime.now();
    }
    
    
    
    public Long getCitId() {
        return citId;
    }

    public void setCitId(Long citId) {
        this.citId = citId;
    }

    public String getCitUsuarioRegistra() {
        return citUsuarioRegistra;
    }

    public void setCitUsuarioRegistra(String citUsuarioRegistra) {
        this.citUsuarioRegistra = citUsuarioRegistra;
    }

    public String getCitMotivo() {
        return citMotivo;
    }

    public void setCitMotivo(String citMotivo) {
        this.citMotivo = citMotivo;
    }

    public LocalDateTime getCitFechaHora() {
        return citFechaHora;
    }

    public void setCitFechaHora(LocalDateTime citFechaHora) {
        this.citFechaHora = citFechaHora;
    }

    public String getCitEstado() {
        return citEstado;
    }

    public void setCitEstado(String citEstado) {
        this.citEstado = citEstado;
    }

    public Long getCliCantespacios() {
        return cliCantespacios;
    }

    public void setCliCantespacios(Long cliCantespacios) {
        this.cliCantespacios = cliCantespacios;
    }

    public Long getCitVersion() {
        return citVersion;
    }

    public void setCitVersion(Long citVersion) {
        this.citVersion = citVersion;
    }

    public CliAgendaDto getCliAgendaDto() {
        return cliAgendaDto;
    }

    public void setCliAgendaDto(CliAgendaDto cliAgendaDto) {
        this.cliAgendaDto = cliAgendaDto;
    }

    public CliPacienteDto getCliPacienteDto() {
        return cliPacienteDto;
    }

    public void setCliPacienteDto(CliPacienteDto cliPacienteDto) {
        this.cliPacienteDto = cliPacienteDto;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
