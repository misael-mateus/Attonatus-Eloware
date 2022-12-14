package br.com.eloware.attonatus.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL , mappedBy = "pessoa")
    private List<Endereco> enderecos = new ArrayList<>();
}
