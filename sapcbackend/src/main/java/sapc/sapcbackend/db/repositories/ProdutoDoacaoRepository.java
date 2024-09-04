package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.ProdutoDoacao;
import sapc.sapcbackend.db.entities.ProdutoDoacaoId;
import java.util.List;

public interface ProdutoDoacaoRepository extends JpaRepository<ProdutoDoacao, ProdutoDoacaoId> {
    List<ProdutoDoacao> findAllByDoacao_Id(Long id);
}


import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.db.entities.ProdutoDoacao;

public interface ProdutoDoacaoRepository extends JpaRepository<ProdutoDoacao, Long> {

}
