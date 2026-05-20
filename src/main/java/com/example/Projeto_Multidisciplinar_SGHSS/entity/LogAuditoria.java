package com.example.Projeto_Multidisciplinar_SGHSS.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_logs_auditoria_lgpd")
public class LogAuditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String txUsuario;
    private String txOperacao;
    private LocalDateTime dtTimestamp;
    private Long idRegistroAfetado;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTxUsuario() { return txUsuario; }
    public void setTxUsuario(String txUsuario) { this.txUsuario = txUsuario; }
    public String getTxOperacao() { return txOperacao; }
    public void setTxOperacao(String txOperacao) { this.txOperacao = txOperacao; }
    public LocalDateTime getDtTimestamp() { return dtTimestamp; }
    public void setDtTimestamp(LocalDateTime dtTimestamp) { this.dtTimestamp = dtTimestamp; }
    public Long getIdRegistroAfetado() { return idRegistroAfetado; }
    public void setIdRegistroAfetado(Long idRegistroAfetado) { this.idRegistroAfetado = idRegistroAfetado; }
}