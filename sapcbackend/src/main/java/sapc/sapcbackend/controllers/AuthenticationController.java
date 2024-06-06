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

import java.util.Optional;

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



    @PostMapping("/desativar")
    public ResponseEntity<String> desativarUsuario(@RequestBody @Valid DeleteUserDTO data) {
        Usuarios userToDeactivate = repository.findByLogin(data.getLogin());

        if (userToDeactivate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (userToDeactivate.getRole() == UserRole.ADMIN) {
            long adminCount = repository.countByRole(UserRole.ADMIN);
            if (adminCount <= 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível desativar o último usuário com acesso total.");
            }
        }

        userToDeactivate.setActive(false);
        repository.save(userToDeactivate);
        return ResponseEntity.ok("Usuário desativado com sucesso.");
    }

    @PostMapping("/ativar")
    public ResponseEntity<String> ativarUsuario(@RequestBody @Valid DeleteUserDTO data) {
        Usuarios userToActivate = repository.findByLogin(data.getLogin());

        if (userToActivate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (userToActivate.isActive()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já está ativo.");
        }

        userToActivate.setActive(true);
        repository.save(userToActivate);
        return ResponseEntity.ok("Usuário ativado com sucesso.");
    }

    @GetMapping("/checkAdmin")
    public ResponseEntity<Boolean> checkAdmin() {
        Optional<Usuarios> adminUser = repository.findByLoginOptional("ADMIN");

        if (adminUser.isPresent()) {
            repository.delete(adminUser.get());
            return ResponseEntity.ok(true); // Admin found and deleted
        } else {
            return ResponseEntity.ok(false); // No admin found
        }
    }
}
