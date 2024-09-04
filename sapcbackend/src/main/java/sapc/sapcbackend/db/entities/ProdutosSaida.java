package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import sapc.sapcbackend.db.entities.produto.Produto;

@Entity
@Table(name = "produtos_saida")
public class ProdutosSaida {
    @EmbeddedId
    private ProdutosSaidaId id;

    @MapsId("saidaEventoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "saida_evento_id", nullable = false)
    private SaidaEvento saidaEvento;

    @MapsId("produtoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_produto")
    private Integer quantidadeProduto;

    public ProdutosSaidaId getId() {
        return id;
    }

    public void setId(ProdutosSaidaId id) {
        this.id = id;
    }

    public SaidaEvento getSaidaEvento() {
        return saidaEvento;
    }

    public void setSaidaEvento(SaidaEvento saidaEvento) {
        this.saidaEvento = saidaEvento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(Integer quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

}