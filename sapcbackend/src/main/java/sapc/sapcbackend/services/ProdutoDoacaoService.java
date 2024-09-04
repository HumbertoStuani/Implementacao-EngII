package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Doacao;
import sapc.sapcbackend.db.entities.ProdutoDoacao;
import sapc.sapcbackend.db.repositories.DoacaoRepository;
import sapc.sapcbackend.db.repositories.ProdutoDoacaoRepository;

import java.util.List;

@Service
public class ProdutoDoacaoService
{
    @Autowired
    ProdutoDoacaoRepository produtoDoacaoRepository;


    public ProdutoDoacao saveProduto(ProdutoDoacao produtoDoacao) {
        return this.produtoDoacaoRepository.save(produtoDoacao);
    }

    public boolean deleteProduto(Long id) {
        try {
            this.produtoDoacaoRepository.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public ProdutoDoacao getByIdDoacao (Long id) {
        return this.produtoDoacaoRepository.findById(id).orElse(null);
    }

    public List<ProdutoDoacao> getAllProdutos() {
        return this.produtoDoacaoRepository.findAll();
    }
}
