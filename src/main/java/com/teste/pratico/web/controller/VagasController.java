package com.teste.pratico.web.controller;

import com.teste.pratico.model.entity.Vagas;
import com.teste.pratico.model.exception.VagasExistenteException;
import com.teste.pratico.web.client.VagasClient;
import lombok.Data;
import org.primefaces.PrimeFaces;
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

    private Vagas entity = new Vagas();

    private final VagasClient client = new VagasClient();

    private List<Vagas> vagasCadastradas;

    @Override
    public void afterPropertiesSet() {
    }

    public void cadastrar() throws VagasExistenteException {
        if (isDataInvalida()) {
            addMessage(FacesMessage.SEVERITY_INFO, "Data não pode ser menor que hoje");
            return;
        }

        try {
            client.register(entity);
            addMessage(FacesMessage.SEVERITY_INFO, "Vaga cadastrada com sucesso");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_WARN, e.getMessage());
        }
    }

    private boolean isDataInvalida() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dataAtual = calendar.getTime();

        return entity.getInicio().before(dataAtual) || entity.getFim().before(dataAtual);
    }

    public void consultar() {
        vagasCadastradas = client.findAll();
    }

    public void editar() {
        client.update(entity);
        addMessage(FacesMessage.SEVERITY_INFO, "Vaga editada com sucesso");
    }

    public void excluir(Integer id) {
        client.delete(id);
        consultar();
        addMessage(FacesMessage.SEVERITY_INFO, "Vaga deletada com sucesso");
    }

    private void addMessage(FacesMessage.Severity severity, String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, severity.getOrdinal() == FacesMessage.SEVERITY_INFO.getOrdinal() ? "Info" : "Warn", message));
    }

    public void showEditDialog(Vagas entity) {
        this.entity = entity;

        PrimeFaces.current().executeScript("PF('editDialog').show();");
    }
}
