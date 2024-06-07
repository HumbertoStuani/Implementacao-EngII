package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapc.sapcbackend.db.entities.Pessoa;
import java.util.Optional;

@Repository
public interface PessoaRepositoryCustom extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByRg(String rg);

}
