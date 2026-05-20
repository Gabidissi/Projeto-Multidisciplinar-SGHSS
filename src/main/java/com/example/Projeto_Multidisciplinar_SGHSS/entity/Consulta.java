package com.example.Projeto_Multidisciplinar_SGHSS.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "profissional_id")
    private Long profissionalId;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    private String status;

    @JsonProperty("eTelemedicina")
    @Column(name = "e_telemedicina")
    private Boolean telemedicina;

    @Column(name = "url_sala_virtual")
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
    public Boolean getTelemedicina() { return telemedicina; }
    public void setTelemedicina(Boolean telemedicina) { this.telemedicina = telemedicina; }
    public String getUrlSalaVirtual() { return urlSalaVirtual; }
    public void setUrlSalaVirtual(String urlSalaVirtual) { this.urlSalaVirtual = urlSalaVirtual; }
}