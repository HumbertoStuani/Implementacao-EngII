package sapc.sapcbackend.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.dto.usuarios.UserResponseDTO;
import sapc.sapcbackend.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getAllUsuarios")
    public List<UserResponseDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/search_name")
    public List<UserResponseDTO> getUsuariosByName(@RequestParam String nome) {
        return usuarioService.getUsuariosByName(nome);
    }
}
