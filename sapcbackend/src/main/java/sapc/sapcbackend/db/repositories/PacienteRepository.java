package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
