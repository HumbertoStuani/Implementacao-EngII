package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapc.sapcbackend.db.entities.*;
import sapc.sapcbackend.db.repositories.*;
import sapc.sapcbackend.dto.caixa.CaixaDTO;
import sapc.sapcbackend.dto.pessoa.ClientePessoaDTO;
import sapc.sapcbackend.dto.venda.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public VendaResponseDTO registrarVenda(VendaDTO vendaDTO) {
        Optional<Caixa> optionalCaixa = caixaRepository.findById(vendaDTO.getIdCaixa());
        if (!optionalCaixa.isPresent()) {
            throw new IllegalArgumentException("Caixa não encontrado");
        }

        Optional<Cliente> optionalCliente = clienteRepository.findById(vendaDTO.getIdCliente());
        if (!optionalCliente.isPresent()) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }

        Caixa caixa = optionalCaixa.get();
        Cliente cliente = optionalCliente.get();

        Venda venda = new Venda();
        venda.setValor(vendaDTO.getValor());
        venda.setCaixa(caixa);
        venda.setCliente(cliente);

        venda = vendaRepository.save(venda); // Persistindo a venda primeiro

        Long vendaId = venda.getIdVenda(); // Tornando a variável efetivamente final

        Venda finalVenda = venda;
        List<ProdutoVenda> produtos = vendaDTO.getProdutos().stream().map(vendaProdutoDTO -> {
            Optional<Produto> optionalProduto = produtoRepository.findById(vendaProdutoDTO.getIdProduto());
            if (!optionalProduto.isPresent()) {
                throw new IllegalArgumentException("Produto não encontrado");
            }
            Produto produto = optionalProduto.get();
            ProdutoVenda produtoVenda = new ProdutoVenda();
            produtoVenda.setVenda(finalVenda);
            produtoVenda.setProduto(produto);
            produtoVenda.setQuantidade(vendaProdutoDTO.getQuantidade());
            return produtoVenda;
        }).collect(Collectors.toList());

        produtoVendaRepository.saveAll(produtos); // Persistindo os produtos da venda

        return convertToVendaResponseDTO(venda);
    }

    public List<VendaResponseDTO> getAllVendas() {
        return vendaRepository.findAll().stream()
                .map(this::convertToVendaResponseDTO)
                .collect(Collectors.toList());
    }

    public VendaResponseDTO getVendaById(Long id) {
        Optional<Venda> optionalVenda = vendaRepository.findById(id);
        if (!optionalVenda.isPresent()) {
            throw new IllegalArgumentException("Venda não encontrada");
        }
        return convertToVendaResponseDTO(optionalVenda.get());
    }

    private VendaResponseDTO convertToVendaResponseDTO(Venda venda) {
        VendaResponseDTO vendaResponseDTO = new VendaResponseDTO();
        vendaResponseDTO.setIdVenda(venda.getIdVenda());
        vendaResponseDTO.setValor(venda.getValor());
        vendaResponseDTO.setCaixa(convertToCaixaDTO(venda.getCaixa()));
        vendaResponseDTO.setCliente(convertToClientePessoaDTO(venda.getCliente()));
        vendaResponseDTO.setProdutos(venda.getProdutos().stream()
                .map(produtoVenda -> {
                    VendaProdutoDTO vendaProdutoDTO = new VendaProdutoDTO();
                    vendaProdutoDTO.setIdProduto(produtoVenda.getProduto().getIdProd());
                    vendaProdutoDTO.setQuantidade(produtoVenda.getQuantidade());
                    return vendaProdutoDTO;
                })
                .collect(Collectors.toList()));
        return vendaResponseDTO;
    }

    private CaixaDTO convertToCaixaDTO(Caixa caixa) {
        CaixaDTO caixaDTO = new CaixaDTO();
        caixaDTO.setId(caixa.getId());
        caixaDTO.setUsuarioId(caixa.getUsuario().getId());
        caixaDTO.setSaldoInicial(caixa.getSaldoInicial());
        caixaDTO.setSaldoFinal(caixa.getSaldoFinal());
        caixaDTO.setDataAbertura(caixa.getDataAbertura());
        caixaDTO.setDataFechamento(caixa.getDataFechamento());
        caixaDTO.setSaldo(caixa.getSaldo());
        return caixaDTO;
    }

    private ClientePessoaDTO convertToClientePessoaDTO(Cliente cliente) {
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
