package sapc.sapcbackend.db.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import sapc.sapcbackend.db.entities.Usuarios;
import org.springframework.data.jpa.repository.Query;
import sapc.sapcbackend.db.entities.UserRole;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, String> {

    Usuarios findByLogin(String login);

    // Método que retorna um Optional
    Optional<Usuarios> findOptionalById(Integer id);

    // Método que retorna diretamente a entidade
    Usuarios findById(Integer id);
    long countByRole(UserRole role);

    List<Usuarios> findByPessoaNomeContaining(String nome);

}
