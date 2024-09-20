package com.teste.pratico.model.entity;

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
@Table(name = "vagas")
@SequenceGenerator(name = "sq_vagas", sequenceName = "sq_vagas", allocationSize = 1)
public class Vagas {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_vagas")
    @Column(name = "id")
    private Integer id;

    @Column(name = "inicio")
    private Date inicio;

    @Column(name = "fim")
    private Date fim;

    @Column(name = "quantidade")
    private Integer quantidade;
}
