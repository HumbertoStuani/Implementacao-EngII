package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.services.ProdutoService;
import sapc.sapcbackend.services.TipoProdutoService;

@CrossOrigin
@RestController
@RequestMapping("produto/")
public class ProdutoController
{
    // produto (Humberto)
    @Autowired
    ProdutoService produtoService;

    @PostMapping(value = "/produto")
    public ResponseEntity<Object> salvarProduto (@RequestBody Produto produto)
    {
        produto.setAtivo(true);
        Produto novo = produtoService.saveProduto(produto);
        return new ResponseEntity<>(novo, HttpStatus.OK);
    }

    @GetMapping(value = "/allprodutos")
    public ResponseEntity<Object> buscarTodosProdutos()
    {
        return new ResponseEntity<>(produtoService.getAllProdutos(),HttpStatus.OK);
    }

    @GetMapping(value = "/produto")
    public ResponseEntity<Object> buscarProduto(@RequestParam("id") Long id) {
        Produto produto = produtoService.getByIdProduto(id);
        if (produto != null)
            return new ResponseEntity<>(produto, HttpStatus.OK);
        return new ResponseEntity<>("Produto inexistente!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/produto")
    public ResponseEntity<Object> deletarProduto (@RequestParam(value = "id") Long id){
        if(this.produtoService.deleteProduto(id))
            return new ResponseEntity<>("",HttpStatus.OK);
        return new ResponseEntity<>("Produto inexistente!",HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/produto")
    public ResponseEntity<Object> alterarProduto(@RequestParam(value ="id") Long id, @RequestBody Produto produto)
    {
        System.out.println(id);
        System.out.println(produto.getNomeProd());
        Produto prod = produtoService.alterarProduto(id,produto);
        if(prod != null)
            return new ResponseEntity<>(produto,HttpStatus.OK);
        return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> getAllProdutos()
    {
        return new ResponseEntity<>(produtoService.getAllProdutos(),HttpStatus.OK);
    }


    // tipo produto (Humberto)
    @Autowired
    TipoProdutoService tipoProdutoService;

    @GetMapping(value = "allprodtipo")
    public ResponseEntity<Object> getAllTipoProduto ()
    {
        return new ResponseEntity<>(tipoProdutoService.getAllTipoProduto(),HttpStatus.OK);
    }
}