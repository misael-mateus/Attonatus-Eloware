package br.com.eloware.attonatus.exception;

public class EnderecoNaoExisteException extends IllegalArgumentException {

    public EnderecoNaoExisteException() {
        super("Não foi possivel alterar o Endereço, pois o Endereço não foi encontrado");
    }

}
