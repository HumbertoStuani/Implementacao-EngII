package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Colaborador;
import sapc.sapcbackend.db.entities.Doacao;
import sapc.sapcbackend.db.repositories.ColaboradorRepository;

@Service
public class ColaboradorService
{
    @Autowired
    ColaboradorRepository colaboradorRepository;

    public Colaborador getByIdCola (Long id) {
        return this.colaboradorRepository.findById(id).orElse(null);
    }
}
