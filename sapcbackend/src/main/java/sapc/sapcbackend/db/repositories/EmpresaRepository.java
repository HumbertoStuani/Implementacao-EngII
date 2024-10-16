package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sapc.sapcbackend.db.entities.empresa.Empresa;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCnpj(String cnpj);

    @Query("SELECT COUNT(e) > 0 FROM Empresa e")
    boolean existsAny();

    Empresa findFirstByOrderByIdDesc();
}
