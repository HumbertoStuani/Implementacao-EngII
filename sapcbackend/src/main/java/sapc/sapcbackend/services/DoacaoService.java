package sapc.sapcbackend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Doacao;
import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.db.repositories.DoacaoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoacaoService
{
    @Autowired
    DoacaoRepository doacaoRepository;

    public Doacao saveDoacao(Doacao doacao) {
        return this.doacaoRepository.save(doacao);
    }

    public boolean deleteProduto(Long id) {
        try {
            this.doacaoRepository.deleteById(id);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public Doacao getByIdDoacao (Long id) {
        return this.doacaoRepository.findById(id).orElse(null);
    }

    public List<Doacao> getAllDoacoes() {
        return this.doacaoRepository.findAll();
    }

    public List<Doacao> getAllDoacoesByData(LocalDate data) {
        return this.doacaoRepository.findAllByData(data);
    }
}
