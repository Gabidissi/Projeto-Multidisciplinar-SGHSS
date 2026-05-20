package com.example.Projeto_Multidisciplinar_SGHSS.repository;

import com.example.Projeto_Multidisciplinar_SGHSS.entity.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}