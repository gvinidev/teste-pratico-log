package com.teste.pratico.web.controller;

import com.teste.pratico.model.entity.Vagas;
import com.teste.pratico.web.client.VagasClient;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@Component
@Data
public class VagasController implements Serializable, InitializingBean {

    @Serial
    private static final long serialVersionUID = 3249890867326927615L;

    private Vagas entity;

    private VagasClient client;

    private List<Vagas> vagasCadastradas;

    @Override
    public void afterPropertiesSet() throws Exception {
        entity = new Vagas();
        client = new VagasClient();
    }

    public void cadastrar() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date dataAtual = calendar.getTime();

        if (entity.getInicio().before(dataAtual) || entity.getFim().before(dataAtual)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Data não pode ser menor que hoje"));
            return;
        }

        client.register(entity);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vaga cadastrada com sucesso"));
    }

    public void consultar() {
        vagasCadastradas = client.findAll();
    }

    public void excluir(Integer id) {
        client.delete(id);
        consultar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vaga deletada com sucesso"));
    }
}
