package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapc.sapcbackend.db.entities.Cliente;
import sapc.sapcbackend.db.entities.Pessoa;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByPessoa(Pessoa pessoa);

}

