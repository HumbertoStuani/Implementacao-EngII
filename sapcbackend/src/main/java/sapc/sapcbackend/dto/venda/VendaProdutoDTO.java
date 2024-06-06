package sapc.sapcbackend.dto.venda;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaProdutoDTO {
    private Long idProduto;
    private int quantidade;
}
