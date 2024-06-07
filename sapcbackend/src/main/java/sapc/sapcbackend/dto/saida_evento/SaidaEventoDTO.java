package sapc.sapcbackend.dto.saida_evento;

import sapc.sapcbackend.dto.evento.EventoSaidaDTO;
import sapc.sapcbackend.dto.produto_saida.ProdutoSaidaDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record SaidaEventoDTO(
        Long id,
        LocalDateTime dataSaida,
        LocalDateTime dataRetirada,
        String descricao,
        EventoSaidaDTO evento,
        Set<ProdutoSaidaDTO> produtosSaidaIds
) {}
