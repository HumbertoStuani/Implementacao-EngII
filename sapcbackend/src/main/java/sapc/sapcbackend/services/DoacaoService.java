package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Doacao;
import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.db.repositories.DoacaoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    public Doacao getDoacaoById(Long id) {
        return doacaoRepository.findById(id).orElse(null);
    }


    public List<Doacao> getAllDoacaoById(int id) {
        return doacaoRepository.findAllById(id);
    }

    public Doacao saveProduto(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    public Doacao aprovarDoacao(Long id, Doacao doacao) {

        Doacao aux = doacaoRepository.findById(id).orElse(null);
        if(aux != null) {
            aux.setSituacao(doacao.getSituacao());
            aux.setColaboradorId(doacao.getColaboradorId());
            return this.doacaoRepository.save(aux);
        }
        return null;
    }


    public List<Doacao> getAllDoacaoAprovada ()
    {
        List<Doacao> doacoes = doacaoRepository.findAll();
        List<Doacao> doacoesAprovada = new ArrayList<Doacao>();

        for (int i = 0; i < doacoes.size(); i++) {
            if(doacoes.get(i).getSituacao().equals("aprovado"))
                doacoesAprovada.add(doacoes.get(i));
        }
        return doacoesAprovada;
    }

    public List<Doacao> getAllDoacaoAguardando ()
    {
        List<Doacao> doacoes = doacaoRepository.findAll();
        List<Doacao> doacoesAguardando = new ArrayList<Doacao>();

        for (int i = 0; i < doacoes.size(); i++) {
            if(doacoes.get(i).getSituacao().equals("aguardando"))
                doacoesAguardando.add(doacoes.get(i));
        }
        return doacoesAguardando;
    }

    public List<Doacao> getAllDoacaoReprovada ()
    {
        List<Doacao> doacoes = doacaoRepository.findAll();
        List<Doacao> doacoesReprovada = new ArrayList<Doacao>();

        for (int i = 0; i < doacoes.size(); i++) {
            if(doacoes.get(i).getSituacao().equals("reprovado"))
                doacoesReprovada.add(doacoes.get(i));
        }
        return doacoesReprovada;
    }


}
