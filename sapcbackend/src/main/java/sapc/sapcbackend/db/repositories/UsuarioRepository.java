package sapc.sapcbackend.db.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import sapc.sapcbackend.db.entities.Usuarios;

public interface UsuarioRepository extends JpaRepository<Usuarios, String> {
    UserDetails findByLogin(String login);
}
