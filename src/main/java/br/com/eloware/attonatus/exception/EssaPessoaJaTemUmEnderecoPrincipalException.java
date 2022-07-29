package br.com.eloware.attonatus.exception;

public class EssaPessoaJaTemUmEnderecoPrincipalException extends IllegalArgumentException {
    public EssaPessoaJaTemUmEnderecoPrincipalException() {
        super("Essa pessoa já tem um endereço principal");
    }
}
