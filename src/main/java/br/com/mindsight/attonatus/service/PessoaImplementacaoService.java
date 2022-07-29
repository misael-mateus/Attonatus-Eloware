package br.com.mindsight.attonatus.service;

import br.com.mindsight.attonatus.dto.EnderecoDTO;
import br.com.mindsight.attonatus.dto.PessoaDTO;
import br.com.mindsight.attonatus.exception.ClienteSemEnderecoException;
import br.com.mindsight.attonatus.exception.PessoaJaExisteException;
import br.com.mindsight.attonatus.persistence.model.Endereco;
import br.com.mindsight.attonatus.persistence.model.Pessoa;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface PessoaImplementacaoService {
    public Pessoa criarPessoa(PessoaDTO pessoaDTO);
    public List<Pessoa> listarPessoas();
    public Optional<Pessoa> buscarPessoa(Long id);
    public Optional<Pessoa> buscarPessoa(PessoaDTO pessoaDTO);
    public Pessoa atualizarPessoa(PessoaDTO pessoaDTO);
    public void deletarPessoa(Long id);
    public void deletarPessoa(String nome, LocalDate dataNascimento);
    public List<Endereco> listarEnderecos(Long id);
    public List<Endereco> listarEnderecos(PessoaDTO pessoaDTO);
    public Endereco buscarEnderecoPrincipal(Long id);
    public Pessoa cadastrarEndereco(Long id, EnderecoDTO enderecoDTO);
    public Pessoa cadastrarEndereco(PessoaDTO pessoaDTO, EnderecoDTO enderecoDTO);
    public Endereco informarEnderecoPrincipal(PessoaDTO pessoaDTO);
}
