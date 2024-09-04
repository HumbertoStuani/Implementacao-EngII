package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Colaborador colaborador;

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

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
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

    @Column(name = "saldo")
    private Double saldo;
}
