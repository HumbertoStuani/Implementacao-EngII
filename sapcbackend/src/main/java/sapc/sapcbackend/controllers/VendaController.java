package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.dto.venda.VendaDTO;
import sapc.sapcbackend.dto.venda.VendaResponseDTO;
import sapc.sapcbackend.services.VendaService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponseDTO> registrarVenda(@RequestBody VendaDTO vendaDTO) {
        VendaResponseDTO vendaResponseDTO = vendaService.registrarVenda(vendaDTO);
        return ResponseEntity.ok(vendaResponseDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VendaResponseDTO>> getAllVendas() {
        List<VendaResponseDTO> vendas = vendaService.getAllVendas();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> getVendaById(@PathVariable Long id) {
        VendaResponseDTO venda = vendaService.getVendaById(id);
        return ResponseEntity.ok(venda);
    }
}
