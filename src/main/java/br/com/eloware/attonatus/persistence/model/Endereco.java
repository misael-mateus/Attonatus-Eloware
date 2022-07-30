package br.com.eloware.attonatus.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
    @JsonIgnore
    private Pessoa pessoa;
}
