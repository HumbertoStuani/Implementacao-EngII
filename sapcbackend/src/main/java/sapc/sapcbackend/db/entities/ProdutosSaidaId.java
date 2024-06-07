package sapc.sapcbackend.db.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ProdutosSaidaId implements java.io.Serializable {
    private static final long serialVersionUID = -6137368010975158380L;
    @Column(name = "saida_evento_id", nullable = false)
    private Long saidaEventoId;

    @Column(name = "produto_id", nullable = false)
    private Long produtoId;

    public Long getSaidaEventoId() {
        return saidaEventoId;
    }

    public void setSaidaEventoId(Long saidaEventoId) {
        this.saidaEventoId = saidaEventoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProdutosSaidaId entity = (ProdutosSaidaId) o;
        return Objects.equals(this.produtoId, entity.produtoId) &&
                Objects.equals(this.saidaEventoId, entity.saidaEventoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId, saidaEventoId);
    }

}