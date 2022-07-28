package br.com.mindsight.attonatus.dto;

import br.com.mindsight.attonatus.persistence.model.Endereco;
import lombok.*;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTO {
    private String nome;
    private LocalDate dataNascimento;
    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> enderecos;

}
