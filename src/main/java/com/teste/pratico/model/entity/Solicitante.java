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
@Table(name = "solicitante")
@SequenceGenerator(name = "sq_solicitante", sequenceName = "sq_solicitante", allocationSize = 1)
public class Solicitante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_solicitante")
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;
}
