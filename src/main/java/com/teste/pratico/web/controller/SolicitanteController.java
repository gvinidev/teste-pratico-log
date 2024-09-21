package com.teste.pratico.web.controller;

import com.teste.pratico.model.entity.Solicitante;
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
public class SolicitanteController implements Serializable, InitializingBean {

    @Serial
    private static final long serialVersionUID = 3249890867326927615L;

    private Solicitante entity = new Solicitante();

    private final SolicitanteClient client = new SolicitanteClient();

    private List<Solicitante> solicitantesCadastrados;

    @Override
    public void afterPropertiesSet() {
    }

    public void cadastrar() {
        client.register(entity);
        addMessage(FacesMessage.SEVERITY_INFO, "Solicitante cadastrado com sucesso");
    }

    public void consultar() {
        solicitantesCadastrados = client.findAll();
    }

    public void excluir(Integer id) {
        client.delete(id);
        consultar();
        addMessage(FacesMessage.SEVERITY_INFO, "Solicitante deletado com sucesso");
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, severity.getOrdinal() == FacesMessage.SEVERITY_INFO.getOrdinal() ? "Info" : "Warn", message));
    }
}
