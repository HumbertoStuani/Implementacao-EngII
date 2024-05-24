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
    public ResponseEntity<Object> salvarTipoEventoEnvio(@RequestParam("tipo") String tipo) {
        if (!tipo.isEmpty()) {
            TipoEvento novoTipoEvento = new TipoEvento();
            novoTipoEvento.setNomeTipo(tipo);
            tipoEventoService.save(novoTipoEvento);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Tipo de evento inválido", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-tipoevento")
    public ResponseEntity<Object> buscarTipoEvento(@RequestParam(value="id") Long id)
    {
        TipoEvento tpEvento;
        tpEvento = tipoEventoService.getById(id);
        if(tpEvento!=null)
            return new ResponseEntity<>(tpEvento,HttpStatus.OK);
        else
            return new ResponseEntity<>("Não existe esse tipo de evento",HttpStatus.OK);
    }
    @GetMapping("/get-all-tipoevento")
    public ResponseEntity<Object> buscarTodosTiposEventos()
    {
        return new ResponseEntity<>(tipoEventoService.getAll(),HttpStatus.OK);
    }

    @PutMapping("/update-tipoevento")
    public ResponseEntity<Object> updateTipoEvento(@RequestParam("id") Long id, @RequestParam("tipo") String tipo)
    {
        if(!tipo.equals("")&&tipoEventoService.alterarNomeEvento(id,tipo))
            return new ResponseEntity<>("",HttpStatus.OK);
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
}
