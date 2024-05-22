package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;

    @Column(name = "nome")
    private String nomeProd;

    @Column(name = "descricao")
    private String descricaoProd;

    @Column(name = "quantidade")
    private int quantidadeProd;

    @Column(name = "valor")
    private double valorProd;

    @Column(name = "ativo")
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private TipoProduto idTipoProd;

    public Produto(Long idProd, String nomeProd, String descricaoProd, int quantidadeProd, double precoProd, boolean excLogica, TipoProduto idTipoProd) {
        this.idProd = idProd;
        this.nomeProd = nomeProd;
        this.descricaoProd = descricaoProd;
        this.quantidadeProd = quantidadeProd;
        this.valorProd = precoProd;
        this.ativo = true;
        this.idTipoProd = idTipoProd;
    }

    public Produto() {
        this(0L,"","",0,0,true,null);
    }

    public Long getIdProd() {
        return idProd;
    }
    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }
    public String getNomeProd() {
        return nomeProd;
    }
    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }
    public String getDescricaoProd() {
        return descricaoProd;
    }
    public void setDescricaoProd(String descricaoProd) {
        this.descricaoProd = descricaoProd;
    }
    public int getQuantidadeProd() {
        return quantidadeProd;
    }
    public void setQuantidadeProd(int quantidadeProd) {
        this.quantidadeProd = quantidadeProd;
    }
    public double getValorProd() {
        return valorProd;
    }
    public void setValorProd(double precoProd) {
        this.valorProd = precoProd;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public TipoProduto getIdTipoProd() {
        return idTipoProd;
    }
    public void setIdTipoProd(TipoProduto idTipoProd) {
        this.idTipoProd = idTipoProd;
    }
}
