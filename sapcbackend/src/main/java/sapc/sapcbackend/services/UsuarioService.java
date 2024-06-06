package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.TipoProduto;
import sapc.sapcbackend.db.entities.Usuario;
import sapc.sapcbackend.db.repositories.TipoProdutoRepository;
import sapc.sapcbackend.db.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService
{
    @Autowired
    private UsuarioRepository repo;

    public Usuario saveUsuario(Usuario usuario){
        return repo.save(usuario);
    }

    public boolean deleteByUsuario (Long id){
        try {
            this.repo.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public Usuario getById (Long id){
        return this.repo.findById(id).orElse(null);
    }

    public Usuario getByLoginAndSenha(String login, String senha)
    {
        return this.repo.findByLoginAndSenha(login,senha);
    }

    public List<Usuario> getAllUsuario(){
        return this.repo.findAll();
    }
}
