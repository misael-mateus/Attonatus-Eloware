package br.com.eloware.attonatus.exception;

public class PessoaSemEnderecoException extends IllegalArgumentException {

    public PessoaSemEnderecoException() {
        super("Cliente sem endereço cadastrado");
    }
}
