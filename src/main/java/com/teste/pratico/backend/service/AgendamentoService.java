package com.teste.pratico.backend.service;

import com.teste.pratico.backend.repository.AgendamentoRepository;
import com.teste.pratico.model.entity.Agendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackOn = Throwable.class)
    public void register(Agendamento entity) {
        repository.save(entity);
    }

    public Optional<Agendamento> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Agendamento> findAll(Agendamento filterEntity) {
        String sql = "SELECT ag.*, sc.nome FROM agendamento ag INNER JOIN solicitante sc ON ag.solicitante_id = sc.id WHERE 1=1 ";
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

        List<Agendamento> agendamentos = new ArrayList<>();

        for (Object row[] : results) {
            Agendamento agendamento = new Agendamento();

            agendamento.setId(((BigInteger) row[0]).intValue());
            agendamento.setData((Date) row[1]);
            agendamento.setNumero((String) row[2]);
            agendamento.setMotivo((String) row[3]);
            agendamento.setSolicitanteId(((BigInteger) row[4]).intValue());
            agendamento.setNomeSolicitante((String) row[5]);

            agendamentos.add(agendamento);
        }

        return agendamentos;
    }

    public void delete(Integer id) {
        Optional<Agendamento> entityOptional = this.findById(id);

        entityOptional.ifPresent(agendamento -> repository.delete(agendamento));
    }
}
