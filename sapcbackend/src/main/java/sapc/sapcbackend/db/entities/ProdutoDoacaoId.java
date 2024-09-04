package sapc.sapcbackend.db.entities;

import java.io.Serializable;
import java.util.Objects;

public class ProdutoDoacaoId implements Serializable {
    private Long doacao;
    private Long produto;

    public ProdutoDoacaoId() {}

    public ProdutoDoacaoId(Long doacao, Long produto) {
        this.doacao = doacao;
        this.produto = produto;
    }

    // getters, setters, equals, and hashCode
    public Long getDoacao() {
        return doacao;
    }

    public void setDoacao(Long doacao) {
        this.doacao = doacao;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDoacaoId that = (ProdutoDoacaoId) o;
        return Objects.equals(doacao, that.doacao) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doacao, produto);
    }
}
