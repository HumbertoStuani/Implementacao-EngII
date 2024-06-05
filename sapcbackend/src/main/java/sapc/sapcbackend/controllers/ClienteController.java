package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Cliente;
import sapc.sapcbackend.dto.pessoa.ClienteDTO;
import sapc.sapcbackend.dto.pessoa.ClientePessoaDTO;
import sapc.sapcbackend.dto.pessoa.PessoaDTO;
import sapc.sapcbackend.services.ClienteService;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClientePessoaDTO clientePessoaDTO) {
        Cliente cliente = clienteService.cadastrarCliente(clientePessoaDTO);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Cliente> alterarCliente(@PathVariable Long id, @RequestBody ClientePessoaDTO clientePessoaDTO) {
        Cliente cliente = clienteService.alterarCliente(id, clientePessoaDTO);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<Void> desativarCliente(@PathVariable Long id) {
        clienteService.desativarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarCliente(@PathVariable Long id) {
        clienteService.ativarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientePessoaDTO>> getAllClientes() {
        List<ClientePessoaDTO> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePessoaDTO> getClienteById(@PathVariable Long id) {
        ClientePessoaDTO cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }
}
