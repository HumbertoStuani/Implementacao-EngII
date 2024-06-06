package sapc.sapcbackend.dto.venda;

import lombok.*;
import sapc.sapcbackend.dto.caixa.CaixaDTO;
import sapc.sapcbackend.dto.pessoa.ClientePessoaDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaResponseDTO {
    private Long idVenda;
    private float valor;
    private CaixaDTO caixa;
    private ClientePessoaDTO cliente;
    private List<VendaProdutoDTO> produtos;
}
