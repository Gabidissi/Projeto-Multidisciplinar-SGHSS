package com.example.Projeto_Multidisciplinar_SGHSS.service;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Consulta;
import com.example.Projeto_Multidisciplinar_SGHSS.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta agendarConsulta(Consulta consulta) {
        consulta.setStatus("AGENDADA");

        if (Boolean.TRUE.equals(consulta.getTelemedicina())) {
            String tokenSala = UUID.randomUUID().toString().substring(0, 8);
            consulta.setUrlSalaVirtual("https://telemed.vidaplus.com.br/sala/v1-" + tokenSala);
        } else {
            consulta.setUrlSalaVirtual("ATENDIMENTO_PRESENCIAL");
        }

        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }
}