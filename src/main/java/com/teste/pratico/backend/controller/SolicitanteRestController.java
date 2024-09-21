package com.teste.pratico.backend.controller;

import com.teste.pratico.backend.service.SolicitanteService;
import com.teste.pratico.backend.service.VagasService;
import com.teste.pratico.model.entity.Solicitante;
import com.teste.pratico.model.entity.Vagas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("solicitante")
public class SolicitanteRestController {

    @Autowired
    private SolicitanteService service;

    @PostMapping
    public void register(@RequestBody Solicitante entity) {
        service.register(entity);
    }

    @GetMapping
    public List<Solicitante> findAll() {
        return service.findAll();
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
