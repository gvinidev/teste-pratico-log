package com.teste.pratico.web.client;

import com.teste.pratico.model.entity.Agendamento;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class AgendamentoClient {

    private final RestTemplate template = new RestTemplate();

    private static final String API_URL = "http://localhost:9292";

    public void register(Agendamento entity) {
        template.postForEntity(API_URL + "/agendamento", entity, Void.class);
    }

    public List<Agendamento> findAll(Agendamento filterEntity) {
        return Arrays.asList(Objects.requireNonNull(template.postForEntity(API_URL + "/agendamento/all", filterEntity, Agendamento[].class).getBody()));
    }

    public void delete(Integer id) {
        template.put(API_URL + "/agendamento/" + id, null, Void.class);
    }
}
