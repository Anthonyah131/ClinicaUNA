/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.wsclinicauna.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author ArauzKJ
 */
public class CliReporteusuariosDto {
    private Long repusuId;
    private LocalDate repusuFechaemision;
    private Long repusuVersion;
    private List<CliUsuarioDto> cliUsuarioList;
    private List<CliUsuarioDto> cliUsuarioListEliminados;
    private Boolean modificado;

    public Long getRepusuId() {
        return repusuId;
    }

    public void setRepusuId(Long repusuId) {
        this.repusuId = repusuId;
    }

    public LocalDate getRepusuFechaemision() {
        return repusuFechaemision;
    }

    public void setRepusuFechaemision(LocalDate repusuFechaemision) {
        this.repusuFechaemision = repusuFechaemision;
    }

    public Long getRepusuVersion() {
        return repusuVersion;
    }

    public void setRepusuVersion(Long repusuVersion) {
        this.repusuVersion = repusuVersion;
    }

    public List<CliUsuarioDto> getCliUsuarioList() {
        return cliUsuarioList;
    }

    public void setCliUsuarioList(List<CliUsuarioDto> cliUsuarioList) {
        this.cliUsuarioList = cliUsuarioList;
    }

    public List<CliUsuarioDto> getCliUsuarioListEliminados() {
        return cliUsuarioListEliminados;
    }

    public void setCliUsuarioListEliminados(List<CliUsuarioDto> cliUsuarioListEliminados) {
        this.cliUsuarioListEliminados = cliUsuarioListEliminados;
    }
    
    
}
