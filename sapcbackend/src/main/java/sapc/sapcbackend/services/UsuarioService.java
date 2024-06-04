package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sapc.sapcbackend.db.entities.Usuarios;
import sapc.sapcbackend.db.repositories.UsuarioRepository;
import sapc.sapcbackend.dto.usuarios.UserResponseDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UserResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserResponseDTO> getUsuariosByName(String nome) {
        return usuarioRepository.findByPessoaNomeContaining(nome).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private UserResponseDTO convertToDto(Usuarios usuario) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(usuario.getId());
        dto.setLogin(usuario.getLogin());
        dto.setActive(usuario.isActive());
        dto.setRole(usuario.getRole());
        dto.setDataAdmissao(usuario.getDataAdmissao());
        dto.setNome(usuario.getPessoa().getNome());
        dto.setTelefone(usuario.getPessoa().getTelefone());
        dto.setSexo(usuario.getPessoa().getSexo());
        dto.setRg(usuario.getPessoa().getRg());
        dto.setCpf(usuario.getPessoa().getCpf());
        dto.setEndereco(usuario.getPessoa().getEndereco());
        dto.setCidade(usuario.getPessoa().getCidade());
        dto.setBairro(usuario.getPessoa().getBairro());
        dto.setUf(usuario.getPessoa().getUf());
        dto.setDataNascimento(usuario.getPessoa().getDataNascimento());
        dto.setCargo(usuario.getCargo());
        dto.setSalario(usuario.getSalario());
        return dto;
    }
}
