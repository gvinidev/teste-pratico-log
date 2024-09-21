package com.teste.pratico.backend.controller;

import com.teste.pratico.backend.service.ConsultaAgendamentoService;
import com.teste.pratico.model.dto.ConsultaAgendamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consulta-agendamento")
public class ConsultaAgendamentoRestController {

    @Autowired
    private ConsultaAgendamentoService service;

    @PostMapping("/all")
    public List<ConsultaAgendamentoDTO> findAll(@RequestBody ConsultaAgendamentoDTO filterEntity) {
        return service.findAll(filterEntity);
    }
}
