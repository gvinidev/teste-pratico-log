package com.teste.pratico.backend.service;

import com.teste.pratico.backend.repository.VagasRepository;
import com.teste.pratico.model.entity.Vagas;
import com.teste.pratico.model.exception.VagasExistenteException;
import com.teste.pratico.model.exception.VagasIndisponiveisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VagasService {

    @Autowired
    private VagasRepository repository;

    @Transactional(rollbackOn = Throwable.class)
    public void register(Vagas entity) throws VagasExistenteException {
        List<Vagas> vagasConflitantes = repository.findVagasByInicioAndFim(entity.getInicio(), entity.getFim());

        if (!vagasConflitantes.isEmpty()) {
            throw new VagasExistenteException();
        }

        repository.save(entity);
    }

    @Transactional(rollbackOn = Throwable.class)
    public void update(Vagas entity) {
        repository.save(entity);
    }

    public Optional<Vagas> findById(Integer id) {
        return repository.findById(id);
    }

    public List<Vagas> findAll() {
        return repository.findAll();
    }

    public void delete(Integer id) {
        Optional<Vagas> entityOptional = this.findById(id);

        entityOptional.ifPresent(vagas -> repository.delete(vagas));
    }
}
