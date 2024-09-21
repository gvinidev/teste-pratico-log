package com.teste.pratico.web.client;

import com.teste.pratico.model.dto.ConsultaAgendamentoDTO;
import com.teste.pratico.model.entity.Agendamento;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ConsultaAgendamentoClient {

    private final RestTemplate template = new RestTemplate();

    private static final String API_URL = "http://localhost:9292";

    public List<ConsultaAgendamentoDTO> findAll(ConsultaAgendamentoDTO filterEntity) {
        return Arrays.asList(Objects.requireNonNull(template.postForEntity(API_URL + "/consulta-agendamento/all", filterEntity, ConsultaAgendamentoDTO[].class).getBody()));
    }
}
