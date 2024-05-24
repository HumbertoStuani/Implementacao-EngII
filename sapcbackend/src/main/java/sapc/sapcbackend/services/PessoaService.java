package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.db.repositories.PessoaRepository;
import sapc.sapcbackend.db.repositories.ProdutoRepository;

import java.util.List;

public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa savePessoa(Pessoa pessoa) {
        return this.pessoaRepository.save(pessoa);
    }

    public boolean deletePessoa(Long id) {
        try {
            this.pessoaRepository.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

//    public Pessoa alterarPessoa(Long id,Pessoa pessoa) {
//        Pessoa pesso = this.pessoaRepository.findById(id).orElse(null);
//        if(pesso != null)
//        {
//            prod.setNomeProd(produto.getNomeProd());
//            return this.produtoRepository.save(prod);
//        }
//        return null;
//    }

    public Pessoa getByIdPessoa (Long id) {
        return this.pessoaRepository.findById(id).orElse(null);
    }

    public List<Pessoa> getAllPessoa() {
        return this.pessoaRepository.findAll();
    }
}
