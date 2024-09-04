package sapc.sapcbackend.dto.doacao;

import sapc.sapcbackend.dto.doacao.DoacaoDTO;
import sapc.sapcbackend.dto.produtos_doacao.ProdutosDoacaoDTO;

import java.util.List;

public class DoacaoRequestDTO {
    private DoacaoDTO doacaoDTO;
    private List<ProdutosDoacaoDTO> produtosDoacao;

    public DoacaoDTO getDoacaoDTO() {
        return doacaoDTO;
    }

    public void setDoacaoDTO(DoacaoDTO doacaoDTO) {
        this.doacaoDTO = doacaoDTO;
    }

    public List<ProdutosDoacaoDTO> getProdutosDoacao() {
        return produtosDoacao;
    }

    public void setProdutosDoacao(List<ProdutosDoacaoDTO> produtosDoacao) {
        this.produtosDoacao = produtosDoacao;
    }
}
