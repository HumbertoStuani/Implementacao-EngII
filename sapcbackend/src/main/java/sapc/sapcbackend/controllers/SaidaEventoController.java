package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.SaidaEvento;
import sapc.sapcbackend.dto.saida_evento.SaidaEventoDTO;
import sapc.sapcbackend.services.SaidaEventoService;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/saida-evento")
public class SaidaEventoController {
    private final SaidaEventoService saidaEventoService;

    @Autowired
    public SaidaEventoController(SaidaEventoService saidaEventoService) {
        this.saidaEventoService = saidaEventoService;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<SaidaEventoDTO>> getAll() {
        return new ResponseEntity<>(this.saidaEventoService.getAllSaidaEventos(), HttpStatus.OK);
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<SaidaEvento> updateById(@PathVariable Long id) {
        return new ResponseEntity<>(this.saidaEventoService.atualizarSaidaEvento(id), HttpStatus.OK);
    }
}
