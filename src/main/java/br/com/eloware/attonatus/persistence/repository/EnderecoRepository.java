package br.com.eloware.attonatus.persistence.repository;

import br.com.eloware.attonatus.persistence.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

//    @Query("select e from Endereco e where e.pessoa.id = :id and e.isPrincipal = true")
//    Endereco findPrincipalByPessoaId(@Param("pessoaId") Long id);

    Endereco findByPessoaIdAndIsPrincipal(Long id, boolean isPrincipal);

    List<Endereco> findByPessoaId(Long id);
}
