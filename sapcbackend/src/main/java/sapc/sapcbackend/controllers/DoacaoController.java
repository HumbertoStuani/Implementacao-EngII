package sapc.sapcbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Doacao;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.repositories.DoacaoRepository;
import sapc.sapcbackend.services.DoacaoService;
import sapc.sapcbackend.services.PessoaService;
import sapc.sapcbackend.services.ProdutoDoacaoService;
import sapc.sapcbackend.services.ProdutoService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "adm/")
public class DoacaoController {

    @Autowired
    DoacaoService doacaoService;

    @GetMapping(value = "/allAguardando")
    public ResponseEntity<Object> findAllAguardando() {
        return new ResponseEntity<>(doacaoService.getAllDoacaoAguardando(), HttpStatus.OK);
    }

    @GetMapping(value = "/allAprovadas")
    public ResponseEntity<Object> findAllAprovadas() {
        return new ResponseEntity<>(doacaoService.getAllDoacaoAprovada(), HttpStatus.OK);
    }

    @GetMapping(value = "/allReprovadas")
    public ResponseEntity<Object> findAllReprovadas() {
        return new ResponseEntity<>(doacaoService.getAllDoacaoReprovada(), HttpStatus.OK);
    }

    @PatchMapping(value = "/alterarSituacao")
    public ResponseEntity<Object> alterarSituacao(@RequestParam(value ="id") Long id, @RequestBody Doacao doacao) {
        Doacao aux = doacaoService.aprovarDoacao(id,doacao);
        if(aux != null)
            return new ResponseEntity<>(aux, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // mexer com ProdutoDoacao
    @Autowired
    ProdutoDoacaoService produtoDoacaoService;

    @GetMapping(value = "/qtdeProdDoacao")
    public ResponseEntity<Object> quantidadeProdutoDoacao(@RequestParam(value ="id") Long id) {
        return new ResponseEntity<>(produtoDoacaoService.QtdeTotalProdutos(id), HttpStatus.OK);
    }

    @GetMapping(value = "/todosProd")
    public ResponseEntity<Object> todosProd(@RequestParam(value ="id") Long id) {
        return new ResponseEntity<>(produtoDoacaoService.findAllById(id),HttpStatus.OK);
    }


    @Autowired
    PessoaService pessoaService;
    // puxar pessoa
    @GetMapping(value = "/pessoaDoacao")
    public ResponseEntity<Object> pessoaDoacao(@RequestParam(value ="id") Long id) {
        Doacao aux = doacaoService.getDoacaoById(id);
        String pessoa = pessoaService.buscaPessoa(aux.getUsuarioId().getId());
        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }
}
