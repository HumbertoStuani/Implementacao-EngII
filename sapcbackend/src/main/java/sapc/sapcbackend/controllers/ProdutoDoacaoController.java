package sapc.sapcbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.dto.doacao.ProdutoDoacaoDTO;
import sapc.sapcbackend.services.ProdutoDoacaoService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/produtosDoacao")
public class ProdutoDoacaoController {

    @Autowired
    private ProdutoDoacaoService produtoDoacaoService;

    @GetMapping("/id")
    public ResponseEntity<List<ProdutoDoacaoDTO>> getProdutosByDoacaoId(@RequestParam("id") Long doacaoId) {
        List<ProdutoDoacaoDTO> produtos = produtoDoacaoService.getProdutosByDoacaoId(doacaoId);
        return ResponseEntity.ok(produtos);
    }
}
