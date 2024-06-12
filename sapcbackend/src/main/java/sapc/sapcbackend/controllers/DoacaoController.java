package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.dto.doacao.DoacaoDTO;
import sapc.sapcbackend.services.DoacaoService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<List<DoacaoDTO>> getDoacoesByColaboradorId(@PathVariable Long colaboradorId) {
        List<DoacaoDTO> doacoes = doacaoService.getDoacoesByColaboradorId(colaboradorId);
        return ResponseEntity.ok(doacoes);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DoacaoDTO>> getDoacoesByUsuarioId(@PathVariable Long usuarioId) {
        List<DoacaoDTO> doacoes = doacaoService.getDoacoesByUsuarioId(usuarioId);
        return ResponseEntity.ok(doacoes);
    }

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<Void> aprovarDoacao(@PathVariable Long id) {
        doacaoService.aprovarDoacao(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reprovar/{id}")
    public ResponseEntity<Void> reprovarDoacao(@PathVariable Long id) {
        doacaoService.reprovarDoacao(id);
        return ResponseEntity.ok().build();
    }
}
