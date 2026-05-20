package com.example.Projeto_Multidisciplinar_SGHSS.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_prontuarios")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_registro")
    private LocalDateTime dataRegistro;

    @Column(name = "evolucao_clinica", length = 2000)
    private String evolucaoClinica;

    @Column(name = "diagnostico_cid")
    private String diagnosticoCID;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro; }
    public String getEvolucaoClinica() { return evolucaoClinica; }
    public void setEvolucaoClinica(String evolucaoClinica) { this.evolucaoClinica = evolucaoClinica; }
    public String getDiagnosticoCID() { return diagnosticoCID; }
    public void setDiagnosticoCID(String diagnosticoCID) { this.diagnosticoCID = diagnosticoCID; }
}