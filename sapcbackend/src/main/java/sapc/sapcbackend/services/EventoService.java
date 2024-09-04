package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapc.sapcbackend.db.entities.*;
import sapc.sapcbackend.db.entities.evento.Evento;
import sapc.sapcbackend.db.entities.evento.exceptions.EventoNotFoundException;
import sapc.sapcbackend.db.entities.evento.exceptions.EventoSaidaJaRealizadaException;
import sapc.sapcbackend.db.entities.produto.Produto;
import sapc.sapcbackend.db.entities.produto.exceptions.ProdutoNotFoundException;
import sapc.sapcbackend.db.entities.produto.exceptions.ProdutoQtdeInsuficienteException;
import sapc.sapcbackend.db.repositories.EventoRepository;
import sapc.sapcbackend.db.repositories.ProdutoRepository;
import sapc.sapcbackend.db.repositories.ProdutosSaidaRepository;
import sapc.sapcbackend.db.repositories.SaidaEventoRepository;
import sapc.sapcbackend.dto.evento.EventoSaidaRequestDTO;
import sapc.sapcbackend.dto.evento.ProdutoSaidaRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {
    private final EventoRepository eventoRepository;
    private final ProdutoRepository produtoRepository;
    private final SaidaEventoRepository saidaEventoRepository;
    private final ProdutosSaidaRepository produtosSaidaRepository;

    @Autowired
    public EventoService(EventoRepository eventoRepository, ProdutoRepository produtoRepository, SaidaEventoRepository saidaEventoRepository, ProdutosSaidaRepository produtosSaidaRepository) {
        this.eventoRepository = eventoRepository;
        this.produtoRepository = produtoRepository;
        this.saidaEventoRepository = saidaEventoRepository;
        this.produtosSaidaRepository = produtosSaidaRepository;
    }

    public List<Evento> getAllEventos() {
        return this.eventoRepository.findAll();
    }

    public Evento getEventoById(Long id) {
        return this.eventoRepository.findById(id).orElse(null);
    }

    @Transactional
    public boolean efetuarSaidaParaEvento(EventoSaidaRequestDTO eventoSaida) {
        Long idEvento = eventoSaida.idEvento();
        List<ProdutoSaidaRequestDTO> produtosSaida = eventoSaida.produtosSaida();

        Evento evento = this.eventoRepository.findById(idEvento)
                .orElseThrow(() -> new EventoNotFoundException("Evento não encontrado!"));

        if(evento.getSaida_realizada()) {
            throw new EventoSaidaJaRealizadaException("Evento ja foi dado saida!");
        }

        SaidaEvento saida = new SaidaEvento();
        saida.setEvento(evento);
        saida.setDataSaida(LocalDateTime.now());
        saida.setDescricao("Saida para evento: " + evento.getNomeevento());
        this.saidaEventoRepository.save(saida);

        for (ProdutoSaidaRequestDTO produtoSaida : produtosSaida) {
            Produto produto = produtoRepository.findById(produtoSaida.IdProduto())
                    .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado!"));

            int novaQuantidade = produto.getQuantidadeProd() - produtoSaida.quantidade();
            if (novaQuantidade < 0) {
                throw new ProdutoQtdeInsuficienteException("Quantidade insuficiente no estoque para o produto ID: " + produtoSaida.IdProduto());
            }
            produto.setQuantidadeProd(novaQuantidade);
            produtoRepository.save(produto);

            ProdutosSaida produtosSaidaa = new ProdutosSaida();
            ProdutosSaidaId produtosSaidaId = new ProdutosSaidaId();
            produtosSaidaId.setSaidaEventoId(saida.getId());
            produtosSaidaId.setProdutoId(produto.getIdProd());

            produtosSaidaa.setId(produtosSaidaId);
            produtosSaidaa.setProduto(produto);
            produtosSaidaa.setSaidaEvento(saida);
            produtosSaidaa.setQuantidadeProduto(produtoSaida.quantidade());

            produtosSaidaRepository.save(produtosSaidaa);
        }

        evento.setSaida_realizada(true);
        this.eventoRepository.save(evento);
        return true;
    }
}
