package com.teste.pratico.backend.controller;

import com.teste.pratico.backend.service.AgendamentoService;
import com.teste.pratico.model.entity.Agendamento;
import com.teste.pratico.model.exception.AgendamentoExistenteException;
import com.teste.pratico.model.exception.LimiteAgendamentoException;
import com.teste.pratico.model.exception.VagasIndisponiveisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agendamento")
public class AgendamentoRestController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    public void register(@RequestBody Agendamento entity) throws VagasIndisponiveisException, AgendamentoExistenteException, LimiteAgendamentoException {
        service.register(entity);
    }

    @PostMapping("/all")
    public List<Agendamento> findAll(@RequestBody Agendamento filterEntity) {
        return service.findAll(filterEntity);
    }

    @PutMapping(value = "{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
