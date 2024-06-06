package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.TipoEvento;
import sapc.sapcbackend.db.entities.Usuario;
import sapc.sapcbackend.services.PessoaService;
import sapc.sapcbackend.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("usuario/")
public class UsuarioController
{

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PessoaService pessoaService;
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


}
