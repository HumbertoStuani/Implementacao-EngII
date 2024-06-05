package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapc.sapcbackend.db.entities.Cliente;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.repositories.ClienteRepository;
import sapc.sapcbackend.db.repositories.PessoaRepositoryCustom;
import sapc.sapcbackend.dto.pessoa.ClientePessoaDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepositoryCustom pessoaRepositoryCustom;

    @Transactional
    public Cliente cadastrarCliente(ClientePessoaDTO clientePessoaDTO) {
        Optional<Pessoa> optionalPessoa = pessoaRepositoryCustom.findByCpf(clientePessoaDTO.getCpf());
        Pessoa pessoa;

        if (optionalPessoa.isPresent()) {
            pessoa = optionalPessoa.get();
        } else {
            pessoa = new Pessoa();
            pessoa.setNome(clientePessoaDTO.getNome());
            pessoa.setTelefone(clientePessoaDTO.getTelefone());
            pessoa.setSexo(clientePessoaDTO.getSexo());
            pessoa.setRg(clientePessoaDTO.getRg());
            pessoa.setCpf(clientePessoaDTO.getCpf());
            pessoa.setEndereco(clientePessoaDTO.getEndereco());
            pessoa.setCidade(clientePessoaDTO.getCidade());
            pessoa.setBairro(clientePessoaDTO.getBairro());
            pessoa.setUf(clientePessoaDTO.getUf());
            pessoa.setDataNascimento(clientePessoaDTO.getDataNascimento());
            pessoa = pessoaRepositoryCustom.save(pessoa);
        }

        Cliente cliente = new Cliente();
        cliente.setPessoa(pessoa);
        cliente.setDataCadastro(clientePessoaDTO.getDataCadastro());
        cliente.setActive(true); // Cliente ativo por padrão

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente alterarCliente(Long id, ClientePessoaDTO clientePessoaDTO) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            Pessoa pessoa = cliente.getPessoa();
            pessoa.setNome(clientePessoaDTO.getNome());
            pessoa.setTelefone(clientePessoaDTO.getTelefone());
            pessoa.setSexo(clientePessoaDTO.getSexo());
            pessoa.setRg(clientePessoaDTO.getRg());
            pessoa.setCpf(clientePessoaDTO.getCpf());
            pessoa.setEndereco(clientePessoaDTO.getEndereco());
            pessoa.setCidade(clientePessoaDTO.getCidade());
            pessoa.setBairro(clientePessoaDTO.getBairro());
            pessoa.setUf(clientePessoaDTO.getUf());
            pessoa.setDataNascimento(clientePessoaDTO.getDataNascimento());
            pessoaRepositoryCustom.save(pessoa);

            cliente.setDataCadastro(clientePessoaDTO.getDataCadastro());
            return clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    public void desativarCliente(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setActive(false);
            clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    public void ativarCliente(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setActive(true);
            clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    public List<ClientePessoaDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClientePessoaDTO getClienteById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            return convertToDTO(optionalCliente.get());
        } else {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
    }

    private ClientePessoaDTO convertToDTO(Cliente cliente) {
        Pessoa pessoa = cliente.getPessoa();
        ClientePessoaDTO dto = new ClientePessoaDTO();
        dto.setId(cliente.getId());
        dto.setNome(pessoa.getNome());
        dto.setTelefone(pessoa.getTelefone());
        dto.setSexo(pessoa.getSexo());
        dto.setRg(pessoa.getRg());
        dto.setCpf(pessoa.getCpf());
        dto.setEndereco(pessoa.getEndereco());
        dto.setCidade(pessoa.getCidade());
        dto.setBairro(pessoa.getBairro());
        dto.setUf(pessoa.getUf());
        dto.setDataNascimento(pessoa.getDataNascimento());
        dto.setDataCadastro(cliente.getDataCadastro());
        dto.setActive(cliente.isActive());
        return dto;
    }
}
