package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Caixa;
import sapc.sapcbackend.db.entities.Colaborador;
import sapc.sapcbackend.db.repositories.CaixaRepository;
import sapc.sapcbackend.db.repositories.ColaboradorRepository;

@Service
public class CaixaService
{
    @Autowired
    CaixaRepository caixaRepository;

    public Caixa caixaGetById(Long id) {
        return this.caixaRepository.findById(id).orElse(null);
    }
}
