package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Parametrizacao;

public interface ParametrizacaoRepository extends JpaRepository<Parametrizacao, Long> {
    public Parametrizacao findByCnpj(String cnpj);
}
