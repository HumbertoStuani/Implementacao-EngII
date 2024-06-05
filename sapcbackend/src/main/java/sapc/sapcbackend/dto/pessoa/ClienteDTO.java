package sapc.sapcbackend.dto.pessoa;

import java.time.LocalDateTime;

public class ClienteDTO {
    private Long idPessoa;
    private LocalDateTime dataCadastro;

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // Getters e setters
}
