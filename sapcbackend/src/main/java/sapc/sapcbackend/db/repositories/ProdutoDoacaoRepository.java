package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.ProdutoDoacao;

import java.util.List;

public interface ProdutoDoacaoRepository extends JpaRepository<ProdutoDoacao, ProdutoDoacao.ProdutoDoacaoId> {
    List<ProdutoDoacao> findByIdDoacaoId(Long doacaoId);
}
