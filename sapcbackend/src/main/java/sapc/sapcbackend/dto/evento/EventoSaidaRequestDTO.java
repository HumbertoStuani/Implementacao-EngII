package sapc.sapcbackend.dto.evento;

import java.util.List;

public record EventoSaidaRequestDTO(Long idEvento, List<ProdutoSaidaRequestDTO> produtosSaida) {
}
