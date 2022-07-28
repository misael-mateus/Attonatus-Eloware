package br.com.mindsight.attonatus.persistence.repository;

import br.com.mindsight.attonatus.persistence.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {


}
