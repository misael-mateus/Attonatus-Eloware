package br.com.eloware.attonatus.controller;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.persistence.model.Endereco;
import br.com.eloware.attonatus.persistence.model.Pessoa;
import br.com.eloware.attonatus.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eloware")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/criarPessoa")
    public ResponseEntity<PessoaDTO> criarPessoa(@RequestBody @Valid PessoaDTO pessoa) {
        return ResponseEntity.ok(modelMapper.map(pessoaService.criarPessoa(pessoa), PessoaDTO.class));
    }

    @GetMapping("/listarPessoas")
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas().get().stream()
                .map(pessoa -> modelMapper.map(pessoa, PessoaDTO.class))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
    }

    @PostMapping("/criarEndereco/{idPessoa}")
    public ResponseEntity<EnderecoDTO> criarEnderecoParaPessoa(@PathVariable Long idPessoa, @RequestBody @Valid EnderecoDTO endereco) {
        return ResponseEntity.ok(pessoaService.criarEndereco(idPessoa, endereco));
    }

    @GetMapping("/listarEnderecos/{idPessoa}")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos(@PathVariable @Valid Long idPessoa) {
        Optional<Pessoa> pessoa = pessoaService.consultarPessoa(idPessoa);
        if (pessoa.isPresent()) {
            List<EnderecoDTO> enderecos = pessoa.get().getEnderecos().stream().map(endereco ->
                    modelMapper.map(endereco, EnderecoDTO.class)).collect(ArrayList::new, List::add, List::addAll);
            return ResponseEntity.ok(enderecos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/enderecoPrincipal/{idPessoa}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPrincipal(@PathVariable @Valid Long idPessoa) {
        Endereco endereco = pessoaService.informarEnderecoPrincipal(idPessoa);
        if (endereco != null) {
            return ResponseEntity.ok(modelMapper.map(endereco, EnderecoDTO.class));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizarPessoa/{idPessoa}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable @Valid Long idPessoa, @RequestBody @Valid PessoaDTO pessoa) {
        Optional<Pessoa> pessoaOptional = pessoaService.consultarPessoa(idPessoa);
        if (pessoaOptional.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(pessoaService.editarPessoa(idPessoa, pessoa), PessoaDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
