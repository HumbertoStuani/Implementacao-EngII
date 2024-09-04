package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Doacao;
import sapc.sapcbackend.db.entities.Paciente;

import java.time.LocalDate;
import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    List<Doacao> findAllByData(LocalDate date);
}
