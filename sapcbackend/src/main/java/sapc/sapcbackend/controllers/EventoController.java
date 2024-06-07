package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.evento.Evento;
import sapc.sapcbackend.dto.evento.EventoSaidaRequestDTO;
import sapc.sapcbackend.services.EventoService;

@CrossOrigin
@RestController
@RequestMapping("/api/evento")
public class EventoController {
    private final EventoService eventoService;

    @Autowired
    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(this.eventoService.getAllEventos(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Evento> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.eventoService.getEventoById(id), HttpStatus.OK);
    }

    @PostMapping("/saida")
    public ResponseEntity<Boolean> saidaEvento(@RequestBody EventoSaidaRequestDTO saidaEvento) {
        return new ResponseEntity<>(this.eventoService.efetuarSaidaParaEvento(saidaEvento), HttpStatus.OK);
    }
}
