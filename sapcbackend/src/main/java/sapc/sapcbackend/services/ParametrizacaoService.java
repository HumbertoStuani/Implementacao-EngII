package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Parametrizacao;
import sapc.sapcbackend.db.repositories.ParametrizacaoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParametrizacaoService {
    @Autowired
    private ParametrizacaoRepository parametrizacaoRepository;

    public Parametrizacao saveParametrizacao(Parametrizacao parametrizacao) {
        Parametrizacao param = this.existeParametrizacao(parametrizacao.getCnpj());
        if(param == null) {
            throw new RuntimeException("Parametrizacao ja existe");
        }

        parametrizacao.setData_criacao(LocalDateTime.now());
        return this.parametrizacaoRepository.save(parametrizacao);
    }

    public Parametrizacao existeParametrizacao(String cnpj) {

        return this.parametrizacaoRepository.findByCnpj(cnpj);
    }

    public Parametrizacao getParametrizacaoById(Long id) {
        return parametrizacaoRepository.findById(id).orElse(null);
    }

    public boolean deleteParametrizacao(Long id) {
        try {
            this.parametrizacaoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
