package sapc.sapcbackend.controllers;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Paciente;
import sapc.sapcbackend.db.entities.Parametrizacao;
import sapc.sapcbackend.services.ParametrizacaoService;

@RestController
@RequestMapping("api/parametrizacao")
public class ParametrizacaoRestController {
    private ParametrizacaoService parametrizacaoService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Parametrizacao> getParametrizacao(@PathVariable Long id) {
        Parametrizacao parametrizacao = this.parametrizacaoService.getParametrizacaoById(id);

        return new ResponseEntity<Parametrizacao>(parametrizacao, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> parametrizar(@RequestBody String cnpj) {
        Parametrizacao existeParametrizacao = this.parametrizacaoService.existeParametrizacao(cnpj);

        if(existeParametrizacao == null) {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(existeParametrizacao, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createParametrizacao(@RequestBody Parametrizacao parametrizacao) {
        Parametrizacao newParametrizacao = this.parametrizacaoService.saveParametrizacao(parametrizacao);

        return new ResponseEntity<>(parametrizacao.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> excluirParametrizacao(@PathVariable Long id) {
        if(this.parametrizacaoService.deleteParametrizacao(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
