package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador,Long> {
}
