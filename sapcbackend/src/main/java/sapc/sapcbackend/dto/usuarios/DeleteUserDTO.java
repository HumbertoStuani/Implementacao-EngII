package sapc.sapcbackend.dto.usuarios;
import jakarta.validation.constraints.NotBlank;

public class DeleteUserDTO {
    @NotBlank
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}


