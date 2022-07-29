package br.com.eloware.attonatus.exception;

public class PessoaJaExisteException extends IllegalArgumentException {
    public PessoaJaExisteException() {
        super("Pessoa já existe: Favor verificar se o nome e data de nascimento já estão cadastrados.");
    }
}

