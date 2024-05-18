package sapc.sapcbackend.DTO.Paciente;

import java.time.LocalDateTime;

public record PacienteDTO(String nome,
                          String telefone,
                          String sexo,
                          String rg,
                          String cpf,
                          String endereco,
                          String cidade,
                          String bairro,
                          String uf,
                          LocalDateTime dataNascimento) {
}
