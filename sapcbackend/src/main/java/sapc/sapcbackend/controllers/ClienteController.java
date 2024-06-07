package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Cliente;
import sapc.sapcbackend.dto.pessoa.ClientePessoaDTO;
import sapc.sapcbackend.services.ClienteService;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/cpf")
    public ResponseEntity<Optional<ClientePessoaDTO>> getClienteByCPF(@RequestBody ClientePessoaDTO clientePessoaDTO) {
        Optional<ClientePessoaDTO> cliente = clienteService.getClienteByCPF(clientePessoaDTO.getCpf());
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/rg")
    public ResponseEntity<Optional<ClientePessoaDTO>> getClienteByRG(@RequestBody ClientePessoaDTO clientePessoaDTO) {
        Optional<ClientePessoaDTO> cliente = clienteService.getClienteByRG(clientePessoaDTO.getRg());
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
