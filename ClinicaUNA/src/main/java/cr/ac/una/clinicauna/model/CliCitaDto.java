package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliCitaDto {

    public SimpleStringProperty citId;
    public SimpleStringProperty citUsuarioRegistra;
    public SimpleStringProperty citMotivo;
    public ObjectProperty<LocalDateTime> citFechaHora;
    public SimpleStringProperty citEstado;
    public SimpleStringProperty cliCantespacios;
    public CliAgendaDto cliAgendaDto;
    public CliPacienteDto cliPacienteDto;
    private Long citVersion;
    private Boolean modificado;

    public CliCitaDto() {
        this.citId = new SimpleStringProperty();
        this.citUsuarioRegistra = new SimpleStringProperty();
        this.citMotivo = new SimpleStringProperty();
        this.citFechaHora = new SimpleObjectProperty();
        this.citEstado = new SimpleStringProperty();
        this.cliCantespacios = new SimpleStringProperty();
        this.modificado = false;
    }

    public Long getCitId() {
        if (this.citId.get() != null && !this.citId.get().isEmpty()) {
            return Long.valueOf(this.citId.get());
        } else {
            return null;
        }
    }

    public void setCitId(Long citId) {
        this.citId.set(citId.toString());
    }

    public String getCitUsuarioRegistra() {
        return citUsuarioRegistra.get();
    }

    public void setCitUsuarioRegistra(String citUsuarioRegistra) {
        this.citUsuarioRegistra.set(citUsuarioRegistra);
    }

    public String getCitMotivo() {
        return citMotivo.get();
    }

    public void setCitMotivo(String citMotivo) {
        this.citMotivo.set(citMotivo);
    }

    public LocalDateTime getCitFechaHora() {
        return citFechaHora.get();
    }

    public void setCitFechaHora(LocalDateTime citFechaHora) {
        this.citFechaHora.set(citFechaHora);
    }

    public String getCitEstado() {
        return citEstado.get();
    }

    public void setCitEstado(String citEstado) {
        this.citEstado.set(citEstado);
    }

    public Long getCliCantespacios() {
        if (this.cliCantespacios.get() != null && !this.cliCantespacios.get().isEmpty()) {
            return Long.valueOf(this.cliCantespacios.get());
        } else {
            return null;
        }
    }

    public void setCliCantespacios(Long cliCantespacios) {
        this.cliCantespacios.set(cliCantespacios.toString());
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

    public Long getCitVersion() {
        return citVersion;
    }

    public void setCitVersion(Long citVersion) {
        this.citVersion = citVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public String getNombreString() {
        return cliPacienteDto.getPacNombre() + " " + cliPacienteDto.getPacPapellido() + " " + cliPacienteDto.getPacSapellido();
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }

    public String citaLabel() {
        String motivo = (getCitMotivo() != null) ? getCitMotivo() : "No indica";

        return "Estado: " + estadoCita()
                + "\nPaciente: " + getNombreString()
                + "\nUsuario que registra: " + getCitUsuarioRegistra()
                + "\nMotivo: " + motivo
                + "\nTelefono: " + cliPacienteDto.getPacTelefono()
                + "\nCorreo: " + cliPacienteDto.getPacCorreo();
    }

    public String estadoCita() {
        return switch (getCitEstado()) {
            case "U" ->
                "Ausente";
            case "A" ->
                "Atendida";
            case "C" ->
                "Cancelada";
            default ->
                "Programada";
        };
    }
}
