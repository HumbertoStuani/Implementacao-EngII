package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import sapc.sapcbackend.db.entities.produto.Produto;

import java.io.Serializable;


@Entity
@Table(name = "produto_doacao")
@IdClass(ProdutoDoacaoId.class)
public class ProdutoDoacao implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "doacao_id", nullable = false)
    private Doacao doacao;

    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "quantidade_produto")
    private int quantidade_produto;

    public ProdutoDoacao(Doacao doacao, Produto produto, int quantidade_produto) {
        this.doacao = doacao;
        this.produto = produto;
        this.quantidade_produto = quantidade_produto;
    }

    @Column(name = "quantidade_produto")
    private int qtde;

    public ProdutoDoacao() {}

    // getters and setters
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

    public int getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}



