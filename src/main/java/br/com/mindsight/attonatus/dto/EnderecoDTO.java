package br.com.mindsight.attonatus.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {
        private String logradouro;
        private String numero;
        private String cep;
        private String cidade;

}
