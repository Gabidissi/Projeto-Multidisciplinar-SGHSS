package com.example.Projeto_Multidisciplinar_SGHSS.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpfCriptografado; // RNF001 - Requisito da LGPD: CPF gravado em Base64 para não expor dado sensível
    private LocalDate dataNascimento;
    private String telefone;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpfCriptografado() { return cpfCriptografado; }
    public void setCpfCriptografado(String cpfCriptografado) { this.cpfCriptografado = cpfCriptografado; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}
