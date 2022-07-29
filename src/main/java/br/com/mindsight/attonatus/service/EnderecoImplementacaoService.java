package br.com.mindsight.attonatus.service;

import br.com.mindsight.attonatus.dto.EnderecoDTO;
import br.com.mindsight.attonatus.persistence.model.Endereco;

import java.util.Optional;

public interface EnderecoImplementacaoService {
    public Endereco criarEndereco(EnderecoDTO enderecoDTO);

    public Optional<Endereco> buscarEndereco(Long id);

    public void deletarEndereco(Long id);
}

