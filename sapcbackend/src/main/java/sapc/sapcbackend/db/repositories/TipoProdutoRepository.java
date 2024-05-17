package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.TipoProduto;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Long> {
}
