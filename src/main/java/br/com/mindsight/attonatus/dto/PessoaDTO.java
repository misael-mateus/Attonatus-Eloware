package br.com.mindsight.attonatus.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PessoaDTO {
    private String nome;
    private LocalDate dataNascimento;
}
