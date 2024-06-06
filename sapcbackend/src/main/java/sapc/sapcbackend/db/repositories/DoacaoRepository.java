package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Doacao;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
    List<Doacao> findAllById(int id);
}
