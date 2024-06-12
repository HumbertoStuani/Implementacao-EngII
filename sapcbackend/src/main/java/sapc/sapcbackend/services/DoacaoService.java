package sapc.sapcbackend.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Doacao;
import sapc.sapcbackend.db.repositories.DoacaoRepository;
import sapc.sapcbackend.dto.doacao.DoacaoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    public List<DoacaoDTO> getDoacoesByColaboradorId(Long colaboradorId) {
        List<Doacao> doacoes = doacaoRepository.findByColaboradorId(colaboradorId);
        return doacoes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional
    public void aprovarDoacao(Long id) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doação não encontrada"));
        doacao.setSituacao("aprovado");
        doacaoRepository.save(doacao);
    }

    @Transactional
    public void reprovarDoacao(Long id) {
        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doação não encontrada"));
        doacao.setSituacao("reprovado");
        doacaoRepository.save(doacao);
    }

    private DoacaoDTO convertToDTO(Doacao doacao) {
        DoacaoDTO dto = new DoacaoDTO();
        dto.setId(doacao.getId());
        dto.setDescricao(doacao.getDescricao());
        dto.setDataAgendamento(doacao.getDataAgendamento());
        dto.setSituacao(doacao.getSituacao());
        dto.setColaboradorId(doacao.getColaborador().getId().longValue());
        dto.setCaixaId(doacao.getCaixa().getId().longValue());
        if (doacao.getUsuario() != null) {
            dto.setUsuarioId(doacao.getUsuario().getId().longValue());
        }
        return dto;
    }
}
