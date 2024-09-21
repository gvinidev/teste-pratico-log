package com.teste.pratico.backend.repository;

import com.teste.pratico.model.entity.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Integer> {

    @Query("SELECT v FROM Vagas v WHERE :dataAgendamento BETWEEN v.inicio AND v.fim")
    List<Vagas> findVagasByPeriodo(@Param("dataAgendamento") Date dataAgendamento);

    @Query("SELECT v.quantidade FROM Vagas v WHERE v.inicio <= :data AND v.fim >= :data")
    Integer findQuantidadeVagasDisponiveis(@Param("data") Date data);
}
