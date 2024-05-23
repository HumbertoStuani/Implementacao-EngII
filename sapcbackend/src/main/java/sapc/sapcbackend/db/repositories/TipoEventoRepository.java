package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.TipoEvento;
import sapc.sapcbackend.db.entities.empresa.Empresa;

import java.util.Optional;

public interface TipoEventoRepository extends JpaRepository<TipoEvento,Long> {
}
