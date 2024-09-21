package com.teste.pratico.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaAgendamentoDTO {

    private String nomeSolicitante;

    private String totalAgendamentos;

    private Integer qtdVagas;

    private BigDecimal percentual;

    // Filtros
    private Date dataInicio;

    private Date dataFim;

    private Integer solicitanteIdFilter;
}
