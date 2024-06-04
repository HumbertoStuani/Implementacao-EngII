package sapc.sapcbackend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.UserRole;
import sapc.sapcbackend.dto.usuarios.AuthenticationDTO;
import sapc.sapcbackend.dto.usuarios.DeleteUserDTO;
import sapc.sapcbackend.dto.usuarios.LoginResponseDTO;
import sapc.sapcbackend.dto.usuarios.RegisterDTO;
import sapc.sapcbackend.db.entities.Usuarios;
import sapc.sapcbackend.config.TokenService;
import sapc.sapcbackend.db.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin
@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Usuarios usuarioAutenticado = (Usuarios) auth.getPrincipal();
        if (!usuarioAutenticado.isEnabled()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não está ativo.");
        }

        var token = tokenService.generateToken(usuarioAutenticado);
        var username = usuarioAutenticado.getUsername();

        return ResponseEntity.ok(new LoginResponseDTO(token, username));
    }


   /* @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Usuário já cadastrado.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuarios newUser = new Usuarios(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }*/

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody @Valid DeleteUserDTO data) {
        Usuarios userToDelete = this.repository.findByLogin(data.getLogin());

        if (userToDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        if (userToDelete.getRole() == UserRole.ADMIN) {

            long adminCount = this.repository.countByRole(UserRole.ADMIN);
            if (adminCount <= 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível excluir o último usuário com acesso total.");
            }
        }

        this.repository.delete(userToDelete);
        return ResponseEntity.ok().build();
    }


}
