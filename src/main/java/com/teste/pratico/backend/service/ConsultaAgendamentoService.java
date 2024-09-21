package com.teste.pratico.backend.service;

import com.teste.pratico.backend.repository.VagasRepository;
import com.teste.pratico.model.dto.ConsultaAgendamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultaAgendamentoService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<ConsultaAgendamentoDTO> findAll(ConsultaAgendamentoDTO filterEntity) {
        String sql = "SELECT ag.numero, sc.nome, vg.quantidade, (CAST(ag.numero AS DECIMAL(10,2)) / vg.quantidade * 100) AS porcentagem " +
                "FROM agendamento ag INNER JOIN solicitante sc ON ag.solicitante_id = sc.id " +
                "CROSS JOIN vagas vg WHERE 1 = 1";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (null != filterEntity.getDataInicio()) {
            sql += " AND ag.data >= '" + formatter.format(filterEntity.getDataInicio()) + "'";
        }

        if (null != filterEntity.getDataFim()) {
            sql += " AND ag.data <= '" + formatter.format(filterEntity.getDataFim()) + "'";
        }

        if (null != filterEntity.getSolicitanteIdFilter()) {
            sql += " AND ag.solicitante_id = " + filterEntity.getSolicitanteIdFilter();
        }

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();

        List<ConsultaAgendamentoDTO> consultaAgendamentos = new ArrayList<>();

        for (Object row[] : results) {
            ConsultaAgendamentoDTO agendamento = new ConsultaAgendamentoDTO();

            agendamento.setTotalAgendamentos((String) row[0]);
            agendamento.setNomeSolicitante((String) row[1]);
            agendamento.setQtdVagas((Integer) row[2]);
            agendamento.setPercentual((BigDecimal) row[3]);

            consultaAgendamentos.add(agendamento);
        }

        return consultaAgendamentos;
    }
}
