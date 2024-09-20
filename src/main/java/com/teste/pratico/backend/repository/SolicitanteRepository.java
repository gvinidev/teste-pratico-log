package com.teste.pratico.backend.repository;

import com.teste.pratico.model.entity.Solicitante;
import com.teste.pratico.model.entity.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Integer> {
}
