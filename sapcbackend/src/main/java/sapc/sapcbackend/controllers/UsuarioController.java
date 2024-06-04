package sapc.sapcbackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Usuarios;
import sapc.sapcbackend.dto.usuarios.UpdateUserDTO;
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

    @PutMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UpdateUserDTO dto) {
        Optional<Usuarios> updatedUser = usuarioService.updateUser(dto);

        if (updatedUser.isPresent()) {
            UserResponseDTO responseDTO = usuarioService.convertToDto(updatedUser.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
