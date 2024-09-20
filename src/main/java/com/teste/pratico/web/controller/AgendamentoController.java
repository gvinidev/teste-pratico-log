package com.teste.pratico.web.controller;

import com.teste.pratico.model.entity.Agendamento;
import com.teste.pratico.model.entity.Solicitante;
import com.teste.pratico.web.client.AgendamentoClient;
import com.teste.pratico.web.client.SolicitanteClient;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Component
@Data
public class AgendamentoController implements Serializable, InitializingBean {

    @Serial
    private static final long serialVersionUID = 3249890867326927615L;

    private Agendamento entity;

    private Agendamento filterEntity;

    private AgendamentoClient client;

    private SolicitanteClient solicitanteClient;

    private List<Agendamento> agendamentosCadastrados;

    private List<Solicitante> solicitantes;

    @Override
    public void afterPropertiesSet() throws Exception {
        entity = new Agendamento();
        filterEntity = new Agendamento();
        client = new AgendamentoClient();
        solicitanteClient = new SolicitanteClient();

        solicitantes = solicitanteClient.findAll();
    }

    public void cadastrar() {
        client.register(entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Agendamento cadastrado com sucesso"));
    }

    public void consultar() {
        agendamentosCadastrados = client.findAll(filterEntity);
    }

    public void excluir(Integer id) {
        client.delete(id);
        consultar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Agendamento deletado com sucesso"));
    }
}
