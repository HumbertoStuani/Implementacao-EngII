package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.repositories.PessoaRepositoryCustom;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepositoryCustom pessoaRepository;

    @GetMapping("/buscarPorCpf")
    public ResponseEntity<Long> getPessoaIdByCpf(@RequestParam String cpf) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cpf);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get().getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarPorRg")
    public ResponseEntity<Long> getPessoaIdByRg(@RequestParam String rg) {
        Optional<Pessoa> pessoa = pessoaRepository.findByRg(rg);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get().getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
