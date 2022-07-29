package br.com.mindsight.attonatus.exception;

public class PessoaJaExisteException extends IllegalArgumentException {
    public PessoaJaExisteException() {
        super("Pessoa já existe: Favor verificar se o nome e data de nascimento já estão cadastrados.");
    }
}

