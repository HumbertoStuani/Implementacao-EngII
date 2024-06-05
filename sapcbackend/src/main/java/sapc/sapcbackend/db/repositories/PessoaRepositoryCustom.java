package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Pessoa;
import java.util.Optional;

public interface PessoaRepositoryCustom extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);
}
