package br.com.eloware.attonatus.service;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.exception.ClienteSemEnderecoException;
import br.com.eloware.attonatus.exception.PessoaJaExisteException;
import br.com.eloware.attonatus.persistence.model.Endereco;
import br.com.eloware.attonatus.persistence.model.Pessoa;
import br.com.eloware.attonatus.persistence.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PessoaService implements PessoaImplementacaoService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PessoaRepository repository;


    public Pessoa criarPessoa(PessoaDTO pessoaDTO) {
        if (repository.findByNomeAndDataNascimento(pessoaDTO.getNome(), pessoaDTO.getDataNascimento()).isPresent()) {
            throw new PessoaJaExisteException();
        }
        return repository.save(modelMapper.map(pessoaDTO, Pessoa.class));
    }



    public Optional<Pessoa> buscarPessoa(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<List<Pessoa>> listarPessoas() {
        return Optional.of(repository.findAll());
    }

    public Optional<Pessoa> buscarPessoa(PessoaDTO pessoaDTO) {
        return repository.findByNomeAndDataNascimento(pessoaDTO.getNome(), pessoaDTO.getDataNascimento());
    }

    public Pessoa atualizarPessoa(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        return repository.save(pessoa);
    }

    public void deletarPessoa(Long id) {
        repository.deleteById(id);
    }

    public void deletarPessoa(String nome, LocalDate dataNascimento) {
        repository.deleteByNomeAndDataNascimento(nome, dataNascimento);
    }

    public List<Endereco> listarEnderecos(Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        return pessoa.map(Pessoa::getEnderecos).orElse(null);
    }

    public List<Endereco> listarEnderecos(PessoaDTO pessoaDTO) {
        Optional<Pessoa> pessoa = repository.findByNomeAndDataNascimento(pessoaDTO.getNome(), pessoaDTO.getDataNascimento());
        return pessoa.map(Pessoa::getEnderecos).orElse(null);
    }

    public Endereco buscarEnderecoPrincipal(Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        return Objects.requireNonNull(pessoa
                        .map(Pessoa::getEnderecos)
                        .orElse(null))
                .stream()
                .filter(Endereco::isPrincipal)
                .findFirst()
                .orElse(null);
    }

    public Pessoa cadastrarEndereco(Long id, EnderecoDTO enderecoDTO) {
        Optional<Pessoa> pessoa = repository.findById(id);
        if (pessoa.isPresent()) {
            Pessoa pessoaAtualizada = pessoa.get();
            Endereco endereco = modelMapper.map(enderecoDTO, Endereco.class);
            endereco.setPessoa(pessoaAtualizada);
            pessoaAtualizada.getEnderecos().add(endereco);
            return repository.save(pessoaAtualizada);
        }
        return null;
    }

    public Endereco informarEnderecoPrincipal(PessoaDTO pessoaDTO) {
        Optional<Pessoa> pessoa = repository.findByNomeAndDataNascimento(pessoaDTO.getNome(), pessoaDTO.getDataNascimento());
        if (pessoa.isPresent()) {
            Pessoa pessoaAtualizada = pessoa.get();
            for (Endereco endereco : pessoaAtualizada.getEnderecos()) {
                if (endereco.isPrincipal()) {
                    return endereco;
                }
            }
        }
        throw new ClienteSemEnderecoException();
    }
}
