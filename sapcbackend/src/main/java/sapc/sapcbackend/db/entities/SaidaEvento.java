package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import sapc.sapcbackend.db.entities.evento.Evento;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "saida_evento")
public class SaidaEvento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('saida_evento_id_seq'")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Column(name = "data_retirada")
    private LocalDateTime dataRetirada;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @OneToMany(mappedBy = "saidaEvento")
    private Set<ProdutosSaida> produtosSaidas = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDateTime dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Set<ProdutosSaida> getProdutosSaidas() {
        return produtosSaidas;
    }

    public void setProdutosSaidas(Set<ProdutosSaida> produtosSaidas) {
        this.produtosSaidas = produtosSaidas;
    }

}