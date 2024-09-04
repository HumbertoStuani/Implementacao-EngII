package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.repositories.PessoaRepository;

import java.util.Optional;

@Service
public class PessoaService{

    @Autowired
    private PessoaRepository pessoaRepository;

    public String buscaPessoa(Long id){
        Pessoa aux = pessoaRepository.findById(id).orElse(null);
        if(aux!= null)
            return aux.getNome();
        return "";
    }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.entities.Usuario;
import sapc.sapcbackend.db.repositories.PessoaRepository;
import sapc.sapcbackend.db.repositories.UsuarioRepository;

import java.util.List;

@Service
public class PessoaService
{
    @Autowired
    private PessoaRepository repo;

    public Pessoa saveUsuario(Pessoa pessoa){
        return repo.save(pessoa);
    }

    public boolean deleteByPessoa (Long id){
        try {
            this.repo.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public Pessoa getById (Long id){
        return this.repo.findById(id).orElse(null);
    }

    public Pessoa getById(long id)
    {
        return this.repo.getById(id);
    }

    public List<Pessoa> getAllPessoa(){
        return this.repo.findAll();
    }
}
