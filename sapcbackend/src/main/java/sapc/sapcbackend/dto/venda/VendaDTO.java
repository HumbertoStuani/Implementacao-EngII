package sapc.sapcbackend.dto.venda;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {
    private float valor;
    private Long idCaixa;
    private Long idCliente;
    private List<VendaProdutoDTO> produtos;
}
