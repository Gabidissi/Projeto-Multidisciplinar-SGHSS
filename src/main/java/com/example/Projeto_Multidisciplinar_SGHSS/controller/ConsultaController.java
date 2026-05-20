package com.example.Projeto_Multidisciplinar_SGHSS.controller;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Consulta;
import com.example.Projeto_Multidisciplinar_SGHSS.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> agendar(@RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaService.agendarConsulta(consulta);
        return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listar() {
        List<Consulta> lista = consultaService.listarTodas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}