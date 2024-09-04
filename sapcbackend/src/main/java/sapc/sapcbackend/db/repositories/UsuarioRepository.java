package sapc.sapcbackend.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sapc.sapcbackend.db.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    Usuario findByLoginAndSenha(String login, String senha);
}