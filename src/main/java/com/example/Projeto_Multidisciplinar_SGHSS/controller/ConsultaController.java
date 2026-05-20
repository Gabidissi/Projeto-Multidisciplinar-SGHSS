package com.example.Projeto_Multidisciplinar_SGHSS.controller;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Consulta;
import com.example.Projeto_Multidisciplinar_SGHSS.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@Tag(name = "Módulo de Consultas & Telemedicina", description = "Endpoints para agendamento de consultas presenciais e geração automática de salas virtuais")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Operation(summary = "Agendar uma nova consulta", description = "Agenda um atendimento físico ou digital. Se o atributo 'eTelemedicina' for verdadeiro (true), o sistema gera um link criptografado e seguro para a sala virtual de atendimento online.")
    public ResponseEntity<Consulta> agendar(@RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaService.agendarConsulta(consulta);
        return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Listar todas as consultas", description = "Retorna o histórico completo de agendamentos e status das salas virtuais geradas no ecossistema.")
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> lista = consultaService.listarTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}