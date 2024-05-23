package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Produto;
import sapc.sapcbackend.db.entities.TipoEvento;
import sapc.sapcbackend.db.repositories.TipoEventoRepository;
import sapc.sapcbackend.services.ProdutoService;
import sapc.sapcbackend.services.TipoEventoService;
import sapc.sapcbackend.services.TipoProdutoService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("adm/")
public class AdminRestController
{

    // tipo de evento (joao)
    @Autowired
    TipoEventoService tipoEventoService;

    @DeleteMapping("/delete-tipoevento")
    public ResponseEntity<Object> excluirTipoEvento(@RequestParam(value="id") Long id)
    {
        if(tipoEventoService.delete(id))
            return new ResponseEntity<>("",HttpStatus.OK);
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
    public ResponseEntity<Object> buscarProduto (@RequestParam("id") Long id ){
        Produto produto = produtoService.getByIdProduto(id);
        if(produto != null)
            return new ResponseEntity<>(produto,HttpStatus.OK);
        return new ResponseEntity<>("Produto inexistente!",HttpStatus.NOT_FOUND);
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
