package com.example.Projeto_Multidisciplinar_SGHSS.service;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.LogAuditoria;
import com.example.Projeto_Multidisciplinar_SGHSS.entity.Paciente;
import com.example.Projeto_Multidisciplinar_SGHSS.repository.LogAuditoriaRepository;
import com.example.Projeto_Multidisciplinar_SGHSS.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private LogAuditoriaRepository logRepository;

    public Paciente salvarPaciente(Paciente paciente, String cpfOriginal) {
        // Regra de Negócio: Codifica o CPF em Base64 antes de persistir no H2 (Camada de Segurança)
        String hashCpf = Base64.getEncoder().encodeToString(cpfOriginal.getBytes());
        paciente.setCpfCriptografado(hashCpf);

        // Salva o paciente unificado na base da VidaPlus
        Paciente salvo = pacienteRepository.save(paciente);

        // RNF002 - Sistema gera o rastro de auditoria automaticamente após salvar
        LogAuditoria log = new LogAuditoria();
        log.setTxUsuario("MEDICO_SISTEMA"); // Simulação do usuário logado na sessão
        log.setTxOperacao("CREATE_PACIENTE");
        log.setDataHoraOperacao(LocalDateTime.now()); // Pega o timestamp atual do servidor
        log.setIdRegistroAfetado(salvo.getId());
        logRepository.save(log); // Grava o log na tabela de auditoria

        return salvo;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}