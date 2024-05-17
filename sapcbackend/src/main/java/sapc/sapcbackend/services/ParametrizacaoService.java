package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Parametrizacao;
import sapc.sapcbackend.db.repositories.ParametrizacaoRepository;

import java.util.List;

@Service
public class ParametrizacaoService {
    @Autowired
    private ParametrizacaoRepository parametrizacaoRepository;

    public Parametrizacao saveParametrizacao(Parametrizacao parametrizacao) {
        return parametrizacaoRepository.save(parametrizacao);
    }

    public Parametrizacao existeParametrizacao(String cnpj) {
        Parametrizacao parametrizacao = parametrizacaoRepository.findByCnpj(cnpj);

        return parametrizacao;
    }

    public Parametrizacao getParametrizacaoById(Long id) {
        return parametrizacaoRepository.findById(id).orElse(null);
    }

    public boolean deleteParametrizacao(Long id) {
        try {
            parametrizacaoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
