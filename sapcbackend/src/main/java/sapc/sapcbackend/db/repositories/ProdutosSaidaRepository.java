package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.ProdutosSaida;
import sapc.sapcbackend.db.entities.ProdutosSaidaId;

public interface ProdutosSaidaRepository extends JpaRepository<ProdutosSaida, ProdutosSaidaId> {
}
