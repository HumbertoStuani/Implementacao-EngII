package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
