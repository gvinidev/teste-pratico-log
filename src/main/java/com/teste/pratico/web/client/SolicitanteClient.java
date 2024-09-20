package com.teste.pratico.web.client;

import com.teste.pratico.model.entity.Solicitante;
import com.teste.pratico.model.entity.Vagas;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class SolicitanteClient {

    private final RestTemplate template = new RestTemplate();

    private static final String API_URL = "http://localhost:9292";

    public void register(Solicitante entity) {
        template.postForEntity(API_URL + "/solicitante", entity, Void.class);
    }

    public List<Solicitante> findAll() {
        return Arrays.asList(Objects.requireNonNull(template.getForEntity(API_URL + "/solicitante", Solicitante[].class).getBody()));
    }

    public void delete(Integer id) {
        template.put(API_URL + "/solicitante/" + id, null, Void.class);
    }
}
