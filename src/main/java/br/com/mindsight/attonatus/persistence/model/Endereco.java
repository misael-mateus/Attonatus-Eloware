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
    private String numero;
    private String cidade;
    @ManyToOne
    private Pessoa pessoa;
}
