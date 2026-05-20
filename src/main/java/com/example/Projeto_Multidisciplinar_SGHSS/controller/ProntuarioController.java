package com.example.Projeto_Multidisciplinar_SGHSS.controller;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Prontuario;
import com.example.Projeto_Multidisciplinar_SGHSS.service.ProntuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
@Tag(name = "Módulo de Prontuários Clínicos", description = "Endpoints para evolução médica e histórico de saúde, com disparo de logs de segurança (LGPD)")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    @Operation(summary = "Registrar uma nova evolução clínica", description = "Grava os dados de saúde do paciente (diagnósticos e CID) e dispara log de auditoria ")
    public ResponseEntity<Prontuario> registrarEvolucao(@RequestBody Prontuario prontuario) {
        Prontuario novoProntuario = prontuarioService.evoluirProntuario(prontuario);
        return new ResponseEntity<>(novoProntuario, HttpStatus.CREATED);
    }

    @GetMapping("/paciente/{pacienteId}")
    @Operation(summary = "Buscar histórico clínico por ID do Paciente", description = "Recupera todas as evoluções médicas de um determinado paciente através do identificador numérico. Toda consulta a esta rota gera uma entrada de auditoria indicando o usuário médico que leu a ficha.")
    public ResponseEntity<List<Prontuario>> listarPorPaciente(@PathVariable Long pacienteId) {
        List<Prontuario> historico = prontuarioService.buscarHistoricoPorPaciente(pacienteId);
        return new ResponseEntity<>(historico, HttpStatus.OK);
    }
}