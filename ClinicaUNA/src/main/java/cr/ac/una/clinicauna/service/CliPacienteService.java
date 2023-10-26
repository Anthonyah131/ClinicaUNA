/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.clinicauna.service;

import cr.ac.una.clinicauna.model.CliPacienteDto;
import cr.ac.una.clinicauna.util.Request;
import cr.ac.una.clinicauna.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;

/**
 *
 * @author ANTHONY
 */
public class CliPacienteService {
    public Respuesta getPaciente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliPacienteController/paciente", "{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliPacienteDto pacienteDto = (CliPacienteDto) request.readEntity(CliPacienteDto.class);
            return new Respuesta(true, "", "", "Paciente", pacienteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteService.class.getName()).log(Level.SEVERE, "Error obteniendo el paciente [" + id + "]", ex);
            return new Respuesta(false, "Error obteniendo el paciente.", "getPaciente " + ex.getMessage());
        }
    }

    public Respuesta getPacientes(String cedula, String nombre, String pApellido) {
        try {
            Request request = new Request("CliPacienteController/pacientes");
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            List<CliPacienteDto> pacientes = (List<CliPacienteDto>) request.readEntity(new GenericType<List<CliPacienteDto>>() {
            });
            if (cedula != null && !cedula.isBlank()) {
                String cedulaBuscado = cedula.toLowerCase();
                pacientes = pacientes.stream()
                        .filter(p -> p.getPacCedula().toLowerCase().contains(cedulaBuscado))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }
            if (nombre != null && !nombre.isBlank()) {
                String nombreBuscado = nombre.toLowerCase();
                pacientes = pacientes.stream()
                        .filter(p -> p.getPacNombre().toLowerCase().contains(nombreBuscado))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }
            if (pApellido != null && !pApellido.isBlank()) {
                String apellidoBuscada = pApellido.toLowerCase();
                pacientes = pacientes.stream()
                        .filter(p -> p.getPacPapellido().toLowerCase().contains(apellidoBuscada))
                        .collect(Collectors.toCollection(FXCollections::observableArrayList));
            }
            return new Respuesta(true, "", "", "Pacientes", pacientes);
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteService.class.getName()).log(Level.SEVERE, "Error obteniendo pacientes.", ex);
            return new Respuesta(false, "Error obteniendo pacientes.", "getPacientes " + ex.getMessage());
        }
    }

    public Respuesta guardarPaciente(CliPacienteDto paciente) {
        try {
            Request request = new Request("CliPacienteController/paciente");
            request.post(paciente);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            CliPacienteDto pacienteDto = (CliPacienteDto) request.readEntity(CliPacienteDto.class);
            return new Respuesta(true, "", "", "Paciente", pacienteDto);
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteService.class.getName()).log(Level.SEVERE, "Error guardando el paciente.", ex);
            return new Respuesta(false, "Error guardando el paciente.", "guardarPaciente " + ex.getMessage());
        }
    }

    public Respuesta eliminarPaciente(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("CliPacienteController/eliminarPaciente", "{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(CliPacienteService.class.getName()).log(Level.SEVERE, "Error eliminando el paciente.", ex);
            return new Respuesta(false, "Error eliminando el paciente.", "eliminarPaciente " + ex.getMessage());
        }
    }
}
