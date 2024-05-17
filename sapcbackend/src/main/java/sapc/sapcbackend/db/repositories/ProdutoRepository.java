package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
