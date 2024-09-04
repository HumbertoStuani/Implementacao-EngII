package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.repositories.PessoaRepository;

import java.util.List;

@Service
public class PessoaService
{
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa saveUsuario(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }

    public boolean deleteByPessoa (Long id){
        try {
            this.pessoaRepository.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public Pessoa getById (Long id){
        return this.pessoaRepository.findById(id).orElse(null);
    }

    public Pessoa getById(long id)
    {
        return this.pessoaRepository.getById(id);
    }

    public List<Pessoa> getAllPessoa(){
        return this.pessoaRepository.findAll();
    }

    public String buscaPessoa(Long id) {
        Pessoa aux = pessoaRepository.findById(id).orElse(null);
        if (aux != null)
            return aux.getNome();
        return "";
    }
}
