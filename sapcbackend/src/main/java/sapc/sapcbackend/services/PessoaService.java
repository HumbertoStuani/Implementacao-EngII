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

}
