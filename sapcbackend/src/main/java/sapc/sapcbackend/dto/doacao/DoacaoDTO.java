package sapc.sapcbackend.dto.doacao;

import java.time.LocalDate;

public record DoacaoDTO(String descr, LocalDate data,Long userId) {
}
