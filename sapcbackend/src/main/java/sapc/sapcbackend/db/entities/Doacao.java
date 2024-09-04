package sapc.sapcbackend.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import jakarta.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "data_agendamento")
    private LocalDate data;

    @Column(name = "situacao")
    private String situacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = true)
    private Colaborador colaborador;

    @ManyToOne
    @JoinColumn(name = "caixa_id", nullable = true)
    private Caixa caixa;

    public Doacao() {}

    public Doacao(String descricao, LocalDate data, String situacao, Usuario usuario, Colaborador colaborador, Caixa caixa) {
        this.descricao = descricao;
        this.data = data;
        this.situacao = situacao;
        this.usuario = usuario;
        this.colaborador = colaborador;
        this.caixa = caixa;
    }

    // Getters and Setters
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
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
}
