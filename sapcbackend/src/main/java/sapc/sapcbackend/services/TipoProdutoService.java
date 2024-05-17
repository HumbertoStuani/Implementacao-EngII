package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.TipoProduto;
import sapc.sapcbackend.db.repositories.TipoProdutoRepository;

import java.awt.*;
import java.util.List;

@Service
public class TipoProdutoService {

    @Autowired
    private TipoProdutoRepository tipoProdutoRepository;

    public TipoProduto saveTipoProduto(TipoProduto tipoProduto) {
        return tipoProdutoRepository.save(tipoProduto);
    }

    public boolean deleteTipoProduto (Long id){
        try {
            this.tipoProdutoRepository.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public TipoProduto alterarTipoProduto (Long id, TipoProduto tipoProduto) {
        TipoProduto tipo = tipoProdutoRepository.findById(id).orElse(null);
        if(tipo != null)
        {
            tipo.setNomeProd(tipoProduto.getNomeProd());
            return tipoProdutoRepository.save(tipo);
        }
        return null;
    }

    public TipoProduto getById (Long id){
        return this.tipoProdutoRepository.findById(id).orElse(null);
    }

    public List<TipoProduto> getAllTipoProduto(){
        return this.tipoProdutoRepository.findAll();
    }
}
