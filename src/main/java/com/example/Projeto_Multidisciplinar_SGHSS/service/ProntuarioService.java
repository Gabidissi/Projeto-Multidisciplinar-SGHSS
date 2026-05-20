package com.example.Projeto_Multidisciplinar_SGHSS.service;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.LogAuditoria;
import com.example.Projeto_Multidisciplinar_SGHSS.entity.Prontuario;
import com.example.Projeto_Multidisciplinar_SGHSS.repository.LogAuditoriaRepository;
import com.example.Projeto_Multidisciplinar_SGHSS.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private LogAuditoriaRepository logRepository;

    public Prontuario evoluirProntuario(Prontuario prontuario) {
        prontuario.setDataRegistro(LocalDateTime.now());
        Prontuario salvo = prontuarioRepository.save(prontuario);

        // RNF002 (Rastreabilidade LGPD) - Registro síncrono obrigatório para logs clínicos sensíveis
        LogAuditoria log = new LogAuditoria();
        log.setTxUsuario("MEDICO_AUTENTICADO");
        log.setTxOperacao("READ_WRITE_PRONTUARIO");
        log.setDtTimestamp(LocalDateTime.now());
        log.setIdRegistroAfetado(salvo.getId());
        logRepository.save(log);

        return salvo;
    }

    public List<Prontuario> buscarHistoricoPorPaciente(Long pacienteId) {
        // Gera log de auditoria também para operações de consulta/leitura de dados sensíveis (Rigor LGPD)
        LogAuditoria log = new LogAuditoria();
        log.setTxUsuario("MEDICO_AUTENTICADO");
        log.setTxOperacao("READ_HISTORICO_CLINICO");
        log.setDtTimestamp(LocalDateTime.now());
        log.setIdRegistroAfetado(pacienteId);
        logRepository.save(log);

        return prontuarioRepository.findAll().stream()
                .filter(p -> p.getPacienteId().equals(pacienteId))
                .toList();
    }
}