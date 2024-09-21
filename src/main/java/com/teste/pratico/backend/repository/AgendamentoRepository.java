package com.teste.pratico.backend.repository;

import com.teste.pratico.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    @Query(value = "SELECT CAST(REPLACE(a.numero, '.00', '') AS INTEGER) FROM agendamento a WHERE a.data BETWEEN :inicio AND :fim", nativeQuery = true)
    Optional<Integer> numeroAgendamentoByDataBetween(@Param("inicio") Date inicio, @Param("fim") Date fim);

    @Query(value = "SELECT CAST(REPLACE(a.numero, '.00', '') AS INTEGER) FROM agendamento a WHERE a.solicitante_id = :solicitanteId" +
            " AND a.data = TO_DATE(:data, 'YYYY-MM-DD')", nativeQuery = true)
    Optional<Integer> countBySolicitanteIdAndData(@Param("solicitanteId") Integer solicitanteId, @Param("data") Date data);

    Optional<Agendamento> findBySolicitanteId(Integer solicitanteId);
}
