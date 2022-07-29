package br.com.eloware.attonatus.service;

import br.com.eloware.attonatus.dto.EnderecoDTO;
import br.com.eloware.attonatus.dto.PessoaDTO;
import br.com.eloware.attonatus.persistence.model.Pessoa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class PessoaTest {

    @Autowired
    private PessoaService service;

    @Test
    public void deveCriarPessoa() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("João");
        pessoaDTO.setDataNascimento(LocalDate.now());
        Pessoa pessoa = service.criarPessoa(pessoaDTO);
        assertNotNull(pessoa);
    }

    @Test
    public void deveCriarPessoaComDoisEndereco() {
        Pessoa pessoa = service.criarPessoa(new PessoaDTO("João", LocalDate.of(1990, 1, 1)));
        service.cadastrarEndereco(pessoa.getId(),
                new EnderecoDTO("Rua1", 2, "5000323", "Coxim", true));
        Pessoa pessoaAtualizada = service.cadastrarEndereco(pessoa.getId(),
                new EnderecoDTO("Rua2", 2, "5000323", "Coxim", false));

        Assertions.assertEquals(2, pessoaAtualizada.getEnderecos().size());
    }

    @Test
    public void deveRetornarOEnderecoPrincipal() {
        Pessoa pessoa = service.criarPessoa(new PessoaDTO("João", LocalDate.of(1990, 1, 1)));
        service.cadastrarEndereco(pessoa.getId(),
                new EnderecoDTO("Rua1", 1, "5000323", "Coxim", false));
        service.cadastrarEndereco(pessoa.getId(),
                new EnderecoDTO("Rua2", 2, "5000323", "Coxim", false));
        Pessoa pessoaAtualizada = service.cadastrarEndereco(pessoa.getId(),
                new EnderecoDTO("Rua3", 3, "5000323", "Coxim", true));
        Assertions.assertEquals("Rua3", service.buscarEnderecoPrincipal(pessoaAtualizada.getId()).getLogradouro());
    }


}