package br.com.eloware.attonatus.exception;

public class ClienteSemEnderecoException extends IllegalArgumentException {

    public ClienteSemEnderecoException() {
        super("Cliente sem endereço cadastrado");
    }
}
