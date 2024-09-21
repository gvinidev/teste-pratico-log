package com.teste.pratico.web.controller;

import com.teste.pratico.model.dto.ConsultaAgendamentoDTO;
import com.teste.pratico.model.entity.Solicitante;
import com.teste.pratico.model.entity.Vagas;
import com.teste.pratico.web.client.ConsultaAgendamentoClient;
import com.teste.pratico.web.client.SolicitanteClient;
import com.teste.pratico.web.client.VagasClient;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Component
@Data
public class ConsultaAgendamentoController implements Serializable, InitializingBean {

    @Serial
    private static final long serialVersionUID = -1124935823785480547L;

    private ConsultaAgendamentoDTO entity = new ConsultaAgendamentoDTO();
    private ConsultaAgendamentoDTO filterEntity = new ConsultaAgendamentoDTO();

    private final ConsultaAgendamentoClient client = new ConsultaAgendamentoClient();
    private final SolicitanteClient solicitanteClient = new SolicitanteClient();
    private final VagasClient vagasClient = new VagasClient();

    private List<ConsultaAgendamentoDTO> agendamentos;
    private List<Solicitante> solicitantes;
    private List<Vagas> vagas;

    @Override
    public void afterPropertiesSet() {
        solicitantes = solicitanteClient.findAll();
        vagas = vagasClient.findAll();
    }

    public void consultar() {
        agendamentos = client.findAll(filterEntity);
    }
}
