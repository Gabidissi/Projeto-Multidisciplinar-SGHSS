package com.example.Projeto_Multidisciplinar_SGHSS.repository;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}