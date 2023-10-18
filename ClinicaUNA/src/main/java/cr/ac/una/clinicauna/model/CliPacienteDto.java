/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ANTHONY
 */
public class CliPacienteDto {

    private SimpleStringProperty pacId;
    private SimpleStringProperty pacNombre;
    private SimpleStringProperty pacPrimerApellido;
    private SimpleStringProperty pacSegundoApellido;
    private SimpleStringProperty pacCedula;
    private SimpleStringProperty pacTelefono;
    private SimpleStringProperty pacCorreo;
    private ObjectProperty<String> pacGenero;
    private ObjectProperty<LocalDate> pacFechaNacimiento;
    private Long pacVersion;
    private Boolean modificado;

    public CliPacienteDto() {
        this.pacId = new SimpleStringProperty();
        this.pacNombre = new SimpleStringProperty();
        this.pacPrimerApellido = new SimpleStringProperty();
        this.pacSegundoApellido = new SimpleStringProperty();
        this.pacCedula = new SimpleStringProperty();
        this.pacTelefono = new SimpleStringProperty();
        this.pacCorreo = new SimpleStringProperty();
        this.pacGenero = new SimpleObjectProperty("M");
        this.pacFechaNacimiento = new SimpleObjectProperty();
        this.modificado = false;
    }

    public Long getPacId() {
        if (this.pacId.get() != null && !this.pacId.get().isEmpty()) {
            return Long.valueOf(this.pacId.get());
        } else {
            return null;
        }
    }

    public void setPacId(Long pacId) {
        this.pacId.set(pacId.toString());
    }

    public String getPacNombre() {
        return pacNombre.get();
    }

    public void setPacNombre(String pacNombre) {
        this.pacNombre.set(pacNombre);
    }

    public String getPacPrimerApellido() {
        return pacPrimerApellido.get();
    }

    public void setPacPrimerApellido(String pacPrimerApellido) {
        this.pacPrimerApellido.set(pacPrimerApellido);
    }

    public String getPacSegundoApellido() {
        return pacSegundoApellido.get();
    }

    public void setPacSegundoApellido(String pacSegundoApellido) {
        this.pacSegundoApellido.set(pacSegundoApellido);
    }

    public String getPacCedula() {
        return pacCedula.get();
    }

    public void setPacCedula(String pacCedula) {
        this.pacCedula.set(pacCedula);
    }

    public String getPacTelefono() {
        return pacTelefono.get();
    }

    public void setPacTelefono(String pacTelefono) {
        this.pacTelefono.set(pacTelefono);
    }

    public String getPacCorreo() {
        return pacCorreo.get();
    }

    public void setPacCorreo(String pacCorreo) {
        this.pacCorreo.set(pacCorreo);
    }

    public String getPacGenero() {
        return pacGenero.get();
    }

    public void setPacGenero(String pacGenero) {
        this.pacGenero.set(pacGenero);
    }

    public LocalDate getPacFechaNacimiento() {
        return pacFechaNacimiento.get();
    }

    public void setPacFechaNacimiento(LocalDate pacFechaNacimiento) {
        this.pacFechaNacimiento.set(pacFechaNacimiento);
    }

    public Long getPacVersion() {
        return pacVersion;
    }

    public void setPacVersion(Long pacVersion) {
        this.pacVersion = pacVersion;
    }

    public Boolean getModificado() {
        return modificado;
    }

    public void setModificado(Boolean modificado) {
        this.modificado = modificado;
    }
}