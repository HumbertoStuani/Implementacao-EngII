package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
