package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.ProdutoVenda;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
}
