/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;

/**
 *
 * @author ArauzKJ
 */
public class CliPacienteDto {
    private Long pacId;
    private String pacNombre;
    private String pacPrimerApellido;
    private String pacSegundoApellido;
    private String pacCedula;
    private Long pacTelefono;
    private String pacCorreo;
    private String pacGenero;
    private LocalDate pacFechaNacimiento;
    private Long pacVersion;

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

    public String getPacPrimerApellido() {
        return pacPrimerApellido;
    }

    public void setPacPrimerApellido(String pacPrimerApellido) {
        this.pacPrimerApellido = pacPrimerApellido;
    }

    public String getPacSegundoApellido() {
        return pacSegundoApellido;
    }

    public void setPacSegundoApellido(String pacSegundoApellido) {
        this.pacSegundoApellido = pacSegundoApellido;
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

    public LocalDate getPacFechaNacimiento() {
        return pacFechaNacimiento;
    }

    public void setPacFechaNacimiento(LocalDate pacFechaNacimiento) {
        this.pacFechaNacimiento = pacFechaNacimiento;
    }

    public Long getPacVersion() {
        return pacVersion;
    }

    public void setPacVersion(Long pacVersion) {
        this.pacVersion = pacVersion;
    }
    
    
}
