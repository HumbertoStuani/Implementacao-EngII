package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.ProdutoDoacao;
import sapc.sapcbackend.db.repositories.ProdutoDoacaoRepository;

import java.util.Collections;
import java.util.List;

@Service
public class ProdutoDoacaoService {

    @Autowired
    private ProdutoDoacaoRepository produtoDoacaoRepository;

    public List<ProdutoDoacao> findAll() {
        return produtoDoacaoRepository.findAll();
    }

    public List<ProdutoDoacao> findAllById(Long id) {
        List<ProdutoDoacao> produtosDoacao = produtoDoacaoRepository.findAllByDoacao_Id(id);
        if (produtosDoacao.isEmpty()) {
            return null;
        }
        else
            return produtosDoacao;
    }


    public int QtdeTotalProdutos (Long id)
    {
        List<ProdutoDoacao> produtoDoacao = produtoDoacaoRepository.findAllByDoacao_Id(id);
        int qtdeTotal = 0;
        for (int i = 0; i < produtoDoacao.size(); i++) {
            qtdeTotal = qtdeTotal + produtoDoacao.get(i).getQuantidade_produto();
        }
        return qtdeTotal;
    }
}
