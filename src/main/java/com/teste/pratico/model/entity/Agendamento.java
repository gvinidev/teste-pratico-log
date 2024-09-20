package com.teste.pratico.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento")
@SequenceGenerator(name = "sq_agendamento", sequenceName = "sq_agendamento", allocationSize = 1)
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_agendamento")
    @Column(name = "id")
    private Integer id;

    @Column(name="data")
    private Date data;

    @Column(name="numero")
    private String numero;

    @Column(name="motivo")
    private String motivo;

    @Column(name="solicitante_id")
    private Integer solicitanteId;

    @Transient
    private String nomeSolicitante;

    // Filtros

    @Transient
    private Date dataInicio;

    @Transient
    private Date dataFim;

    @Transient
    private Integer solicitanteIdFilter;
}
