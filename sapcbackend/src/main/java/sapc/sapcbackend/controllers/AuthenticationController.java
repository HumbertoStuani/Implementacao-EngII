package sapc.sapcbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.UserRole;
import sapc.sapcbackend.dto.usuarios.*;
import sapc.sapcbackend.db.entities.Usuarios;
import sapc.sapcbackend.config.TokenService;
import sapc.sapcbackend.db.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sapc.sapcbackend.services.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final TokenService tokenService;

    @Autowired
    public AuthenticationController(UsuarioService usuarioService, AuthenticationManager authenticationManager, UsuarioRepository repository, TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Usuarios usuarioAutenticado = (Usuarios) auth.getPrincipal();
        if (!usuarioAutenticado.isEnabled()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDTO(null, "Usuário não está ativo."));
        }

        var token = tokenService.generateToken(usuarioAutenticado);
        var username = usuarioAutenticado.getUsername();

        return ResponseEntity.ok(new LoginResponseDTO(token, username));
    }



    @PostMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody @Valid DeleteUserDTO data) {
        Usuarios userToDelete = repository.findByLogin(data.getLogin());

        if (userToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (userToDelete.getRole() == UserRole.ADMIN) {
            long adminCount = repository.countByRole(UserRole.ADMIN);
            if (adminCount <= 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível excluir o último usuário com acesso total.");
            }
        }

        repository.delete(userToDelete);
        return ResponseEntity.ok("Usuário deletado com sucesso.");
    }
}
