package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produto_doacao")
public class ProdutoDoacao {

    @EmbeddedId
    private ProdutoDoacaoId id;

    @ManyToOne
    @MapsId("doacaoId")
    @JoinColumn(name = "doacao_id")
    private Doacao doacao;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "quantidade_produto")
    private Integer quantidade;

    public ProdutoDoacaoId getId() {
        return id;
    }

    public void setId(ProdutoDoacaoId id) {
        this.id = id;
    }

    public Doacao getDoacao() {
        return doacao;
    }

    public void setDoacao(Doacao doacao) {
        this.doacao = doacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Embeddable
    public static class ProdutoDoacaoId implements Serializable {
        private Long doacaoId;
        private Long produtoId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProdutoDoacaoId that = (ProdutoDoacaoId) o;
            return Objects.equals(doacaoId, that.doacaoId) && Objects.equals(produtoId, that.produtoId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(doacaoId, produtoId);
        }

        public Long getDoacaoId() {
            return doacaoId;
        }

        public void setDoacaoId(Long doacaoId) {
            this.doacaoId = doacaoId;
        }

        public Long getProdutoId() {
            return produtoId;
        }

        public void setProdutoId(Long produtoId) {
            this.produtoId = produtoId;
        }
    }
}
