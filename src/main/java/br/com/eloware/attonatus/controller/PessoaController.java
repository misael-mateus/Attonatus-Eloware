package br.com.eloware.attonatus.controller;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.exception.EssaPessoaJaTemUmEnderecoPrincipalException;
import br.com.eloware.attonatus.persistence.model.Endereco;
import br.com.eloware.attonatus.persistence.model.Pessoa;
import br.com.eloware.attonatus.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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
    public ResponseEntity<PessoaDTO> criarEnderecoParaPessoa(@PathVariable Long idPessoa, @RequestBody @Valid EnderecoDTO endereco) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoa(idPessoa);
        pessoa.ifPresent(value -> value.getEnderecos().forEach(p -> {
            if (p.isPrincipal() && endereco.isPrincipal()) {
                throw new EssaPessoaJaTemUmEnderecoPrincipalException();
            }
        }));

        if (pessoaService.buscarPessoa(idPessoa).isPresent()) {
            return ResponseEntity.ok(modelMapper.map(pessoaService.cadastrarEndereco(idPessoa, endereco), PessoaDTO.class));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/listarEnderecos/{idPessoa}")
    public ResponseEntity<List<EnderecoDTO>> listarEnderecos(@PathVariable @Valid Long idPessoa) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoa(idPessoa);
        if (pessoa.isPresent()) {
            List<EnderecoDTO> enderecos = pessoa.get().getEnderecos().stream().map(endereco ->
                    modelMapper.map(endereco, EnderecoDTO.class)).collect(ArrayList::new, List::add, List::addAll);
            return ResponseEntity.ok(enderecos);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/enderecoPrincipal/{idPessoa}")
    public ResponseEntity<EnderecoDTO> buscarEnderecoPrincipal(@PathVariable @Valid Long idPessoa) {
        Optional<Pessoa> pessoa = pessoaService.buscarPessoa(idPessoa);
        if (pessoa.isPresent()) {
            if (pessoa.get().getEnderecos().stream().anyMatch(Endereco::isPrincipal)) {
                return ResponseEntity.ok(modelMapper
                        .map(pessoa.get().getEnderecos()
                                .stream()
                                .filter(Endereco::isPrincipal)
                                .findFirst().get(), EnderecoDTO.class));
            }

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizarPessoa/{idPessoa}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable @Valid Long idPessoa, @RequestBody @Valid PessoaDTO pessoa) {
        Optional<Pessoa> pessoaOptional = pessoaService.buscarPessoa(idPessoa);
        if (pessoaOptional.isPresent()) {
            return ResponseEntity.ok(modelMapper.map(pessoaService.atualizarPessoa(idPessoa, pessoa), PessoaDTO.class));
        }
        return ResponseEntity.notFound().build();
    }
}
