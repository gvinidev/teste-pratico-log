package com.teste.pratico.backend.service;

import com.teste.pratico.backend.repository.SolicitanteRepository;
import com.teste.pratico.backend.repository.VagasRepository;
import com.teste.pratico.model.entity.Solicitante;
import com.teste.pratico.model.entity.Vagas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitanteService {

    @Autowired
    private SolicitanteRepository repository;

    @Transactional(rollbackOn = Throwable.class)
    public void register(Solicitante entity) {
        repository.save(entity);
    }

    public Optional<Solicitante> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Solicitante> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        Optional<Solicitante> entityOptional = this.findById(id);

        entityOptional.ifPresent(solicitante -> repository.delete(solicitante));
    }
}
