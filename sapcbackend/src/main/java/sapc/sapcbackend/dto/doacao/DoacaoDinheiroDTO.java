package sapc.sapcbackend.dto.doacao;

import java.time.LocalDate;

public record DoacaoDinheiroDTO(Long userId, Double valor, LocalDate data,String descr)
{

}
