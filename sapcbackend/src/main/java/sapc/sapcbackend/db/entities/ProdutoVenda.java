package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "produtos_venda")
@IdClass(ProdutoVendaId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVenda {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(name = "qtde_produto", nullable = false)
    private int quantidade;
}

