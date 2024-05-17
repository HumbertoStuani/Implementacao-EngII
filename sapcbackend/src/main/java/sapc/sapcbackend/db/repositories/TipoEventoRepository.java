package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.TipoEvento;

public interface TipoEventoRepository extends JpaRepository<TipoEvento,Long> {
}
