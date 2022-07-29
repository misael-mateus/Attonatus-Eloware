package br.com.mindsight.attonatus.persistence.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String CEP;
    private int numero;
    private String cidade;
    private boolean isPrincipal;
    @ManyToOne
    private Pessoa pessoa;
}
