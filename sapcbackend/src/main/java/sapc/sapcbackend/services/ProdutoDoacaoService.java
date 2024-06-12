package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.ProdutoDoacao;
import sapc.sapcbackend.db.repositories.ProdutoDoacaoRepository;
import sapc.sapcbackend.dto.doacao.ProdutoDoacaoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoDoacaoService {

    @Autowired
    private ProdutoDoacaoRepository produtoDoacaoRepository;

    public List<ProdutoDoacaoDTO> getProdutosByDoacaoId(Long doacaoId) {
        List<ProdutoDoacao> produtosDoacao = produtoDoacaoRepository.findByIdDoacaoId(doacaoId);
        return produtosDoacao.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private ProdutoDoacaoDTO convertToDTO(ProdutoDoacao produtoDoacao) {
        ProdutoDoacaoDTO dto = new ProdutoDoacaoDTO();
        dto.setProdutoId(produtoDoacao.getProduto().getIdProd());
        dto.setDescricao(produtoDoacao.getProduto().getDescricaoProd());
        dto.setTipoId(produtoDoacao.getProduto().getIdTipoProd().getIdTipoProduto()); // Corrigido para acessar o ID do tipo
        dto.setValor(produtoDoacao.getProduto().getValorProd());
        dto.setNome(produtoDoacao.getProduto().getNomeProd());
        dto.setQuantidade(produtoDoacao.getQuantidade());
        return dto;
    }
}

