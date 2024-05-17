package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.TipoEvento;
import sapc.sapcbackend.db.repositories.TipoEventoRepository;
import sapc.sapcbackend.services.TipoEventoService;

@RestController
@RequestMapping("adm/")
public class AdminRestController
{
    @Autowired
    TipoEventoService tipoEventoRepository;

    @GetMapping("/delete-tipoevento")
    public ResponseEntity<Object> excluirTipoEvento(@RequestParam(value="id") Long id)
    {
        if(tipoEventoRepository.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-tipoevento")
    public ResponseEntity<Object> salvarTipoEvento(@RequestBody TipoEvento tpEvento)
    {
        TipoEvento novo;
        novo = tipoEventoRepository.save(tpEvento);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @GetMapping("/get-tipoevento")
    public ResponseEntity<Object> buscarTipoEvento(@RequestParam(value="id") Long id)
    {
        TipoEvento tpEvento;
        tpEvento = tipoEventoRepository.getById(id);
        return new ResponseEntity<>(tpEvento,HttpStatus.OK);
    }
    @GetMapping("/get-all-tipoevento")
    public ResponseEntity<Object> buscarTodosTiposEventos()
    {
        return new ResponseEntity<>(tipoEventoRepository.getAll(),HttpStatus.OK);
    }


}
