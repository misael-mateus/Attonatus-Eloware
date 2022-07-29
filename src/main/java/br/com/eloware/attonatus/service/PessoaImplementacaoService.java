package br.com.eloware.attonatus.service;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.persistence.model.Endereco;
import br.com.eloware.attonatus.persistence.model.Pessoa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PessoaImplementacaoService {
    public Pessoa criarPessoa(PessoaDTO pessoaDTO);
    public Optional<Pessoa> buscarPessoa(Long id);
    public Optional<List<Pessoa>> listarPessoas();
    public Optional<Pessoa> buscarPessoa(PessoaDTO pessoaDTO);
    public Pessoa atualizarPessoa(Long id, PessoaDTO pessoaDTO);
    public void deletarPessoa(Long id);
    public void deletarPessoa(String nome, LocalDate dataNascimento);
    public List<Endereco> listarEnderecos(Long id);
    public List<Endereco> listarEnderecos(PessoaDTO pessoaDTO);
    public Endereco buscarEnderecoPrincipal(Long id);
    public Pessoa cadastrarEndereco(Long id, EnderecoDTO enderecoDTO);
    public Endereco informarEnderecoPrincipal(PessoaDTO pessoaDTO);
}
