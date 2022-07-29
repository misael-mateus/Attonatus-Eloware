package br.com.mindsight.attonatus.exception;

public class ClienteSemEnderecoException extends IllegalArgumentException {

    public ClienteSemEnderecoException() {
        super("Cliente sem endereço cadastrado");
    }
}
