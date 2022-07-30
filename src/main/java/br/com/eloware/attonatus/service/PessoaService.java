package br.com.eloware.attonatus.service;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.persistence.model.Endereco;
import br.com.eloware.attonatus.persistence.model.Pessoa;
import br.com.eloware.attonatus.persistence.repository.EnderecoRepository;
import br.com.eloware.attonatus.persistence.repository.PessoaRepository;
import br.com.eloware.attonatus.service.factory.FactoryMethods;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService implements PessoaImplementacaoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(PessoaDTO pessoaDTO) {
        return pessoaRepository.saveAndFlush(modelMapper.map(pessoaDTO, Pessoa.class));
    }

    public Optional<Pessoa> consultarPessoa(Long id) {
        return pessoaRepository.findById(id);
    }

    @Override
    public Optional<List<Pessoa>> listarPessoas() {
        return Optional.of(pessoaRepository.findAll());
    }

    public Pessoa editarPessoa(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada"));
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
        return pessoaRepository.saveAndFlush(pessoa);
    }

    public Endereco informarEnderecoPrincipal(Long id) {
        return enderecoRepository.findByPessoaIdAndIsPrincipal(id, true);
    }

    public EnderecoDTO criarEndereco(Long idPessoa, EnderecoDTO enderecoDTO) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(()
                -> new IllegalArgumentException("Pessoa não encontrada"));
        Endereco endereco = modelMapper.map(enderecoDTO, Endereco.class);
        endereco.setPessoa(pessoa);
        List<Endereco> enderecos = enderecoRepository.findByPessoaId(pessoa.getId());
        if (endereco.isPrincipal()) {
            FactoryMethods.verificaDuplicidadeDeEnderecosPrincipais(enderecos);
        }
        return modelMapper.map(enderecoRepository.saveAndFlush(endereco), EnderecoDTO.class);
    }

}
