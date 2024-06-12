package sapc.sapcbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sapc.sapcbackend.db.entities.Cliente;
import sapc.sapcbackend.dto.pessoa.ClienteDTO;
import sapc.sapcbackend.dto.pessoa.ClientePessoaDTO;
import sapc.sapcbackend.services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
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

    @GetMapping("/{id}")
    public ResponseEntity<ClientePessoaDTO> getClienteById(@PathVariable Long id) {
        ClientePessoaDTO cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientePessoaDTO>> getAllClientes() {
        List<ClientePessoaDTO> clientes = clienteService.getAllClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Long> getClienteIdByCpf(@PathVariable String cpf) {
        return clienteService.getClienteIdByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rg/{rg}")
    public ResponseEntity<Long> getClienteIdByRg(@PathVariable String rg) {
        return clienteService.getClienteIdByRg(rg)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
