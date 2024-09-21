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

    private Agendamento entity = new Agendamento();
    private Agendamento filterEntity = new Agendamento();

    private AgendamentoClient client = new AgendamentoClient();
    private SolicitanteClient solicitanteClient = new SolicitanteClient();

    private List<Agendamento> agendamentosCadastrados;
    private List<Solicitante> solicitantes;

    @Override
    public void afterPropertiesSet() {
        solicitantes = solicitanteClient.findAll();
    }

    public void cadastrar() {
        try {
            client.register(entity);
            addMessage(FacesMessage.SEVERITY_INFO, "Agendamento cadastrado com sucesso");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_WARN, e.getMessage());
        }
    }

    public void consultar() {
        agendamentosCadastrados = client.findAll(filterEntity);
    }

    public void excluir(Integer id) {
        client.delete(id);
        consultar();
        addMessage(FacesMessage.SEVERITY_INFO, "Agendamento deletado com sucesso");
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, severity.getOrdinal() == FacesMessage.SEVERITY_INFO.getOrdinal() ? "Info" : "Warn", message));
    }
}
