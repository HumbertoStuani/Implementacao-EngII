package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.SaidaEvento;
import sapc.sapcbackend.db.repositories.SaidaEventoRepository;
import sapc.sapcbackend.dto.evento.EventoSaidaDTO;
import sapc.sapcbackend.dto.produto_saida.ProdutoSaidaDTO;
import sapc.sapcbackend.dto.saida_evento.SaidaEventoDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaidaEventoService {
    private final SaidaEventoRepository saidaEventoRepository;

    @Autowired
    public SaidaEventoService(SaidaEventoRepository saidaEventoRepository) {
        this.saidaEventoRepository = saidaEventoRepository;
    }

    public SaidaEvento atualizarSaidaEvento(Long id) {
        SaidaEvento saidaEvento = this.getSaidaEventoById(id);
        saidaEvento.setDataRetirada(LocalDateTime.now());
        return this.saidaEventoRepository.save(saidaEvento);
    }

    public SaidaEvento getSaidaEventoById(Long id) {
        return saidaEventoRepository.findById(id).orElse(null);
    }

    public List<SaidaEventoDTO> getAllSaidaEventos() {
        return this.saidaEventoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SaidaEventoDTO convertToDTO(SaidaEvento saidaEvento) {
        return new SaidaEventoDTO(
                saidaEvento.getId(),
                saidaEvento.getDataSaida(),
                saidaEvento.getDataRetirada(),
                saidaEvento.getDescricao(),
                new EventoSaidaDTO(
                        saidaEvento.getEvento().getId(),
                        saidaEvento.getEvento().getNomeevento()
                ),
                saidaEvento.getProdutosSaidas().stream()
                        .map(produtoSaida -> new ProdutoSaidaDTO(
                                produtoSaida.getProduto().getIdProd(),
                                produtoSaida.getProduto().getNomeProd(),
                                produtoSaida.getQuantidadeProduto()
                        ))
                        .collect(Collectors.toSet())
        );
    }
}
