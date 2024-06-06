package sapc.sapcbackend.dto.caixa;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CaixaDTO {
    private Long id;
    private Integer usuarioId;
    private float saldoInicial;
    private float saldoFinal;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private float saldo;
}
