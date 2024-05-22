package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.repositories.TipoProdutoRepository;

@Entity
@Table(name = "tipo_produto")
public class TipoProduto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoProduto;

    @Column(name = "nometipo")
    private String nometipo;


    public TipoProduto(Long idTipoProduto, String nometipo) {
        this.idTipoProduto = idTipoProduto;
        this.nometipo = nometipo;
    }

    public TipoProduto() {
        this(0L,"");
    }

    public Long getIdTipoProduto() {
        return idTipoProduto;
    }

    public void setIdTipoProduto(Long idTipoProduto) {
        this.idTipoProduto = idTipoProduto;
    }

    public String getNomeTipoProduto () {
        return nometipo;
    }

    public void setNomeTipoProduto (String nometipo) {
        this.nometipo = nometipo;
    }




}
