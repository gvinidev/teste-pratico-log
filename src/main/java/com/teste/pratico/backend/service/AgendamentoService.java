package com.teste.pratico.backend.service;

import com.teste.pratico.backend.repository.AgendamentoRepository;
import com.teste.pratico.backend.repository.VagasRepository;
import com.teste.pratico.model.entity.Agendamento;
import com.teste.pratico.model.entity.Vagas;
import com.teste.pratico.model.exception.AgendamentoExistenteException;
import com.teste.pratico.model.exception.LimiteAgendamentoException;
import com.teste.pratico.model.exception.VagasIndisponiveisException;
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

    @Autowired
    private VagasRepository vagasRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Transactional(rollbackOn = Throwable.class)
    public void register(Agendamento entity) throws VagasIndisponiveisException, AgendamentoExistenteException, LimiteAgendamentoException {
        Optional<Agendamento> agendamento = repository.findBySolicitanteId(entity.getSolicitanteId());

        if (agendamento.isPresent()) {
            throw new AgendamentoExistenteException();
        }

        boolean vagasDisponiveis = isVagasDisponiveis(entity.getData(), Double.parseDouble(entity.getNumero()));
        if (!vagasDisponiveis) {
            throw new VagasIndisponiveisException();
        }

        int quantidadeVagas = vagasRepository.findQuantidadeVagasDisponiveis(entity.getData());
        int limiteAgendamentos = limiteAgendamentos(quantidadeVagas);

        Optional<Integer> agendamentosExistentes = repository.countBySolicitanteIdAndData(
                entity.getSolicitanteId(), entity.getData());

        if (agendamentosExistentes.isPresent()) {
            if (agendamentosExistentes.get() + Double.parseDouble(entity.getNumero()) >= limiteAgendamentos) {
                throw new LimiteAgendamentoException();
            }
        } else if (Double.parseDouble(entity.getNumero()) >= limiteAgendamentos) {
            throw new LimiteAgendamentoException();
        }

        repository.save(entity);
    }

    public Optional<Agendamento> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Agendamento> findAll(Agendamento filterEntity) {
        String sql = "SELECT ag.*, sc.nome FROM agendamento ag "
                + "INNER JOIN solicitante sc ON ag.solicitante_id = sc.id WHERE 1=1 ";

        if (filterEntity.getDataInicio() != null) {
            sql += " AND ag.data >= '" + formatter.format(filterEntity.getDataInicio()) + "'";
        }

        if (filterEntity.getDataFim() != null) {
            sql += " AND ag.data <= '" + formatter.format(filterEntity.getDataFim()) + "'";
        }

        if (filterEntity.getSolicitanteIdFilter() != null) {
            sql += " AND ag.solicitante_id = " + filterEntity.getSolicitanteIdFilter();
        }

        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();
        List<Agendamento> agendamentos = new ArrayList<>();

        for (Object[] row : results) {
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
        entityOptional.ifPresent(repository::delete);
    }

    private boolean isVagasDisponiveis(Date data, Double quantidade) {
        List<Vagas> vagasDisponiveis = vagasRepository.findVagasByPeriodo(data);

        if (vagasDisponiveis.isEmpty()) {
            return false;
        }

        for (Vagas vagas : vagasDisponiveis) {
            Optional<Integer> agendamentosExistentes = repository.numeroAgendamentoByDataBetween(
                    vagas.getInicio(), vagas.getFim());

            if (agendamentosExistentes.isPresent()
                    && agendamentosExistentes.get() + quantidade > vagas.getQuantidade()) {
                return false;
            }
        }

        return true;
    }

    public int limiteAgendamentos(int quantidadeVagas) {
        return (int) Math.floor(quantidadeVagas * 0.25);
    }
}
