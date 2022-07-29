package br.com.eloware.attonatus.persistence.repository;

import br.com.eloware.attonatus.persistence.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    public Optional<Pessoa> findByNomeAndDataNascimento(String nome, LocalDate dataNascimento);
    public void deleteByNomeAndDataNascimento(String nome, LocalDate dataNascimento);
}
