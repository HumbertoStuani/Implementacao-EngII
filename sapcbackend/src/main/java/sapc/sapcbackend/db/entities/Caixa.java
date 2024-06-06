package sapc.sapcbackend.db.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "caixa")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Caixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    private float saldoInicial;
    private float saldoFinal;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private float saldo;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public void setSaldoInicial(float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public void setSaldoFinal(float saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataFechamento(LocalDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
}
