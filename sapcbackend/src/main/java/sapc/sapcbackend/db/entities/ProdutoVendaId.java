package sapc.sapcbackend.db.entities;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendaId implements Serializable {
    private Long venda;
    private Long produto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoVendaId)) return false;
        ProdutoVendaId that = (ProdutoVendaId) o;
        return Objects.equals(venda, that.venda) &&
                Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venda, produto);
    }
}
