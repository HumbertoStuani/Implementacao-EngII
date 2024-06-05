package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}