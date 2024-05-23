package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.TipoEvento;
import sapc.sapcbackend.services.TipoEventoService;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("tp-evento/")
public class TipoEventoController {
    // tipo de evento (joao)
    @Autowired
    TipoEventoService tipoEventoService;

    @DeleteMapping("/delete-tipoevento")
    public ResponseEntity<Object> excluirTipoEvento(@RequestParam(value="id") Long id)
    {
        if(tipoEventoService.delete(id))
            return new ResponseEntity<>("", HttpStatus.OK);
        else
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-tipoevento-envio")
    public ResponseEntity<Object> salvarTipoEventoEnvio(@RequestParam("tipo") String tipo)
    {
        List<TipoEvento> tipos = tipoEventoService.getAll();
        long id =1;
        if(tipos.size() >= 1)
            id = tipos.get(tipos.size()-1).getId()+1;
        if(!tipo.equals(""))
        {
            salvarTipoEvento(new TipoEvento(id,tipo));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("Tipo evento invalido",HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/add-tipoevento")
    public ResponseEntity<Object> salvarTipoEvento(@RequestBody TipoEvento tpEvento)
    {
        TipoEvento novo;
        novo = tipoEventoService.save(tpEvento);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @GetMapping("/get-tipoevento")
    public ResponseEntity<Object> buscarTipoEvento(@RequestParam(value="id") Long id)
    {
        TipoEvento tpEvento;
        tpEvento = tipoEventoService.getById(id);
        return new ResponseEntity<>(tpEvento,HttpStatus.OK);
    }
    @GetMapping("/get-all-tipoevento")
    public ResponseEntity<Object> buscarTodosTiposEventos()
    {
        return new ResponseEntity<>(tipoEventoService.getAll(),HttpStatus.OK);
    }

    @PutMapping("/update-tipoevento")
    public ResponseEntity<Object> updateTipoEvento(@RequestParam("id") Long id, @RequestParam("tipo") String tipo)
    {
        System.out.println("tipo: "+tipo);
        if(tipoEventoService.alterarNomeEvento(id,tipo))
            return new ResponseEntity<>("",HttpStatus.OK);
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
}
