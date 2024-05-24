package sapc.sapcbackend.dto.usuarios;

import sapc.sapcbackend.db.entities.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
