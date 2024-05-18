package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nomeevento")
    private String nomeevento;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_final")
    private LocalDate dataFinal;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "tipo_evento_id", nullable = false)
    private TipoEvento idTipoEvento;
}
