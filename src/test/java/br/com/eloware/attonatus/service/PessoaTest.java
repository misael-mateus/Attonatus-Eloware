package br.com.eloware.attonatus.service;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.exception.EssaPessoaJaTemUmEnderecoPrincipalException;
import br.com.eloware.attonatus.persistence.model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class PessoaTest {

    @Autowired
    private PessoaService service;

    @Autowired
    private ModelMapper modelMapper;

    static PessoaDTO pessoaDTO;

    @BeforeAll
    public static void criarPessoaDTO() {
        pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("João");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
    }

    @Test
    public void deveCriarPessoa() {
        Pessoa pessoa = service.criarPessoa(pessoaDTO);
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoa.getDataNascimento(), pessoaDTO.getDataNascimento());
    }

    @Test
    public void deveEditarPessoa() {
        Pessoa pessoa = service.criarPessoa(pessoaDTO);
        pessoaDTO.setNome("Maria");
        pessoaDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        Pessoa pessoaEditada = service.editarPessoa(pessoa.getId(), pessoaDTO);
        Assertions.assertEquals(pessoaEditada.getNome(), pessoaDTO.getNome());
        Assertions.assertEquals(pessoaEditada.getDataNascimento(), pessoaDTO.getDataNascimento());
    }

    @Test
    public void deveConsultarPessoa() {
        Pessoa pessoa = service.criarPessoa(pessoaDTO);
        service.consultarPessoa(pessoa.getId());
        Assertions.assertEquals(pessoa.getNome(), pessoaDTO.getNome());
    }


    @Test
    public void deveInformarEnderecoPrincipal() {
        Pessoa pessoa = service.criarPessoa(pessoaDTO);
        service.criarEndereco(pessoa.getId(), EnderecoDTO.builder()
                .logradouro("Rua 1").numero(1).cidade("Coxim").isPrincipal(true).cep("4241123").build());
        service.criarEndereco(pessoa.getId(), EnderecoDTO.builder()
                .logradouro("Rua 4").numero(3).cidade("Corumba").isPrincipal(false).cep("2443324").build());
        Assertions.assertEquals("Rua 1", service.informarEnderecoPrincipal(pessoa.getId()).getLogradouro());
    }

    @Test
    public void deveVincularEnderecoAPessoa() {
        Pessoa pessoa = service.criarPessoa(pessoaDTO);
        service.criarEndereco(pessoa.getId(), EnderecoDTO.builder()
                .logradouro("Rua 1").numero(1).cidade("Coxim").isPrincipal(true).cep("4241123").build());
        Assertions.assertEquals("Rua 1", service.consultarPessoa(pessoa.getId()).get().getEnderecos().get(0).getLogradouro());
    }

    @Test
    public void naoDevePermitirCriarDoisEnderecosPrincipais() {
        Pessoa pessoa = service.criarPessoa(pessoaDTO);
        service.criarEndereco(pessoa.getId(), EnderecoDTO.builder()
                .logradouro("Rua 1").numero(1).cidade("Coxim").isPrincipal(true).cep("4241123").build());
        Assertions.assertThrows(EssaPessoaJaTemUmEnderecoPrincipalException.class, () -> {
            service.criarEndereco(pessoa.getId(), EnderecoDTO.builder()
                    .logradouro("Rua 3").numero(3).cidade("Coxim").isPrincipal(true).cep("4241123").build());
        });
    }



}