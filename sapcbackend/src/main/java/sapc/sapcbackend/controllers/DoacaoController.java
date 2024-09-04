package sapc.sapcbackend.controllers;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.*;
import sapc.sapcbackend.dto.doacao.DoacaoDTO;
import sapc.sapcbackend.dto.doacao.DoacaoDinheiroDTO;
import sapc.sapcbackend.dto.doacao.DoacaoRequestDTO;
import sapc.sapcbackend.dto.produtos_doacao.ProdutosDoacaoDTO;
import sapc.sapcbackend.services.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("doacao/")
public class DoacaoController
{

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    DoacaoService doacaoService;

    @Autowired
    ColaboradorService colaboradorService;

    @Autowired
    ProdutoDoacaoService produtoDoacaoService;

    @Autowired
    CaixaService caixaService;

    @GetMapping("/get-usuario")
    public ResponseEntity<Object> buscarUsuario(@RequestParam(value="login") String login, @RequestParam(value = "senha") String senha)
    {
        Usuario usuario;
        usuario = usuarioService.getByLoginAndSenha(login,senha);
        if(usuario!=null)
            return new ResponseEntity<>(usuario.getPessoa(), HttpStatus.OK);
        else
            return new ResponseEntity<>("usuario não existe...",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-all-usuarios")
    public ResponseEntity<Object> buscarTodosUsuarios()
    {
        return new ResponseEntity<>(usuarioService.getAllUsuario(),HttpStatus.OK);
    }

    @GetMapping(value = "/all-produtos")
    public ResponseEntity<Object> buscarTodosProdutos()
    {
        return new ResponseEntity<>(produtoService.getAllProdutos(),HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "fazer-doacao")
    public ResponseEntity<Object> cadastrarDoacao(@RequestBody DoacaoRequestDTO doacaoRequestDTO)
    {
        List<ProdutosDoacaoDTO> produtosDoacao = doacaoRequestDTO.getProdutosDoacao();
        DoacaoDTO doacaoDTO = doacaoRequestDTO.getDoacaoDTO();
        if (produtosDoacao.size() > 0) {
            List<Doacao> list= doacaoService.getAllDoacoesByData(doacaoDTO.data());
            if(list.size()<5&&!doacaoDTO.data().getDayOfWeek().equals("SATURDAY")&&!doacaoDTO.data().getDayOfWeek().equals("SUNDAY"))
            {
                Doacao doacao = new Doacao(doacaoDTO.descr(), doacaoDTO.data(), "aguardando", usuarioService.getById(doacaoDTO.userId()), colaboradorService.getByIdCola(1l), caixaService.caixaGetById(1l));
                doacao = doacaoService.saveDoacao(doacao);
                for (ProdutosDoacaoDTO produtoDoacaoDTO : produtosDoacao) {
                    ProdutoDoacao prod = new ProdutoDoacao(doacao, produtoService.getByNomeProduto(produtoDoacaoDTO.nome()), produtoDoacaoDTO.quantidade());
                    if (prod != null)
                        produtoDoacaoService.saveProduto(prod);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
                if(list.size()<5)
                    return new ResponseEntity<>("Coleta de doacao nao pode ser feita nessa data.",HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity<>("Coleta nao disponivel em Sabados e Domingos",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    @PostMapping(value = "fazer-doacao-dinheiro")
    public ResponseEntity<Object> cadastrarDoacaoDinheiro(@RequestBody DoacaoDinheiroDTO doacaoDinheiroDTO)
    {
        String aux[] = doacaoDinheiroDTO.descr().split(": ");
        int num = Integer.parseInt(aux[1]);
        System.out.println("numero: "+ num);
        if(num>0)
        {
            Doacao doacao = new Doacao(doacaoDinheiroDTO.descr(), LocalDate.now(),"aprovada",usuarioService.getById(doacaoDinheiroDTO.userId()),
                    colaboradorService.getByIdCola(1l),caixaService.caixaGetById(1l));
            doacao = doacaoService.saveDoacao(doacao);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
