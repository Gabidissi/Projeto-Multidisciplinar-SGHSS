package com.example.Projeto_Multidisciplinar_SGHSS.controller;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Paciente;
import com.example.Projeto_Multidisciplinar_SGHSS.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> criarPaciente(@RequestBody Paciente paciente) {
        // Intercepta o dado bruto enviado no payload JSON para tratamento seguro de LGPD
        String cpfOriginal = paciente.getCpfCriptografado();
        Paciente novoPaciente = pacienteService.salvarPaciente(paciente, cpfOriginal);
        return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> lista = pacienteService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}