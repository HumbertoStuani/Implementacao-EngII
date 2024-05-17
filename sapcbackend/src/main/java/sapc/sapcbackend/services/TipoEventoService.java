package sapc.sapcbackend.services;

import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.TipoEvento;
import sapc.sapcbackend.db.repositories.TipoEventoRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TipoEventoService
{
    @Autowired
    private TipoEventoRepository repo;

    public TipoEvento save(TipoEvento tpEvento)
    {
        return  repo.save(tpEvento);
    }

    public TipoEvento getById(Long id)
    {
        TipoEvento tpEvento = repo.findById(id).get();
        return tpEvento;
    }

    public List<TipoEvento> getAll()
    {
        return repo.findAll();
    }

    public boolean delete(long id)
    {
        try
        {
            repo.deleteById(id);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean alterarNomeEvento(long id, String nome)
    {
        try
        {
            TipoEvento tpEvento = repo.findById(id).get();
            tpEvento.setNomeTipo(nome);
            repo.save(tpEvento);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

}
