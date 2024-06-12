package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sapc.sapcbackend.db.entities.Cliente;
import sapc.sapcbackend.db.entities.Pessoa;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByPessoa(Pessoa pessoa);
    Optional<Cliente> findByPessoaId(Long pessoaId);
    @Query("SELECT c FROM Cliente c WHERE c.pessoa.cpf = :cpf")
    Optional<Cliente> findByPessoaCpf(@Param("cpf") String cpf);

    @Query("SELECT c FROM Cliente c WHERE c.pessoa.rg = :rg")
    Optional<Cliente> findByPessoaRg(@Param("rg") String rg);
}

