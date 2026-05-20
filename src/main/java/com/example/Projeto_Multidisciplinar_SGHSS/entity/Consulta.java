package com.example.Projeto_Multidisciplinar_SGHSS.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pacienteId;
    private Long profissionalId;
    private LocalDateTime dataHora;
    private String status;
    private Boolean eTelemedicina;
    private String urlSalaVirtual;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPacienteId() { return pacienteId; }
    public void setPacienteId(Long pacienteId) { this.pacienteId = pacienteId; }
    public Long getProfissionalId() { return profissionalId; }
    public void setProfissionalId(Long profissionalId) { this.profissionalId = profissionalId; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Boolean getETelemedicina() { return eTelemedicina; }
    public void setETelemedicina(Boolean eTelemedicina) { this.eTelemedicina = eTelemedicina; }
    public String getUrlSalaVirtual() { return urlSalaVirtual; }
    public void setUrlSalaVirtual(String urlSalaVirtual) { this.urlSalaVirtual = urlSalaVirtual; }
}