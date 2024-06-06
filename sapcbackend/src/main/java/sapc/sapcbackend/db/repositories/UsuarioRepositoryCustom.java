package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sapc.sapcbackend.db.entities.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryCustom extends JpaRepository<Usuarios, Integer> {
        Optional<Usuarios> findByLogin(String login);

        @Query("SELECT COUNT(u) FROM usuarios u WHERE u.role = :role")
        long countByRole(@Param("role") String role);

        List<Usuarios> findByPessoaNomeContaining(String nome);

        Optional<Usuarios> findOptionalByLogin(String login);
}
