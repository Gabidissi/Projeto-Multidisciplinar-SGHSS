package com.example.Projeto_Multidisciplinar_SGHSS.controller;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Paciente;
import com.example.Projeto_Multidisciplinar_SGHSS.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@Tag(name = "Módulo de Pacientes", description = "Endpoints para gestão cadastral e unificação de prontuários em conformidade com a LGPD")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Operation(summary = "Cadastrar um novo paciente", description = "Recebe os dados do paciente, encripta o CPF em Base64 (LGPD) e dispara o log de auditoria.")
    public ResponseEntity<Paciente> criarPaciente(@RequestBody Paciente paciente) {
        // Intercepta o dado bruto enviado no payload JSON para tratamento seguro de LGPD
        String cpfOriginal = paciente.getCpfCriptografado();
        Paciente novoPaciente = pacienteService.salvarPaciente(paciente, cpfOriginal);
        return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todos os pacientes", description = "Retorna uma lista com todos os pacientes unificados na base de dados da rede VidaPlus.")
    public ResponseEntity<List<Paciente>> listarPacientes() {
        List<Paciente> lista = pacienteService.listarTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}