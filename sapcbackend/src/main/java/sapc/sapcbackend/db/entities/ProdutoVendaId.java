package sapc.sapcbackend.db.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVendaId implements Serializable {
    private Long idVenda;
    private Long idProduto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoVendaId that = (ProdutoVendaId) o;
        return Objects.equals(idVenda, that.idVenda) && Objects.equals(idProduto, that.idProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVenda, idProduto);
    }
}