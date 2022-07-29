package br.com.mindsight.attonatus.service;

import br.com.mindsight.attonatus.dto.EnderecoDTO;
import br.com.mindsight.attonatus.persistence.model.Endereco;
import br.com.mindsight.attonatus.persistence.repository.EnderecoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService implements EnderecoImplementacaoService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EnderecoRepository repository;

    public Endereco criarEndereco(EnderecoDTO enderecoDTO) {
        return repository.save(modelMapper.map(enderecoDTO, Endereco.class));
    }

    public Optional<Endereco> buscarEndereco(Long id) {
        return repository.findById(id);
    }

    public void deletarEndereco(Long id) {
        repository.deleteById(id);
    }

}
