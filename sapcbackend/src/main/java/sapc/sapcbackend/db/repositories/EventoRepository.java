package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.evento.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
