package sapc.sapcbackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sapc.sapcbackend.DTO.Paciente.PacienteDTO;

@RestController
public class PacienteController {
    @PostMapping("/create")
    public ResponseEntity<Object> createPaciente(@RequestBody PacienteDTO body) {
        return ResponseEntity.ok().body("");
    }
}
