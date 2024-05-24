package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.entities.Produto;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
}
