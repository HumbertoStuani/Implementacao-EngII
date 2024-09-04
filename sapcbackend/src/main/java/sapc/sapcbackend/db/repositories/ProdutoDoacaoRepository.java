package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.db.entities.ProdutoDoacao;

public interface ProdutoDoacaoRepository extends JpaRepository<ProdutoDoacao, Long> {

}
