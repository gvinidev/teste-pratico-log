package com.teste.pratico.backend.controller;

import com.teste.pratico.backend.service.VagasService;
import com.teste.pratico.model.entity.Vagas;
import com.teste.pratico.model.exception.VagasExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vagas")
public class VagasRestController {

    @Autowired
    private VagasService service;

    @PostMapping
    public void register(@RequestBody Vagas entity) throws VagasExistenteException {
        service.register(entity);
    }

    @GetMapping
    public List<Vagas> findAll() {
        return service.findAll();
    }

    @PutMapping()
    public void update(@RequestBody Vagas entity) {
        service.update(entity);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
