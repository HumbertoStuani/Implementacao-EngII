package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "saldo_inicial")
    private double saldoInicial;

    @Column(name = "saldo_final")
    private double saldoFinal;

    @OneToOne
    @JoinColumn(name = "colaborador")
    private Colaborador colaborador;

    @Column(name = "data_abertura")
    private LocalDateTime dataAbertura;

    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @Column(name = "saldo")
    private double saldo;

    public Caixa(Long id, double saldoInicial, double saldoFinal, Colaborador colaborador, LocalDateTime dataAbertura, double saldo, LocalDateTime dataFechamento) {
        this.id = id;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.colaborador = colaborador;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.dataFechamento = dataFechamento;
    }

    public Caixa() {
        this(0L,0.0,0.0,null,null,0.0,null);
    }

    @Column(name = "saldo_inicial")
    private Double saldIni;

    @Column(name = "saldo_final")
    private Double saldoFim;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Double getSaldIni() {
        return saldIni;
    }

    public void setSaldIni(Double saldIni) {
        this.saldIni = saldIni;
    }

    public Double getSaldoFim() {
        return saldoFim;
    }

    public void setSaldoFim(Double saldoFim) {
        this.saldoFim = saldoFim;
    }

    public LocalDate getDataAbe() {
        return dataAbe;
    }

    public void setDataAbe(LocalDate dataAbe) {
        this.dataAbe = dataAbe;
    }

    public LocalDate getDataFec() {
        return dataFec;
    }

    public void setDataFec(LocalDate dataFec) {
        this.dataFec = dataFec;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Column(name = "data_abertura")
    private LocalDate dataAbe;

    @Column(name = "data_fechamento")
    private LocalDate dataFec;
}
