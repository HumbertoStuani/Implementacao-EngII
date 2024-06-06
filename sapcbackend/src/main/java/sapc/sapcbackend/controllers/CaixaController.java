package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Caixa;
import sapc.sapcbackend.services.CaixaService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/caixa")
public class CaixaController {
    @Autowired
    private CaixaService caixaService;

    @PostMapping("/abrir")
    public ResponseEntity<Caixa> abrirCaixa(@RequestParam Integer usuarioId, @RequestParam float saldoInicial) { // Ajuste para Integer
        Caixa caixa = caixaService.abrirCaixa(usuarioId, saldoInicial);
        return ResponseEntity.ok(caixa);
    }

    @PutMapping("/fechar/{id}")
    public ResponseEntity<Caixa> fecharCaixa(@PathVariable Long id) {
        Caixa caixa = caixaService.fecharCaixa(id);
        return ResponseEntity.ok(caixa);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Caixa>> getAllCaixas() {
        List<Caixa> caixas = caixaService.getAllCaixas();
        return ResponseEntity.ok(caixas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> getCaixaById(@PathVariable Long id) {
        Caixa caixa = caixaService.getCaixaById(id);
        return ResponseEntity.ok(caixa);
    }
}
