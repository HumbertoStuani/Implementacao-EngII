package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sapc.sapcbackend.db.entities.Parametrizacao;

@Repository
public interface ParametrizacaoRepository extends JpaRepository<Parametrizacao, Long> {
    public Parametrizacao findByCnpj(String cnpj);
}
