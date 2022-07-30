package br.com.eloware.attonatus.persistence.repository;

import br.com.eloware.attonatus.persistence.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
