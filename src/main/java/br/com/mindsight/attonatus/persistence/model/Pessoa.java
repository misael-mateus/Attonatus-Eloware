package br.com.mindsight.attonatus.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;
}
