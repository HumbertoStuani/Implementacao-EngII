package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produtos_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVenda {
    @EmbeddedId
    private ProdutoVendaId id;

    @ManyToOne
    @MapsId("idVenda")
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda;

    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(name = "qtde_produto")
    private Integer quantidade;
}

