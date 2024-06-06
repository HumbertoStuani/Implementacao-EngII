package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
}
