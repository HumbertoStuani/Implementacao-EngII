package sapc.sapcbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sapc.sapcbackend.db.entities.Pessoa;
import sapc.sapcbackend.db.entities.Usuarios;
import sapc.sapcbackend.db.repositories.PessoaRepository;
import sapc.sapcbackend.db.repositories.UsuarioRepository;
import sapc.sapcbackend.dto.usuarios.RegisterDTO;
import sapc.sapcbackend.dto.usuarios.UpdateUserDTO;
import sapc.sapcbackend.dto.usuarios.UserResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PessoaRepository pessoaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.pessoaRepository = pessoaRepository;
    }

    public List<UserResponseDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserResponseDTO> getUsuariosByName(String nome) {
        return usuarioRepository.findByPessoaNomeContaining(nome).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserResponseDTO convertToDto(Usuarios usuario) {
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

    public Optional<Usuarios> updateUser(UpdateUserDTO dto) {
        Optional<Usuarios> optionalUser = usuarioRepository.findOptionalById(dto.getId());

        if (optionalUser.isPresent()) {
            Usuarios user = optionalUser.get();
            user.setLogin(dto.getLogin());
            user.setActive(dto.isActive());
            user.setRole(dto.getRole());
            user.setDataAdmissao(dto.getDataAdmissao());
            user.setCargo(dto.getCargo());
            user.setSalario(dto.getSalario());

            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
                user.setPassword(encryptedPassword);
            }

            Pessoa pessoa = user.getPessoa();
            pessoa.setNome(dto.getNome());
            pessoa.setTelefone(dto.getTelefone());
            pessoa.setSexo(dto.getSexo());
            pessoa.setRg(dto.getRg());
            pessoa.setCpf(dto.getCpf());
            pessoa.setEndereco(dto.getEndereco());
            pessoa.setCidade(dto.getCidade());
            pessoa.setBairro(dto.getBairro());
            pessoa.setUf(dto.getUf());
            pessoa.setDataNascimento(dto.getDataNascimento());

            usuarioRepository.save(user);

            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public UserResponseDTO register(RegisterDTO data) {
        if (usuarioRepository.findByLogin(data.getLogin()) != null) {
            throw new IllegalArgumentException("Usuário já cadastrado.");
        }

        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(data.getNome());
        novaPessoa.setTelefone(data.getTelefone());
        novaPessoa.setSexo(data.getSexo());
        novaPessoa.setRg(data.getRg());
        novaPessoa.setCpf(data.getCpf());
        novaPessoa.setEndereco(data.getEndereco());
        novaPessoa.setCidade(data.getCidade());
        novaPessoa.setBairro(data.getBairro());
        novaPessoa.setUf(data.getUf());
        novaPessoa.setDataNascimento(data.getDataNascimento());

        novaPessoa = pessoaRepository.save(novaPessoa); // Salva a entidade Pessoa primeiro e obtém a entidade salva com o ID gerado

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        Usuarios newUser = new Usuarios(data.getLogin(), encryptedPassword, data.getRole(), data.getDataAdmissao(), data.getCargo(), data.getSalario());
        newUser.setPessoa(novaPessoa); // Associa a pessoa salva à entidade Usuarios

        usuarioRepository.save(newUser); // Salva a entidade Usuarios

        return convertToDto(newUser);
    }
}
