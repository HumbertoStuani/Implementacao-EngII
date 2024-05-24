package sapc.sapcbackend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.services.PessoaService;
import sapc.sapcbackend.services.ProdutoService;

@CrossOrigin
@RestController
@RequestMapping("psa/")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping(value = "/salvar-pessoa")
    public ResponseEntity<Object> salvarPessoa (@RequestBody Pessoa pessoa)
    {
        Pessoa novo = pessoaService.savePessoa(pessoa);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @GetMapping(value = "/allpessoas")
    public ResponseEntity<Object> buscarTodosProdutos()
    {
        return new ResponseEntity<>(produtoService.getAllProdutos(),HttpStatus.OK);
    }

    @DeleteMapping("/deletar-pessoa")
    public ResponseEntity<Object> deletarProduto (@RequestParam(value = "id") Long id){
        if(this.produtoService.deleteProduto(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        return new ResponseEntity<>("Produto inexistente!",HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/alterar-pessoa")
    public ResponseEntity<Object> alterarProduto(@RequestParam(value ="id") Long id, @RequestBody Produto produto)
    {
        Produto prod = produtoService.alterarProduto(id,produto);
        if(prod != null)
            return new ResponseEntity<>(produto,HttpStatus.OK);
        return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> getAllPessoa()
    {
        return new ResponseEntity<>(pessoaService.getAllPessoa(),HttpStatus.OK);
    }


}
