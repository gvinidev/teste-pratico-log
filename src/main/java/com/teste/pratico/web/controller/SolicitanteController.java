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

    private Solicitante entity;

    private SolicitanteClient client;

    private List<Solicitante> solicitantesCadastrados;

    @Override
    public void afterPropertiesSet() throws Exception {
        entity = new Solicitante();
        client = new SolicitanteClient();
    }

    public void cadastrar() {
        client.register(entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Solicitante cadastrado com sucesso"));
    }

    public void consultar() {
        solicitantesCadastrados = client.findAll();
    }

    public void excluir(Integer id) {
        client.delete(id);
        consultar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Solicitante deletado com sucesso"));
    }
}
