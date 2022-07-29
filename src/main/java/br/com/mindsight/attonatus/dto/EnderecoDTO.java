package br.com.mindsight.attonatus.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EnderecoDTO {
        private String logradouro;
        private int numero;
        private String cep;
        private String cidade;
        private boolean isPrincipal;
}
