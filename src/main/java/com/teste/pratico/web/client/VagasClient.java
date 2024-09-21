package com.teste.pratico.web.client;

import com.teste.pratico.model.entity.Vagas;
import com.teste.pratico.model.exception.VagasExistenteException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class VagasClient {

    private final RestTemplate template = new RestTemplate();

    private static final String API_URL = "http://localhost:9292";

    public void register(Vagas entity) throws VagasExistenteException {
        template.postForEntity(API_URL + "/vagas", entity, Void.class);
    }

    public void update(Vagas entity) {
        template.put(API_URL + "/vagas", entity, Void.class);
    }

    public List<Vagas> findAll() {
        return Arrays.asList(Objects.requireNonNull(template.getForEntity(API_URL + "/vagas", Vagas[].class).getBody()));
    }

    public void delete(Integer id) {
        template.delete(API_URL + "/vagas/" + id, null, Void.class);
    }
}
