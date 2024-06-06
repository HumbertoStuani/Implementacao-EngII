package sapc.sapcbackend.db.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "doacao")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;


    @Column(name = "data_agendamento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataAgendamento;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Pessoa pessoaId;

    @OneToOne
    @JoinColumn(name = "colaborador_id",nullable = false)
    private Colaborador colaboradorId;


    @Column(name = "situacao")
    private String situacao;

    public Doacao(Long id, String descricao, Date dataAgendamento, Pessoa usuarioId, Colaborador colaboradorId,String situacao) {
        this.id = id;
        this.descricao = descricao;
        this.dataAgendamento = dataAgendamento;
        this.pessoaId = usuarioId;
        this.colaboradorId = colaboradorId;
        this.situacao = "";
    }

    public Doacao (){
        this(0L,"",null,null,null,"");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pessoa getUsuarioId() {
        return pessoaId;
    }

    public void setUsuarioId(Pessoa usuarioId) {
        this.pessoaId = usuarioId;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Colaborador getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(Colaborador colaboradorId) {
        this.colaboradorId = colaboradorId;
    }


    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
