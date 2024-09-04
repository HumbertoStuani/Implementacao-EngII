package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.produto.Produto;
import sapc.sapcbackend.db.repositories.ProdutoRepository;

import java.util.List;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto saveProduto(Produto produto) {
        return this.produtoRepository.save(produto);
    }

    public boolean deleteProduto(Long id) {
        try {
            this.produtoRepository.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public Produto alterarProduto(Long id,Produto produto) {
        Produto prod = this.produtoRepository.findById(id).orElse(null);
        if(prod != null)
        {
            prod.setNomeProd(produto.getNomeProd());
            prod.setDescricaoProd(produto.getDescricaoProd());
            prod.setValorProd(produto.getValorProd());
            prod.setIdTipoProd(produto.getIdTipoProd());
            prod.setQuantidadeProd(produto.getQuantidadeProd());
            return this.produtoRepository.save(prod);
        }
        return null;
    }
    
    public Produto getByIdProduto (Long id) {
        return this.produtoRepository.findById(id).orElse(null);
    }

    public Produto getByNomeProduto (String nome) {
        return this.produtoRepository.findByNomeProd(nome);
    }

    public List<Produto> getAllProdutos() {
        return this.produtoRepository.findAll();
    }

}
