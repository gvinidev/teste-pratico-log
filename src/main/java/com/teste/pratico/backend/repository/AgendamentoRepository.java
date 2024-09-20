package com.teste.pratico.backend.repository;

import com.teste.pratico.model.entity.Agendamento;
import com.teste.pratico.model.entity.Solicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
