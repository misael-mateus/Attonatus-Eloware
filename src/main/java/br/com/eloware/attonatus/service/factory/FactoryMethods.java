package br.com.eloware.attonatus.service.factory;

import br.com.eloware.attonatus.exception.EssaPessoaJaTemUmEnderecoPrincipalException;
import br.com.eloware.attonatus.persistence.model.Endereco;

import java.util.List;

public class FactoryMethods {
    public static void verificaDuplicidadeDeEnderecosPrincipais(List<Endereco> enderecos) {
        int contador = (int) enderecos.stream().filter(Endereco::isPrincipal).count();
        if (contador > 0) {
            throw new EssaPessoaJaTemUmEnderecoPrincipalException();
        }
    }
}
