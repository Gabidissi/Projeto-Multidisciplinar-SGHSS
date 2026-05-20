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
        // Criptografia em Base64 para proteção de dados sensíveis na persistência (Compliance LGPD - RNF001)
        String hashCpf = Base64.getEncoder().encodeToString(cpfOriginal.getBytes());
        paciente.setCpfCriptografado(hashCpf);

        Paciente salvo = pacienteRepository.save(paciente);

        // Automação de Rastreabilidade (RNF002) - Injeção síncrona obrigatória de Log de Auditoria
        LogAuditoria log = new LogAuditoria();
        log.setTxUsuario("MEDICO_SISTEMA"); // Simulação do contexto do usuário autenticado
        log.setTxOperacao("CREATE_PACIENTE");
        log.setDataHoraOperacao(LocalDateTime.now());
        log.setIdRegistroAfetado(salvo.getId());
        logRepository.save(log);

        return salvo;
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}