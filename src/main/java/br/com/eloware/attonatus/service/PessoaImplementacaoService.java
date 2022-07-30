package br.com.eloware.attonatus.service;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.persistence.model.Endereco;
import br.com.eloware.attonatus.persistence.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaImplementacaoService {
    public Pessoa criarPessoa(PessoaDTO pessoaDTO);
    public Optional<Pessoa> consultarPessoa(Long id);
    public Optional<List<Pessoa>> listarPessoas();
    public Pessoa editarPessoa(Long id, PessoaDTO pessoaDTO);
    public Endereco informarEnderecoPrincipal(Long id);
    public EnderecoDTO criarEndereco(Long id, EnderecoDTO enderecoDTO);
}
