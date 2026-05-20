package com.example.Projeto_Multidisciplinar_SGHSS.controller;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Prontuario;
import com.example.Projeto_Multidisciplinar_SGHSS.service.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<Prontuario> registrarEvolucao(@RequestBody Prontuario prontuario) {
        Prontuario novoProntuario = prontuarioService.evoluirProntuario(prontuario);
        return new ResponseEntity<>(novoProntuario, HttpStatus.CREATED);
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Prontuario>> listarPorPaciente(@PathVariable Long pacienteId) {
        List<Prontuario> historico = prontuarioService.buscarHistoricoPorPaciente(pacienteId);
        return new ResponseEntity<>(historico, HttpStatus.OK);
    }
}